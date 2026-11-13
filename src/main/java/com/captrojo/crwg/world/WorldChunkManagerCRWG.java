package com.captrojo.crwg.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.gen.data.GConst;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.captrojo.crwg.world.gen.layer.GenLayerAverage;
import com.captrojo.crwg.world.gen.layer.GenLayerBiomeTypes;
import com.captrojo.crwg.world.gen.layer.GenLayerCached;
import com.captrojo.crwg.world.gen.layer.GenLayerLTInfect;
import com.captrojo.crwg.world.gen.layer.GenLayerLTInfectAlways;
import com.captrojo.crwg.world.gen.layer.GenLayerLTInfectChecked;
import com.captrojo.crwg.world.gen.layer.GenLayerLTRaise;
import com.captrojo.crwg.world.gen.layer.GenLayerLTRaiseGenerous;
import com.captrojo.crwg.world.gen.layer.GenLayerLTRaiseMulti;
import com.captrojo.crwg.world.gen.layer.GenLayerLTValue;
import com.captrojo.crwg.world.gen.layer.GenLayerLakes;
import com.captrojo.crwg.world.gen.layer.GenLayerLandType;
import com.captrojo.crwg.world.gen.layer.GenLayerRandUnbounded;
import com.captrojo.crwg.world.gen.layer.GenLayerRiverCRWG;
import com.captrojo.crwg.world.gen.layer.GenLayerRiverMixCRWG;
import com.captrojo.crwg.world.gen.layer.GenLayerShoresCRWG;
import com.captrojo.crwg.world.gen.layer.GenLayerTuneUp;
import com.captrojo.crwg.world.gen.layer.GenLayerWetlands;
import com.captrojo.crwg.world.gen.longlayer.GenLayerLong;
import com.captrojo.crwg.world.gen.longlayer.GenLayerLongBiomesCRWG;
import com.captrojo.crwg.world.gen.longlayer.GenLayerLongSmooth;
import com.captrojo.crwg.world.gen.longlayer.GenLayerLongZoom;
import com.captrojo.crwg.world.gen.longlayer.LongCache;
import com.captrojo.crwg.world.gen.noise.NoisePerlinFractal;
import com.captrojo.crwg.world.gen.noise.layer.NLayer;
import com.captrojo.crwg.world.gen.noise.layer.NLayer2DNoise;
import com.captrojo.crwg.world.gen.noise.layer.NLayerAdd;
import com.captrojo.crwg.world.gen.noise.layer.NLayerGDiv;
import com.captrojo.crwg.world.gen.noise.layer.NLayerMax;
import com.captrojo.crwg.world.gen.noise.layer.NLayerMultiplyAdd;
import com.captrojo.crwg.world.gen.noise.layer.NLayerRidge;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerCRWG extends WorldChunkManager
{
	public static double temp_scale;
	public static double temp_scale_64;

	public static double rain_scale;
	public static double rain_scale_64;

	public static double awesome_scale;
	public static double awesome_scale_64;

	static {
		temp_scale = 1.0 / 640.0;
		temp_scale_64 = temp_scale * 64.0;

		rain_scale = 1.0 / 640.0;
		rain_scale_64 = rain_scale * 64.0;

		awesome_scale = 1.0 / 1280.0;
		awesome_scale_64 = awesome_scale * 64.0;
	}

	private GenArrays gen_arrays = new GenArrays();

	public GenLayer genlayer_biometype;
	public GenLayer genlayer_species;
	public GenLayerLong genlayer_biomes;

	public GenLayerCached genlayer_landtype_unscaled;

	public NLayer n_gen_deepocean;
	public NLayer n_gen_ocean;
	public NLayer n_gen_shallowocean;
	public NLayer n_gen_beach;

	public NLayer n_gen_lowlands;
	public NLayer n_gen_hills;
	public NLayer n_gen_highlands;
	public NLayer n_gen_mountains;

	public NLayer n_gen_plateaus;
	public NLayer n_gen_foothills;

	public NLayer n_gen_rivers;
	public NLayer n_gen_lakes;
	public NLayer n_gen_wetlands;

	public NLayerGDiv n_sel_deepocean;
	public NLayerGDiv n_sel_ocean;
	public NLayerGDiv n_sel_shallowocean;
	public NLayerGDiv n_sel_beach;

	public NLayerGDiv n_sel_lowlands;
	public NLayerGDiv n_sel_hills;
	public NLayerGDiv n_sel_highlands;

	public NLayerGDiv n_sel_mountains;
	public NLayerGDiv n_sel_plateaus;
	public NLayerGDiv n_sel_foothills;

	public NLayerGDiv n_sel_rivers;
	public NLayerGDiv n_sel_lakes;
	public NLayerGDiv n_sel_wetlands;

	public NoisePerlinFractal noise_temperature;
	public NoisePerlinFractal noise_rainfall;
	public NoisePerlinFractal noise_awesomeness;

	/**
	 * we love that NoiseGeneratorPerlin is actually fractal simplex noise.
	 * good job, Forge
	 */
	public NoiseGeneratorPerlin noise_soil_bumpiness;

	public WorldChunkManagerCRWG(World world)
	{
		long seed = world.getSeed();
		Random rand = new Random(seed);

		this.noise_temperature = (new NoisePerlinFractal(4, true)).initNoiseGens(rand);
		this.noise_rainfall = (new NoisePerlinFractal(4, true)).initNoiseGens(rand);
		this.noise_awesomeness = (new NoisePerlinFractal(4, true)).initNoiseGens(rand);

		GenLayer glayer_main, glayer_river, glayer_biometype, glayer_species, glayer_landtype_unscaled;
		NLayer nlayer1, nlayer2;

		/* Continents */
		glayer_main = new GenLayerIsland(100L);
		glayer_main = new GenLayerFuzzyZoom(110L, glayer_main);
		glayer_main = GenLayerZoom.magnify(120L, glayer_main, 1);
		glayer_main = new GenLayerAddIsland(130L, glayer_main);
		glayer_main = new GenLayerSmooth(140L, glayer_main);
		glayer_main = GenLayerZoom.magnify(150L, glayer_main, 1);
		glayer_main = new GenLayerSmooth(160L, glayer_main);

		/* Land type */
		glayer_main = new GenLayerLandType(200L, glayer_main);
		glayer_main = new GenLayerLTRaiseMulti(
			210L,
			glayer_main,
			new int[] {GConst.LOWLANDS, GConst.SHALLOWOCEAN},
			new int[] {GConst.HILLS, GConst.OCEAN},
			new int[] {4, 4},
			new int[] {5, 5}
		);
		glayer_main = GenLayerZoom.magnify(220L, glayer_main, 1);
		glayer_main = new GenLayerLTRaiseMulti(
			230L,
			glayer_main,
			new int[] {GConst.HILLS, GConst.OCEAN},
			new int[] {GConst.HIGHLANDS, GConst.DEEPOCEAN},
			new int[] {4, 4},
			new int[] {5, 5}
		);

		glayer_main = GenLayerZoom.magnify(240L, glayer_main, 1);
		glayer_main = new GenLayerLTRaise(250L, glayer_main, GConst.HIGHLANDS, GConst.MOUNTAINS, 4, 5);
		glayer_main = new GenLayerLTRaiseGenerous(255L, glayer_main, GConst.HIGHLANDS, GConst.PLATEAUS, 1, 4);

		glayer_main = new GenLayerLakes(370L, glayer_main);
		glayer_main = new GenLayerLTInfect(375L, glayer_main, GConst.LAKES, 2, 3);

		glayer_main = new GenLayerWetlands(1100L, glayer_main);
		glayer_main = new GenLayerLTInfectChecked(1110L, glayer_main, GConst.WETLANDS, 2, 3, GConst.LAKES);

		glayer_main = new GenLayerFuzzyZoom(260L, glayer_main);
		glayer_main = GenLayerZoom.magnify(270L, glayer_main, 1);
		glayer_main = new GenLayerSmooth(280L, glayer_main);

		/* Beaches */
		glayer_main = new GenLayerShoresCRWG(300L, glayer_main);
		glayer_main = GenLayerZoom.magnify(310L, glayer_main, 1);

		/* Rivers */
		glayer_river = new GenLayerIsland(400L);
		glayer_river = GenLayerZoom.magnify(410L, glayer_river, 1);
		glayer_river = new GenLayerAddIsland(420L, glayer_river);
		glayer_river = new GenLayerFuzzyZoom(430L, glayer_river);
		glayer_river = new GenLayerRemoveTooMuchOcean(440L, glayer_river);
		glayer_river = new GenLayerFuzzyZoom(450L, glayer_river);
		glayer_river = new GenLayerFuzzyZoom(451L, glayer_river);
		glayer_river = new GenLayerFuzzyZoom(452L, glayer_river);
		glayer_river = GenLayerZoom.magnify(470L, glayer_river, 2);
		glayer_river = new GenLayerRiverCRWG(490L, glayer_river);
		glayer_main = new GenLayerRiverMixCRWG(500L, glayer_main, glayer_river);

		glayer_main = new GenLayerTuneUp(550L, glayer_main);
		glayer_main = new GenLayerLTInfectAlways(560L, glayer_main, GConst.FOOTHILLS, GConst.MOUNTAINS);
		glayer_main = new GenLayerLTInfectAlways(561L, glayer_main, GConst.FOOTHILLS, GConst.MOUNTAINS);

		/* Final terrain scaling */
		glayer_landtype_unscaled = new GenLayerCached(glayer_main);
		this.genlayer_landtype_unscaled = (GenLayerCached) glayer_landtype_unscaled;

		/* Biome types */
		glayer_biometype = new GenLayerBiomeTypes(1000L, this.noise_temperature, this.noise_rainfall, this.noise_awesomeness);
		for (int i = 0; i < 2; i++) {
			glayer_biometype = new GenLayerZoom((long) (1010 + i), glayer_biometype);
		}
		this.genlayer_biometype = glayer_biometype;

		/* Species (specific biomes of a type) */
		glayer_species = new GenLayerRandUnbounded(800L);
		for (int i = 0; i < 4; i++) {
			glayer_species = new GenLayerZoom((long) (810 + i), glayer_species);
		}
		glayer_species = new GenLayerSmooth(840L, glayer_species);
		this.genlayer_species = glayer_species;

		/* Biomes */
		GenLayerLong glayerl_biomes;
		glayerl_biomes = new GenLayerLongBiomesCRWG(2000L, glayer_species, glayer_biometype, glayer_main);
		glayerl_biomes = new GenLayerLongZoom(600L, glayerl_biomes);
		glayerl_biomes = new GenLayerLongZoom(610L, glayerl_biomes);
		glayerl_biomes = new GenLayerLongSmooth(611L, glayerl_biomes);
		glayerl_biomes = new GenLayerLongZoom(620L, glayerl_biomes);
		glayerl_biomes = new GenLayerLongZoom(630L, glayerl_biomes);
		glayerl_biomes = new GenLayerLongSmooth(631L, glayerl_biomes);
		this.genlayer_biomes = glayerl_biomes;

		/* Selectors */
		this.n_sel_deepocean = this.createSelector(3000, glayer_landtype_unscaled, GConst.DEEPOCEAN, 1000);
		this.n_sel_ocean = this.createSelector(3100, glayer_landtype_unscaled, GConst.OCEAN, 1000);
		this.n_sel_shallowocean = this.createSelector(3200, glayer_landtype_unscaled, GConst.SHALLOWOCEAN, 1000);
		this.n_sel_beach = this.createSelector(3300, glayer_landtype_unscaled, GConst.BEACH, 1000);

		this.n_sel_lowlands = this.createSelector(3400, glayer_landtype_unscaled, GConst.LOWLANDS, 1000);
		this.n_sel_hills = this.createSelector(3500, glayer_landtype_unscaled, GConst.HILLS, 1000);
		this.n_sel_highlands = this.createSelector(3600, glayer_landtype_unscaled, GConst.HIGHLANDS, 1000);

		this.n_sel_mountains = this.createSelector(3700, glayer_landtype_unscaled, GConst.MOUNTAINS, 1000);
		this.n_sel_plateaus = this.createSelector(3800, glayer_landtype_unscaled, GConst.PLATEAUS, 1000);
		this.n_sel_foothills = this.createSelector(3900, glayer_landtype_unscaled, GConst.FOOTHILLS, 1000);

		this.n_sel_rivers = this.createSelector(4000, glayer_landtype_unscaled, GConst.RIVERS, 2000);
		this.n_sel_lakes = this.createSelector(4100, glayer_landtype_unscaled, GConst.LAKES, 1000);
		this.n_sel_wetlands = this.createSelector(4200, glayer_landtype_unscaled, GConst.WETLANDS, 1000);

		/* World seed */
		glayerl_biomes.initWorldGenSeed(seed);

		/* Deep Ocean */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 33.0, 1.0 / 33.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.2, 0.3);
		this.n_gen_deepocean = nlayer1;

		/* Ocean */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 25.0, 1.0 / 25.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.20, 0.60);
		this.n_gen_ocean = nlayer1;

		/* Shallow Ocean */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 25.0, 1.0 / 25.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.15, 0.95);
		this.n_gen_shallowocean = nlayer1;

		/* Beach */
		nlayer1 = new NLayer2DNoise(rand, 3, true, 1.0 / 32.0, 1.0 / 32.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.15, 1.20);
		this.n_gen_beach = nlayer1;

		/* Lowlands */
		nlayer1 = new NLayer2DNoise(rand, 6, true, 1.0 / 35.0, 1.0 / 35.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.25, 1.20);
		this.n_gen_lowlands = nlayer1;

		/* Hills */
		nlayer1 = new NLayer2DNoise(rand, 5, true, 1.0 / 40.0, 1.0 / 40.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.55, 1.25);
		this.n_gen_hills = nlayer1;

		/* Highlands */
		nlayer1 = new NLayer2DNoise(rand, 5, true, 1.0 / 55.0, 1.0 / 55.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.50, 1.55);
		this.n_gen_highlands = nlayer1;

		/* Mountains */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 200.0, 1.0 / 200.0);
		nlayer1 = new NLayerRidge(nlayer1);
		nlayer2 = new NLayer2DNoise(rand, 4, true, 1.0 / 200.0, 1.0 / 200.0);
		nlayer2 = new NLayerRidge(nlayer2);
		nlayer1 = new NLayerMax(nlayer1, nlayer2);
		nlayer2 = new NLayer2DNoise(rand, 4, true, 1.0 / 200.0, 1.0 / 200.0);
		nlayer2 = new NLayerRidge(nlayer2);
		nlayer1 = new NLayerMax(nlayer1, nlayer2);

		nlayer2 = new NLayer2DNoise(rand, 5, true, 1.0 / 40.0, 1.0 / 40.0);
		nlayer2 = new NLayerMultiplyAdd(nlayer2, 1.0, -0.7);
		nlayer1 = new NLayerAdd(nlayer1, nlayer2);

		nlayer1 = new NLayerMultiplyAdd(nlayer1, 1.70, 2.00);
		this.n_gen_mountains = nlayer1;

		/* Plateaus */
		nlayer1 = new NLayer2DNoise(rand, 5, true, 1.0 / 80.0, 1.0 / 80.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.20, 1.90);
		nlayer2 = new NLayer2DNoise(rand, 4, true, 1.0 / 40.0, 1.0 / 40.0);
		nlayer2 = new NLayerMultiplyAdd(nlayer2, 0.40, 0.00);
		nlayer1 = new NLayerAdd(nlayer1, nlayer2);
		this.n_gen_plateaus = nlayer1;

		/* Foothills */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 100.0, 1.0 / 100.0);
		nlayer1 = new NLayerRidge(nlayer1);
		nlayer2 = new NLayer2DNoise(rand, 4, true, 1.0 / 125.0, 1.0 / 125.0);
		nlayer2 = new NLayerRidge(nlayer2);
		nlayer1 = new NLayerMax(nlayer1, nlayer2);
		nlayer2 = new NLayer2DNoise(rand, 4, true, 1.0 / 133.0, 1.0 / 133.0);
		nlayer2 = new NLayerRidge(nlayer2);
		nlayer1 = new NLayerMax(nlayer1, nlayer2);

		nlayer2 = new NLayer2DNoise(rand, 5, true, 1.0 / 25.0, 1.0 / 25.0);
		nlayer2 = new NLayerMultiplyAdd(nlayer2, 1.0, -0.7);
		nlayer1 = new NLayerAdd(nlayer1, nlayer2);

		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.90, 1.00);
		this.n_gen_foothills = nlayer1;

		/* Rivers */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 75.0, 1.0 / 75.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.05, 1.00);
		this.n_gen_rivers = nlayer1;

		/* Lakes */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 25.0, 1.0 / 25.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.15, 0.90);
		this.n_gen_lakes = nlayer1;

		/* Wetlands */
		nlayer1 = new NLayer2DNoise(rand, 3, true, 1.0 / 24.0, 1.0 / 24.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.24, 1.16);
		this.n_gen_wetlands = nlayer1;

		/* Other stuff */
		this.noise_soil_bumpiness = new NoiseGeneratorPerlin(rand, 4);
	}

	public void resetArrays()
	{
		IntCache.resetIntCache();
		LongCache.resetLongCache();
		this.genlayer_landtype_unscaled.reset();
	}

	private NLayerGDiv createSelector(int base_seed, GenLayer unscaled, int land_type, int value)
	{
		GenLayer glayer_sel = new GenLayerLTValue((long) base_seed, unscaled, land_type, value);
		for (int i = 0; i < 4; i++) {
			glayer_sel = new GenLayerAverage((long) (base_seed + 10 + i), glayer_sel);
			glayer_sel = new GenLayerZoom((long) (base_seed + 20 + i), glayer_sel);
			if ((i & 0x1) != 0) {
				glayer_sel = new GenLayerAverage((long) (base_seed + 30 + i), glayer_sel);
			}
		}
		return new NLayerGDiv(glayer_sel, 1000);
	}

	public GenArrays calcArrays(int chunk_x, int chunk_z)
	{
		return this.generateArrays(chunk_x << 4, chunk_z << 4, 16, 16);
	}

	public GenArrays generateArrays(int ax, int ay, int aw, int ah)
	{
		final int asize = aw * ah;

		this.resetArrays();

		GenArrays arrays = this.gen_arrays;
		arrays.initCertainArraysIfNecessary(asize);

		/* biomes */
		long[] long_arr = this.genlayer_biomes.getLongs(ax, ay, aw, ah);

		LandType[] LT_ENUM = LandType.values();
		BiomeTypeCRWG[] BT_ENUM = BiomeTypeCRWG.values();

		for (int i = 0; i < asize; i++) {
			long l = long_arr[i];

			int biome_id = (int) l & 0xffff;
			arrays.biome_id_array[i] = biome_id;
			arrays.biome_array[i] = BiomeGenBase.getBiome(biome_id);

			int species = (int) l & 0xffff0000;
			arrays.species_array[i] = species;

			int land_type_id = (int) (l >> 32) & 0x3f;
			arrays.land_type_id_array[i] = land_type_id;
			arrays.land_type_array[i] = LT_ENUM[land_type_id];

			int biome_type_id = (int) (l >> 38) & 0x3f;
			arrays.biome_type_id_array[i] = biome_type_id;
			BiomeTypeCRWG biome_type = BT_ENUM[biome_type_id];
			arrays.biome_type_array[i] = biome_type;
			arrays.s_temperatures[i] = biome_type.s_temp;
			arrays.s_rainfalls[i] = biome_type.s_rain;
		}

		/* selectors */
		this.calcSelectors(arrays, ax, ay, aw, ah, asize);

		/* noise */
		if (this.n_sel_deepocean.didWeEncounterAnything()) {
			arrays.deep_ocean_noise = this.n_gen_deepocean.generate(ax, ay, aw, ah, asize, arrays.deep_ocean_sel);
		}
		if (this.n_sel_ocean.didWeEncounterAnything()) {
			arrays.ocean_noise = this.n_gen_ocean.generate(ax, ay, aw, ah, asize, arrays.ocean_sel);
		}
		if (this.n_sel_shallowocean.didWeEncounterAnything()) {
			arrays.shallow_ocean_noise = this.n_gen_shallowocean.generate(ax, ay, aw, ah, asize, arrays.shallow_ocean_sel);
		}
		if (this.n_sel_beach.didWeEncounterAnything()) {
			arrays.beach_noise = this.n_gen_beach.generate(ax, ay, aw, ah, asize, arrays.beach_sel);
		}

		if (this.n_sel_lowlands.didWeEncounterAnything()) {
			arrays.lowland_noise = this.n_gen_lowlands.generate(ax, ay, aw, ah, asize, arrays.lowland_sel);
		}
		if (this.n_sel_hills.didWeEncounterAnything()) {
			arrays.hill_noise = this.n_gen_hills.generate(ax, ay, aw, ah, asize, arrays.hill_sel);
		}
		if (this.n_sel_highlands.didWeEncounterAnything()) {
			arrays.highland_noise = this.n_gen_highlands.generate(ax, ay, aw, ah, asize, arrays.highland_sel);
		}
		if (this.n_sel_mountains.didWeEncounterAnything()) {
			arrays.mountain_noise = this.n_gen_mountains.generate(ax, ay, aw, ah, asize, arrays.mountain_sel);
		}

		if (this.n_sel_plateaus.didWeEncounterAnything()) {
			arrays.plateau_noise = this.n_gen_plateaus.generate(ax, ay, aw, ah, asize, arrays.plateau_sel);
		}
		if (this.n_sel_foothills.didWeEncounterAnything()) {
			arrays.foothill_noise = this.n_gen_foothills.generate(ax, ay, aw, ah, asize, arrays.foothill_sel);
		}

		if (this.n_sel_rivers.didWeEncounterAnything()) {
			arrays.river_noise = this.n_gen_rivers.generate(ax, ay, aw, ah, asize, arrays.river_sel);
		}
		if (this.n_sel_lakes.didWeEncounterAnything()) {
			arrays.lake_noise = this.n_gen_lakes.generate(ax, ay, aw, ah, asize, arrays.lake_sel);
		}
		if (this.n_sel_wetlands.didWeEncounterAnything()) {
			arrays.wetlands_noise = this.n_gen_wetlands.generate(ax, ay, aw, ah, asize, arrays.wetlands_sel);
		}

		this.gen_arrays.soil_bumpiness_noise = this.noise_soil_bumpiness.func_151599_a(
			this.gen_arrays.soil_bumpiness_noise,
			(double) ax,
			(double) ay,
			aw,
			ah,
			0.0625,
			0.0625,
			1.0
		);

		return this.gen_arrays;
	}

	private void calcSelectors(GenArrays arrays, int ax, int ay, int aw, int ah, int asize)
	{
		arrays.deep_ocean_sel = this.n_sel_deepocean.generate(ax, ay, aw, ah, asize, null);
		arrays.ocean_sel = this.n_sel_ocean.generate(ax, ay, aw, ah, asize, null);
		if (this.n_sel_deepocean.didWeEncounterAnything()) {
			this.zeroSelectors(arrays, asize, GConst.SHALLOWOCEAN, 99);
			return;
		}

		arrays.shallow_ocean_sel = this.n_sel_shallowocean.generate(ax, ay, aw, ah, asize, null);
		arrays.beach_sel = this.n_sel_beach.generate(ax, ay, aw, ah, asize, null);
		
		arrays.lowland_sel = this.n_sel_lowlands.generate(ax, ay, aw, ah, asize, null);
		arrays.hill_sel = this.n_sel_hills.generate(ax, ay, aw, ah, asize, null);
		arrays.highland_sel = this.n_sel_highlands.generate(ax, ay, aw, ah, asize, null);

		arrays.mountain_sel = this.n_sel_mountains.generate(ax, ay, aw, ah, asize, null);
		arrays.plateau_sel = this.n_sel_plateaus.generate(ax, ay, aw, ah, asize, null);
		arrays.foothill_sel = this.n_sel_foothills.generate(ax, ay, aw, ah, asize, null);

		arrays.river_sel = this.n_sel_rivers.generate(ax, ay, aw, ah, asize, null);
		arrays.lake_sel = this.n_sel_lakes.generate(ax, ay, aw, ah, asize, null);
		arrays.wetlands_sel = this.n_sel_wetlands.generate(ax, ay, aw, ah, asize, null);
	}

	private void zeroSelector(double[] arr, int asize)
	{
		Arrays.fill(arr, 0, asize, 0.0);
	}

	private void zeroSelectors(GenArrays arrays, int asize, int start, int end)
	{
		LandType[] LT_ENUM = LandType.values();

		for (int i = start; i <= end && i < LT_ENUM.length; i++) {
			switch (LT_ENUM[i]) {
			default:
				break;

			case DEEP_OCEAN:
				this.zeroSelector(arrays.deep_ocean_sel, asize);
				break;
			case OCEAN:
				this.zeroSelector(arrays.ocean_sel, asize);
				break;
			case SHALLOW_OCEAN:
				this.zeroSelector(arrays.shallow_ocean_sel, asize);
				break;
			case BEACH:
				this.zeroSelector(arrays.beach_sel, asize);
				break;

			case LOWLANDS:
				this.zeroSelector(arrays.lowland_sel, asize);
				break;
			case HILLS:
				this.zeroSelector(arrays.hill_sel, asize);
				break;
			case HIGHLANDS:
				this.zeroSelector(arrays.highland_sel, asize);
				break;

			case MOUNTAINS:
				this.zeroSelector(arrays.mountain_sel, asize);
				break;
			case PLATEAUS:
				this.zeroSelector(arrays.plateau_sel, asize);
				break;
			case FOOTHILLS:
				this.zeroSelector(arrays.foothill_sel, asize);
				break;

			case RIVER:
				this.zeroSelector(arrays.river_sel, asize);
				break;
			case LAKE:
				this.zeroSelector(arrays.lake_sel, asize);
				break;
			case WETLANDS:
				this.zeroSelector(arrays.wetlands_sel, asize);
				break;

			}
		}
	}

	@Override
	public float[] getRainfall(float[] array, int ax, int az, int aw, int ah)
	{
		this.resetArrays();

		int size = aw * ah;
		if (array == null || array.length < size) {
			array = new float[size];
		}

		double[] r_arr = new double[size];
		r_arr = this.noise_rainfall.calcArray(r_arr, ax, az, aw, ah, rain_scale, rain_scale);

		for (int i = 0; i < size; i++) {
			double t = r_arr[i];
			t = ((6 * t - 15) * t + 10) * t * t * t;
			array[i] = (float) t;
		}

		return array;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] array, int ax, int az, int aw, int ah)
	{
		this.resetArrays();

		int size = aw * ah;
		if (array == null || array.length < size) {
			array = new BiomeGenBase[size];
		}

		long[] abiome_long = this.genlayer_biomes.getLongs(ax, az, aw, ah);
		for (int xz = 0; xz < size; xz++) {
			array[xz] = BiomeGenBase.getBiome((int) abiome_long[xz] & 0xffff);
			if (array[xz] == null) {
				array[xz] = BiomeGenBase.ocean;
			}
		}

		return array;
	}

	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] array, int ax, int az, int aw, int ah, boolean use_cache)
	{
		this.resetArrays();

		if (array == null || array.length < aw * ah) {
			array = new BiomeGenBase[aw * ah];
		}

		if (use_cache && aw == 16 && ah == 16 && (ax & 15) == 0 && (az & 15) == 0) {
			BiomeGenBase[] array2 = this.biomeCache.getCachedBiomes(ax, az);
			System.arraycopy(array2, 0, array, 0, aw * ah);
			return array;
		} else {
			long[] abiome_long = this.genlayer_biomes.getLongs(ax, az, aw, ah);

			for (int xz = 0; xz < aw * ah; xz++) {
				array[xz] = BiomeGenBase.getBiome((int) abiome_long[xz] & 0xffff);
			}

			return array;
		}
	}

	public boolean areBiomesViable(int x, int z, int size, List<BiomeGenBase> biome_list)
	{
		this.resetArrays();

		int ax = x - size;
		int az = z - size;
		int ax2 = x + size;
		int az2 = z + size;
		int aw = ax2 - ax + 1;
		int ah = az2 - az + 1;
		long[] abiome_long = this.genlayer_biomes.getLongs(ax, az, aw, ah);

		for (int xz = 0; xz < aw * ah; xz++) {
			BiomeGenBase biome = BiomeGenBase.getBiome((int) abiome_long[xz] & 0xffff);

			if (!biome_list.contains(biome)) {
				return false;
			}
		}
		return true;
	}

	public ChunkPosition findBiomePosition(int x, int z, int size, List<BiomeGenBase> biome_list, Random rand)
	{
		this.resetArrays();

		int ax = x - size;
		int az = z - size;
		int ax2 = x + size;
		int az2 = z + size;
		int aw = ax2 - ax + 1;
		int ah = az2 - az + 1;
		long[] abiome_long = this.genlayer_biomes.getLongs(ax, az, aw, ah);

		ChunkPosition chunk_pos = null;
		int i = 0;

		for (int xz = 0; xz < aw * ah; xz++) {
			int x1 = ax + xz % aw;
			int z1 = az + xz / aw;
			BiomeGenBase biome = BiomeGenBase.getBiome((int) abiome_long[xz] & 0xffff);
			if (biome_list.contains(biome) && (chunk_pos == null || rand.nextInt(i + 1) == 0)) {
				chunk_pos = new ChunkPosition(x1, 0, z1);
				++i;
			}
		}

		return chunk_pos;
	}
}

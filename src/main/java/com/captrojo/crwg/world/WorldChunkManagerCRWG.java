package com.captrojo.crwg.world;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.gen.data.GConst;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.captrojo.crwg.world.gen.genlayer.GenLayerAverage;
import com.captrojo.crwg.world.gen.genlayer.GenLayerBiomeTypes;
import com.captrojo.crwg.world.gen.genlayer.GenLayerBiomesCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerCached;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLTInfect;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLTInfectAlways;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLTInfectChecked;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLTRaise;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLTRaiseGenerous;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLTRaiseMulti;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLTValue;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLakes;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLandType;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRand;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRiverCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRiverMixCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerShoresCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerTuneUp;
import com.captrojo.crwg.world.gen.genlayer.GenLayerWetlands;
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

	private static class NoiseGenRunnable implements Runnable
	{
		final WorldChunkManagerCRWG wcm;

		NoiseGenRunnable(WorldChunkManagerCRWG wcm)
		{
			this.wcm = wcm;
		}

		@Override
		public void run()
		{
			this.wcm.gen_arrays.deep_ocean_noise = this.wcm.n_gen_deepocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.ocean_noise = this.wcm.n_gen_ocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.shallow_ocean_noise = this.wcm.n_gen_shallowocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.beach_noise = this.wcm.n_gen_beach.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);

			this.wcm.gen_arrays.lowland_noise = this.wcm.n_gen_lowlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.hill_noise = this.wcm.n_gen_hills.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.highland_noise = this.wcm.n_gen_highlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.mountain_noise = this.wcm.n_gen_mountains.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);

			this.wcm.gen_arrays.plateau_noise = this.wcm.n_gen_plateaus.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.foothill_noise = this.wcm.n_gen_foothills.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			
			this.wcm.gen_arrays.river_noise = this.wcm.n_gen_rivers.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.lake_noise = this.wcm.n_gen_lakes.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.wetlands_noise = this.wcm.n_gen_wetlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
		}
	}

	private static class SelRunnable1 implements Runnable
	{
		final WorldChunkManagerCRWG wcm;

		SelRunnable1(WorldChunkManagerCRWG wcm)
		{
			this.wcm = wcm;
		}

		@Override
		public void run()
		{
			this.wcm.gen_arrays.deep_ocean_sel = this.wcm.n_sel_deepocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.ocean_sel = this.wcm.n_sel_ocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.shallow_ocean_sel = this.wcm.n_sel_shallowocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.beach_sel = this.wcm.n_sel_beach.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);

			this.wcm.gen_arrays.lake_sel = this.wcm.n_sel_lakes.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.wetlands_sel = this.wcm.n_sel_wetlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
		}
	}

	private static class SelRunnable2 implements Runnable
	{
		final WorldChunkManagerCRWG wcm;

		public SelRunnable2(WorldChunkManagerCRWG wcm)
		{
			this.wcm = wcm;
		}

		@Override
		public void run()
		{
			this.wcm.gen_arrays.lowland_sel = this.wcm.n_sel_lowlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.hill_sel = this.wcm.n_sel_hills.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.highland_sel = this.wcm.n_sel_highlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.mountain_sel = this.wcm.n_sel_mountains.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);

			this.wcm.gen_arrays.plateau_sel = this.wcm.n_sel_plateaus.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.foothill_sel = this.wcm.n_sel_foothills.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
		}
	}

	private GenArrays gen_arrays = new GenArrays();

	private int c_ax, c_ay, c_aw, c_ah, c_asize;

	public GenLayerCached genlayer_landtype;
	public GenLayer genlayer_biometype;
	public GenLayer genlayer_species;
	public GenLayer genlayer_biomes;

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

	public NLayer n_sel_deepocean;
	public NLayer n_sel_ocean;
	public NLayer n_sel_shallowocean;
	public NLayer n_sel_beach;

	public NLayer n_sel_lowlands;
	public NLayer n_sel_hills;
	public NLayer n_sel_highlands;
	public NLayer n_sel_mountains;

	public NLayer n_sel_plateaus;
	public NLayer n_sel_foothills;

	public NLayer n_sel_rivers;
	public NLayer n_sel_lakes;
	public NLayer n_sel_wetlands;

	public NoisePerlinFractal noise_temperature;
	public NoisePerlinFractal noise_rainfall;
	public NoisePerlinFractal noise_awesomeness;

	/**
	 * we love that NoiseGeneratorPerlin is actually fractal simplex noise.
	 * good job, Forge
	 */
	public NoiseGeneratorPerlin noise_soil_bumpiness;

	private ExecutorService exec_svc;
	private WorldChunkManagerCRWG.NoiseGenRunnable run_noise_gen;
	private WorldChunkManagerCRWG.SelRunnable1 run_sel_1;
	private WorldChunkManagerCRWG.SelRunnable2 run_sel_2;

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
		glayer_river = GenLayerZoom.magnify(450L, glayer_river, 3);
		glayer_river = new GenLayerSmooth(460L, glayer_river);
		glayer_river = GenLayerZoom.magnify(470L, glayer_river, 2);
		glayer_river = new GenLayerSmooth(480L, glayer_river);
		glayer_river = new GenLayerRiverCRWG(490L, glayer_river);
		glayer_main = new GenLayerRiverMixCRWG(500L, glayer_main, glayer_river);
		
		glayer_main = new GenLayerTuneUp(550L, glayer_main);
		glayer_main = new GenLayerLTInfectAlways(560L, glayer_main, GConst.FOOTHILLS, GConst.MOUNTAINS);
		glayer_main = new GenLayerLTInfectAlways(561L, glayer_main, GConst.FOOTHILLS, GConst.MOUNTAINS);

		/* Final terrain scaling */
		glayer_landtype_unscaled = new GenLayerCached(glayer_main);
		this.genlayer_landtype_unscaled = (GenLayerCached) glayer_landtype_unscaled;

		glayer_main = new GenLayerZoom(600L, glayer_main);
		glayer_main = new GenLayerSmooth(601L, glayer_main);
		glayer_main = new GenLayerZoom(610L, glayer_main);
		glayer_main = new GenLayerSmooth(611L, glayer_main);
		glayer_main = new GenLayerSmooth(612L, glayer_main);
		glayer_main = new GenLayerZoom(620L, glayer_main);
		glayer_main = new GenLayerSmooth(621L, glayer_main);
		glayer_main = new GenLayerZoom(630L, glayer_main);
		glayer_main = new GenLayerSmooth(631L, glayer_main);
		glayer_main = new GenLayerSmooth(632L, glayer_main);
		glayer_main = new GenLayerCached(glayer_main);
		this.genlayer_landtype = (GenLayerCached) glayer_main;

		/* Biome types */
		glayer_biometype = new GenLayerBiomeTypes(1000L, this.noise_temperature, this.noise_rainfall, this.noise_awesomeness);
		for (int i = 0; i < 6; i++) {
			glayer_biometype = new GenLayerZoom((long) (1010 + i), glayer_biometype);
			glayer_biometype = new GenLayerSmooth((long) (1020 + i), glayer_biometype);
		}
		this.genlayer_biometype = glayer_biometype;

		/* Species (specific biomes of a type) */
		glayer_species = new GenLayerRand(800L, Integer.MAX_VALUE);
		for (int i = 0; i < 4; i++) {
			glayer_species = new GenLayerZoom((long) (810 + i), glayer_species);
			glayer_species = new GenLayerZoom((long) (820 + i), glayer_species);
			glayer_species = new GenLayerSmooth((long) (830 + i), glayer_species);
		}
		glayer_species = new GenLayerSmooth(840L, glayer_species);
		this.genlayer_species = glayer_species;

		/* Biomes */
		glayer_main = new GenLayerBiomesCRWG(2000L, glayer_species, glayer_biometype, glayer_main);
		this.genlayer_biomes = glayer_main;

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

		this.n_sel_rivers = this.createSelector(4000, glayer_landtype_unscaled, GConst.RIVERS, 1500);
		this.n_sel_lakes = this.createSelector(4100, glayer_landtype_unscaled, GConst.LAKES, 1000);
		this.n_sel_wetlands = this.createSelector(4200, glayer_landtype_unscaled, GConst.WETLANDS, 1000);

		/* World seed */
		glayer_main.initWorldGenSeed(seed);

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
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.25, 1.22);
		this.n_gen_lowlands = nlayer1;

		/* Hills */
		nlayer1 = new NLayer2DNoise(rand, 5, true, 1.0 / 40.0, 1.0 / 40.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.55, 1.30);
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

		/* yay, multithreading! */
		this.exec_svc = Executors.newFixedThreadPool(3);
		this.run_noise_gen = new WorldChunkManagerCRWG.NoiseGenRunnable(this);
		this.run_sel_1 = new WorldChunkManagerCRWG.SelRunnable1(this);
		this.run_sel_2 = new WorldChunkManagerCRWG.SelRunnable2(this);
	}

	public void shutdown()
	{
		this.exec_svc.shutdown();
	}
	
	public void resetArrays()
	{
		IntCache.resetIntCache();
		this.genlayer_landtype_unscaled.reset();
		this.genlayer_landtype.reset();
	}

	private NLayer createSelector(int base_seed, GenLayer unscaled, int land_type, int value)
	{
		GenLayer glayer_sel = new GenLayerLTValue((long) base_seed, unscaled, land_type, value);
		for (int i = 0; i < 4; i++) {
			glayer_sel = new GenLayerAverage((long) (base_seed + 10 + i), glayer_sel);
			glayer_sel = new GenLayerZoom((long) (base_seed + 20 + i), glayer_sel);
			if ((i & 0x1) != 0) {
				glayer_sel = new GenLayerAverage((long) (base_seed + 30 + i), glayer_sel);
			}
			glayer_sel = new GenLayerSmooth((long) (base_seed + 40 + i), glayer_sel);
		}

		glayer_sel = new GenLayerSmooth(base_seed + 50L, glayer_sel);
		return new NLayerGDiv(glayer_sel, 1000);
	}

	public GenArrays generateArrays(int ax, int ay, int aw, int ah)
	{
		final int asize = aw * ah;
		this.c_ax = ax;
		this.c_ay = ay;
		this.c_aw = aw;
		this.c_ah = ah;
		this.c_asize = asize;

		this.resetArrays();

		/* noise */
		Future<?> future0 = this.exec_svc.submit(this.run_noise_gen);

		/* biomes */
		this.gen_arrays.land_type_id_array = this.genlayer_landtype.getInts(ax, ay, aw, ah);
		this.gen_arrays.biome_id_array = this.genlayer_biomes.getInts(ax, ay, aw, ah);
		this.gen_arrays.biome_type_id_array = this.genlayer_biometype.getInts(ax, ay, aw, ah);
		this.gen_arrays.species_array = this.genlayer_species.getInts(ax, ay, aw, ah);

		/* selectors */
		/* we need a call to generate the cached layer before the other threads run */
		this.gen_arrays.river_sel = this.n_sel_rivers.generate(ax, ay, aw, ah, asize);

		Future<?> future1 = this.exec_svc.submit(this.run_sel_1);
		Future<?> future2 = this.exec_svc.submit(this.run_sel_2);

		this.gen_arrays.initCertainArraysIfNecessary(asize);

		for (int i = 0; i < asize; i++) {
			int biome_id = this.gen_arrays.biome_id_array[i];
			BiomeGenBase biome = BiomeGenBase.getBiome(biome_id);
			this.gen_arrays.biome_array[i] = biome;
			int biome_type_id = this.gen_arrays.biome_type_id_array[i];
			BiomeTypeCRWG type = BiomeTypeCRWG.values()[biome_type_id];
			this.gen_arrays.biome_type_array[i] = type;
			this.gen_arrays.s_temperatures[i] = type.s_temp;
			this.gen_arrays.s_rainfalls[i] = type.s_rain;
			int land_type_id = this.gen_arrays.land_type_id_array[i];
			this.gen_arrays.land_type_array[i] = LandType.values()[land_type_id];
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

		/* wait for other threads */
		try {
			future0.get();
			future1.get();
			future2.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return this.gen_arrays;
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

		int[] abiomeid = this.genlayer_biomes.getInts(ax, az, aw, ah);
		for (int xz = 0; xz < size; xz++) {
			array[xz] = BiomeGenBase.getBiome(abiomeid[xz]);
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
			int[] abiomeid = this.genlayer_biomes.getInts(ax, az, aw, ah);

			for (int xz = 0; xz < aw * ah; xz++) {
				array[xz] = BiomeGenBase.getBiome(abiomeid[xz]);
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
		int[] abiomeid = this.genlayer_biomes.getInts(ax, az, aw, ah);

		for (int xz = 0; xz < aw * ah; xz++) {
			BiomeGenBase biome = BiomeGenBase.getBiome(abiomeid[xz]);

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
		int[] abiomeid = this.genlayer_biomes.getInts(ax, az, aw, ah);

		ChunkPosition chunk_pos = null;
		int i = 0;

		for (int xz = 0; xz < aw * ah; xz++) {
			int x1 = ax + xz % aw;
			int z1 = az + xz / aw;
			BiomeGenBase biome = BiomeGenBase.getBiome(abiomeid[xz]);
			if (biome_list.contains(biome) && (chunk_pos == null || rand.nextInt(i + 1) == 0)) {
				chunk_pos = new ChunkPosition(x1, 0, z1);
				++i;
			}
		}

		return chunk_pos;
	}
}

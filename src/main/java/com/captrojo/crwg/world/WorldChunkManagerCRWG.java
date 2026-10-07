package com.captrojo.crwg.world;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.captrojo.crwg.world.gen.genlayer.GConst;
import com.captrojo.crwg.world.gen.genlayer.GenLayerAverage;
import com.captrojo.crwg.world.gen.genlayer.GenLayerBiomeTypes;
import com.captrojo.crwg.world.gen.genlayer.GenLayerBiomesCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerCached;
import com.captrojo.crwg.world.gen.genlayer.GenLayerChangeLandType;
import com.captrojo.crwg.world.gen.genlayer.GenLayerChangeLandTypeMulti;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLT1000;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLT1500;
import com.captrojo.crwg.world.gen.genlayer.GenLayerLandType;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRand;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRiverCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRiverMixCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerShoresCRWG;
import com.captrojo.crwg.world.gen.noise.NoisePerlinFractal;
import com.captrojo.crwg.world.gen.noise.layer.NLayer;
import com.captrojo.crwg.world.gen.noise.layer.NLayer2DNoise;
import com.captrojo.crwg.world.gen.noise.layer.NLayerDiv1000;
import com.captrojo.crwg.world.gen.noise.layer.NLayerMountainCap;
import com.captrojo.crwg.world.gen.noise.layer.NLayerMultiplyAdd;
import com.captrojo.crwg.world.gen.noise.layer.NLayerSquared;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
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
	public static double temp_scale_pz;
	public static double temp_scale;
	public static double rain_scale_pz;
	public static double rain_scale;

	static {
		temp_scale_pz = 1.0 / 10.0;
		temp_scale = temp_scale_pz / 64.0;
		rain_scale_pz = 1.0 / 10.0;
		rain_scale = rain_scale_pz / 64.0;
	}

	public static class GenArrays
	{
		/* biomes */
		int[] biome_id_array;
		BiomeGenBase[] biome_array;

		/* noise */
		double[] deep_ocean_noise;
		double[] ocean_noise;
		double[] shallow_ocean_noise;
		double[] beach_noise;

		double[] lowland_noise;
		double[] hill_noise;
		double[] highland_noise;
		double[] mountain_noise;

		double[] river_noise;

		/* selectors */
		double[] deep_ocean_sel;
		double[] ocean_sel;
		double[] shallow_ocean_sel;
		double[] beach_sel;

		double[] lowland_sel;
		double[] hill_sel;
		double[] highland_sel;
		double[] mountain_sel;

		double[] river_sel;
	}

	private static class NoiseGenCallable implements Callable<Object>
	{
		final WorldChunkManagerCRWG wcm;

		NoiseGenCallable(WorldChunkManagerCRWG wcm)
		{
			this.wcm = wcm;
		}

		@Override
		public Object call()
		{
			this.wcm.gen_arrays.deep_ocean_noise = this.wcm.n_gen_deepocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.ocean_noise = this.wcm.n_gen_ocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.shallow_ocean_noise = this.wcm.n_gen_shallowocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.beach_noise = this.wcm.n_gen_beach.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);

			this.wcm.gen_arrays.lowland_noise = this.wcm.n_gen_lowlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.hill_noise = this.wcm.n_gen_hills.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.highland_noise = this.wcm.n_gen_highlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.mountain_noise = this.wcm.n_gen_mountains.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);

			this.wcm.gen_arrays.river_noise = this.wcm.n_gen_rivers.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
		
			return null;
		}
	}
	
	private static class OceanSelCallable implements Callable<Object>
	{
		final WorldChunkManagerCRWG wcm;
		
		OceanSelCallable(WorldChunkManagerCRWG wcm)
		{
			this.wcm = wcm;
		}
		
		@Override
		public Object call()
		{
			this.wcm.gen_arrays.deep_ocean_sel = this.wcm.n_sel_deepocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.ocean_sel = this.wcm.n_sel_ocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.shallow_ocean_sel = this.wcm.n_sel_shallowocean.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.beach_sel = this.wcm.n_sel_beach.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			
			return null;
		}
	}
	
	private static class BasicLandSelCallable implements Callable<Object>
	{
		final WorldChunkManagerCRWG wcm;
		
		public BasicLandSelCallable(WorldChunkManagerCRWG wcm)
		{
			this.wcm = wcm;
		}
		
		@Override
		public Object call()
		{
			this.wcm.gen_arrays.lowland_sel = this.wcm.n_sel_lowlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.hill_sel = this.wcm.n_sel_hills.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.highland_sel = this.wcm.n_sel_highlands.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			this.wcm.gen_arrays.mountain_sel = this.wcm.n_sel_mountains.generate(this.wcm.c_ax, this.wcm.c_ay, this.wcm.c_aw, this.wcm.c_ah, this.wcm.c_asize);
			
			return null;
		}
	}

	private WorldChunkManagerCRWG.GenArrays gen_arrays = new WorldChunkManagerCRWG.GenArrays();
	
	private int c_ax, c_ay, c_aw, c_ah, c_asize;

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

	public NLayer n_gen_rivers;

	public NLayer n_sel_deepocean;
	public NLayer n_sel_ocean;
	public NLayer n_sel_shallowocean;
	public NLayer n_sel_beach;

	public NLayer n_sel_lowlands;
	public NLayer n_sel_hills;
	public NLayer n_sel_highlands;
	public NLayer n_sel_mountains;

	public NLayer n_sel_rivers;

	public NoisePerlinFractal noise_temperature;
	public NoisePerlinFractal noise_rainfall;

	private ExecutorService svc_noise_gen;
	private ExecutorService svc_ocean_sel;
	private ExecutorService svc_basic_land_sel;
	
	private WorldChunkManagerCRWG.NoiseGenCallable call_noise_gen;
	private WorldChunkManagerCRWG.OceanSelCallable call_ocean_sel;
	private WorldChunkManagerCRWG.BasicLandSelCallable call_basic_land_sel;

	public WorldChunkManagerCRWG(World world)
	{
		long seed = world.getSeed();
		Random rand = new Random(seed);

		this.noise_temperature = (new NoisePerlinFractal(4, true)).initNoiseGens(rand);
		this.noise_rainfall = (new NoisePerlinFractal(4, true)).initNoiseGens(rand);

		GenLayer glayer_main, glayer_river, glayer_biometype, glayer_species, glayer_landtype_unscaled, glayer_sel;
		NLayer nlayer1;

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
		glayer_main = new GenLayerChangeLandTypeMulti(
			210L,
			glayer_main,
			new int[] {GConst.LOWLANDS, GConst.SHALLOWOCEAN},
			new int[] {GConst.HILLS, GConst.OCEAN},
			new int[] {3, 3},
			new int[] {4, 4}
		);
		glayer_main = GenLayerZoom.magnify(220L, glayer_main, 1);
		glayer_main = new GenLayerChangeLandTypeMulti(
			230L,
			glayer_main,
			new int[] {GConst.HILLS, GConst.OCEAN},
			new int[] {GConst.HIGHLANDS, GConst.DEEPOCEAN},
			new int[] {4, 4},
			new int[] {5, 5}
		);
		glayer_main = GenLayerZoom.magnify(240L, glayer_main, 1);
		glayer_main = new GenLayerChangeLandType(250L, glayer_main, GConst.HIGHLANDS, GConst.MOUNTAINS, 4, 5);

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
		glayer_river = new GenLayerRemoveTooMuchOcean(340L, glayer_river);
		glayer_river = GenLayerZoom.magnify(450L, glayer_river, 3);
		glayer_river = new GenLayerSmooth(460L, glayer_river);
		glayer_river = GenLayerZoom.magnify(470L, glayer_river, 2);
		glayer_river = new GenLayerSmooth(480L, glayer_river);
		glayer_river = new GenLayerRiverCRWG(490L, glayer_river);
		glayer_main = new GenLayerRiverMixCRWG(500L, glayer_main, glayer_river);

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

		/* Biome types */
		glayer_biometype = new GenLayerBiomeTypes(1000L, this.noise_temperature, this.noise_rainfall);
		for (int i = 0; i < 6; i++) {
			glayer_biometype = new GenLayerZoom((long) (1010 + i), glayer_biometype);
			glayer_biometype = new GenLayerSmooth((long) (1020 + i), glayer_biometype);
		}

		/* Species (specific biomes of a type) */
		glayer_species = new GenLayerRand(800L);
		for (int i = 0; i < 4; i++) {
			glayer_species = new GenLayerZoom((long) (810 + i), glayer_species);
			glayer_species = new GenLayerZoom((long) (820 + i), glayer_species);
			glayer_species = new GenLayerSmooth((long) (830 + i), glayer_species);
		}
		glayer_species = new GenLayerSmooth(840L, glayer_species);

		/* Biomes */
		glayer_main = new GenLayerBiomesCRWG(2000L, glayer_species, glayer_biometype, glayer_main);

		/* Selectors */
		this.n_sel_deepocean = this.createSelector1000(3000, glayer_landtype_unscaled, GConst.DEEPOCEAN);
		this.n_sel_ocean = this.createSelector1000(3100, glayer_landtype_unscaled, GConst.OCEAN);
		this.n_sel_shallowocean = this.createSelector1000(3200, glayer_landtype_unscaled, GConst.SHALLOWOCEAN);
		this.n_sel_beach = this.createSelector1000(3300, glayer_landtype_unscaled, GConst.BEACH);

		this.n_sel_lowlands = this.createSelector1000(3400, glayer_landtype_unscaled, GConst.LOWLANDS);
		this.n_sel_hills = this.createSelector1000(3500, glayer_landtype_unscaled, GConst.HILLS);
		this.n_sel_highlands = this.createSelector1000(3600, glayer_landtype_unscaled, GConst.HIGHLANDS);
		this.n_sel_mountains = this.createSelector1000(3700, glayer_landtype_unscaled, GConst.MOUNTAINS);

		this.n_sel_rivers = this.createSelector1500(3800, glayer_landtype_unscaled, GConst.RIVERS);

		/* World seed */
		glayer_main.initWorldGenSeed(seed);

		this.genlayer_biomes = glayer_main;

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
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 35.0, 1.0 / 35.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.20, 1.35);
		this.n_gen_lowlands = nlayer1;

		/* Hills */
		nlayer1 = new NLayer2DNoise(rand, 3, true, 1.0 / 50.0, 1.0 / 50.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.45, 1.40);
		this.n_gen_hills = nlayer1;

		/* Highlands */
		nlayer1 = new NLayer2DNoise(rand, 3, true, 1.0 / 55.0, 1.0 / 55.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.40, 1.70);
		this.n_gen_highlands = nlayer1;

		/* Mountains */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 100.0, 1.0 / 100.0);
		nlayer1 = new NLayerSquared(nlayer1);
		nlayer1 = new NLayerSquared(nlayer1);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 3.0, 0.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 2.00, 1.70);
		nlayer1 = new NLayerMountainCap(nlayer1);
		this.n_gen_mountains = nlayer1;

		/* Rivers */
		nlayer1 = new NLayer2DNoise(rand, 4, true, 1.0 / 75.0, 1.0 / 75.0);
		nlayer1 = new NLayerMultiplyAdd(nlayer1, 0.05, 1.00);
		this.n_gen_rivers = nlayer1;
		
		/* yay, multithreading! */
		this.svc_noise_gen = Executors.newSingleThreadExecutor();
		this.svc_ocean_sel = Executors.newSingleThreadExecutor();
		this.svc_basic_land_sel = Executors.newSingleThreadExecutor();
		
		this.call_noise_gen = new WorldChunkManagerCRWG.NoiseGenCallable(this);
		this.call_ocean_sel = new WorldChunkManagerCRWG.OceanSelCallable(this);
		this.call_basic_land_sel = new WorldChunkManagerCRWG.BasicLandSelCallable(this);
	}

	private NLayer createSelector1000(int base_seed, GenLayer unscaled, int land_type)
	{
		GenLayer glayer_sel = new GenLayerLT1000((long) base_seed, unscaled, land_type);
		return this.createSelectorI(base_seed, unscaled, land_type, glayer_sel);
	}

	private NLayer createSelector1500(int base_seed, GenLayer unscaled, int land_type)
	{
		GenLayer glayer_sel = new GenLayerLT1500((long) base_seed, unscaled, land_type);
		return this.createSelectorI(base_seed, unscaled, land_type, glayer_sel);
	}

	private NLayer createSelectorI(int base_seed, GenLayer unscaled, int land_type, GenLayer glayer_sel)
	{
		for (int i = 0; i < 4; i++) {
			glayer_sel = new GenLayerAverage((long) (base_seed + 10 + i), glayer_sel);
			glayer_sel = new GenLayerZoom((long) (base_seed + 20 + i), glayer_sel);
			if ((i & 0x1) != 0) {
				glayer_sel = new GenLayerAverage((long) (base_seed + 30 + i), glayer_sel);
			}
			glayer_sel = new GenLayerSmooth((long) (base_seed + 40 + i), glayer_sel);
		}

		glayer_sel = new GenLayerSmooth(base_seed + 50L, glayer_sel);
		return new NLayerDiv1000(glayer_sel);
	}

	public WorldChunkManagerCRWG.GenArrays generateArrays(int ax, int ay, int aw, int ah)
	{
		final int asize = aw * ah;
		this.c_ax = ax;
		this.c_ay = ay;
		this.c_aw = aw;
		this.c_ah = ah;
		this.c_asize = asize;
		
		IntCache.resetIntCache();

		/* noise */
		Future<Object> future0 = this.svc_noise_gen.submit(this.call_noise_gen);

		/* biomes */
		this.gen_arrays.biome_id_array = this.genlayer_biomes.getInts(ax, ay, aw, ah);

		/* selectors */
		/* we need a call to generate the cached layer before the other threads run */
		this.genlayer_landtype_unscaled.reset();
		this.gen_arrays.river_sel = this.n_sel_rivers.generate(ax, ay, aw, ah, asize);

		Future<Object> future1 = this.svc_ocean_sel.submit(this.call_ocean_sel);
		Future<Object> future2 = this.svc_basic_land_sel.submit(this.call_basic_land_sel);
		
		/* convert biome ids to BiomeGenBase pointers */
		if (this.gen_arrays.biome_array == null || this.gen_arrays.biome_array.length < asize) {
			this.gen_arrays.biome_array = new BiomeGenBase[asize];
		}
		for (int i = 0; i < asize; i++) {
			BiomeGenBase biome = BiomeGenBase.getBiome(this.gen_arrays.biome_id_array[i]);
			this.gen_arrays.biome_array[i] = biome;
		}
		
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
		IntCache.resetIntCache();
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
		IntCache.resetIntCache();
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

	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] array, int ax, int az, int aw, int ah, boolean p_76931_6_)
	{
		IntCache.resetIntCache();
		if (array == null || array.length < aw * ah) {
			array = new BiomeGenBase[aw * ah];
		}

		if (p_76931_6_ && aw == 16 && ah == 16 && (ax & 15) == 0 && (az & 15) == 0) {
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
		IntCache.resetIntCache();
		int ax = x - size >> 2;
		int az = z - size >> 2;
		int ax2 = x + size >> 2;
		int az2 = z + size >> 2;
		int aw = ax2 - ax + 1;
		int ah = az2 - az + 1;
		int[] abiomeid = this.genlayer_biomes.getInts(ax, az, aw, ah);

		for (int xz = 0; xz < aw * ah; ++xz) {
			BiomeGenBase biome = BiomeGenBase.getBiome(abiomeid[xz]);

			if (!biome_list.contains(biome)) {
				return false;
			}
		}
		return true;
	}

	public ChunkPosition findBiomePosition(int x, int z, int size, List<BiomeGenBase> biome_list, Random rand)
	{
		IntCache.resetIntCache();
		int ax = x - size >> 2;
		int az = z - size >> 2;
		int ax2 = x + size >> 2;
		int az2 = z + size >> 2;
		int aw = ax2 - ax + 1;
		int ah = az2 - az + 1;
		int[] abiomeid = this.genlayer_biomes.getInts(ax, az, aw, ah);

		ChunkPosition chunk_pos = null;
		int i = 0;

		for (int xz = 0; xz < aw * ah; ++xz) {
			int x1 = ax + xz % aw << 2;
			int z1 = az + xz / aw << 2;
			BiomeGenBase biome = BiomeGenBase.getBiome(abiomeid[xz]);
			if (biome_list.contains(biome) && (chunk_pos == null || rand.nextInt(i + 1) == 0)) {
				chunk_pos = new ChunkPosition(x1, 0, z1);
				++i;
			}
		}

		return chunk_pos;
	}
}

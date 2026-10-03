package com.captrojo.crwg.world;

import java.util.List;
import java.util.Random;

import com.captrojo.crwg.world.gen.genlayer.GenLayerBiomes;
import com.captrojo.crwg.world.gen.noise.layer.NLayer;
import com.captrojo.crwg.world.gen.noise.layer.NLayer2DNoise;
import com.captrojo.crwg.world.gen.noise.layer.NLayerContinentToHeight;
import com.captrojo.crwg.world.gen.noise.layer.NLayerFade;
import com.captrojo.crwg.world.gen.noise.layer.NLayerFadeLessSteep;
import com.captrojo.crwg.world.gen.noise.layer.NLayerHillsMountains;
import com.captrojo.crwg.world.gen.noise.layer.NLayerIncreaseOceans;
import com.captrojo.crwg.world.gen.noise.layer.NLayerSaved;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerCRWG extends WorldChunkManager
{
	public NLayerSaved nl_continentalness;
	public NLayerSaved nl_hill_selector;
	public NLayerSaved nl_height;
	
	public NLayer nl_temperature;
	public NLayer nl_rainfall;

	public GenLayer layer_height_types;
	public GenLayer layer_biomes;

	public WorldChunkManagerCRWG(World world)
	{
		long seed = world.getSeed();
		Random rand = new Random(seed);

		NLayer layer, layer2, layer3, layer4;
		
		double terrain_scale = 2.0;
		double biome_scale = 1.0;

		layer = new NLayer2DNoise(rand, 10, false, 1.0 / (900.0 * terrain_scale), 1.0 / (900.0 * terrain_scale), 1.0);
		layer = new NLayerIncreaseOceans(layer);
		layer = new NLayerSaved(layer, true);
		this.nl_continentalness = (NLayerSaved) layer;

		layer = new NLayerContinentToHeight(layer);
		
		layer2 = new NLayer2DNoise(rand, 5, true, 1.0 / (100.0 * terrain_scale), 1.0 / (100.0 * terrain_scale));
		layer2 = new NLayerSaved(layer2, false);
		this.nl_hill_selector = (NLayerSaved) layer2;
		
		layer3 = new NLayer2DNoise(rand, 3, true, 1.0 / 50.0, 1.0 / 50.0);
		layer3 = new NLayerFade(layer3);
		layer4 = new NLayer2DNoise(rand, 8, true, 1.0 / 130.0, 1.0 / 130.0);
		layer = new NLayerHillsMountains(layer, this.nl_continentalness, layer2, layer3, layer4);

		layer = new NLayerSaved(layer, false);
		this.nl_height = (NLayerSaved) layer;
		
		this.nl_temperature = new NLayer2DNoise(rand, 3, true, 1.0 / (320.0 * biome_scale), 1.0 / (320.0 * biome_scale));
		this.nl_temperature = new NLayerFade(this.nl_temperature);
		
		this.nl_rainfall = new NLayer2DNoise(rand, 3, true, 1.0 / (640.0 * biome_scale), 1.0 / (640.0 * biome_scale));
		this.nl_rainfall = new NLayerFade(this.nl_rainfall);
		
		this.layer_biomes = new GenLayerBiomes(seed, this);
		this.layer_biomes.initWorldGenSeed(seed);
	}

	@Override
	public float[] getRainfall(float[] array, int ax, int az, int aw, int ah)
	{
		IntCache.resetIntCache();
		int size = aw * ah;
		if (array == null || array.length < size) {
			array = new float[size];
		}

		for (int i = 0; i < size; i++) {
			array[i] = 0.5f;
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

		int[] abiomeid = this.layer_biomes.getInts(ax, az, aw, ah);
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
			int[] abiomeid = this.layer_biomes.getInts(ax, az, aw, ah);

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
		int[] abiomeid = this.layer_biomes.getInts(ax, az, aw, ah);

		for (int xz = 0; xz < aw * ah; ++xz) {
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(abiomeid[xz]);

			if (!biome_list.contains(biomegenbase)) {
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
		int[] abiomeid = this.layer_biomes.getInts(ax, az, aw, ah);

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

package com.captrojo.crwg.world;

import com.captrojo.crwg.world.genlayer.*;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.List;
import java.util.Random;

public class WorldChunkManagerCRWG extends WorldChunkManager
{
	public Layer layer_continentalness;
	public Layer layer_height;

	public LayerRiverSelector layer_river_selector;

	public Layer layer_rainfall;
	public Layer layer_temperature;

	public GenLayer layer_biomes;

	public WorldChunkManagerCRWG(World world)
	{
		long seed = world.getSeed();
		Layer layer;
		Layer layer2;
		Layer layer3;

		layer = new Layer2DNoise(Layer.createSeed(seed, 1L), 4, 500, 1.0 / 7.0);
		layer = new LayerIncreaseOceans(layer); /* Make oceans majority */
		this.layer_continentalness = layer;

		layer = new LayerContinentHeightCurve(layer);

		layer2 = new Layer2DNoise(Layer.createSeed(seed, 5784934345L), 3, 100, 1.0);
		layer = new LayerLandHeightTweaks(layer, layer2, 1.0 / 5.0);

		layer2 = new Layer2DNoise(Layer.createSeed(seed, 8L), 4, 50, 1.0);
		layer3 = new Layer2DNoise(Layer.createSeed(seed, 35445246234L), 3, 20, 1.0);
		layer = new LayerAddMountains(layer, layer2, 1.0 / 7.0, layer3, 0.1);

		layer2 = new Layer2DNoise(Layer.createSeed(seed, 1234567890L), 3, 37, 1.0);
		layer = new LayerAdd(layer, layer2, 0.06);

		layer2 = new Layer2DNoise(Layer.createSeed(seed, 9999999L), 2, 10, 1.0);
		layer = new LayerAdd(layer, layer2, 0.01);

		layer2 = new Layer2DNoise(Layer.createSeed(seed, 444444L), 3, 100, 1.0);
		this.layer_river_selector = new LayerRiverSelector(layer2, 0.07);
		layer = new LayerRivers(layer, this.layer_river_selector);

		this.layer_height = layer;

		this.layer_rainfall = new Layer2DNoise(Layer.createSeed(seed, 1234L), 3, 1000, 1.0);
		this.layer_temperature = new Layer2DNoise(Layer.createSeed(seed, 5678L), 3, 1000, 1.0);

		this.layer_biomes = new GenLayerBiomes(
			1L,
			this.layer_height,
			this.layer_river_selector,
			this.layer_rainfall,
			this.layer_temperature
		);
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

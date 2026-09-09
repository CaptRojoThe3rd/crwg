package com.captrojo.crwg.world.genlayer;

import com.captrojo.crwg.main.CRWG;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomes extends GenLayer
{
	Layer layer_height;
	LayerRiverSelector layer_river_selector;
	Layer layer_rainfall;
	Layer layer_temperature;

	public GenLayerBiomes(
		long base_seed,
		Layer layer_height,
		LayerRiverSelector layer_river_selector,
		Layer layer_rainfall,
		Layer layer_temperature
	)
	{
		super(base_seed);

		this.layer_height = layer_height;
		this.layer_river_selector = layer_river_selector;
		this.layer_rainfall = layer_rainfall;
		this.layer_temperature = layer_temperature;
	}

	@Override
	public int[] getInts(int ax, int az, int aw, int ah)
	{
		int size = aw * ah;
		int[] array = IntCache.getIntCache(size);

		double[] aheight = this.layer_height.generate(ax, az, aw, ah, size);
		/* A reference to the river selector's array is saved so we don't have to regenerate it */
		double[] ariversel = this.layer_river_selector.saved;

		for (int xz = 0; xz < size; xz++) {
			double h = aheight[xz];
			double r = ariversel[xz];

			if (r < -0.7) {
				array[xz] = BiomeGenBase.river.biomeID;
			} else {
				if (h < 0.03 && h > -0.1 && r > -0.7) {
					array[xz] = BiomeGenBase.beach.biomeID;
				} else if (h <= -0.1) {
					array[xz] = BiomeGenBase.ocean.biomeID;
				} else {
					array[xz] = BiomeGenBase.plains.biomeID;
				}
			}
		}

		return array;
	}
}

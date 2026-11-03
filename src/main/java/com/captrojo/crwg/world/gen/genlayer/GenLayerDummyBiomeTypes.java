package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDummyBiomeTypes extends GenLayer
{
	public GenLayerDummyBiomeTypes()
	{
		super(1L);
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);

		for (int i = 0; i < asize; i++) {
			n_arr[i] = BiomeTypeCRWG.DECIDUOUS_FOREST.ordinal();
		}

		return n_arr;
	}
}

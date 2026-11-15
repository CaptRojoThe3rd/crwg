package com.captrojo.crwg.world.gen.layer;

import java.util.Arrays;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerZero extends GenLayer
{
	public GenLayerZero()
	{
		super(0L);
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		final int asize = aw * ah;
		int[] arr = IntCache.getIntCache(asize);
		Arrays.fill(arr, 0, asize, 0);
		return arr;
	}
}

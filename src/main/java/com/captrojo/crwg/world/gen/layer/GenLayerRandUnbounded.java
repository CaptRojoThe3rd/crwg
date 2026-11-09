package com.captrojo.crwg.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRandUnbounded extends GenLayer
{
	public GenLayerRandUnbounded(long base_seed)
	{
		super(base_seed);
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		final int asize = aw * ah;
		int[] array = IntCache.getIntCache(asize);

		for (int x = 0; x < aw; x++) {
			for (int y = 0; y < ah; y++) {
				int idx = x + (y * aw);
				this.initChunkSeed((long) (ax + x), (long) (ay + y));
				array[idx] = (int) this.nextLong(4294967295L);
			}
		}

		return array;
	}
}

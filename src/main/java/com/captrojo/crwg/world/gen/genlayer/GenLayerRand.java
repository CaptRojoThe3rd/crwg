package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRand extends GenLayer
{
	final int bound;

	public GenLayerRand(long base_seed, int bound)
	{
		super(base_seed);
		this.bound = bound;
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
				array[idx] = this.nextInt(this.bound);
			}
		}

		return array;
	}
}

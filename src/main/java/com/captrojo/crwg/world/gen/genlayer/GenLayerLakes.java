package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLakes extends GenLayer
{
	public GenLayerLakes(long base_seed, GenLayer parent)
	{
		super(base_seed);
		this.parent = parent;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		final int asize = aw * ah;
		int[] p_arr = this.parent.getInts(ax, ay, aw, ah);
		int[] n_arr = IntCache.getIntCache(asize);
		
		for (int x = 0; x < aw; x++) {
			for (int y = 0; y < ah; y++) {
				int i = x + (y * aw);
				n_arr[i] = p_arr[i];
				if (p_arr[i] < GConst.HILLS) {
					continue;
				}
				this.initChunkSeed((long) (ax + x), (long) (ay + y));
				if (this.nextInt(100) == 0) {
					n_arr[i] = GConst.LAKES;
				}
			}
		}
		
		return n_arr;
	}
}

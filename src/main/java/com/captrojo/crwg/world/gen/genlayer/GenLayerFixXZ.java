package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerFixXZ extends GenLayer
{
	public GenLayerFixXZ(long base_seed, GenLayer parent)
	{
		super(base_seed);
		this.parent = parent;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		final int asize = aw * ah;

		int[] old_arr = this.parent.getInts(ax, ay, aw, ah);
		int[] new_arr = IntCache.getIntCache(asize);

		for (int x = 0; x < aw; x++) {
			for (int z = 0; z < ah; z++) {
				int old_idx = z + (x * ah);
				int new_idx = x + (z * aw);
				new_arr[new_idx] = old_arr[old_idx];
			}
		}

		return new_arr;
	}
}

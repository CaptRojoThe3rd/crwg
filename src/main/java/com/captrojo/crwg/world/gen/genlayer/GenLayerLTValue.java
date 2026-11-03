package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLTValue extends GenLayer
{
	final int land_type;
	final int value;

	public GenLayerLTValue(long base_seed, GenLayer parent, int land_type, int value)
	{
		super(base_seed);
		this.parent = parent;
		this.land_type = land_type;
		this.value = value;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int[] p_arr = this.parent.getInts(ax, ay, aw, ah);

		final int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);

		for (int i = 0; i < asize; i++) {
			if (p_arr[i] != this.land_type) {
				n_arr[i] = 0;
				continue;
			}
			n_arr[i] = this.value;
		}

		return n_arr;
	}
}

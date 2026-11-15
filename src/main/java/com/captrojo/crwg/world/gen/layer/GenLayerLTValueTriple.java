package com.captrojo.crwg.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLTValueTriple extends GenLayer
{
	final int land_type_1, land_type_2, land_type_3;
	final int value;

	public GenLayerLTValueTriple(long base_seed, GenLayer parent, int lt1, int lt2, int lt3, int value)
	{
		super(base_seed);
		this.parent = parent;
		this.land_type_1 = lt1;
		this.land_type_2 = lt2;
		this.land_type_3 = lt3;
		this.value = value;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int[] p_arr = this.parent.getInts(ax, ay, aw, ah);

		final int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);

		for (int i = 0; i < asize; i++) {
			int p = p_arr[i];
			if (p == this.land_type_1 || p == this.land_type_2 || p == this.land_type_3) {
				n_arr[i] = this.value;
				continue;
			}
			n_arr[i] = 0;
		}

		return n_arr;
	}
}

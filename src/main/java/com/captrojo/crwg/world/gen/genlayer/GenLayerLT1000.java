package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLT1000 extends GenLayer
{
	final int land_type;
	
	public GenLayerLT1000(long base_seed, GenLayer parent, int land_type)
	{
		super(base_seed);
		this.parent = parent;
		this.land_type = land_type;
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
			n_arr[i] = 1000;
		}
		
		return n_arr;
	}
}

package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLandType extends GenLayer
{
	private static final int[] LUT = {GConst.SHALLOWOCEAN, GConst.LOWLANDS};

	public GenLayerLandType(long base_seed, GenLayer parent)
	{
		super(base_seed);
		this.parent = parent;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int[] p_arr = this.parent.getInts(ax, ay, aw, ah);

		int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);

		for (int i = 0; i < asize; i++) {
			n_arr[i] = LUT[p_arr[i]];
		}

		return n_arr;
	}
}

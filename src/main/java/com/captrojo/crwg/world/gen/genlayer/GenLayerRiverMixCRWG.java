package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverMixCRWG extends GenLayer
{
	private final GenLayer river;

	public GenLayerRiverMixCRWG(long base_seed, GenLayer parent, GenLayer river)
	{
		super(base_seed);
		this.parent = parent;
		this.river = river;
	}

	@Override
	public void initWorldGenSeed(long seed)
	{
		this.river.initWorldGenSeed(seed);
		super.initWorldGenSeed(seed);
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int[] p_arr = this.parent.getInts(ax, ay, aw, ah);
		int[] r_arr = this.river.getInts(ax, ay, aw, ah);

		int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);

		for (int i = 0; i < asize; i++) {
			n_arr[i] = p_arr[i];
			if (r_arr[i] == GConst.WATER && p_arr[i] > GConst.SHALLOWOCEAN && p_arr[i] < GConst.MOUNTAINS) {
				n_arr[i] = GConst.RIVERS;
			}
		}

		return n_arr;
	}
}

package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAverage extends GenLayer
{
	public GenLayerAverage(long base_seed, GenLayer parent)
	{
		super(base_seed);
		this.parent = parent;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int p_ax = ax - 1;
		int p_ay = ay - 1;
		int p_aw = aw + 2;
		int p_ah = ah + 2;
		int[] p_arr = this.parent.getInts(p_ax, p_ay, p_aw, p_ah);

		int[] n_arr = IntCache.getIntCache(aw * ah);

		for (int x = 0; x < aw; x++) {
			for (int y = 0; y < ah; y++) {
				int p_idx = (x + 1) + ((y + 1) * p_aw);
				int n_idx = x + (y * aw);

				int px0y0 = p_arr[p_idx - p_aw - 1];
				int px1y0 = p_arr[p_idx - p_aw];
				int px2y0 = p_arr[p_idx - p_aw + 1];
				int px0y1 = p_arr[p_idx - 1];
				int px1y1 = p_arr[p_idx];
				int px2y1 = p_arr[p_idx + 1];
				int px0y2 = p_arr[p_idx + p_aw - 1];
				int px1y2 = p_arr[p_idx + p_aw];
				int px2y2 = p_arr[p_idx + p_aw + 1];

				int sum = px0y0 + px1y0 + px2y0 + px0y1 + px1y1 + px2y1 + px0y2 + px1y2 + px2y2;
				n_arr[n_idx] = sum / 9;
			}
		}

		return n_arr;
	}
}

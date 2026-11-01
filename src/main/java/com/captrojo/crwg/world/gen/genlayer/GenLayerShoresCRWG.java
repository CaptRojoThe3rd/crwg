package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShoresCRWG extends GenLayer
{
	public GenLayerShoresCRWG(long base_seed, GenLayer parent)
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
				
				if (
					px1y1 < GConst.BEACH
					&& (
						px0y0 > GConst.BEACH
						|| px1y0 > GConst.BEACH
						|| px2y0 > GConst.BEACH
						|| px0y1 > GConst.BEACH
						|| px2y1 > GConst.BEACH
						|| px0y2 > GConst.BEACH
						|| px1y2 > GConst.BEACH
						|| px2y2 > GConst.BEACH
					)
				) {
					n_arr[n_idx] = GConst.BEACH;
				} else {
					n_arr[n_idx] = px1y1;
				}
			}
		}
		
		return n_arr;
	}
}

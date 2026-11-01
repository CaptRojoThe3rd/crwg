package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverCRWG extends GenLayer
{
	public GenLayerRiverCRWG(long base_seed, GenLayer parent)
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
				
				int top = p_arr[p_idx - p_aw];
				int bottom = p_arr[p_idx + p_aw];
				int left = p_arr[p_idx - 1];
				int right = p_arr[p_idx + 1];
				int center = p_arr[p_idx];
				
				int op = center ^ 0x1;
				
				if (top == op || bottom == op || left == op || right == op) {
					n_arr[n_idx] = GConst.WATER;
				} else {
					n_arr[n_idx] = GConst.LAND;
				}
			}
		}
		
		return n_arr;
	}
}

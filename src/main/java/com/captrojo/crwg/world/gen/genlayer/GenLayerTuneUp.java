package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerTuneUp extends GenLayer
{
	private int[] pvals = new int[9];
	
	public GenLayerTuneUp(long base_seed, GenLayer parent)
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
				this.pvals[0] = px0y0;
				this.pvals[1] = px1y0;
				this.pvals[2] = px2y0;
				this.pvals[3] = px0y1;
				this.pvals[4] = px1y1;
				this.pvals[5] = px2y1;
				this.pvals[6] = px0y2;
				this.pvals[7] = px1y2;
				this.pvals[8] = px2y2;

				n_arr[n_idx] = px1y1;

				if (px1y1 == GConst.MOUNTAINS) {
					if (
						px0y0 == GConst.LAKES ||
						px0y0 == GConst.WETLANDS ||
						px1y0 == GConst.LAKES ||
						px1y0 == GConst.WETLANDS ||
						px2y0 == GConst.LAKES ||
						px2y0 == GConst.WETLANDS ||
						px0y1 == GConst.LAKES ||
						px0y1 == GConst.WETLANDS ||
						px2y1 == GConst.LAKES ||
						px2y1 == GConst.WETLANDS ||
						px0y2 == GConst.LAKES ||
						px0y2 == GConst.WETLANDS ||
						px1y2 == GConst.LAKES ||
						px1y2 == GConst.WETLANDS ||
						px2y2 == GConst.LAKES ||
						px2y2 == GConst.WETLANDS
					) {
						n_arr[n_idx] = GConst.FOOTHILLS;
					}
				} else if (px1y1 == GConst.RIVERS) {
					int b = 0;
					for (int i = 0; i < 9; i++) {
						if (this.pvals[i] == GConst.BEACH) {
							b++;
						}
					}
					if (b < 2) {
						if (
							(px0y1 == GConst.SHALLOWOCEAN && px2y1 >= GConst.BEACH) ||
							(px2y1 == GConst.SHALLOWOCEAN && px0y1 >= GConst.BEACH) ||
							(px1y0 == GConst.SHALLOWOCEAN && px1y2 >= GConst.BEACH) ||
							(px1y2 == GConst.SHALLOWOCEAN && px1y0 >= GConst.BEACH)
						) {
							n_arr[n_idx] = GConst.BEACH;
						}
					}
				}
			}
		}

		return n_arr;
	}
}

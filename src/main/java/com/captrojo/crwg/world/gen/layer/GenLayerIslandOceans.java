package com.captrojo.crwg.world.gen.layer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerIslandOceans extends GenLayer
{
	private final int old_ocean, new_ocean;

	public GenLayerIslandOceans(long base_seed, GenLayer parent, int old_ocean, int new_ocean)
	{
		super(base_seed);
		this.parent = parent;
		this.old_ocean = old_ocean;
		this.new_ocean = new_ocean;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int p_ax = ax - 1;
		int p_ay = ay - 1;
		int p_aw = aw + 2;
		int p_ah = ah + 2;
		int[] p_arr = this.parent.getInts(p_ax, p_ay, p_aw, p_ah);

		final int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);

		for (int y = 0; y < ah; y++) {
			int yn = y * aw;
			int yp = (y + 1) * p_aw;
			for (int x = 0; x < aw; x++) {
				int p_idx = x + 1 + yp;
				int n_idx = x + yn;

				int top = p_arr[p_idx - p_aw];
				int bottom = p_arr[p_idx + p_aw];
				int left = p_arr[p_idx - 1];
				int right = p_arr[p_idx + 1];
				int center = p_arr[p_idx];
				
				n_arr[n_idx] = center;

				if (center != this.old_ocean) {
					continue;
				}
				if (
					top == GConst.ISLAND
					|| bottom == GConst.ISLAND
					|| left == GConst.ISLAND
					|| right == GConst.ISLAND
					|| top == GConst.MUSHROOM_ISLAND
					|| bottom == GConst.MUSHROOM_ISLAND
					|| left == GConst.MUSHROOM_ISLAND
					|| right == GConst.MUSHROOM_ISLAND
				) {
					n_arr[n_idx] = this.new_ocean;
				}
			}
		}

		return n_arr;
	}
}

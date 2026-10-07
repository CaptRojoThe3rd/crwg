package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerChangeLandTypeMulti extends GenLayer
{
	private final int[] old_types;
	private final int[] new_types;
	private final int[] rdens;
	private final int[] rnums;

	public GenLayerChangeLandTypeMulti(long base_seed, GenLayer parent, int[] old_types, int[] new_types, int[] rnums, int[] rdens)
	{
		super(base_seed);
		this.parent = parent;
		this.old_types = old_types;
		this.new_types = new_types;
		this.rnums = rnums;
		this.rdens = rdens;
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

				n_arr[n_idx] = center;

				this.initChunkSeed((long) (x + ax), (long) (y + ay));
				for (int i = 0; i < this.old_types.length; i++) {
					if (
						this.old_types[i] == top
						&& top == bottom
						&& bottom == left
						&& left == right
						&& right == center
					) {
						if (this.nextInt(this.rdens[i]) < this.rnums[i]) {
							n_arr[n_idx] = this.new_types[i];
						}
					}
				}
			}
		}

		return n_arr;
	}
}

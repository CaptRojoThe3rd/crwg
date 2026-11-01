package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLTInfectChecked extends GenLayer
{
	private final int type;
	private final int rnum;
	private final int rden;
	private final int bad_type;
	
	public GenLayerLTInfectChecked(long base_seed, GenLayer parent, int type, int rnum, int rden, int bad_type)
	{
		super(base_seed);
		this.parent = parent;
		this.type = type;
		this.rnum = rnum;
		this.rden = rden;
		this.bad_type = bad_type;
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

				int p = p_arr[p_idx];
				n_arr[n_idx] = p;
				
				if (p == this.bad_type) {
					continue;
				}
				
				int top = p_arr[p_idx - p_aw];
				int bottom = p_arr[p_idx + p_aw];
				int left = p_arr[p_idx - 1];
				int right = p_arr[p_idx + 1];
				
				if (this.type == top || this.type == bottom || this.type == left || this.type == right) {
					this.initChunkSeed((long) (x + ax), (long) (y + ay));
					if (this.nextInt(this.rden) < this.rnum) {
						n_arr[n_idx] = this.type;
					}
				}
			}
		}
		
		return n_arr;
	}
}

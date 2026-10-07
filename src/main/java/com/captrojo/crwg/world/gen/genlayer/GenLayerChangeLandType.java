package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerChangeLandType extends GenLayer
{
	private final int old_type;
	private final int new_type;
	private final int rden;
	private final int rnum;
	
	public GenLayerChangeLandType(long base_seed, GenLayer parent, int old_type, int new_type, int rnum, int rden)
	{
		super(base_seed);
		this.parent = parent;
		this.old_type = old_type;
		this.new_type = new_type;
		this.rnum = rnum;
		this.rden = rden;
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
				
				if (this.old_type == top && top == bottom && bottom == left && left == right && right == center) {
					this.initChunkSeed((long) (x + ax), (long) (y + ay));
					if (this.nextInt(this.rden) < this.rnum) {
						n_arr[n_idx] = this.new_type;
					}
				}
			}
		}
		
		return n_arr;
	}
}

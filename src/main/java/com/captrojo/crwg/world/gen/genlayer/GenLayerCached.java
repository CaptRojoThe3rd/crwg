package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCached extends GenLayer
{
	int[] cached_array;
	boolean available;
	
	public GenLayerCached(GenLayer parent)
	{
		super(1L);
		this.parent = parent;
	}
	
	public void reset()
	{
		this.available = false;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		if (this.available) {
			return this.cached_array;
		}
		
		int[] p_arr = this.parent.getInts(ax, ay, aw, ah);
		
		final int asize = aw * ah;
		this.cached_array = IntCache.getIntCache(asize);
		
		for (int i = 0; i < asize; i++) {
			this.cached_array[i] = p_arr[i];
		}
		
		this.available = true;
		return this.cached_array;
	}
}

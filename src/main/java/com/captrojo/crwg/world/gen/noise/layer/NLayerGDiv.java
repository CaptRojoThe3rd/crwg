package com.captrojo.crwg.world.gen.noise.layer;

import net.minecraft.world.gen.layer.GenLayer;

public class NLayerGDiv extends NLayer
{
	final GenLayer parent;
	double[] array;
	
	final double divider;
	
	public NLayerGDiv(GenLayer parent, double divider)
	{
		this.parent = parent;
		this.divider = divider;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		int[] i_arr = this.parent.getInts(ax, ay, aw, ah);
		
		if (this.array == null || this.array.length < asize) {
			this.array = new double[asize];
		}
		
		for (int i = 0; i < asize; i++) {
			double d = ((double) i_arr[i]) / this.divider;
			this.array[i] = d;
		}
		
		return this.array;
	}
}

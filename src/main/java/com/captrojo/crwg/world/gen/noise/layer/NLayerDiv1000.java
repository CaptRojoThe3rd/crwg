package com.captrojo.crwg.world.gen.noise.layer;

import net.minecraft.world.gen.layer.GenLayer;

public class NLayerDiv1000 extends NLayer
{
	final GenLayer parent;
	double[] array;
	
	public NLayerDiv1000(GenLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		int[] i_arr = this.parent.getInts(ax, ay, aw, ah);
		
		if (this.array == null || this.array.length < asize) {
			this.array = new double[asize];
		}
		
		for (int i = 0; i < asize; i++) {
			double d = ((double) i_arr[i]) / 1000.0;
			this.array[i] = Math.min(d, 1.0);
		}
		
		return this.array;
	}
}

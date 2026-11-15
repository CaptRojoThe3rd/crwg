package com.captrojo.crwg.world.gen.noise.layer;

import net.minecraft.world.gen.layer.GenLayer;

public class NLayerGDiv extends NLayer
{
	final GenLayer parent;
	double[] array;

	final double divider;

	/* for optimization purposes */
	public double total_encountered;

	public NLayerGDiv(GenLayer parent, double divider)
	{
		this.parent = parent;
		this.divider = divider;
	}

	public void resetEncountered()
	{
		this.total_encountered = 0.0;
	}

	public boolean didWeEncounterAnything()
	{
		return (this.total_encountered > 0.0);
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize, double[] selector)
	{
		int[] i_arr = this.parent.getInts(ax, ay, aw, ah);

		if (this.array == null || this.array.length < asize) {
			this.array = new double[asize];
		}

		this.total_encountered = 0.0;
		for (int i = 0; i < asize; i++) {
			double d = ((double) i_arr[i]) / this.divider;
			this.array[i] = d;
			this.total_encountered += d;
		}

		return this.array;
	}
}

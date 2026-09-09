package com.captrojo.crwg.world.genlayer;

public class LayerAdd extends LayerFork
{
	final double m;

	public LayerAdd(Layer parent_1, Layer parent_2, double m)
	{
		super(parent_1, parent_2);
		this.m = m;
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		double[] array_1 = this.parent_1.generate(ax, az, aw, ah, asize);
		double[] array_2 = this.parent_2.generate(ax, az, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			array_1[i] += (array_2[i] * this.m);
		}

		return array_1;
	}
}

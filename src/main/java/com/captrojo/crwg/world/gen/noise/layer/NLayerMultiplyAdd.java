package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerMultiplyAdd extends NLayer
{
	final NLayer parent;
	final double m;
	final double a;

	public NLayerMultiplyAdd(NLayer parent, double m, double a)
	{
		this.parent = parent;
		this.m = m;
		this.a = a;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			array[i] *= this.m;
			array[i] += this.a;
		}

		return array;
	}
}

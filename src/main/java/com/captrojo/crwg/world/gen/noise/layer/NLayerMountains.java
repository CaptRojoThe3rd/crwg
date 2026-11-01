package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerMountains extends NLayer
{
	final NLayer parent;

	public NLayerMountains(NLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array[i];

			d = d * 2.0 - 1.0;
			d = Math.abs(d);
			d = d * d;

			array[i] = d;
		}

		return array;
	}
}

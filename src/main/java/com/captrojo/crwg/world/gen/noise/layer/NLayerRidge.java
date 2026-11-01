package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerRidge extends NLayer
{
	final NLayer parent;

	public NLayerRidge(NLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array[i];

			d = 2.0 * d - 1.0;
			d = 1.0 - Math.abs(d);
			d = d * d * d * d;

			array[i] = d;
		}

		return array;
	}
}

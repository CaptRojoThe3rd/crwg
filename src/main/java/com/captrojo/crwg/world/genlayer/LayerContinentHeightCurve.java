package com.captrojo.crwg.world.genlayer;

public class LayerContinentHeightCurve extends LayerStraight
{
	public LayerContinentHeightCurve(Layer parent)
	{
		super(parent);
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, az, aw, ah, asize);

		final double b0 = 0.15;
		final double nb0 = -b0;
		final double b0a = b0 * 2.5;

		for (int i = 0; i < asize; i++) {
			double d = array[i];

			if (d > nb0) {
				d *= 0.5;
			} else {
				d = d * 3 + b0a;
				d = Math.max(-0.5, d);
			}

			array[i] = d;
		}

		return array;
	}
}

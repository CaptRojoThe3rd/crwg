package com.captrojo.crwg.world.genlayer;

public class LayerIncreaseOceans extends LayerStraight
{
	static final double a01 = -0.5;
	static final double a02 = 4.0 / 3.0;
	static final double a03 = 1.0 / 3.0;

	public LayerIncreaseOceans(Layer parent)
	{
		super(parent);
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, az, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array[i];

			if (d < a01) {
				d = -1.0;
			} else {
				d = a02 * d - a03;
			}

			array[i] = d;
		}

		return array;
	}
}

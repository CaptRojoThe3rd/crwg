package com.captrojo.crwg.world.genlayer;

public class LayerLandHeightTweaks extends LayerStraight
{
	static final double c01 = 0.16;
	static final double c02 = 0.20;
	static final double c03 = 0.6;
	static final double c04 = 0.64;

	Layer tweak_noise;
	final double m;

	public LayerLandHeightTweaks(Layer parent, Layer tweak_noise, double m)
	{
		super(parent);
		this.tweak_noise = tweak_noise;
		this.m = m;
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		double[] array_1 = this.parent.generate(ax, az, aw, ah, asize);
		double[] array_2 = this.tweak_noise.generate(ax, az, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array_1[i];
			double d2 = array_2[i] * this.m;

			/* piecewise hell */
			if (d2 < c01) {
				d2 = 0.1 * d2 + 1;
			} else if (d2 < c02) {
				d2 = 5 * (d2 - c01) + 1.016;
			} else if (d2 < c03) {
				d2 = 0.1 * d2 + 1.196;
			} else if (d2 < c04) {
				d2 = 7 * (d2 - c03) + 1.256;
			} else {
				d2 = 0.2 * d2 + 1.41;
			}

			array_1[i] = d * d2;
		}

		return array_1;
	}
}

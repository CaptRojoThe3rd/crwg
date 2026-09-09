package com.captrojo.crwg.world.genlayer;

public class LayerAddMountains extends LayerStraight
{
	static final double d00 = 5.0 / 3.0;

	Layer mountain_noise;
	final double mm;

	Layer extra_noise;
	final double me;

	public LayerAddMountains(Layer parent, Layer mountain_noise, double mm, Layer extra_noise, double me)
	{
		super(parent);

		this.mountain_noise = mountain_noise;
		this.mm = mm;

		this.extra_noise = extra_noise;
		this.me = me;
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, az, aw, ah, asize);
		double[] array2 = this.mountain_noise.generate(ax, az, aw, ah, asize);
		double[] array3 = this.extra_noise.generate(ax, az, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d2 = array2[i];
			if (d2 > 0) {
				d2 *= this.mm;
				array[i] += (d2 * d2 * d2 * d00) + (array3[i] * d2 * this.me);
			}
		}

		return array;
	}
}

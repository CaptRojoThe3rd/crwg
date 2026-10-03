package com.captrojo.crwg.world.gen.noise.layer;

import net.minecraft.util.MathHelper;

public class NLayerContinentToHeight extends NLayer
{
	static final double a00 = 0.15;
	static final double a00n = -a00;
	static final double a01 = 2.5 * a00;

	final NLayer parent;

	public NLayerContinentToHeight(NLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double n = array[i];

			if (n > a00) {
				n = ((double) MathHelper.sin((float) ((30.0 * (n - 0.2)))) / 30.0) + (0.5 * n) + 0.034;
			} else if (n < a00n) {
				n = Math.max(3 * n + a01, -0.5);
			} else {
				n = 0.5 * n;
			}

			array[i] = n;
		}

		return array;
	}
}

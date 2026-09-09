package com.captrojo.crwg.world.genlayer;

public class LayerRivers extends LayerStraight
{
	final Layer selector;

	public LayerRivers(Layer parent, Layer selector)
	{
		super(parent);

		this.selector = selector;
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		double[] array_1 = this.parent.generate(ax, az, aw, ah, asize);
		double[] array_2 = this.selector.generate(ax, az, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double g1 = array_2[i];
			double g2a = 0.8 * g1 + 1;
			double g2b = 1 - g2a;
			double g3 = g1 * 0.125;

			double d = array_1[i];
			d = (d * g2a) + (g3 * g2b);
			array_1[i] = d;
		}

		return array_1;
	}
}

package com.captrojo.crwg.world.genlayer;

public class LayerRiverSelector extends LayerStraight
{
	public double[] saved;

	final double m;

	public LayerRiverSelector(Layer parent, double m)
	{
		super(parent);
		this.m = m;
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, az, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array[i] * this.m;
			array[i] = -1 / (1 + (1000 * (d * d)));;
		}

		/* River selector noise is never modified later; we can cache it for biome gen */
		this.saved = array;
		return array;
	}
}

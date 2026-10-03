package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerFadeLessSteep extends NLayerFade
{
	public NLayerFadeLessSteep(NLayer parent)
	{
		super(parent);
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array[i];
			d = d * d * (3 - (2 * d));
			array[i] = d;
		}

		return array;
	}
}

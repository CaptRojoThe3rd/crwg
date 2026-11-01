package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerOddFade extends NLayer
{
	final NLayer parent;

	public NLayerOddFade(NLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array[i];
			d = (0.8 * d) + 0.2;
			d = ((6 * d - 15) * d + 10) * d * d * d;
			array[i] = d;
		}

		return array;
	}
}

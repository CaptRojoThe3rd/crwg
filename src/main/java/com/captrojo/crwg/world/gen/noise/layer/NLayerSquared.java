package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerSquared extends NLayer
{
	final NLayer parent;

	public NLayerSquared(NLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			array[i] = array[i] * array[i];
		}

		return array;
	}
}

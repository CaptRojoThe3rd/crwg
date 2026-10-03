package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerIncreaseOceans extends NLayer
{
	final NLayer parent;

	public NLayerIncreaseOceans(NLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			array[i] = Math.max(2 * (array[i] - 0.5), -1);
		}

		return array;
	}
}

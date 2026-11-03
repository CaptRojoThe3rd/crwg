package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerMountainMix extends NLayer
{
	final NLayer layer1, layer2;

	public NLayerMountainMix(NLayer layer1, NLayer layer2)
	{
		this.layer1 = layer1;
		this.layer2 = layer2;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array1 = this.layer1.generate(ax, ay, aw, ah, asize);
		double[] array2 = this.layer2.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d1 = array1[i];
			double d2 = array2[i];

			double d = Math.max(d1, d2);
			d = d * d;

			array1[i] = d;
		}

		return array1;
	}
}

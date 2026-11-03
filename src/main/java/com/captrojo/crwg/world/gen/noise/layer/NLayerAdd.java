package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerAdd extends NLayer
{
	final NLayer layer1;
	final NLayer layer2;

	public NLayerAdd(NLayer layer1, NLayer layer2)
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
			array1[i] += array2[i];
		}

		return array1;
	}
}

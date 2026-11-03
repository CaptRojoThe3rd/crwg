package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerMountainCap extends NLayer
{
	final NLayer parent;

	public NLayerMountainCap(NLayer parent)
	{
		this.parent = parent;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			double d = array[i];
			if (d < 3.5) {
				continue;
			}
			array[i] = Math.cbrt(d - 3.2) + 2.83056;
		}

		return array;
	}
}

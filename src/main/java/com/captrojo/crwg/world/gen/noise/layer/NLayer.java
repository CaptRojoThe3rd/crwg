package com.captrojo.crwg.world.gen.noise.layer;

public abstract class NLayer
{
	public abstract double[] generate(int ax, int ay, int aw, int ah, int asize);
	
	protected double[] createArrayIfNeeded(double[] array, int asize)
	{
		if (array == null || array.length < asize) {
			return new double[asize];
		}
		return array;
	}
}

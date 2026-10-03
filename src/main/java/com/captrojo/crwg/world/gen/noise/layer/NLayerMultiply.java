package com.captrojo.crwg.world.gen.noise.layer;

public class NLayerMultiply extends NLayer
{
	final NLayer parent;
	final double m;
	
	public NLayerMultiply(NLayer parent, double m)
	{
		this.parent = parent;
		this.m = m;
	}
	
	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		for (int i = 0; i < asize; i++) {
			array[i] *= this.m;
		}
		
		return array;
	}

}

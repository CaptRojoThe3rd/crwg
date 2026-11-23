package com.captrojo.crwg.world.gen.placement;

import java.util.Random;

import com.captrojo.crwg.util.RandUtil;

public class HeightProvTrapezoid implements HeightProvider
{
	private final int min;
	private final int max;
	private final int plateau;

	public HeightProvTrapezoid(final int min_height, final int max_height, final int plateau)
	{
		this.min = min_height;
		this.max = max_height;
		this.plateau = plateau;
	}

	@Override
	public int getHeight(final Random rand)
	{
		int range = this.max - this.min;
		int start = (range - this.plateau) / 2;
		int end = range - start;
		return this.min + RandUtil.betweenInclusive(rand, 0, end) + RandUtil.betweenInclusive(rand, 0, start);
	}
}

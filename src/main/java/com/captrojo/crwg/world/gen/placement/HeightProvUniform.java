package com.captrojo.crwg.world.gen.placement;

import java.util.Random;

import com.captrojo.crwg.util.RandUtil;

public class HeightProvUniform implements HeightProvider
{
	private final int min;
	private final int max;

	public HeightProvUniform(final int min_height, final int max_height)
	{
		this.min = min_height;
		this.max = max_height;
	}

	@Override
	public int getHeight(final Random rand)
	{
		return RandUtil.betweenInclusive(rand, this.min, this.max);
	}
}

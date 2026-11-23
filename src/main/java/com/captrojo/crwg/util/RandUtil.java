package com.captrojo.crwg.util;

import java.util.Random;

public abstract class RandUtil
{
	public static int betweenInclusive(final Random rand, final int min, final int max)
	{
		return rand.nextInt(max - min + 1) + min;
	}
}

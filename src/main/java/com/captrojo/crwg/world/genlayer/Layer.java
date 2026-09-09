package com.captrojo.crwg.world.genlayer;

public abstract class Layer
{
	public static long createSeed(long world_seed, long base_seed)
	{
		world_seed *= world_seed * 6364136223846793005L + 1442695040888963407L;
		world_seed += base_seed;
		world_seed *= world_seed * 6364136223846793005L + 1442695040888963407L;
		world_seed += base_seed;
		world_seed *= world_seed * 6364136223846793005L + 1442695040888963407L;
		world_seed += base_seed;
		return world_seed;
	}

	public Layer()
	{
	}

	public abstract double[] generate(int ax, int az, int aw, int ah, int asize);
}

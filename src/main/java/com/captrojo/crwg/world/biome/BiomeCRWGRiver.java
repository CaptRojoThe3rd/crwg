package com.captrojo.crwg.world.biome;

public class BiomeCRWGRiver extends BiomeCRWGBase
{
	public BiomeCRWGRiver(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);
		this.spawnableCreatureList.clear();
	}
}

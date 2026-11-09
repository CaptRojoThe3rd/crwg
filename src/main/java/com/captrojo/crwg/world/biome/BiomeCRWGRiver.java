package com.captrojo.crwg.world.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeCRWGRiver extends BiomeCRWGBase
{
	public BiomeCRWGRiver(int id, String name, int color, float temperature, float rainfall, boolean swampy)
	{
		super(id, name, color, temperature, rainfall);
		this.spawnableCreatureList.clear();

		if (swampy) {
			this.decorator.waterlilies = 2;
		}
	}

	@Override
	public WorldGenerator getWaterlilyGen(World world, int x, int y, int z, Random rand)
	{
		return rand.nextBoolean() ? BiomeCRWGBase.Gen_Waterlily : BiomeCRWGBase.Gen_Waterlily_Small;
	}
}

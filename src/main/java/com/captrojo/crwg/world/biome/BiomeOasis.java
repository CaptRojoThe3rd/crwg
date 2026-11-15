package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeOasis extends BiomeDesert
{
	public BiomeOasis(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.waterColorMultiplier = 0xa1fcf0;

		this.decorator.underwater_gravel = 0;
		this.decorator.underwater_sand = 3;
		this.decorator.underwater_clay = 1;

		this.decorator.grass_chance = 0.5F;

		this.decorator.trees = 2;

		this.decorator.cacti = 5;

		this.spawnableCreatureList.clear();
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return TreeGens.Palm;
	}
}

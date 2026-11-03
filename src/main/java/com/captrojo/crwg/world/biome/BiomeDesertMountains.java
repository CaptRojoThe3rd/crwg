package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDesertMountains extends BiomeCRWGBase
{
	public BiomeDesertMountains(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.decorator.grass = 1;
		this.decorator.grass_chance = 0.1f;
		this.decorator.flowers = 0;
		this.decorator.reeds = 3;
		this.decorator.cacti = 0;
		this.decorator.dead_bushes = 1;

		this.spawnableCreatureList.clear();
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		return BlockState.ORANGE_SAND;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		return BlockState.ORANGE_SANDSTONE;
	}

	@Override
	public WorldGenerator getGrassGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeDesert.GRASS_GENS[rand.nextInt(BiomeDesert.GRASS_GENS.length)];
	}
}

package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDesert extends BiomeCRWGBase
{
	static final WorldGenerator[] GRASS_GENS = {
		BiomeCRWGBase.Gen_ShortGrass_Dry_A,
		BiomeCRWGBase.Gen_ShortGrass_Dry_B,
		BiomeCRWGBase.Gen_ShortGrass_Dry_C,
		BiomeCRWGBase.Gen_ShortGrass_Dry_D
	};
	
	public BiomeDesert(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.decorator_lowlands.grass = 1;
		this.decorator_lowlands.grass_chance = 0.25f;
		this.decorator_lowlands.flowers = 0;
		this.decorator_lowlands.reeds = 50;
		this.decorator_lowlands.cacti = 10;
		this.decorator_lowlands.dead_bushes = 2;

		this.spawnableCreatureList.clear();
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		return BlockState.SAND;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		return BlockState.SAND;
	}

	@Override
	public WorldGenerator getGrassGen(World world, int x, int y, int z, Random rand)
	{
		return GRASS_GENS[rand.nextInt(GRASS_GENS.length)];
	}
}

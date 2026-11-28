package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDesertMountains extends BiomeCRWGBase
{
	public BiomeDesertMountains(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
		this.spawnableCreatureList.clear();
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.grass = 1;
		optns.grass_chance = 0.1F;
		optns.flowers = 0;
		optns.shrubs = 1;

		optns.reeds = 3;
		optns.cacti = 0;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		return BlockStates.ORANGE_SAND;
	}

	@Override
	public BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		return BlockStates.ORANGE_SANDSTONE;
	}

	@Override
	public WorldGenerator getGrassGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeDesert.GRASS_GENS[rand.nextInt(BiomeDesert.GRASS_GENS.length)];
	}

	@Override
	public WorldGenerator getBushGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_Bush_Dead;
	}
}

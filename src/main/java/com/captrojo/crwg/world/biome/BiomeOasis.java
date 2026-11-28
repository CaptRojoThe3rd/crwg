package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeOasis extends BiomeDesert
{
	public BiomeOasis(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
		this.waterColorMultiplier = 0xa1fcf0;
		this.spawnableCreatureList.clear();
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.underwater_gravel = 0;
		optns.underwater_sand = 3;
		optns.underwater_clay = 1;

		optns.grass_chance = 0.5F;

		optns.trees = 2;

		optns.cacti = 5;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return TreeGens.Palm;
	}
}

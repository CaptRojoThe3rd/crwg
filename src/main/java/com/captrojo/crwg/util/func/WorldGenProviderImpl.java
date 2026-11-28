package com.captrojo.crwg.util.func;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenProviderImpl implements TriArgProvider<World, Random, BlockPos, WorldGenerator>
{
	private final WorldGenerator generator;

	public WorldGenProviderImpl(final WorldGenerator gen)
	{
		this.generator = gen;
	}

	@Override
	public WorldGenerator get(final World world, final Random rand, final BlockPos origin)
	{
		return this.generator;
	}
}

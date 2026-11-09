package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.world.World;

public class BiomeFrozenWasteland extends BiomeWasteland
{
	public BiomeFrozenWasteland(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.decorator.lapis_amount *= 1.5;

		this.copyToHigherDecorators();
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		return BlockState.SNOW_BLOCK;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		return BlockState.SNOW_BLOCK;
	}

	@Override
	public BlockState getExtFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, BlockState filler)
	{
		return BlockState.GRAVEL;
	}
}

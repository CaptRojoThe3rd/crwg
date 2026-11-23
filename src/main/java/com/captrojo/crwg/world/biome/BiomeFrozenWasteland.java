package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;

public class BiomeFrozenWasteland extends BiomeWasteland
{
	public BiomeFrozenWasteland(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
	}

	@Override
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals
	)
	{
		return BlockStates.SNOW_BLOCK;
	}

	@Override
	public BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		return BlockStates.SNOW_BLOCK;
	}

	@Override
	public BlockState getExtFillerBlock(final World world, final Random rand, final GenVals vals, final BlockState filler)
	{
		return BlockStates.GRAVEL;
	}
}

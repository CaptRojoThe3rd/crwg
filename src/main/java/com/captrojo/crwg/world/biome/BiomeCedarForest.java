package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeCedarForest extends BiomeAbstractConiferous
{
	public BiomeCedarForest(
		final BiomeInfo info,
		final float temperature,
		final float rainfall,
		final boolean old_growth
	)
	{
		super(info, temperature, rainfall, old_growth);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (this.old_growth) {
			return TreeGens.Cedar_Big;
		}
		return TreeGens.Cedar_Small;
	}
}

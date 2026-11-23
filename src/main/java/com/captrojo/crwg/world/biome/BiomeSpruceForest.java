package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;

public class BiomeSpruceForest extends BiomeAbstractConiferous
{
	public BiomeSpruceForest(final BiomeInfo info, final float temperature, final float rainfall, final boolean old_growth)
	{
		super(info, temperature, rainfall, old_growth);
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return this.old_growth ? TreeGens.Spruce_Big : TreeGens.Spruce;
	}
}

package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;

public class BiomeVioletForest extends BiomeAbstractConiferous
{
	public BiomeVioletForest(final BiomeInfo info, final float temperature, final float rainfall, final boolean old_growth)
	{
		super(info, temperature, rainfall, old_growth);
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		super.initDecorators(world_seed);
		DecoratorOptions optns = this.decorator.optns_lowland;

		optns.flowers = 2;

		optns.trees = 6;
		optns.extra_tree_chance = 0.5F;
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return this.old_growth ? TreeGens.Violet_Big : TreeGens.Violet_Small;
	}

	@Override
	public Feature getFlowerGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_Plant_PurpleBush;
	}
}

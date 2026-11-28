package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

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
		DecoratorOptions optns = this.decorator.getOptions(null);

		optns.flowers = 2;

		optns.trees = 6;
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return this.old_growth ? TreeGens.Violet_Big : TreeGens.Violet_Small;
	}

	@Override
	public WorldGenerator getFlowerGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_Plant_PurpleBush;
	}
}

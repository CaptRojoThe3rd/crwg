package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;

public class BiomeVibrantForest extends BiomeCRWGBase
{
	public BiomeVibrantForest(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.trees = 8;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;

		if (rand.nextInt(7) == 0) {
			int s = world_crwg.crwg$getSubspeciesVal(origin.x, origin.z) & 3;
			switch (s) {
			case 0:
				return TreeGens.Spruce;
			case 1:
				return TreeGens.Pine_Small;
			case 2:
				return TreeGens.Cedar_Small;
			case 3:
				return TreeGens.Tamarack_Tiny;
			}
		}

		if (rand.nextInt(5) == 0) {
			return TreeGens.Birch;
		}

		return rand.nextBoolean() ? TreeGens.Oak_Big : TreeGens.Oak;
	}
}

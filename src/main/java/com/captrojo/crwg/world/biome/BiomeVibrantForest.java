package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeVibrantForest extends BiomeCRWGBase
{
	public BiomeVibrantForest(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;

		if (rand.nextInt(7) == 0) {
			int s = world_crwg.crwg$getSubspeciesVal(x, z) & 3;
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

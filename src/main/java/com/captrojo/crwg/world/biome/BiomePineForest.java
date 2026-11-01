package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomePineForest extends BiomeAbstractConiferous
{
	public BiomePineForest(int id, String name, int color, float temperature, float rainfall, boolean old_growth)
	{
		super(id, name, color, temperature, rainfall, old_growth);
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (this.old_growth) {
			return TreeGens.Pine_Big;
		}
		if (rand.nextBoolean()) {
			return TreeGens.Pine_Med;
		}
		return TreeGens.Pine_Small;
	}
}

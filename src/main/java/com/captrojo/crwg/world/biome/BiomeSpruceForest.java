package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeSpruceForest extends BiomeAbstractConiferous
{
	public BiomeSpruceForest(int id, String name, int color, float temperature, float rainfall, boolean old_growth)
	{
		super(id, name, color, temperature, rainfall, old_growth);
	}
	
	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return this.old_growth ? TreeGens.Spruce_Big : TreeGens.Spruce;
	}
}

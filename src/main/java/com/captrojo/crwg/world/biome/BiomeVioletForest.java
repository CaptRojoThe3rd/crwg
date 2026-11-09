package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeVioletForest extends BiomeAbstractConiferous
{
	public BiomeVioletForest(int id, String name, int color, float temperature, float rainfall, boolean old_growth)
	{
		super(id, name, color, temperature, rainfall, old_growth);

		this.decorator.flowers = 2;
		
		this.decorator.trees = 6;
		this.decorator.extra_tree_chance = 0.5F;

		this.copyToHigherDecorators();
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return this.old_growth ? TreeGens.Violet_Big : TreeGens.Violet_Small;
	}

	@Override
	public WorldGenerator getFlowerGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Plant_PurpleBush;
	}
}

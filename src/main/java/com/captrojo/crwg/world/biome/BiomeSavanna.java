package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeSavanna extends BiomeCRWGBase
{
	public BiomeSavanna(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.decorator.trees = 1;
		this.decorator.extra_tree_chance = 0.1F;

		this.decorator.flowers = 4;
		this.decorator.grass = 20;
		this.decorator.tall_grass = 7;

		this.copyToHigherDecorators();

		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(5) == 0) {
			return TreeGens.Oak;
		}
		return TreeGens.Acacia;
	}
}

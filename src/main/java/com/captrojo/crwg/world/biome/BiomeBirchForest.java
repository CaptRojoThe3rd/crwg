package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeBirchForest extends BiomeCRWGBase
{
	public static final int NORMAL = 0;
	public static final int CHAPARRAL = 1;
	public static final int OLD_GROWTH = 2;

	final boolean old_growth;

	public BiomeBirchForest(int id, String name, int color, float temperature, float rainfall, int type)
	{
		super(id, name, color, temperature, rainfall);

		this.old_growth = (type == OLD_GROWTH);
		
		this.decorator_lowlands.boulders = 1;
		this.decorator_lowlands.boulder_chance = 0.05f;

		if (type == NORMAL || type == OLD_GROWTH) {
			this.decorator_lowlands.trees = 8;
			this.decorator_lowlands.grass = 3;
			this.decorator_lowlands.flowers = 2;
			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		} else if (type == CHAPARRAL) {
			this.decorator_lowlands.trees = 1;
			this.decorator_lowlands.grass = 4;
			this.decorator_lowlands.flowers = 3;
		}
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (this.old_growth) {
			return rand.nextBoolean() ? TreeGens.Birch : TreeGens.Birch_Tall;
		}
		return TreeGens.Birch;
	}

	@Override
	public void decorate(World world, Random rand, int x0, int z0)
	{
		int tall_flowers = rand.nextInt(5) - 2;
		for (int i = 0; i < tall_flowers; i++) {
			WorldGenerator gen = rand.nextBoolean() ? BiomeCRWGBase.Gen_DPlant_Lilac : BiomeCRWGBase.Gen_DPlant_Peony;
			gen.generate(world, rand, x0, world.getHeightValue(x0, z0), z0);
		}

		super.decorate(world, rand, x0, z0);
	}
}

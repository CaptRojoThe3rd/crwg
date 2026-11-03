package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeOakForest extends BiomeCRWGBase
{
	static final WorldGenerator[] flower_forest_flowers = {
		BiomeCRWGBase.Gen_Flower_Poppy,
		BiomeCRWGBase.Gen_Flower_Allium,
		BiomeCRWGBase.Gen_Flower_Houstonia,
		BiomeCRWGBase.Gen_Flower_TulipRed,
		BiomeCRWGBase.Gen_Flower_TulipOrange,
		BiomeCRWGBase.Gen_Flower_TulipWhite,
		BiomeCRWGBase.Gen_Flower_TulipPink,
		BiomeCRWGBase.Gen_Flower_OxeyeDaisy,
		/* duplicate this one to make it more common */
		BiomeCRWGBase.Gen_Flower_WoodlandSage,
		BiomeCRWGBase.Gen_Flower_WoodlandSage
	};
	static final WorldGenerator[] flower_forest_double_plants = {
		BiomeCRWGBase.Gen_DPlant_Lilac,
		BiomeCRWGBase.Gen_DPlant_Peony,
		BiomeCRWGBase.Gen_DPlant_RoseBush,
		BiomeCRWGBase.Gen_DPlant_PurpleNightshade
	};

	public static final int NORMAL = 0;
	public static final int CHAPARRAL = 1;
	public static final int FLOWER = 2;
	public static final int FLOWER_CHAPARRAL = 3;

	final boolean chaparral;
	final boolean flower_forest;

	public BiomeOakForest(int id, String name, int color, float temperature, float rainfall, int type)
	{
		super(id, name, color, temperature, rainfall);

		this.chaparral = (type == CHAPARRAL || type == FLOWER_CHAPARRAL);
		this.flower_forest = (type == FLOWER || type == FLOWER_CHAPARRAL);

		this.decorator.boulders = 1;
		this.decorator.boulder_chance = 0.05F;

		if (type == NORMAL) {
			this.decorator.trees = 10;
			this.decorator.grass = 12;
			this.decorator.flowers = 3;

			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		} else if (type == CHAPARRAL) {
			this.decorator.trees = 1;
			this.decorator.grass = 8;
			this.decorator.flowers = 5;
		} else if (type == FLOWER) {
			this.decorator.trees = 7;
			this.decorator.grass = 2;
			this.decorator.flowers = 60;

			this.flowers.clear();
			for (int i = 0; i < BlockFlower.field_149859_a.length; i++) {
				this.addFlower(Blocks.red_flower, i == 1 ? 0 : i, 10);
			}

			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 2, 3));
		} else if (type == FLOWER_CHAPARRAL) {
			this.decorator.trees = 1;
			this.decorator.grass = 2;
			this.decorator.flowers = 25;

			this.flowers.clear();
			for (int i = 0; i < BlockFlower.field_149859_a.length; i++) {
				this.addFlower(Blocks.red_flower, i == 1 ? 0 : i, 10);
			}
		}
		
		this.decorator_hills.copyFrom(this.decorator);
		this.decorator_highlands.copyFrom(this.decorator);
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (this.chaparral) {
			return (rand.nextInt(10) == 0) ? TreeGens.Oak_Big : TreeGens.Oak;
		}
		return TreeGens.Oak_Big;
	}

	@Override
	public WorldGenerator getFlowerGen(World world, int x, int y, int z, Random rand)
	{
		if (this.flower_forest) {
			double d0 = (1.0 + BiomeGenBase.plantNoise.func_151601_a((double) x / 96.0, (double) z / 96.0)) / 2.0;
			d0 = MathHelper.clamp_double(d0, 0.0, 0.9999);
			int i0 = (int) (d0 * (double) flower_forest_flowers.length);
			return flower_forest_flowers[i0];
		}
		if (rand.nextBoolean()) {
			return BiomeCRWGBase.Gen_Flower_WoodlandSage;
		}
		return super.getFlowerGen(world, x, y, z, rand);
	}

	@Override
	public WorldGenerator getGrassGen(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(4) == 0) {
			return BiomeCRWGBase.Gen_TallGrass;
		}
		if (rand.nextInt(3) == 0) {
			return BiomeCRWGBase.Gen_Clover_Large;
		}
		return BiomeCRWGBase.Gen_Clover;
	}

	@Override
	public void decorate(World world, Random rand, int x0, int z0)
	{
		int tall_flowers = rand.nextInt(5) - 3;
		if (this.flower_forest) {
			tall_flowers += 2;
		}
		for (int i = 0; i < tall_flowers; i++) {
			int f = rand.nextInt(flower_forest_double_plants.length);
			flower_forest_double_plants[f].generate(world, rand, x0, world.getHeightValue(x0, z0), z0);
		}

		super.decorate(world, rand, x0, z0);
	}
}

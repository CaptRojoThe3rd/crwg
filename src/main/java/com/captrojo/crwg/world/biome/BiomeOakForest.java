package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeOakForest extends BiomeCRWGBase
{
	private static final WorldGenFlowers[] FLOWER_FOREST_FLOWERS_A = {
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

	private static final WorldGenFlowers[] FLOWER_FOREST_FLOWERS_B = {
		BiomeCRWGBase.Gen_Flower_Sage,
		BiomeCRWGBase.Gen_Flower_WoodlandSage,

		BiomeCRWGBase.Gen_Flower_AquaHyacinth,
		BiomeCRWGBase.Gen_Flower_CreamHyacinth,
		BiomeCRWGBase.Gen_Flower_CrimsonHyacinth,
		BiomeCRWGBase.Gen_Flower_DeepBlueHyacinth,
		BiomeCRWGBase.Gen_Flower_PinkHyacinth,
		BiomeCRWGBase.Gen_Flower_SalmonPinkHyacinth
	};

	private static final WorldGenerator[] FLOWER_FOREST_DOUBLE_PLANTS = {
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
			this.decorator.flowers = 3;
			this.decorator.grass = 12;

			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		} else if (type == CHAPARRAL) {
			this.decorator.trees = 1;
			this.decorator.flowers = 5;
			this.decorator.grass = 8;
		} else if (type == FLOWER) {
			this.decorator.trees = 7;
			this.decorator.flowers = 60;
			this.decorator.grass = 2;

			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 2, 3));
		} else if (type == FLOWER_CHAPARRAL) {
			this.decorator.trees = 1;
			this.decorator.flowers = 25;
			this.decorator.grass = 2;
		}

		if (type == FLOWER || type == FLOWER_CHAPARRAL) {
			this.flowers.clear();
			for (int i = 0; i < BlockFlower.field_149859_a.length; i++) {
				this.addFlower(Blocks.red_flower, i == 1 ? 0 : i, 10);
			}
			this.addFlower(ModBlocks.flower_woodland_sage, 0, 10);
		}

		this.copyToHigherDecorators();
	}

	private WorldGenFlowers[] getFlowerArray(World world, int x, int y, int z)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;
		int s = world_crwg.crwg$getSubspeciesVal(x, z);
		return ((s & 1) == 0) ? FLOWER_FOREST_FLOWERS_A : FLOWER_FOREST_FLOWERS_B;
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
			WorldGenerator[] flower_arr = this.getFlowerArray(world, x, y, z);
			double d = (1.0 + BiomeGenBase.plantNoise.func_151601_a((double) x / 96.0, (double) z / 96.0)) / 2.0;
			d = MathHelper.clamp_double(d, 0.0, 0.9999);
			int i = (int) (d * (double) flower_arr.length);
			return flower_arr[i];
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
	public void decorate(World world, Random rand, int cx0, int cz0)
	{
		int tall_flowers = rand.nextInt(5) - 3;
		if (this.flower_forest) {
			tall_flowers += 2;
		}
		for (int i = 0; i < tall_flowers; i++) {
			int f = rand.nextInt(FLOWER_FOREST_DOUBLE_PLANTS.length);
			FLOWER_FOREST_DOUBLE_PLANTS[f].generate(world, rand, cx0, world.getHeightValue(cx0, cz0), cz0);
		}
		super.decorate(world, rand, cx0, cz0);
	}

	@Override
	public void plantFlower(World world, Random rand, int x, int y, int z)
	{
		if (!this.flower_forest) {
			super.plantFlower(world, rand, x, y, z);
			return;
		}
		WorldGenFlowers[] flower_arr = this.getFlowerArray(world, x, y, z);
		WorldGenFlowers flower = flower_arr[rand.nextInt(flower_arr.length)];
		if (flower.field_150552_a.canBlockStay(world, x, y, z)) {
			world.setBlock(x, y, z, flower.field_150552_a, 0, 3);
		}
	}
}

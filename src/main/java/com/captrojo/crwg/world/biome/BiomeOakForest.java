package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.AbstractFeature;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.FeatureDoublePlant;
import com.captrojo.crwg.world.gen.feature.FeatureFlowers;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeOakForest extends BiomeCRWGBase
{
	private static final FeatureFlowers[] FLOWER_FOREST_FLOWERS_A = {
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

	private static final FeatureFlowers[] FLOWER_FOREST_FLOWERS_B = {
		BiomeCRWGBase.Gen_Flower_Sage,
		BiomeCRWGBase.Gen_Flower_WoodlandSage,

		BiomeCRWGBase.Gen_Flower_AquaHyacinth,
		BiomeCRWGBase.Gen_Flower_CreamHyacinth,
		BiomeCRWGBase.Gen_Flower_CrimsonHyacinth,
		BiomeCRWGBase.Gen_Flower_DeepBlueHyacinth,
		BiomeCRWGBase.Gen_Flower_PinkHyacinth,
		BiomeCRWGBase.Gen_Flower_SalmonPinkHyacinth
	};

	private static final FeatureDoublePlant[] FLOWER_FOREST_DOUBLE_PLANTS = {
		BiomeCRWGBase.Gen_DPlant_Lilac,
		BiomeCRWGBase.Gen_DPlant_Peony,
		BiomeCRWGBase.Gen_DPlant_RoseBush,
		BiomeCRWGBase.Gen_DPlant_PurpleNightshade
	};

	public static final byte NORMAL = 0;
	public static final byte CHAPARRAL = 1;
	public static final byte FLOWER = 2;
	public static final byte FLOWER_CHAPARRAL = 3;

	private final int type;
	final boolean chaparral;
	final boolean flower_forest;

	public BiomeOakForest(final BiomeInfo info, final float temperature, final float rainfall, final byte type)
	{
		super(info, temperature, rainfall);
		this.type = type;

		this.chaparral = (type == CHAPARRAL || type == FLOWER_CHAPARRAL);
		this.flower_forest = (type == FLOWER || type == FLOWER_CHAPARRAL);

		if (type == NORMAL) {
			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		} else if (type == FLOWER) {
			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 2, 3));
		}

		if (type == FLOWER || type == FLOWER_CHAPARRAL) {
			this.flowers.clear();
			for (int i = 0; i < BlockFlower.field_149859_a.length; i++) {
				this.addFlower(Blocks.red_flower, i == 1 ? 0 : i, 10);
			}
			this.addFlower(ModBlocks.flower_woodland_sage, 0, 10);
		}
	}

	private FeatureFlowers[] getFlowerArray(final World world, final int x, final int y, final int z)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;
		int s = world_crwg.crwg$getSubspeciesVal(x, z);
		return ((s & 1) == 0) ? FLOWER_FOREST_FLOWERS_A : FLOWER_FOREST_FLOWERS_B;
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.boulders = 1;
		optns.boulder_chance = 0.05F;

		if (this.type == NORMAL) {
			optns.trees = 10;
			optns.flowers = 3;
			optns.grass = 12;
		} else if (this.type == CHAPARRAL) {
			optns.trees = 1;
			optns.flowers = 5;
			optns.grass = 8;
		} else if (this.type == FLOWER) {
			optns.trees = 7;
			optns.flowers = 60;
			optns.grass = 2;
		} else if (this.type == FLOWER_CHAPARRAL) {
			optns.trees = 1;
			optns.flowers = 25;
			optns.grass = 2;
		}

		this.decorator.setAllOptions(optns);
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (this.chaparral) {
			return (rand.nextInt(10) == 0) ? TreeGens.Oak_Big : TreeGens.Oak;
		}
		return TreeGens.Oak_Big;
	}

	@Override
	public Feature getFlowerGen(final World world, final Random rand, final BlockPos origin)
	{
		if (this.flower_forest) {
			AbstractFeature[] flower_arr = this.getFlowerArray(world, origin.x, origin.y, origin.z);
			double d = BiomeGenBase.plantNoise.func_151601_a((double) origin.x / 96.0, (double) origin.z / 96.0);
			d = (d + 1.0) / 2.0;
			d = MathHelper.clamp_double(d, 0.0, 0.9999);
			int i = (int) (d * (double) flower_arr.length);
			return flower_arr[i];
		}
		if (rand.nextBoolean()) {
			return BiomeCRWGBase.Gen_Flower_WoodlandSage;
		}
		return super.getFlowerGen(world, rand, origin);
	}

	@Override
	public Feature getGrassGen(final World world, final Random rand, final BlockPos origin)
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
		final BlockPos pos = new BlockPos(cx0 + 8, world.getHeightValue(cx0 + 8, cz0 + 8), cz0 + 8);
		int tall_flowers = rand.nextInt(5) - 3;
		if (this.flower_forest) {
			tall_flowers += 2;
		}
		for (int i = 0; i < tall_flowers; i++) {
			int f = rand.nextInt(FLOWER_FOREST_DOUBLE_PLANTS.length);
			FLOWER_FOREST_DOUBLE_PLANTS[f].generate(world, rand, pos);
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
		FeatureFlowers[] flower_arr = this.getFlowerArray(world, x, y, z);
		FeatureFlowers flower = flower_arr[rand.nextInt(flower_arr.length)];
		if (flower.block.canBlockStay(world, x, y, z)) {
			world.setBlock(x, y, z, flower.block, flower.meta, 3);
		}
	}
}

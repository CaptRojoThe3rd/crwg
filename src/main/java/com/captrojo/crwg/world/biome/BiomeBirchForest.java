package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.WorldGenFeature;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeBirchForest extends BiomeCRWGBase
{
	static final byte NORMAL = 0;
	static final byte CHAPARRAL = 1;
	static final byte OLD_GROWTH = 2;

	private final int type;

	public BiomeBirchForest(final BiomeInfo info, final float temperature, final float rainfall, final byte type)
	{
		super(info, temperature, rainfall);
		this.type = type;

		if (type == NORMAL || type == OLD_GROWTH) {
			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
		}
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.boulders = 1;
		optns.boulder_chance = 0.05F;

		if (this.type == NORMAL || this.type == OLD_GROWTH) {
			optns.trees = 8;
			optns.grass = 3;
			optns.flowers = 2;
		} else {
			optns.trees = 1;
			optns.grass = 4;
			optns.flowers = 3;
		}

		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (this.type == OLD_GROWTH) {
			return rand.nextBoolean() ? TreeGens.Birch : TreeGens.Birch_Tall;
		}
		return TreeGens.Birch;
	}

	@Override
	public void decorate(final World world, final Random rand, final int cx0, final int cz0)
	{
		int tall_flowers = rand.nextInt(5) - 2;
		for (int i = 0; i < tall_flowers; i++) {
			WorldGenFeature gen = rand.nextBoolean() ? BiomeCRWGBase.Gen_DPlant_Lilac : BiomeCRWGBase.Gen_DPlant_Peony;
			gen.generate(world, rand, cx0 + 8, 0, cz0 + 8);
		}

		super.decorate(world, rand, cx0, cz0);
	}
}

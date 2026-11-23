package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeSavanna extends BiomeCRWGBase
{
	public BiomeSavanna(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.flowers = 4;
		optns.grass = 20;
		optns.tall_grass = 7;

		optns.trees = 1;
		optns.extra_tree_chance = 0.1F;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (rand.nextInt(5) == 0) {
			return TreeGens.Oak;
		}
		return TreeGens.Acacia;
	}
}

package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.world.World;

public class BiomeFen extends BiomeAbstractSwamp
{
	private final Feature tree_1;
	private final Feature tree_2;
	private final Feature shrub;
	private final int grass_color;
	private final int foliage_color;

	public BiomeFen(final BiomeInfo info, final float temperature, final float rainfall, final int type)
	{
		super(info, temperature, rainfall);

		if (type == 0) {
			this.tree_1 = TreeGens.Spruce_Tiny;
			this.tree_2 = TreeGens.Cedar_Tiny;
			this.shrub = TreeGens.Cedar_Shrub;
			this.grass_color = 0x9bb759;
			this.foliage_color = 0x88b759;
		} else {
			this.tree_1 = TreeGens.Tamarack_Tiny;
			this.tree_2 = TreeGens.Pine_Tiny;
			this.shrub = TreeGens.Pine_Shrub;
			this.grass_color = 0xb7b459;
			this.foliage_color = 0xabb759;
		}

		this.waterlily_chance = 0.05;
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		super.initDecorators(world_seed);
		DecoratorOptions optns = this.decorator.optns_lowland;

		optns.flowers = 0;
		optns.grass = 10;
		optns.tall_grass = 30;

		optns.trees = 6;

		optns.reeds = 0;
		optns.pumpkins = 1;
		optns.pumpkin_chance = 0.01F;
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (rand.nextInt(12) == 0) {
			return this.tree_1;
		}
		if (rand.nextInt(6) == 0) {
			return this.tree_2;
		}
		return this.shrub;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(final int x, final int y, final int z)
	{
		return this.grass_color;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(final int x, final int y, final int z)
	{
		return this.foliage_color;
	}
}

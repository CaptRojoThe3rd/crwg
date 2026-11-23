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
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeMarsh extends BiomeAbstractSwamp
{
	final int grass_color;
	final int foliage_color;

	public BiomeMarsh(final BiomeInfo info, final float temperature, final float rainfall, final int type)
	{
		super(info, temperature, rainfall);

		if (type == 0) {
			this.grass_color = 0x708440;
			this.foliage_color = 0x628440;
		} else {
			this.grass_color = 0x848240;
			this.foliage_color = 0x7b8440;
		}
	}

	private int getTreeCount(final World world, final int cx0, final int cz0, final Random rand)
	{
		double d = BiomeGenBase.plantNoise.func_151601_a((double) cx0 / 256.0, (double) cz0 / 256.0);
		if (d < -0.333) {
			return 7;
		}
		return 0;
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		super.initDecorators(world_seed);
		DecoratorOptions optns = this.decorator.optns_lowland;

		optns.grass = 10;
		optns.tall_grass = 30;

		optns.sup_trees = this::getTreeCount;
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return TreeGens.Willow_Black;
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

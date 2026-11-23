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

public class BiomeCypressSwamp extends BiomeAbstractSwamp
{
	public BiomeCypressSwamp(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
		this.waterColorMultiplier = 0x9ffc4e;
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		super.initDecorators(world_seed);
		DecoratorOptions optns = this.decorator.optns_lowland;
		optns.trees = 4;
		optns.grass = 6;
		optns.flowers = 0;
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return TreeGens.Bald_Cypress;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(final int x, final int y, final int z)
	{
		double d0 = BiomeGenBase.plantNoise.func_151601_a((double) x * 0.01, (double) z * 0.01);
		return (d0 < -0.1) ? 0x428c32 : 0x6f9e50;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(final int x, final int y, final int z)
	{
		return 0x6f9e50;
	}
}

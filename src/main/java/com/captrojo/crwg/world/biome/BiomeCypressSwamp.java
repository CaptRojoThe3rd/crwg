package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeCypressSwamp extends BiomeAbstractSwamp
{
	public BiomeCypressSwamp(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.waterColorMultiplier = 0x9ffc4e;

		this.decorator_lowlands.trees = 4;
		this.decorator_lowlands.grass = 6;
		this.decorator_lowlands.flowers = 0;
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return TreeGens.Bald_Cypress;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double) x * 0.01, (double) z * 0.01);
		return (d0 < -0.1) ? 0x428c32 : 0x6f9e50;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 0x6f9e50;
	}
}

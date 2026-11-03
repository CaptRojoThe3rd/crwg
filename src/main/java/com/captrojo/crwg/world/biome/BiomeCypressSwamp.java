package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeCypressSwamp extends BiomeAbstractSwamp
{
	public BiomeCypressSwamp(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.waterColorMultiplier = 0x9ffc4e;

		this.decorator.trees = 4;
		this.decorator.grass = 6;
		this.decorator.flowers = 0;

		this.decorator_hills.copyFrom(this.decorator);
		this.decorator_highlands.copyFrom(this.decorator);
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
		double d0 = BiomeGenBase.plantNoise.func_151601_a((double) x * 0.01, (double) z * 0.01);
		return (d0 < -0.1) ? 0x428c32 : 0x6f9e50;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 0x6f9e50;
	}
}

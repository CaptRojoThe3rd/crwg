package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeMarsh extends BiomeAbstractSwamp
{
	final int grass_color;
	final int foliage_color;

	public BiomeMarsh(int id, String name, int color, float temperature, float rainfall, int type)
	{
		super(id, name, color, temperature, rainfall);

		if (type == 0) {
			this.grass_color = 0x708440;
			this.foliage_color = 0x628440;
		} else {
			this.grass_color = 0x848240;
			this.foliage_color = 0x7b8440;
		}

		this.decorator.sup_trees = this::getTreeCount;

		this.decorator.grass = 10;
		this.decorator.tall_grass = 30;
	}

	private int getTreeCount(World world, int x0, int z0, Random rand)
	{
		double d = BiomeGenBase.plantNoise.func_151601_a((double) x0 / 256.0, (double) z0 / 256.0);
		if (d < -0.333) {
			return 7;
		}
		return 0;
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return TreeGens.Willow_Black;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return this.grass_color;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return this.foliage_color;
	}
}

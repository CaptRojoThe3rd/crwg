package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeFen extends BiomeAbstractSwamp
{
	final WorldGenAbstractTree tree_1;
	final WorldGenAbstractTree tree_2;
	final WorldGenAbstractTree shrub;
	final int grass_color;
	final int foliage_color;

	public BiomeFen(int id, String name, int color, float temperature, float rainfall, int type)
	{
		super(id, name, color, temperature, rainfall);

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

		this.decorator.trees = 6;

		this.decorator.flowers = 0;
		this.decorator.grass = 10;
		this.decorator.tall_grass = 30;

		this.decorator.reeds = 0;
		this.decorator.pumpkins = 1;
		this.decorator.pumpkin_chance = 0.01F;
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
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

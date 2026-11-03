package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeFen extends BiomeAbstractSwamp
{
	public BiomeFen(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

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
			return TreeGens.Spruce_Tiny;
		}
		if (rand.nextInt(6) == 0) {
			return TreeGens.Cedar_Tiny;
		}
		return TreeGens.Cedar_Shrub;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 0xabb759;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 0x98b759;
	}
}

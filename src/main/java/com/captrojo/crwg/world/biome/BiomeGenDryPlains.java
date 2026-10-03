package com.captrojo.crwg.world.biome;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenDryPlains extends BiomeGenBase
{
	final int grass_color;
	final int foliage_color;
	
	public BiomeGenDryPlains(int id, int grass_color, int foliage_color, float temperature, String name)
	{
		super(id);
		this.grass_color = grass_color;
		this.foliage_color = foliage_color;
		
		this.setBiomeName(name);
	        this.setHeight(height_LowPlains);
	        this.setTemperatureRainfall(temperature, 0.075f);
		this.setColor(0xc9b37a);

		this.spawnableCreatureList.clear();
		this.flowers.clear();

	        this.theBiomeDecorator.treesPerChunk = -999;
	        this.theBiomeDecorator.flowersPerChunk = -1;
	        this.theBiomeDecorator.grassPerChunk = 10;
	}
	
	@Override
	public void decorate(World world, Random rand, int x, int z)
	{
		super.decorate(world, rand, x, z);
	}
	
	@Override
	public void plantFlower(World world, Random rand, int x, int y, int z)
	{
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

package com.captrojo.crwg.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

		this.decorator.trees = 0;

		this.decorator.grass = 10;
		this.decorator.tall_grass = 30;
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

package com.captrojo.crwg.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeBog extends BiomeAbstractSwamp
{
	final int grass_color;
	final int foliage_color;

	public BiomeBog(int id, String name, int color, float temperature, float rainfall, int type)
	{
		super(id, name, color, temperature, rainfall);

		if (type == 0) {
			this.grass_color = 0xaad14f;
			this.foliage_color = 0xc4d14f;
		} else {
			this.grass_color = 0x93772a;
			this.foliage_color = 0x93852a;
		}

		this.decorator.flowers = 0;
		this.decorator.grass = 20;

		this.decorator.reeds = 0;
		this.decorator.pumpkins = 0;
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

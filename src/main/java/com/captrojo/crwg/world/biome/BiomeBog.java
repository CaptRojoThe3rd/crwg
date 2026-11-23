package com.captrojo.crwg.world.biome;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeBog extends BiomeAbstractSwamp
{
	private final int grass_color;
	private final int foliage_color;

	public BiomeBog(
		final BiomeInfo info,
		final float temperature,
		final float rainfall,
		final int type
	)
	{
		super(info, temperature, rainfall);

		if (type == 0) {
			this.grass_color = 0xaad14f;
			this.foliage_color = 0xc4d14f;
		} else {
			this.grass_color = 0x93772a;
			this.foliage_color = 0x93852a;
		}
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		super.initDecorators(world_seed);
		DecoratorOptions optns = this.decorator.optns_lowland;

		optns.flowers = 0;
		optns.grass = 20;

		optns.reeds = 0;
		optns.pumpkins = 0;
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

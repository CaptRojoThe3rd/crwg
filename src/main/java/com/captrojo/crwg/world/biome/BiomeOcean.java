package com.captrojo.crwg.world.biome;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeOcean extends BiomeCRWGBase
{
	public BiomeOcean(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
		this.spawnableCreatureList.clear();
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.grass = 0;
		optns.flowers = 0;
		optns.reeds = 0;
		optns.pumpkins = 0;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public BiomeGenBase.TempCategory getTempCategory()
	{
		return BiomeGenBase.TempCategory.OCEAN;
	}
}

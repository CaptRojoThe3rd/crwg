package com.captrojo.crwg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCRWGOcean extends BiomeCRWGBase
{
	public BiomeCRWGOcean(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);
		this.spawnableCreatureList.clear();
		
		this.decorator_lowlands.grass = 0;
		this.decorator_lowlands.flowers = 0;
		this.decorator_lowlands.reeds = 0;
		this.decorator_lowlands.pumpkins = 0;
		
		this.decorator_lowlands.dirt_blob_amount = 10;
		this.decorator_lowlands.dirt_blob_max_y = 128;
		this.decorator_lowlands.gravel_blob_amount = 5;
		this.decorator_lowlands.gravel_blob_max_y = 128;
	}

	@Override
	public BiomeGenBase.TempCategory getTempCategory()
	{
		return BiomeGenBase.TempCategory.OCEAN;
	}
}

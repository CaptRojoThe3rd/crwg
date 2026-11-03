package com.captrojo.crwg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCRWGOcean extends BiomeCRWGBase
{
	public BiomeCRWGOcean(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);
		this.spawnableCreatureList.clear();
		
		this.decorator.grass = 0;
		this.decorator.flowers = 0;
		this.decorator.reeds = 0;
		this.decorator.pumpkins = 0;
		
		this.decorator.dirt_blob_amount = 10;
		this.decorator.dirt_blob_max_y = 128;
		this.decorator.gravel_blob_amount = 5;
		this.decorator.gravel_blob_max_y = 128;
	}

	@Override
	public BiomeGenBase.TempCategory getTempCategory()
	{
		return BiomeGenBase.TempCategory.OCEAN;
	}
}

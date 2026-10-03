package com.captrojo.crwg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * A helper class for overriding biome temperature and rainfall values.
 * Used for fixing that data for use in GenLayerBiomes.
 */
public class BiomeAndClimate
{
	public final BiomeGenBase biome;
	public final float temperature;
	public final float rainfall;
	
	public BiomeAndClimate(BiomeGenBase biome)
	{
		this(biome, biome.temperature, biome.rainfall);
	}
	
	public BiomeAndClimate(BiomeGenBase biome, float temperature, float rainfall)
	{
		this.biome = biome;
		this.temperature = temperature;
		this.rainfall = rainfall;
	}
}

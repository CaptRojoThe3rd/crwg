package com.captrojo.crwg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Provides references for mutated biomes, since vanilla doesn't seem to have that.
 */
public abstract class MutatedBiomes
{
	public static final BiomeGenBase Sunflower_Plains = MutatedBiomes.getMutatedOf(BiomeGenBase.plains);
	
	public static final BiomeGenBase Ice_Spikes = MutatedBiomes.getMutatedOf(BiomeGenBase.icePlains);
	public static final BiomeGenBase Ice_Spike_Mountains = MutatedBiomes.getMutatedOf(BiomeGenBase.iceMountains);
	
	public static BiomeGenBase getMutatedOf(BiomeGenBase biome)
	{
		return BiomeGenBase.getBiome(biome.biomeID + 128);
	}
}

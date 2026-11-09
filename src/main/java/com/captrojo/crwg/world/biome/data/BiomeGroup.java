package com.captrojo.crwg.world.biome.data;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGroup
{
	BiomeGenBase plains;
	BiomeGenBase mountain;
	BiomeGenBase plateau;

	public BiomeGroup(
		BiomeGenBase plains,
		BiomeGenBase mountain,
		BiomeGenBase plateau
	)
	{
		this.plains = plains;
		this.mountain = mountain;
		this.plateau = plateau;
	}

	public BiomeGenBase getFromLandType(LandType type)
	{
		switch (type) {
		default:
		case LOWLANDS:
		case HILLS:
		case HIGHLANDS:
		case FOOTHILLS:
			return this.plains;
		case MOUNTAINS:
			return this.mountain;
		case PLATEAUS:
			return this.plateau;
		}
	}
}

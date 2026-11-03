package com.captrojo.crwg.world.biome.data;

import com.captrojo.crwg.world.gen.data.GConst;

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
	
	public BiomeGenBase getFromLandType(int type)
	{
		switch (type) {
		default:
		case GConst.LOWLANDS:
		case GConst.HILLS:
		case GConst.HIGHLANDS:
		case GConst.FOOTHILLS:
			return this.plains;
		case GConst.MOUNTAINS:
			return this.mountain;
		case GConst.PLATEAUS:
			return this.plateau;
		}
	}
}

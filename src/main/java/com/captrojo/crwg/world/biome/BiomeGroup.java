package com.captrojo.crwg.world.biome;

import com.captrojo.crwg.world.gen.genlayer.GConst;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGroup
{
	BiomeGenBase lowland;
	BiomeGenBase hills;
	BiomeGenBase highland;
	BiomeGenBase mountain;
	BiomeGenBase plateau;
	
	public BiomeGroup(
		BiomeGenBase lowland,
		BiomeGenBase hills,
		BiomeGenBase highland,
		BiomeGenBase mountain,
		BiomeGenBase plateau
	)
	{
		this.lowland = lowland;
		this.hills = hills;
		this.highland = highland;
		this.mountain = mountain;
		this.plateau = plateau;
	}
	
	public BiomeGenBase getFromHeightType(int type)
	{
		switch (type) {
		default:
		case GConst.LOWLANDS:
			return this.lowland;
		case GConst.HILLS:
			return this.hills;
		case GConst.HIGHLANDS:
			return this.highland;
		case GConst.MOUNTAINS:
			return this.mountain;
		case GConst.PLATEAUS:
			return this.plateau;
		}
	}
}

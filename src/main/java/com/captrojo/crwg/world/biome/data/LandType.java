package com.captrojo.crwg.world.biome.data;

/**
 * Replacement for GConst.LOWLANDS, GConst.HILLS, etc.
 */
public enum LandType
{
	NULL("Null"),
	
	DEEP_OCEAN("Deep Ocean"),
	OCEAN("Ocean"),
	SHALLOW_OCEAN("Shallow Ocean"),
	BEACH("Beach"),
	
	LOWLANDS("Lowlands"),
	HILLS("Hills"),
	HIGHLANDS("Highlands"),
	MOUNTAINS("Mountains"),
	
	PLATEAUS("Plateaus"),
	FOOTHILLS("Foothills"),
	
	RIVER("River"),
	LAKE("Lake"),
	WETLANDS("Wetlands");
	
	public final String name;
	
	LandType(String name)
	{
		this.name = name;
	}
}

package com.captrojo.crwg.world.biome.data;

public enum LandType
{
	NULL("Null"),

	DEEP_OCEAN("Deep Ocean"),
	OCEAN("Ocean"),
	SHALLOW_OCEAN("Shallow Ocean"),
	BEACH("Beach"),

	ISLAND("Island"),
	ISLAND_HILLS("Island Hills"),
	MUSHROOM_ISLAND("Mushroom Island"),
	MUSHROOM_ISLAND_HILLS("Mushroom Island Hills"),

	LOWLANDS("Lowlands"),
	HILLS("Hills"),
	HIGHLANDS("Highlands"),

	MOUNTAINS("Mountains"),
	PLATEAUS("Plateaus"),
	FOOTHILLS("Foothills"),
	EXTREME_HILLS("Extreme Hills"),

	RIVER("River"),
	LAKE("Lake"),
	WETLANDS("Wetlands");

	public final String name;

	LandType(String name)
	{
		this.name = name;
	}
}

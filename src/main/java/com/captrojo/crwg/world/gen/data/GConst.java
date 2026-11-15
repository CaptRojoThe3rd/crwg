package com.captrojo.crwg.world.gen.data;

import com.captrojo.crwg.world.biome.data.LandType;

public abstract class GConst
{
	/* Land/water */
	public static final int WATER = 0;
	public static final int LAND = 1;

	/* Terrain types */
	public static final int NULL = LandType.NULL.ordinal();

	public static final int DEEPOCEAN = LandType.DEEP_OCEAN.ordinal();
	public static final int OCEAN = LandType.OCEAN.ordinal();
	public static final int SHALLOWOCEAN = LandType.SHALLOW_OCEAN.ordinal();
	public static final int BEACH = LandType.BEACH.ordinal();

	public static final int ISLAND = LandType.ISLAND.ordinal();
	public static final int ISLAND_HILLS = LandType.ISLAND_HILLS.ordinal();
	public static final int MUSHROOM_ISLAND = LandType.MUSHROOM_ISLAND.ordinal();
	public static final int MUSHROOM_ISLAND_HILLS = LandType.MUSHROOM_ISLAND_HILLS.ordinal();

	public static final int LOWLANDS = LandType.LOWLANDS.ordinal();
	public static final int HILLS = LandType.HILLS.ordinal();
	public static final int HIGHLANDS = LandType.HIGHLANDS.ordinal();

	public static final int MOUNTAINS = LandType.MOUNTAINS.ordinal();
	public static final int PLATEAUS = LandType.PLATEAUS.ordinal();
	public static final int FOOTHILLS = LandType.FOOTHILLS.ordinal();
	public static final int EXTREME_HILLS = LandType.EXTREME_HILLS.ordinal();

	public static final int RIVERS = LandType.RIVER.ordinal();
	public static final int LAKES = LandType.LAKE.ordinal();
	public static final int WETLANDS = LandType.WETLANDS.ordinal();

	/* Temperature + rainfall */
	public static final int FROZEN = 0;
	public static final int COLD = 1;
	public static final int TEMPERATE = 2;
	public static final int WARM = 3;
	public static final int HOT = 4;

	public static final String[] TEMPERATURE_NAMES = {"Frozen", "Cold", "Temperate", "Warm", "Hot"};

	public static final int DRY = 0;
	public static final int MODERATE = 1;
	public static final int WET = 2;

	public static final String[] RAINFALL_NAMES = {"Dry", "Moderate", "Wet"};

	public static final int MUSHROOM = 0;
	public static final int BASIC = 1;
	public static final int SEMI_MAGICAL = 2;
	public static final int MAGICAL = 3;

	public static final String[] AWESOMENESS_NAMES = {"Mushroom", "Basic", "Semi-Magical", "Magical"};
}

package com.captrojo.crwg.world.gen.data;

public abstract class GConst
{
	/* Land/water */
	public static final int WATER = 0;
	public static final int LAND = 1;

	/* Terrain types */
	public static final int NULL = 0;

	public static final int DEEPOCEAN = 1;
	public static final int OCEAN = 2;
	public static final int SHALLOWOCEAN = 3;
	public static final int BEACH = 4;

	public static final int LOWLANDS = 5;
	public static final int HILLS = 6;
	public static final int HIGHLANDS = 7;
	public static final int MOUNTAINS = 8;

	public static final int PLATEAUS = 9;
	public static final int FOOTHILLS = 10;

	public static final int RIVERS = 11;
	public static final int LAKES = 12;
	public static final int WETLANDS = 13;

	public static final String[] LT_NAMES = {
		"Null",

		"Deep Ocean",
		"Ocean",
		"Shallow Ocean",
		"Beach",

		"Lowlands",
		"Hills",
		"Highlands",
		"Mountains",

		"Plateau",
		"Foothills",

		"River",
		"Lake",
		"Wetlands"
	};

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

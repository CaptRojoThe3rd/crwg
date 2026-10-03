package com.captrojo.crwg.config;

public abstract class BiomeConfig
{
	public static int id_dry_plains = 300;
	public static int id_dry_plains_cold = 301;
	
	public static void init()
	{
		ModConfig._category = "Biome_IDs";
		
		id_dry_plains = ModConfig.getInt("dry_plains", null, id_dry_plains);
		id_dry_plains_cold = ModConfig.getInt("dry_plains_cold", null, id_dry_plains_cold);
	}
}

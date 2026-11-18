package com.captrojo.crwg.config;

import com.captrojo.crwg.world.biome.BiomeCRWGBase;

public abstract class BiomeConfig
{
	public static void init()
	{
		ModConfig._category = "Biome_IDs";

		for (BiomeCRWGBase biome : BiomeCRWGBase.BIOMES) {
			int id = biome.biomeID + 0x100;
			id = ModConfig.getInt(String.format("%03x_", id - 0x100) + biome.biomeName.replace(' ', '_'), null, id);
			biome.biomeID = id;
		}
	}
}

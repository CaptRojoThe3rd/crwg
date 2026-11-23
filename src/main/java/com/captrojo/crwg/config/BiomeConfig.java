package com.captrojo.crwg.config;

import com.captrojo.crwg.world.biome.info.BiomeInfo;

public abstract class BiomeConfig
{
	public static void init()
	{
		ModConfig._category = "Biome_IDs";

		for (BiomeInfo biome_info : BiomeInfo.getInfoList()) {
			int id = biome_info.id + 0x100;
			id = ModConfig.getInt(String.format("%03x_", id - 0x100) + biome_info.unlocalized_name, null, id);
			biome_info.id = id;
		}
	}
}

package com.captrojo.crwg.config;

public abstract class BiomeConfig
{
	public static int id_page_a = 0x01;
	public static int id_page_b = 0x02;
	public static int id_page_c = 0x03;

	public static void init()
	{
		ModConfig._category = "Biome_IDs";
		ModConfig.setCategoryComment("Page = a group of 256 ids (page 1 is IDs 0x0100-0x01ff or 256-511)");

		id_page_a = ModConfig.getInt("id_page_a", "Biome ID page A", id_page_a);
		id_page_b = ModConfig.getInt("id_page_b", "Biome ID page B", id_page_b);
		id_page_c = ModConfig.getInt("id_page_c", "Biome ID page C", id_page_c);
	}
}

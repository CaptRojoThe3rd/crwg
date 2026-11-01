package com.captrojo.crwg.world.biome.data;

import java.util.ArrayList;

import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Vanilla's four types are far too limiting.
 */
public enum BiomeTypeCRWG
{
	NULL("Null", GConst.TEMPERATE, GConst.MODERATE, WetlandType.SWAMP),
	
	DESERT("Desert", GConst.HOT, GConst.DRY, WetlandType.MARSH),
	SAVANNA("Savanna", GConst.HOT, GConst.DRY, WetlandType.MARSH),
	SCRUBLAND("Scrubland", GConst.WARM, GConst.MODERATE, WetlandType.MARSH),
	TROPICAL_FOREST("Tropical Forest", GConst.WARM, GConst.WET, WetlandType.SWAMP),
	RAINFOREST("Rainforest", GConst.WARM, GConst.WET, WetlandType.SWAMP),
	
	COOLER_DESERT("Cooler Desert", GConst.WARM, GConst.DRY, WetlandType.MARSH),
	DRY_GRASSLAND("Dry Grassland", GConst.TEMPERATE, GConst.DRY, WetlandType.MARSH),
	GRASSLAND("Grassland", GConst.TEMPERATE, GConst.MODERATE, WetlandType.SWAMP),
	CHAPARRAL("Chaparral", GConst.TEMPERATE, GConst.MODERATE, WetlandType.SWAMP),
	DECIDUOUS_FOREST("Deciduous Forest", GConst.TEMPERATE, GConst.WET, WetlandType.SWAMP),
	DENSE_DECIDUOUS_FOREST("Dense Deciduous Forest", GConst.TEMPERATE, GConst.WET, WetlandType.SWAMP),
	
	COLD_GRASSLAND("Cold Grassland", GConst.COLD, GConst.DRY, WetlandType.BOG),
	BOREAL_FOREST("Boreal Forest", GConst.COLD, GConst.MODERATE, WetlandType.FEN),
	DENSE_BOREAL_FOREST("Dense Boreal Forest", GConst.COLD, GConst.WET, WetlandType.FEN),
	
	SNOWY_BOREAL_FOREST("Snowy Boreal Forest", GConst.FROZEN, GConst.MODERATE, WetlandType.BOG),
	SNOWY_DENSE_BOREAL_FOREST("Snowy Dense Boreal Forest", GConst.FROZEN, GConst.WET, WetlandType.FEN),
	
	FROZEN_WASTELAND("Frozen Wasteland", GConst.FROZEN, GConst.DRY, WetlandType.BOG),
	TUNDRA("Tundra", GConst.FROZEN, GConst.MODERATE, WetlandType.BOG),
	SPECIAL_TUNDRA("Special Tundra", GConst.FROZEN, GConst.WET, WetlandType.BOG);
	
	public static final BiomeGroup fallback = new BiomeGroup(ModBiomes.ocean, ModBiomes.ocean, ModBiomes.ocean, ModBiomes.ocean, ModBiomes.ocean);
	
	public final String name;
	public final int s_temp;
	public final int s_rain;
	public final WetlandType wetland_type;
	
	final ArrayList<BiomeGroup> biomes;
	
	BiomeTypeCRWG(String name, int simple_temp, int simple_rain, WetlandType wetland_type)
	{
		this.name = name;
		this.s_temp = simple_temp;
		this.s_rain = simple_rain;
		this.wetland_type = wetland_type;
		
		this.biomes = new ArrayList<BiomeGroup>();
	}
	
	public BiomeGroup getFromSpecies(int species)
	{
		if (this.biomes.size() == 0) {
			return fallback;
		}
		return this.biomes.get(species % this.biomes.size());
	}

	public void addGroupOf(BiomeGenBase lowlands, BiomeGenBase hills, BiomeGenBase highlands, BiomeGenBase mountains, BiomeGenBase plateaus)
	{
		this.biomes.add(new BiomeGroup(lowlands, hills, highlands, mountains, plateaus));
	}
}

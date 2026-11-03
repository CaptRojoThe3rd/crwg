package com.captrojo.crwg.world.biome.data;

import java.util.Arrays;

import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Vanilla's four types are far too limiting.
 */
public enum BiomeTypeCRWG
{
	NULL("Null", GConst.TEMPERATE, GConst.MODERATE, GConst.BASIC, WetlandType.SWAMP),

	/* BASIC BIOME TYPES */

	DESERT("Desert", GConst.HOT, GConst.DRY, GConst.BASIC, WetlandType.MARSH),
	SAVANNA("Savanna", GConst.HOT, GConst.DRY, GConst.BASIC, WetlandType.MARSH),
	SCRUBLAND("Scrubland", GConst.WARM, GConst.MODERATE, GConst.BASIC, WetlandType.MARSH),
	TROPICAL_FOREST("Tropical Forest", GConst.WARM, GConst.WET, GConst.BASIC, WetlandType.SWAMP),
	RAINFOREST("Rainforest", GConst.WARM, GConst.WET, GConst.BASIC, WetlandType.SWAMP),

	COOLER_DESERT("Cooler Desert", GConst.WARM, GConst.DRY, GConst.BASIC, WetlandType.MARSH),
	DRY_GRASSLAND("Dry Grassland", GConst.TEMPERATE, GConst.DRY, GConst.BASIC, WetlandType.MARSH),
	GRASSLAND("Grassland", GConst.TEMPERATE, GConst.MODERATE, GConst.BASIC, WetlandType.SWAMP),
	CHAPARRAL("Chaparral", GConst.TEMPERATE, GConst.MODERATE, GConst.BASIC, WetlandType.SWAMP),
	DECIDUOUS_FOREST("Deciduous Forest", GConst.TEMPERATE, GConst.WET, GConst.BASIC, WetlandType.SWAMP),
	DENSE_DECIDUOUS_FOREST("Dense Deciduous Forest", GConst.TEMPERATE, GConst.WET, GConst.BASIC, WetlandType.SWAMP),

	COLD_GRASSLAND("Cold Grassland", GConst.COLD, GConst.DRY, GConst.BASIC, WetlandType.BOG),
	BOREAL_FOREST("Boreal Forest", GConst.COLD, GConst.MODERATE, GConst.BASIC, WetlandType.FEN),
	DENSE_BOREAL_FOREST("Dense Boreal Forest", GConst.COLD, GConst.WET, GConst.BASIC, WetlandType.FEN),

	SNOWY_BOREAL_FOREST("Snowy Boreal Forest", GConst.FROZEN, GConst.MODERATE, GConst.BASIC, WetlandType.BOG),
	SNOWY_DENSE_BOREAL_FOREST("Snowy Dense Boreal Forest", GConst.FROZEN, GConst.WET, GConst.BASIC, WetlandType.FEN),

	FROZEN_WASTELAND("Frozen Wasteland", GConst.FROZEN, GConst.DRY, GConst.BASIC, WetlandType.BOG),
	TUNDRA("Tundra", GConst.FROZEN, GConst.MODERATE, GConst.BASIC, WetlandType.BOG),

	/* MAGICAL BIOME TYPES */

	LANAYRU("Coral Land", GConst.WARM, GConst.WET, GConst.MAGICAL, WetlandType.MARSH),
	COLORFUL_RAINFOREST("Colorful Rainforest", GConst.WARM, GConst.WET, GConst.MAGICAL, WetlandType.SWAMP),

	COLORFUL_BOREAL_FOREST("Colorful Boreal Forest", GConst.COLD, GConst.MODERATE, GConst.MAGICAL, WetlandType.FEN),
	COLORFUL_DENSE_BOREAL_FOREST("Colorful Dense Boreal Forest", GConst.COLD, GConst.WET, GConst.MAGICAL, WetlandType.FEN),

	COLORFUL_SNOWY_BOREAL_FOREST("Colorful Snowy Boreal Forest", GConst.FROZEN, GConst.MODERATE, GConst.MAGICAL, WetlandType.BOG),
	COLORFUL_SNOWY_DENSE_BOREAL_FOREST("Colorful Snowy Dense Boreal Forest", GConst.FROZEN, GConst.WET, GConst.MAGICAL, WetlandType.FEN),

	SPECIAL_TUNDRA("Special Tundra", GConst.FROZEN, GConst.WET, GConst.SEMI_MAGICAL, WetlandType.BOG),

	/* MUSHROOM BIOME TYPES */

	MUSHROOM_DESERT("Mushroom Desert", GConst.HOT, GConst.DRY, GConst.MUSHROOM, WetlandType.MUSHSHROOM),
	MUSHROOM_FIELD("Mushroom Field", GConst.TEMPERATE, GConst.DRY, GConst.MUSHROOM, WetlandType.MUSHSHROOM);

	public final String name;
	public final int s_temp;
	public final int s_rain;
	public final int s_awesome;
	public final WetlandType wetland_type;

	BiomeGroup[] biomes_groups;

	BiomeTypeCRWG(String name, int simple_temp, int simple_rain, int simple_awesome, WetlandType wetland_type)
	{
		this.name = name;
		this.s_temp = simple_temp;
		this.s_rain = simple_rain;
		this.s_awesome = simple_awesome;
		this.wetland_type = wetland_type;
	}

	public BiomeGenBase getBiome(LandType land_type, int species)
	{
		if (this.biomes_groups == null) {
			return ModBiomes.ocean;
		}
		BiomeGroup group = this.biomes_groups[species % this.biomes_groups.length];
		return group.getFromLandType(land_type.ordinal());
	}
	
	public void add(BiomeGroup group)
	{
		if (this.biomes_groups == null) {
			this.biomes_groups = new BiomeGroup[1];
		} else {
			this.biomes_groups = Arrays.copyOf(this.biomes_groups, this.biomes_groups.length + 1);
		}
		this.biomes_groups[this.biomes_groups.length - 1] = group;
	}
	
	public void addGroupOf(BiomeGenBase plains, BiomeGenBase mountains, BiomeGenBase plateau)
	{
		this.add(new BiomeGroup(plains, mountains, plateau));
	}
}

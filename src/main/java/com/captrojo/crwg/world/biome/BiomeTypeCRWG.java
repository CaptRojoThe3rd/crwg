package com.captrojo.crwg.world.biome;

import java.util.ArrayList;

import com.captrojo.crwg.world.gen.genlayer.GConst;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Vanilla's four types are far too limiting for my taste.
 */
public enum BiomeTypeCRWG
{
	DESERT(GConst.WARM, GConst.DRY),
	SAVANNA(GConst.WARM, GConst.DRY),
	SCRUBLAND(GConst.WARM, GConst.DRY),
	TROPICAL_FOREST(GConst.WARM, GConst.WET),
	RAINFOREST(GConst.WARM, GConst.WET),
	
	COOLER_DESERT(GConst.WARM, GConst.DRY),
	GRASSLAND(GConst.WARM, GConst.WET),
	CHAPARRAL(GConst.WARM, GConst.WET),
	DECIDUOUS_FOREST(GConst.WARM, GConst.WET),
	DENSE_DECIDUOUS_FOREST(GConst.WARM, GConst.WET),
	
	COLD_GRASSLAND(GConst.COLD, GConst.DRY),
	BOREAL_FOREST(GConst.COLD, GConst.WET),
	DENSE_BOREAL_FOREST(GConst.COLD, GConst.WET),
	
	TUNDRA(GConst.COLD, GConst.DRY);
	
	public static final Block[] debug_blocks = {
		Blocks.sandstone,
		Blocks.redstone_block,
		Blocks.planks,
		Blocks.emerald_block,
		Blocks.emerald_ore,
		
		Blocks.hardened_clay,
		Blocks.grass,
		Blocks.clay,
		Blocks.log,
		Blocks.log2,
		
		Blocks.snow,
		Blocks.brick_block,
		Blocks.mossy_cobblestone,
		
		Blocks.packed_ice
	};
	
	public final int s_temp;
	public final int s_rain;
	final ArrayList<BiomeGroup> biomes;
	
	BiomeTypeCRWG(int simple_temp, int simple_rain)
	{
		this.s_temp = simple_temp;
		this.s_rain = simple_rain;
		this.biomes = new ArrayList<BiomeGroup>();
	}
	
	public BiomeGroup getFromSpecies(int species)
	{
		return this.biomes.get(species % this.biomes.size());
	}
	
	public void addBiomeGroup(BiomeGroup biome)
	{
		this.biomes.add(biome);
	}
	
	public void addGroupOf(BiomeGenBase lowlands, BiomeGenBase hills, BiomeGenBase highlands, BiomeGenBase mountains, BiomeGenBase plateaus)
	{
		this.biomes.add(new BiomeGroup(lowlands, hills, highlands, mountains, plateaus));
	}
}

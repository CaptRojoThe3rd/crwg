package com.captrojo.crwg.world.biome;

import com.captrojo.crwg.config.BiomeConfig;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public abstract class ModBiomes
{
	public static BiomeGenBase dry_plains;
	public static BiomeGenBase dry_plains_cold;
	
	public static void init()
	{
		dry_plains = new BiomeGenDryPlains(BiomeConfig.id_dry_plains, 0xacba6d, 0x8b965a, 0.8f, "Dry Plains");
		dry_plains_cold = new BiomeGenDryPlains(BiomeConfig.id_dry_plains_cold, 0x92b28e, 0x677a65, 0.4f, "Cold Dry Plains");
		
		BiomeDictionary.registerBiomeType(dry_plains, Type.DEAD);
		BiomeDictionary.registerBiomeType(dry_plains_cold, Type.DEAD, Type.COLD);
		
		BiomeEntry e_dry_plains = new BiomeEntry(dry_plains, 10);
		BiomeEntry e_dry_plains_cold = new BiomeEntry(dry_plains_cold, 10);
		
		BiomeManager.addBiome(BiomeType.WARM, e_dry_plains);
		BiomeManager.addBiome(BiomeType.COOL, e_dry_plains_cold);
	}
}

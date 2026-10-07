package com.captrojo.crwg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public abstract class ModBiomes
{
	public static void registerBiomes()
	{
	}

	public static void initBiomeTypes()
	{
		BiomeTypeCRWG.DESERT.addGroupOf(BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.desertHills, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau);
		
		BiomeTypeCRWG.SAVANNA.addGroupOf(BiomeGenBase.savanna, BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.savannaPlateau, BiomeGenBase.savannaPlateau);
		
		BiomeTypeCRWG.SCRUBLAND.addGroupOf(BiomeGenBase.savanna, BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, BiomeGenBase.savannaPlateau, BiomeGenBase.savannaPlateau);
		
		BiomeTypeCRWG.TROPICAL_FOREST.addGroupOf(BiomeGenBase.roofedForest, BiomeGenBase.roofedForest, BiomeGenBase.roofedForest, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		
		BiomeTypeCRWG.RAINFOREST.addGroupOf(BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.jungleHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		
		BiomeTypeCRWG.COOLER_DESERT.addGroupOf(BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.desertHills, BiomeGenBase.mesaPlateau_F, BiomeGenBase.mesaPlateau_F);
		
		BiomeTypeCRWG.GRASSLAND.addGroupOf(BiomeGenBase.plains, BiomeGenBase.plains, BiomeGenBase.plains, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);

		BiomeTypeCRWG.CHAPARRAL.addGroupOf(BiomeGenBase.plains, BiomeGenBase.plains, BiomeGenBase.plains, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, BiomeGenBase.birchForestHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
	
		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		
		BiomeTypeCRWG.COLD_GRASSLAND.addGroupOf(BiomeGenBase.plains, BiomeGenBase.plains, BiomeGenBase.plains, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		
		BiomeTypeCRWG.BOREAL_FOREST.addGroupOf(BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.taigaHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		
		BiomeTypeCRWG.DENSE_BOREAL_FOREST.addGroupOf(BiomeGenBase.megaTaiga, BiomeGenBase.megaTaigaHills, BiomeGenBase.megaTaigaHills, BiomeGenBase.extremeHills, BiomeGenBase.extremeHills);
		
		BiomeTypeCRWG.TUNDRA.addGroupOf(BiomeGenBase.icePlains, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.iceMountains, BiomeGenBase.iceMountains);
	}
}

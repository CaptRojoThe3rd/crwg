package com.captrojo.crwg.mixins.interfaces;

import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;

public interface ChunkCRWG
{
	/* SURFACE DATA */
	
	int[] crwg$getSurfDataMapA();
	
	void crwg$setSurfDataMapA(int[] map);
	
	LandType crwg$getLandType(int x, int z);
	
	void crwg$setLandType(int x, int z, LandType land_type);
	
	BiomeTypeCRWG crwg$getBiomeType(int x, int z);
	
	void crwg$setBiomeType(int x, int z, BiomeTypeCRWG biome_type);
	
	Soil crwg$getSoilType(int x, int z);
	
	void crwg$setSoilType(int x, int z, Soil soil_type);
	
	int crwg$getSpeciesVal(int x, int z);
	
	void crwg$setSpeciesVal(int x, int z, int species);
	
	/* GENERATION DATA */
	
	int[] crwg$getGndHeightMap();
	
	void crwg$setGndHeightMap(int[] gnd_height_map);
	
	int crwg$getGndHeight(int x, int z);
	
	void crwg$setGndHeight(int x, int z, int height);
}

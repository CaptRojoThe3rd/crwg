package com.captrojo.crwg.mixins.interfaces;

import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;

public interface WorldCRWG
{
	ChunkCRWG crwg$getChunk(int chunk_x, int chunk_z);
	
	ChunkCRWG crwg$getChunkFromBlockCoords(int x, int z);
	
	/* SURFACE DATA */
	
	LandType crwg$getLandType(int x, int z);
	
	boolean crwg$setLandType(int x, int z, LandType land_type);
	
	BiomeTypeCRWG crwg$getBiomeType(int x, int z);
	
	boolean crwg$setBiomeType(int x, int z, BiomeTypeCRWG biome_type);
	
	Soil crwg$getSoilType(int x, int z);
	
	boolean crwg$setSoilType(int x, int z, Soil soil_type);
	
	int crwg$getSpeciesVal(int x, int z);
	
	boolean crwg$setSpeciesVal(int x, int z, int species);
	
	/* GENERATION DATA */
	
	int crwg$getGndHeight(int x, int z);
	
	boolean crwg$setGndHeight(int x, int z, int height);
}

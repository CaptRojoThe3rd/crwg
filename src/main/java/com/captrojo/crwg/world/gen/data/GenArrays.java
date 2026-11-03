package com.captrojo.crwg.world.gen.data;

import com.captrojo.crwg.mixins.interfaces.ChunkCRWG;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;
import com.captrojo.crwg.world.chunk.ChnkConst;

import net.minecraft.world.biome.BiomeGenBase;

public class GenArrays
{
	private int asize = 0;
	private GenVals gen_vals = new GenVals();

	/* biomes & climate data */
	public int[] biome_id_array;
	public BiomeGenBase[] biome_array;

	public int[] biome_type_id_array;
	public BiomeTypeCRWG[] biome_type_array;

	public int[] species_array;

	public int[] s_temperatures;
	public int[] s_rainfalls;

	/* noise */
	public double[] deep_ocean_noise;
	public double[] ocean_noise;
	public double[] shallow_ocean_noise;
	public double[] beach_noise;

	public double[] lowland_noise;
	public double[] hill_noise;
	public double[] highland_noise;
	public double[] mountain_noise;

	public double[] plateau_noise;
	public double[] foothill_noise;

	public double[] river_noise;
	public double[] lake_noise;
	public double[] wetlands_noise;

	/* selectors */
	public double[] deep_ocean_sel;
	public double[] ocean_sel;
	public double[] shallow_ocean_sel;
	public double[] beach_sel;

	public double[] lowland_sel;
	public double[] hill_sel;
	public double[] highland_sel;
	public double[] mountain_sel;

	public double[] plateau_sel;
	public double[] foothill_sel;

	public double[] river_sel;
	public double[] lake_sel;
	public double[] wetlands_sel;

	/* other data */
	public int[] land_type_id_array;
	public LandType[] land_type_array;

	public double[] soil_bumpiness_noise;
	public int[] gnd_height_map;

	public void initCertainArraysIfNecessary(int asize)
	{
		if (this.asize >= asize) {
			return;
		}
		this.asize = asize;
		this.biome_array = new BiomeGenBase[asize];
		this.biome_type_id_array = new int[asize];
		this.biome_type_array = new BiomeTypeCRWG[asize];
		this.species_array = new int[asize];
		this.s_temperatures = new int[asize];
		this.s_rainfalls = new int[asize];
		this.land_type_id_array = new int[asize];
		this.land_type_array = new LandType[asize];
		this.gnd_height_map = new int[asize];
	}

	public GenVals getVals(int idx)
	{
		this.gen_vals.species = this.species_array[idx];
		this.gen_vals.s_temp = this.s_temperatures[idx];
		this.gen_vals.s_rain = this.s_rainfalls[idx];
		this.gen_vals.soil_bumpiness_noise = this.soil_bumpiness_noise[idx];
		this.gen_vals.gnd_height = this.gnd_height_map[idx];
		return this.gen_vals;
	}

	public void addDataToChunk(ChunkCRWG chunk)
	{
		int[] surf_data_map_a = chunk.crwg$getSurfDataMapA();
		for (int i = 0; i < 256; i++) {
			int v = this.land_type_id_array[i];
			v |= (this.biome_type_id_array[i] << ChnkConst.SHIFT_BIOMETYPE);

			int s_temp = this.s_temperatures[i];
			int s_rain = this.s_rainfalls[i];
			int soil = Soil.Soil_Map[s_temp][s_rain][(this.species_array[i] >> 16) & 3].ordinal();
			v |= (soil << ChnkConst.SHIFT_SOILTYPE);
			
			v |= (((this.species_array[i] >> 23) & 0xff) << ChnkConst.SHIFT_SUBSPECIES);

			surf_data_map_a[i] = v;
		}
		chunk.crwg$setGndHeightMap(this.gnd_height_map);
	}
}

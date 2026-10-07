package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.biome.BiomeGroup;
import com.captrojo.crwg.world.biome.BiomeTypeCRWG;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesCRWG extends GenLayer
{
	final GenLayer species;
	final GenLayer biome_types;
	final GenLayer terr_types;
	
	public GenLayerBiomesCRWG(long base_seed, GenLayer species, GenLayer biome_types, GenLayer terr_types)
	{
		super(base_seed);
		this.species = species;
		this.biome_types = biome_types;
		this.terr_types = terr_types;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int[] sp_arr = this.species.getInts(ax, ay, aw, ah);
		int[] bt_arr = this.biome_types.getInts(ax, ay, aw, ah);
		int[] tt_arr = this.terr_types.getInts(ax, ay, aw, ah);
		
		final int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);
		
		for (int i = 0; i < asize; i++) {
			int species = sp_arr[i];
			BiomeTypeCRWG biome_type = BiomeTypeCRWG.values()[bt_arr[i]];
			int terr_type = tt_arr[i];
			
			BiomeGenBase biome = BiomeGenBase.ocean;
			
			if (terr_type == GConst.RIVERS) {
				biome = (biome_type.s_temp == GConst.FROZEN) ? BiomeGenBase.frozenRiver : BiomeGenBase.river;
			} else if (terr_type == GConst.OCEAN || terr_type == GConst.SHALLOWOCEAN) {
				biome = BiomeGenBase.ocean;
			} else if (terr_type == GConst.DEEPOCEAN) {
				biome = BiomeGenBase.deepOcean;
			} else if (terr_type == GConst.BEACH) {
				if (biome_type.s_temp == GConst.WARM) {
					biome = BiomeGenBase.beach;
				} else {
					biome = (biome_type.s_rain == GConst.WET) ? BiomeGenBase.coldBeach : BiomeGenBase.stoneBeach;
				}
			} else if (terr_type == GConst.LAKES) {
				biome = BiomeGenBase.ocean;
			} else {
				BiomeGroup group = biome_type.getFromSpecies(species);
				biome = group.getFromHeightType(terr_type);
			}
			
			n_arr[i] = biome.biomeID;
		}
		
		return n_arr;
	}
}

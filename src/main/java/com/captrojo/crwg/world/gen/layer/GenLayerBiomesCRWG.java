package com.captrojo.crwg.world.gen.layer;

import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesCRWG extends GenLayer
{
	public static BiomeCRWGBase[][] ocean_biomes;
	public static BiomeCRWGBase[][] beach_biomes;

	final GenLayer species;
	final GenLayer biome_types;
	final GenLayer land_types;

	public GenLayerBiomesCRWG(long base_seed, GenLayer species, GenLayer biome_types, GenLayer terr_types)
	{
		super(base_seed);
		this.species = species;
		this.biome_types = biome_types;
		this.land_types = terr_types;
	}

	@Override
	public void initWorldGenSeed(long seed)
	{
		this.species.initWorldGenSeed(seed);
		this.biome_types.initWorldGenSeed(seed);
		this.land_types.initWorldGenSeed(seed);
		super.initWorldGenSeed(seed);
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int[] sp_arr = this.species.getInts(ax, ay, aw, ah);
		int[] bt_arr = this.biome_types.getInts(ax, ay, aw, ah);
		int[] lt_arr = this.land_types.getInts(ax, ay, aw, ah);

		final int asize = aw * ah;
		int[] n_arr = IntCache.getIntCache(asize);

		final LandType[] LT_ENUM = LandType.values();

		for (int i = 0; i < asize; i++) {
			int species = sp_arr[i];
			BiomeTypeCRWG biome_type = BiomeTypeCRWG.values()[bt_arr[i]];
			int land_type = lt_arr[i];

			BiomeGenBase biome;

			switch (LT_ENUM[land_type]) {
			case RIVER:
				if (biome_type.s_temp == GConst.HOT && biome_type.s_rain == GConst.WET) {
					biome = ModBiomes.river_swampy;
				} else if (biome_type.s_temp == GConst.FROZEN) {
					biome = ModBiomes.river_frozen;
				} else {
					biome = ModBiomes.river;
				}
				break;

			case SHALLOW_OCEAN:
			case OCEAN:
			case DEEP_OCEAN:
				biome = GenLayerBiomesCRWG.ocean_biomes[biome_type.s_temp][biome_type.s_rain];
				break;

			case BEACH:
				if (biome_type.s_awesome == GConst.MUSHROOM) {
					biome = ModBiomes.mushroom_shore;
				} else {
					biome = GenLayerBiomesCRWG.beach_biomes[biome_type.s_temp][biome_type.s_rain];
				}
				break;

			case LAKE:
				if (biome_type.s_temp == GConst.FROZEN) {
					biome = ModBiomes.lake_frozen;
				} else {
					biome = ModBiomes.lake;
				}
				break;

			case WETLANDS:
				int s;
				switch (biome_type.wetland_type) {
				default:
				case SWAMP:
					s = (species >>> 20) % 3;
					if (s == 2) {
						biome = ModBiomes.cypress_swamp;
					} else {
						biome = ModBiomes.dark_swamp;
					}
					break;
				case FEN:
					s = (species >>> 20) & 1;
					if (s == 0) {
						biome = ModBiomes.fen_green;
					} else {
						biome = ModBiomes.fen_yellow;
					}
					break;
				}
				break;

			default:
				biome = biome_type.getBiome(LT_ENUM[land_type], species & 0xffff);
				break;
			}
			
			n_arr[i] = biome.biomeID;
		}

		return n_arr;
	}
}

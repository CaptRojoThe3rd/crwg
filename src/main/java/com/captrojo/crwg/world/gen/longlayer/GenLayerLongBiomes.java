package com.captrojo.crwg.world.gen.longlayer;

import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLongBiomes extends GenLayerLong
{
	public static BiomeCRWGBase[][] ocean_biomes;
	public static BiomeCRWGBase[][] beach_biomes;

	final GenLayer species;
	final GenLayer biome_types;
	final GenLayer land_types;

	public GenLayerLongBiomes(long base_seed, GenLayer species, GenLayer biome_types, GenLayer land_types)
	{
		super(base_seed);
		this.species = species;
		this.biome_types = biome_types;
		this.land_types = land_types;
	}

	@Override
	public long[] getLongs(int ax, int ay, int aw, int ah)
	{
		int[] sp_arr = this.species.getInts(ax, ay, aw, ah);
		int[] bt_arr = this.biome_types.getInts(ax, ay, aw, ah);
		int[] lt_arr = this.land_types.getInts(ax, ay, aw, ah);

		final int asize = aw * ah;
		long[] n_arr = LongCache.getLongCache(asize);

		final LandType[] LT_ENUM = LandType.values();
		final BiomeTypeCRWG[] BT_ENUM = BiomeTypeCRWG.values();

		for (int i = 0; i < asize; i++) {
			int species = sp_arr[i];
			int biome_type_id = bt_arr[i];
			BiomeTypeCRWG biome_type = BT_ENUM[biome_type_id];
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
				biome = GenLayerLongBiomes.ocean_biomes[biome_type.s_temp][biome_type.s_rain];
				break;

			case BEACH:
				if (biome_type.s_awesome == GConst.MUSHROOM) {
					biome = ModBiomes.mushroom_shore;
				} else {
					biome = GenLayerLongBiomes.beach_biomes[biome_type.s_temp][biome_type.s_rain];
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
				case OASIS:
					biome = ModBiomes.oasis;
					break;
				default:
				case SWAMP:
					s = (species >>> 20) % 3;
					if (s == 2) {
						biome = ModBiomes.cypress_swamp;
					} else {
						biome = ModBiomes.dark_swamp;
					}
					break;
				case MARSH:
					s = (species >>> 20) & 1;
					if (s == 0) {
						biome = ModBiomes.marsh_green;
					} else {
						biome = ModBiomes.marsh_yellow;
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

			case MUSHROOM_ISLAND:
			case MUSHROOM_ISLAND_HILLS:
				if (biome_type.s_temp == GConst.FROZEN) {
					biome_type = BiomeTypeCRWG.MUSHROOM_TUNDRA;
				} else if (biome_type.s_temp == GConst.HOT && biome_type.s_rain == GConst.DRY) {
					biome_type = BiomeTypeCRWG.MUSHROOM_DESERT;
				} else if (biome_type.s_rain == GConst.WET) {
					biome_type = BiomeTypeCRWG.MUSHROOM_FOREST;
				} else {
					biome_type = BiomeTypeCRWG.MUSHROOM_FIELD;
				}
				biome_type_id = biome_type.ordinal();
				/* fallthrough */

			default:
				biome = biome_type.getBiome(LT_ENUM[land_type], species & 0xffff);
				break;
			}

			n_arr[i] = (long) biome.biomeID;
			n_arr[i] |= ((long) species) & 0xffff0000L;
			n_arr[i] |= ((long) land_type) << 32;
			n_arr[i] |= ((long) biome_type_id) << 38;
		}

		return n_arr;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		long[] l_arr = this.getLongs(ax, ay, aw, ah);
		final int asize = aw * ah;
		int[] i_arr = IntCache.getIntCache(asize);
		for (int i = 0; i < asize; i++) {
			i_arr[i] = (int) l_arr[i] & 0xffff;
		}
		return i_arr;
	}

	@Override
	public void initWorldGenSeed(long seed)
	{
		this.species.initWorldGenSeed(seed);
		this.biome_types.initWorldGenSeed(seed);
		this.land_types.initWorldGenSeed(seed);
		super.initWorldGenSeed(seed);
	}
}

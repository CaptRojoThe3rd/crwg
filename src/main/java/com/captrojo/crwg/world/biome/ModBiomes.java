package com.captrojo.crwg.world.biome;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.gen.genlayer.GenLayerBiomesCRWG;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;

public abstract class ModBiomes
{
	public static BiomeCRWGBase ocean;
	public static BiomeCRWGBase ocean_lukewarm;
	public static BiomeCRWGBase ocean_warm;
	public static BiomeCRWGBase ocean_cold;
	public static BiomeCRWGBase ocean_frozen;

	public static BiomeCRWGBase beach_frozen;
	public static BiomeCRWGBase beach_gravel;
	public static BiomeCRWGBase beach;
	public static BiomeCRWGBase beach_tropical;

	public static BiomeCRWGBase river;
	public static BiomeCRWGBase river_frozen;
	public static BiomeCRWGBase river_swampy;

	public static BiomeCRWGBase lake;
	public static BiomeCRWGBase lake_frozen;

	public static BiomeCRWGBase oasis;

	public static BiomeCRWGBase dark_swamp;
	public static BiomeCRWGBase mangrove_swamp;
	public static BiomeCRWGBase cypress_swamp;

	public static BiomeCRWGBase marsh_green;
	public static BiomeCRWGBase marsh_yellow;

	public static BiomeCRWGBase bog_green;
	public static BiomeCRWGBase bog_yellow;
	public static BiomeCRWGBase peat_bog;

	public static BiomeCRWGBase fen_green;
	public static BiomeCRWGBase fen_yellow;

	public static BiomeCRWGBase grassland;
	public static BiomeCRWGBase grassland_sunflower;

	public static BiomeCRWGBase sparse_temperate_mountains;
	public static BiomeCRWGBase sparse_plateau;

	public static BiomeCRWGBase oak_chaparral;
	public static BiomeCRWGBase oak_forest;
	public static BiomeCRWGBase flower_chaparral;
	public static BiomeCRWGBase flower_forest;

	public static BiomeCRWGBase dark_oak_forest;

	public static BiomeCRWGBase birch_chaparral;
	public static BiomeCRWGBase birch_forest;
	public static BiomeCRWGBase old_growth_birch_forest;

	public static BiomeCRWGBase mountains;
	public static BiomeCRWGBase frozen_mountains;

	public static BiomeCRWGBase gravel_plateau;
	public static BiomeCRWGBase snowy_plateau;

	public static BiomeCRWGBase tundra;
	public static BiomeCRWGBase ice_spikes;

	public static BiomeCRWGBase spruce_forest;
	public static BiomeCRWGBase old_growth_spruce_forest;
	public static BiomeCRWGBase snowy_spruce_forest;
	public static BiomeCRWGBase snowy_old_growth_spruce_forest;

	public static BiomeCRWGBase pine_forest;
	public static BiomeCRWGBase old_growth_pine_forest;
	public static BiomeCRWGBase snowy_pine_forest;
	public static BiomeCRWGBase snowy_old_growth_pine_forest;

	public static BiomeCRWGBase cedar_forest;
	public static BiomeCRWGBase old_growth_cedar_forest;
	public static BiomeCRWGBase snowy_cedar_forest;
	public static BiomeCRWGBase snowy_old_growth_cedar_forest;

	public static BiomeCRWGBase desert;
	public static BiomeCRWGBase desert_mountains;
	public static BiomeCRWGBase desert_plateau;

	public static BiomeCRWGBase jungle;
	public static BiomeCRWGBase jungle_mountains;
	public static BiomeCRWGBase jungle_plateau;

	public static void registerBiomes()
	{
		/* Create CRWG biomes */
		ocean = new BiomeCRWGOcean(0x000, "Ocean", 0x000070, 0.5F, 0.5F);
		ocean_lukewarm = new BiomeCRWGOcean(0x003, "Lukewarm Ocean", 0x102070, 0.6F, 0.5F);
		ocean_warm = new BiomeCRWGOcean(0x006, "Warm Ocean", 0x204070, 0.8F, 0.5F);
		ocean_cold = new BiomeCRWGOcean(0x009, "Cold Ocean", 0x080860, 0.3F, 0.5F);
		ocean_frozen = (new BiomeCRWGOcean(0x00c, "Frozen Ocean", 0x181850, 0.1F, 0.5F)).setEnableSnow();

		beach_frozen = new BiomeCRWGBeach(0x00f, "Cold Beach", 0xa2a284, 0.0F, 0.4F, BlockState.STONE, false);
		beach_gravel = new BiomeCRWGBeach(0x010, "Gravel Beach", 0xa3a392, 0.2F, 0.4F, BlockState.GRAVEL, false);
		beach = new BiomeCRWGBeach(0x011, "Beach", 0xc6c66f, 0.8F, 0.4F, BlockState.SAND, false);
		beach_tropical = new BiomeCRWGBeach(0x012, "Tropical Beach", 0xe9f46b, 1.2F, 0.3F, BlockState.SAND, true);

		river = new BiomeCRWGRiver(0x013, "River", 0x0000ff, 0.5F, 0.5F);
		river_frozen = (new BiomeCRWGRiver(0x014, "Frozen River", 0x5050ff, 0.0F, 0.5F)).setEnableSnow();
		river_swampy = new BiomeCRWGRiver(0x015, "Swampy River", 0x0040ff, 0.6F, 0.7F);

		lake = new BiomeCRWGRiver(0x016, "Lake", 0x2020f0, 0.5F, 0.5F);
		lake_frozen = (new BiomeCRWGRiver(0x017, "Frozen Lake", 0x4040f0, 0.0F, 0.5F)).setEnableSnow();

		dark_swamp = new BiomeDarkSwamp(0x018, "Dark Swamp", 0x07f9b2, 0.8F, 0.9F);
		cypress_swamp = new BiomeCypressSwamp(0x01a, "Cypress Swamp", 0x07f998, 0.8F, 0.9F);

		fen_green = new BiomeFen(0x020, "Fen", 0x98b759, 0.55F, 0.8F);

		grassland = new BiomeGrassland(0x022, "Grassland", 0x80ff50, 0.8F, 0.4F, false);
		grassland_sunflower = new BiomeGrassland(0x0a2, "Sunflower Grassland", 0x80ff50, 0.8F, 0.4F, true);

		sparse_temperate_mountains = new BiomeSparseTemperateMountains(0x023, "Sparse Temperate Mountains");
		sparse_plateau = new BiomeSparseTemperateMountains(0x024, "Sparse Plateau");

		oak_chaparral = new BiomeOakForest(0x025, "Oak Chaparral", 0x056621, 0.7F, 0.6F, BiomeOakForest.CHAPARRAL);
		oak_forest = new BiomeOakForest(0x026, "Oak Forest", 0x056621, 0.7F, 0.8F, BiomeOakForest.NORMAL);
		flower_chaparral = new BiomeOakForest(0x0a5, "Flower Chaparral", 0x056621, 0.7F, 0.633F, BiomeOakForest.FLOWER_CHAPARRAL);
		flower_forest = new BiomeOakForest(0x0a6, "Flower Forest", 0x056621, 0.7F, 0.833F, BiomeOakForest.FLOWER);
		dark_oak_forest = new BiomeDarkOakForest(0x027, "Dark Oak Forest", 0x40511a, 0.7F, 0.8F);

		birch_chaparral = new BiomeBirchForest(0x028, "Birch Chaparral", 0x307444, 0.6F, 0.55F, BiomeBirchForest.CHAPARRAL);
		birch_forest = new BiomeBirchForest(0x029, "Birch Forest", 0x307444, 0.6F, 0.65F, BiomeBirchForest.NORMAL);
		old_growth_birch_forest = new BiomeBirchForest(0x0a9, "Old Growth Birch Forest", 0x307444, 0.6F, 0.65F, BiomeBirchForest.OLD_GROWTH);

		mountains = new BiomeMountains(0x02a, "Mountains", 0x808080, 0.25F, 0.55F);
		frozen_mountains = new BiomeMountains(0x02b, "Frozen Mountains", 0xa0a0c0, -0.55F, 0.55F);

		gravel_plateau = (new BiomeWasteland(0x02c, "Gravel Plateau", 0xa0a0a0, 0.3F, 0.3F)).setEnableSnow();
		snowy_plateau = new BiomeTundra(0x02d, "Snowy Plateau", 0xdfffff, -0.45F, 0.4F, false);

		tundra = new BiomeTundra(0x02e, "Tundra", 0xc0f0f0, -0.15F, 0.4F, false);
		ice_spikes = new BiomeTundra(0x0ae, "Ice Spike Tundra", 0xb0e0f0, -0.25F, 0.6F, true);

		spruce_forest = new BiomeSpruceForest(0x02f, "Spruce Forest", 0x0b6659, 0.35F, 0.7F, false);
		old_growth_spruce_forest = new BiomeSpruceForest(0x0af, "Old Growth Spruce Forest", 0x596651, 0.35F, 0.85F, true);
		snowy_spruce_forest = new BiomeSpruceForest(0x030, "Snowy Spruce Forest", 0x0b6659, -0.25F, 0.7F, false);
		snowy_old_growth_spruce_forest = new BiomeSpruceForest(0x0b0, "Snowy Old Growth Spruce Forest", 0x596651, -0.25F, 0.85F, true);

		pine_forest = new BiomePineForest(0x031, "Pine Forest", 0x0b663e, 0.35F, 0.7F, false);
		old_growth_pine_forest = new BiomePineForest(0x0b1, "Old Growth Pine Forest", 0x5e6650, 0.35F, 0.85F, true);
		snowy_pine_forest = new BiomePineForest(0x032, "Snowy Pine Forest", 0x0b663e, -0.25F, 0.7F, false);
		snowy_old_growth_pine_forest = new BiomePineForest(0x0b2, "Snowy Old Growth Pine Forest", 0x5e6650, -0.25F, 0.85F, true);

		cedar_forest = new BiomeCedarForest(0x033, "Cedar Forest", 0x226035, 0.35F, 0.7F, false);
		old_growth_cedar_forest = new BiomeCedarForest(0x0b3, "Old Growth Cedar Forest", 0x52664f, 0.35F, 0.85F, true);
		snowy_cedar_forest = new BiomeCedarForest(0x034, "Snowy Cedar Forest", 0x426035, -0.25F, 0.7F, false);
		snowy_old_growth_cedar_forest = new BiomeCedarForest(0x0b4, "Snowy Old Growth Cedar Forest", 0x52664f, -0.25F, 0.85F, true);

		desert = (new BiomeDesert(0x035, "Desert", 0xfa9418, 2.0F, 0.0F)).setDisableRain();
		desert_mountains = (new BiomeDesertMountains(0x036, "Desert Mountains", 0xffd458, 1.6F, 0.0F)).setDisableRain();
		desert_plateau = (new BiomeDesertMountains(0x037, "Desert Plateau", 0xffc448, 1.7F, 0.0F)).setDisableRain();

		jungle = new BiomeJungle(0x038, "Jungle", 0x537b09, 0.95F, 0.9F, 0);
		jungle_mountains = new BiomeJungle(0x039, "Jungle Mountains", 0x83ab39, 0.8F, 0.9F, 2);
		jungle_plateau = new BiomeJungle(0x03a, "Jungle Plateau", 0x93bb49, 0.7F, 0.9F, 1);

		/* Register CRWG biomes with BiomeDictionary and BiomeManager */
		addOcean(ocean, Type.OCEAN);
		addOcean(ocean_lukewarm, Type.OCEAN);
		addOcean(ocean_warm, Type.OCEAN);
		addOcean(ocean_cold, Type.OCEAN, Type.COLD);
		addOcean(ocean_frozen, Type.OCEAN, Type.COLD, Type.SNOWY);

		add(beach_frozen, Type.BEACH, Type.COLD, Type.SNOWY);
		add(beach_gravel, Type.BEACH, Type.COLD);
		add(beach, Type.BEACH);
		add(beach_tropical, Type.BEACH, Type.HOT);

		add(river, Type.RIVER);
		add(river_frozen, Type.RIVER, Type.COLD, Type.SNOWY);
		add(river_swampy, Type.RIVER, Type.WET);

		add(lake, Type.WATER);
		add(lake_frozen, Type.WATER, Type.COLD, Type.SNOWY);

		add(dark_swamp, BiomeType.COOL, false, true, true, Type.WET, Type.SWAMP);
		add(cypress_swamp, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP);

		add(fen_green, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP);

		add(grassland, BiomeType.WARM, true, true, true, Type.PLAINS);
		add(grassland_sunflower, BiomeType.WARM, true, true, true, Type.PLAINS);

		add(sparse_temperate_mountains, BiomeType.COOL, true, false, true, Type.SPARSE, Type.MOUNTAIN);
		add(sparse_plateau, BiomeType.COOL, true, false, true, Type.SPARSE, Type.HILLS);

		add(oak_chaparral, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE);
		add(oak_forest, BiomeType.WARM, true, false, true, Type.FOREST);
		add(flower_chaparral, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE);
		add(flower_forest, BiomeType.WARM, true, false, true, Type.FOREST);

		add(dark_oak_forest, BiomeType.WARM, true, false, true, Type.FOREST, Type.DENSE, Type.SPOOKY);

		add(birch_chaparral, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE);
		add(birch_forest, BiomeType.WARM, true, false, true, Type.FOREST);
		add(old_growth_birch_forest, BiomeType.WARM, true, false, true, Type.FOREST);

		add(mountains, BiomeType.COOL, false, false, true, Type.MOUNTAIN, Type.SPARSE, Type.COLD);
		add(frozen_mountains, BiomeType.ICY, false, false, true, Type.MOUNTAIN, Type.SPARSE, Type.COLD, Type.SNOWY);

		add(gravel_plateau, BiomeType.COOL, false, false, false, Type.WASTELAND, Type.HILLS, Type.COLD);
		add(snowy_plateau, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.HILLS, Type.COLD, Type.SNOWY);

		add(tundra, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY);
		add(ice_spikes, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY);

		add(spruce_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(old_growth_spruce_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(snowy_spruce_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_old_growth_spruce_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);

		add(pine_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(old_growth_pine_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(snowy_pine_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_old_growth_pine_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);

		add(cedar_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(old_growth_cedar_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(snowy_cedar_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_old_growth_cedar_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);

		add(desert, BiomeType.DESERT, false, true, true, Type.HOT, Type.DRY, Type.SANDY);
		add(desert_mountains, BiomeType.DESERT, false, false, true, Type.HOT, Type.DRY, Type.SANDY, Type.MOUNTAIN);
		add(desert_plateau, BiomeType.DESERT, false, false, true, Type.HOT, Type.DRY, Type.SANDY, Type.HILLS);

		add(jungle, BiomeType.WARM, true, false, true, Type.HOT, Type.WET, Type.DENSE, Type.JUNGLE);
		add(jungle_mountains, BiomeType.WARM, true, false, true, Type.WET, Type.DENSE, Type.JUNGLE, Type.MOUNTAIN);
		add(jungle_plateau, BiomeType.WARM, true, true, true, Type.WET, Type.DENSE, Type.JUNGLE, Type.HILLS);

		/* Init GenLayer stuff */
		GenLayerBiomesCRWG.ocean_biomes = new BiomeCRWGBase[][] {
			{ocean_frozen, ocean_frozen, ocean_frozen},
			{ocean_cold, ocean_cold, ocean_cold},
			{ocean, ocean, ocean},
			{ocean_lukewarm, ocean_lukewarm, ocean_lukewarm},
			{ocean_warm, ocean_warm, ocean_warm}
		};
		GenLayerBiomesCRWG.beach_biomes = new BiomeCRWGBase[][] {
			{beach_frozen, beach_frozen, beach_frozen},
			{beach_gravel, beach_gravel, beach_gravel},
			{beach, beach, beach},
			{beach, beach, beach_tropical},
			{beach_tropical, beach_tropical, beach_tropical}
		};
	}

	private static void addOcean(BiomeGenBase biome, BiomeDictionary.Type... types)
	{
		add(biome, types);
		BiomeManager.oceanBiomes.add(biome);
	}

	private static void add(BiomeGenBase biome, BiomeDictionary.Type... types)
	{
		add(biome, null, false, false, false, types);
	}

	private static void add(
		BiomeGenBase biome,
		BiomeType vtype,
		boolean spawn,
		boolean village,
		boolean stronghold,
		BiomeDictionary.Type... types
	)
	{
		if (vtype != null) {
//			BiomeEntry entry = new BiomeEntry(biome, 5);
//			BiomeManager.addBiome(vtype, entry);
		}
		if (spawn) {
			BiomeManager.addSpawnBiome(biome);
		}
		if (village) {
			BiomeManager.addVillageBiome(biome, true);
		}
		if (stronghold) {
			BiomeManager.addStrongholdBiome(biome);
		}
		BiomeDictionary.registerBiomeType(biome, types);
	}

	public static void initBiomeTypes()
	{
		/* IMPORTANT NOTES:
		 * 
		 * The order in which biome groups are added matters. It particularly matters
		 * for the chaparral, deciduous forest, and dense deciuous forest biome types.
		 * If order is not maintained, then a birch forest could generate next to an oak
		 * chaparral (for example). A similar issue is present with the boreal forest
		 * and dense boreal forest types.
		 * 
		 * Each group has an equal chance of spawning. Currently, if you want something to
		 * be rare, everything else must be added multiple times. (Or add a specific biome
		 * type for a rare biome.)
		 */

		/* BASIC BIOME TYPES */

		BiomeTypeCRWG.DESERT.addGroupOf(desert, desert_mountains, desert_plateau);

		BiomeTypeCRWG.SAVANNA.addGroupOf(desert, desert_mountains, desert_plateau);

		BiomeTypeCRWG.SCRUBLAND.addGroupOf(desert, desert_mountains, desert_plateau);

		BiomeTypeCRWG.TROPICAL_FOREST.addGroupOf(dark_oak_forest, sparse_temperate_mountains, sparse_plateau);

		BiomeTypeCRWG.RAINFOREST.addGroupOf(jungle, jungle_mountains, jungle_plateau);

		BiomeTypeCRWG.COOLER_DESERT.addGroupOf(desert, desert_mountains, desert_plateau);

		BiomeTypeCRWG.GRASSLAND.addGroupOf(grassland, mountains, gravel_plateau);
		BiomeTypeCRWG.GRASSLAND.addGroupOf(grassland_sunflower, mountains, gravel_plateau);

		BiomeTypeCRWG.CHAPARRAL.addGroupOf(oak_chaparral, mountains, gravel_plateau);
		BiomeTypeCRWG.CHAPARRAL.addGroupOf(flower_chaparral, mountains, gravel_plateau);
		BiomeTypeCRWG.CHAPARRAL.addGroupOf(birch_chaparral, mountains, gravel_plateau);

		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(oak_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(flower_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(birch_forest, mountains, gravel_plateau);

		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(dark_oak_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(flower_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(old_growth_birch_forest, mountains, gravel_plateau);

		BiomeTypeCRWG.COLD_GRASSLAND.addGroupOf(grassland, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.BOREAL_FOREST.addGroupOf(spruce_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.BOREAL_FOREST.addGroupOf(pine_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.BOREAL_FOREST.addGroupOf(cedar_forest, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.DENSE_BOREAL_FOREST.addGroupOf(old_growth_spruce_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.DENSE_BOREAL_FOREST.addGroupOf(old_growth_pine_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.DENSE_BOREAL_FOREST.addGroupOf(old_growth_cedar_forest, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.SNOWY_BOREAL_FOREST.addGroupOf(snowy_spruce_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_BOREAL_FOREST.addGroupOf(snowy_pine_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_BOREAL_FOREST.addGroupOf(snowy_cedar_forest, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.SNOWY_DENSE_BOREAL_FOREST.addGroupOf(snowy_old_growth_spruce_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_DENSE_BOREAL_FOREST.addGroupOf(snowy_old_growth_pine_forest, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_DENSE_BOREAL_FOREST.addGroupOf(snowy_old_growth_cedar_forest, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.FROZEN_WASTELAND.addGroupOf(tundra, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.TUNDRA.addGroupOf(tundra, frozen_mountains, snowy_plateau);

		/* MAGICAL BIOME TYPES */

		BiomeTypeCRWG.SPECIAL_TUNDRA.addGroupOf(ice_spikes, frozen_mountains, snowy_plateau);
	}
}

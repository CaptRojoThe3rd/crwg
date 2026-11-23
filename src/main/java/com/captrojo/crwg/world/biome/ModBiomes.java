package com.captrojo.crwg.world.biome;

import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.longlayer.GenLayerLongBiomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;

public abstract class ModBiomes
{
	/* BASIC BIOMES */

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
	/* reserved: mangrove swamp (1) */
	public static BiomeCRWGBase cypress_swamp;

	public static BiomeCRWGBase marsh_green;
	public static BiomeCRWGBase marsh_yellow;
	/* reserved: salt marsh (1) */

	public static BiomeCRWGBase bog_green;
	public static BiomeCRWGBase bog_yellow;
	/* reserved: peat bog (1) */

	public static BiomeCRWGBase fen_green;
	public static BiomeCRWGBase fen_yellow;

	public static BiomeCRWGBase grassland;
	public static BiomeCRWGBase sunflower_plains;
	public static BiomeCRWGBase dry_grassland;
	public static BiomeCRWGBase cold_grassland;

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

	/* reserved: maple (3) */
	/* reserved: yellow autumn maple (3) */
	/* reserved: red autumn maple (3) */

	public static BiomeCRWGBase stony_peaks;
	public static BiomeCRWGBase mountains;
	public static BiomeCRWGBase frozen_mountains;

	public static BiomeCRWGBase gravel_plateau;
	public static BiomeCRWGBase snowy_plateau;

	public static BiomeCRWGBase wasteland;
	public static BiomeCRWGBase frozen_wasteland;
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

	/* reserved: redwood (4) */
	/* reserved: fir (4) */

	public static BiomeCRWGBase desert;
	public static BiomeCRWGBase desert_mountains;
	public static BiomeCRWGBase desert_plateau;

	public static BiomeCRWGBase jungle;
	public static BiomeCRWGBase jungle_plateau;

	public static BiomeCRWGBase savanna;
	public static BiomeCRWGBase savanna_plateau;

	/* MUSHROOM BIOMES */

	public static BiomeCRWGBase mushroom_shore;
	public static BiomeCRWGBase mushroom_mountains;
	public static BiomeCRWGBase mushroom_plateau;

	public static BiomeCRWGBase mushroom_desert;
	public static BiomeCRWGBase mushroom_field;
	public static BiomeCRWGBase mushroom_forest;
	public static BiomeCRWGBase mushroom_tundra;

	/* MAGICAL BIOMES */

	public static BiomeCRWGBase vibrant_forest;
	public static BiomeCRWGBase enchanted_ash_forest;

	public static BiomeCRWGBase rainbow_forest;
	public static BiomeCRWGBase rainbow_jungle;

	public static BiomeCRWGBase violet_forest;
	public static BiomeCRWGBase old_growth_violet_forest;
	public static BiomeCRWGBase snowy_violet_forest;
	public static BiomeCRWGBase snowy_old_growth_violet_forest;

	public static void registerBiomes()
	{
		/**** BIOME INITIALIZATION / REGISTRATION ****/

		/* BASIC BIOMES */
		ocean = new BiomeOcean(BiomeInfo.ocean, 0.5F, 0.5F);
		ocean_lukewarm = new BiomeOcean(BiomeInfo.ocean_lukewarm, 0.6F, 0.5F);
		ocean_warm = new BiomeOcean(BiomeInfo.ocean_warm, 0.8F, 0.5F);
		ocean_cold = new BiomeOcean(BiomeInfo.ocean_cold, 0.3F, 0.5F);
		ocean_frozen = (new BiomeOcean(BiomeInfo.ocean_frozen, 0.1F, 0.5F)).setEnableSnow();

		beach_frozen = new BiomeBeach(BiomeInfo.beach_frozen, 0.0F, 0.4F, BiomeBeach.TYPE_STONE);
		beach_gravel = new BiomeBeach(BiomeInfo.beach_gravel, 0.2F, 0.4F, BiomeBeach.TYPE_GRAVEL);
		beach = new BiomeBeach(BiomeInfo.beach, 0.8F, 0.4F, BiomeBeach.TYPE_NORMAL);
		beach_tropical = new BiomeBeach(BiomeInfo.beach_tropical, 1.2F, 0.3F, BiomeBeach.TYPE_TROPICAL);

		river = new BiomeRiver(BiomeInfo.river, 0.5F, 0.5F, false);
		river_frozen = (new BiomeRiver(BiomeInfo.river_frozen, 0.0F, 0.5F, false)).setEnableSnow();
		river_swampy = new BiomeRiver(BiomeInfo.river_swampy, 0.6F, 0.7F, true);

		lake = new BiomeRiver(BiomeInfo.lake, 0.5F, 0.5F, false);
		lake_frozen = (new BiomeRiver(BiomeInfo.lake_frozen, 0.0F, 0.5F, false)).setEnableSnow();

		oasis = new BiomeOasis(BiomeInfo.oasis, 1.5F, 0.5F);

		dark_swamp = new BiomeDarkSwamp(BiomeInfo.dark_swamp, 0.8F, 0.9F);
		/* reserved: mangrove swamp (010) */
		cypress_swamp = new BiomeCypressSwamp(BiomeInfo.cypress_swamp, 0.8F, 0.9F);

		marsh_green = new BiomeMarsh(BiomeInfo.marsh_green, 0.45F, 0.7F, 0);
		marsh_yellow = new BiomeMarsh(BiomeInfo.marsh_yellow, 0.45F, 0.65F, 1);

		bog_green = new BiomeBog(BiomeInfo.bog_green, 0.35F, 0.5F, 0);
		bog_yellow = new BiomeBog(BiomeInfo.bog_yellow, 0.35F, 0.45F, 1);
		/* reserved: peat bog (016) */

		fen_green = new BiomeFen(BiomeInfo.fen_green, 0.55F, 0.8F, 0);
		fen_yellow = new BiomeFen(BiomeInfo.fen_yellow, 0.55F, 0.75F, 1);

		grassland = new BiomeGrassland(BiomeInfo.grassland, 0.8F, 0.4F, BiomeGrassland.TYPE_PLAIN);
		sunflower_plains = new BiomeGrassland(BiomeInfo.sunflower_plains, 0.8F, 0.4F, BiomeGrassland.TYPE_SUNFLOWER);
		dry_grassland = new BiomeGrassland(BiomeInfo.dry_grassland, 0.8F, 0.25F, BiomeGrassland.TYPE_DRY);
		cold_grassland = new BiomeGrassland(BiomeInfo.cold_grassland, 0.4F, 0.4F, BiomeGrassland.TYPE_COLD);

		sparse_temperate_mountains = new BiomeSparseTemperateMountains(BiomeInfo.sparse_temperate_mountains, 0.6F, 0.5F);
		sparse_plateau = new BiomeSparseTemperateMountains(BiomeInfo.sparse_plateau, 0.6F, 0.5F);

		oak_chaparral = new BiomeOakForest(BiomeInfo.oak_chaparral, 0.7F, 0.6F, BiomeOakForest.CHAPARRAL);
		oak_forest = new BiomeOakForest(BiomeInfo.oak_forest, 0.7F, 0.8F, BiomeOakForest.NORMAL);
		flower_chaparral = new BiomeOakForest(BiomeInfo.flower_chaparral, 0.7F, 0.633F, BiomeOakForest.FLOWER_CHAPARRAL);
		flower_forest = new BiomeOakForest(BiomeInfo.flower_forest, 0.7F, 0.833F, BiomeOakForest.FLOWER);

		dark_oak_forest = new BiomeDarkOakForest(BiomeInfo.dark_oak_forest, 0.7F, 0.8F);

		birch_chaparral = new BiomeBirchForest(BiomeInfo.birch_chaparral, 0.6F, 0.55F, BiomeBirchForest.CHAPARRAL);
		birch_forest = new BiomeBirchForest(BiomeInfo.birch_forest, 0.6F, 0.65F, BiomeBirchForest.NORMAL);
		old_growth_birch_forest = new BiomeBirchForest(BiomeInfo.old_growth_birch_forest, 0.6F, 0.65F, BiomeBirchForest.OLD_GROWTH);

		/* reserved: maple (027, 028, 029) */
		/* reserved: yellow autumn maple (030, 031, 032) */
		/* reserved: red autumn maple (033, 034, 035) */

		stony_peaks = new BiomeMountains(BiomeInfo.stony_peaks, 0.45F, 0.35F);
		mountains = new BiomeMountains(BiomeInfo.mountains, 0.25F, 0.55F);
		frozen_mountains = (new BiomeMountains(BiomeInfo.frozen_mountains, -0.55F, 0.55F)).setEnableSnow();

		gravel_plateau = new BiomeWasteland(BiomeInfo.gravel_plateau, 0.3F, 0.3F);
		snowy_plateau = (new BiomeTundra(BiomeInfo.snowy_plateau, -0.45F, 0.4F, false)).setEnableSnow();

		wasteland = new BiomeWasteland(BiomeInfo.wasteland, 0.4F, 0.0F);
		frozen_wasteland = (new BiomeFrozenWasteland(BiomeInfo.frozen_wasteland, -0.7F, 0.0F)).setEnableSnow();
		tundra = (new BiomeTundra(BiomeInfo.tundra, -0.15F, 0.4F, false)).setEnableSnow();
		ice_spikes = (new BiomeTundra(BiomeInfo.ice_spikes, -0.25F, 0.6F, true)).setEnableSnow();

		spruce_forest = new BiomeSpruceForest(BiomeInfo.spruce_forest, 0.35F, 0.7F, false);
		old_growth_spruce_forest = new BiomeSpruceForest(BiomeInfo.old_growth_spruce_forest, 0.35F, 0.85F, true);
		snowy_spruce_forest = (new BiomeSpruceForest(BiomeInfo.snowy_spruce_forest, -0.25F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_spruce_forest = (new BiomeSpruceForest(BiomeInfo.snowy_old_growth_spruce_forest, -0.25F, 0.85F, true)).setEnableSnow();

		pine_forest = new BiomePineForest(BiomeInfo.pine_forest, 0.35F, 0.7F, false);
		old_growth_pine_forest = new BiomePineForest(BiomeInfo.old_growth_pine_forest, 0.35F, 0.85F, true);
		snowy_pine_forest = (new BiomePineForest(BiomeInfo.snowy_pine_forest, -0.25F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_pine_forest = (new BiomePineForest(BiomeInfo.snowy_old_growth_pine_forest, -0.25F, 0.85F, true)).setEnableSnow();

		cedar_forest = new BiomeCedarForest(BiomeInfo.cedar_forest, 0.35F, 0.7F, false);
		old_growth_cedar_forest = new BiomeCedarForest(BiomeInfo.old_growth_cedar_forest, 0.35F, 0.85F, true);
		snowy_cedar_forest = (new BiomeCedarForest(BiomeInfo.snowy_cedar_forest, -0.25F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_cedar_forest = (new BiomeCedarForest(BiomeInfo.snowy_old_growth_cedar_forest, -0.25F, 0.85F, true)).setEnableSnow();

		/* reserved: redwood (04b, 04c, 04d, 04e) */
		/* reserved: fir (04f, 050, 051, 052) */

		desert = (new BiomeDesert(BiomeInfo.desert, 2.0F, 0.0F)).setDisableRain();
		desert_mountains = (new BiomeDesertMountains(BiomeInfo.desert_mountains, 1.6F, 0.0F)).setDisableRain();
		desert_plateau = (new BiomeDesertMountains(BiomeInfo.desert_plateau, 1.7F, 0.0F)).setDisableRain();

		jungle = new BiomeJungle(BiomeInfo.jungle, 0.95F, 0.9F, 0);
		jungle_plateau = new BiomeJungle(BiomeInfo.jungle_plateau, 0.7F, 0.9F, 1);

		savanna = (new BiomeSavanna(BiomeInfo.savanna, 1.2F, 0.0F)).setDisableRain();
		savanna_plateau = (new BiomeSavanna(BiomeInfo.savanna_plateau, 1.0F, 0.0F)).setDisableRain();

		/* MUSHROOM BIOMES */

		mushroom_shore = new BiomeMushroomField(BiomeInfo.mushroom_shore, 0.8F, 0.7F, 1);
		mushroom_mountains = new BiomeMushroomMountains(BiomeInfo.mushroom_mountains, 0.45F, 0.7F);
		mushroom_plateau = new BiomeMushroomMountains(BiomeInfo.mushroom_plateau, 0.6F, 0.7F);

		mushroom_desert = new BiomeMushroomField(BiomeInfo.mushroom_desert, 1.5F, 0.5F, 0);
		mushroom_field = new BiomeMushroomField(BiomeInfo.mushroom_field, 0.8F, 0.7F, 1);
		mushroom_forest = new BiomeMushroomField(BiomeInfo.mushroom_forest, 0.8F, 1.0F, 2);
		mushroom_tundra = (new BiomeMushroomField(BiomeInfo.mushroom_tundra, 0.0F, 1.0F, 0)).setEnableSnow();

		/* MAGICAL BIOMES */

		vibrant_forest = new BiomeVibrantForest(BiomeInfo.vibrant_forest, 0.7F, 0.8F);
		enchanted_ash_forest = new BiomeEnchantedAshForest(BiomeInfo.enchanted_ash_forest, 0.5F, 0.7F);

		rainbow_forest = new BiomeRainbowForest(BiomeInfo.rainbow_forest, 0.9F, 0.9F, false);
		rainbow_jungle = new BiomeRainbowForest(BiomeInfo.rainbow_jungle, 0.9F, 1.0F, true);

		violet_forest = new BiomeVioletForest(BiomeInfo.violet_forest, 0.3F, 0.7F, false);
		old_growth_violet_forest = new BiomeVioletForest(BiomeInfo.old_growth_violet_forest, 0.3F, 0.85F, true);
		snowy_violet_forest = (new BiomeVioletForest(BiomeInfo.snowy_violet_forest, 0.0F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_violet_forest = (new BiomeVioletForest(BiomeInfo.snowy_old_growth_violet_forest, 0.0F, 0.85F, true)).setEnableSnow();

		/**** BIOME DICTIONARY REGISTRATION ****/

		/* BASIC BIOMES */

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

		add(marsh_green, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP);
		add(marsh_yellow, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP);

		add(bog_green, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP, Type.COLD);
		add(bog_yellow, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP, Type.COLD);

		add(fen_green, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP);
		add(fen_yellow, BiomeType.COOL, false, false, true, Type.WET, Type.SWAMP);

		add(grassland, BiomeType.WARM, true, true, true, Type.PLAINS);
		add(sunflower_plains, BiomeType.WARM, true, true, true, Type.PLAINS);
		add(dry_grassland, BiomeType.WARM, true, true, true, Type.PLAINS);
		add(cold_grassland, BiomeType.COOL, true, true, true, Type.PLAINS);

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

		add(stony_peaks, BiomeType.COOL, false, false, true, Type.MOUNTAIN, Type.SPARSE);
		add(mountains, BiomeType.COOL, false, false, true, Type.MOUNTAIN, Type.SPARSE, Type.COLD);
		add(frozen_mountains, BiomeType.ICY, false, false, true, Type.MOUNTAIN, Type.SPARSE, Type.COLD, Type.SNOWY);

		add(gravel_plateau, BiomeType.COOL, false, false, false, Type.WASTELAND, Type.HILLS, Type.COLD);
		add(snowy_plateau, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.HILLS, Type.COLD, Type.SNOWY);

		add(frozen_wasteland, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY);
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
		add(jungle_plateau, BiomeType.WARM, true, true, true, Type.WET, Type.DENSE, Type.JUNGLE, Type.HILLS);

		add(savanna, BiomeType.WARM, true, true, true, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		add(savanna_plateau, BiomeType.WARM, true, false, true, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE, Type.HILLS);

		/* MUSHROOM BIOMES */

		add(mushroom_shore, BiomeType.WARM, false, false, false, Type.MUSHROOM);
		add(mushroom_mountains, BiomeType.COOL, false, false, false, Type.MUSHROOM, Type.MOUNTAIN);
		add(mushroom_plateau, BiomeType.COOL, false, false, false, Type.MUSHROOM, Type.HILLS);

		add(mushroom_desert, BiomeType.DESERT, false, false, false, Type.MUSHROOM, Type.HOT);
		add(mushroom_field, BiomeType.WARM, false, false, false, Type.MUSHROOM);
		add(mushroom_forest, BiomeType.WARM, false, false, false, Type.MUSHROOM);
		add(mushroom_tundra, BiomeType.ICY, false, false, false, Type.MUSHROOM, Type.COLD, Type.SNOWY);

		/* MAGICAL BIOMES */

		add(vibrant_forest, BiomeType.WARM, false, false, true, Type.FOREST, Type.MAGICAL, Type.LUSH);
		add(enchanted_ash_forest, BiomeType.COOL, false, false, true, Type.FOREST, Type.MAGICAL);

		add(rainbow_forest, BiomeType.WARM, false, false, true, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.JUNGLE);
		add(rainbow_jungle, BiomeType.WARM, false, false, true, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.JUNGLE);

		add(violet_forest, BiomeType.COOL, false, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(old_growth_violet_forest, BiomeType.COOL, false, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(snowy_violet_forest, BiomeType.COOL, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_old_growth_violet_forest, BiomeType.COOL, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);

		/**** OTHER STUFF ****/

		/* Init GenLayer stuff */
		GenLayerLongBiomes.ocean_biomes = new BiomeCRWGBase[][] {
			{ocean_frozen, ocean_frozen, ocean_frozen},
			{ocean_cold, ocean_cold, ocean_cold},
			{ocean, ocean, ocean},
			{ocean_lukewarm, ocean_lukewarm, ocean_lukewarm},
			{ocean_warm, ocean_warm, ocean_warm}
		};
		GenLayerLongBiomes.beach_biomes = new BiomeCRWGBase[][] {
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

		/* MUSHROOM BIOME TYPES */

		BiomeTypeCRWG.MUSHROOM_DESERT.addGroupOf(mushroom_desert, mushroom_mountains, mushroom_plateau);

		BiomeTypeCRWG.MUSHROOM_FIELD.addGroupOf(mushroom_field, mushroom_mountains, mushroom_plateau);

		BiomeTypeCRWG.MUSHROOM_FOREST.addGroupOf(mushroom_forest, mushroom_mountains, mushroom_plateau);

		BiomeTypeCRWG.MUSHROOM_TUNDRA.addGroupOf(mushroom_tundra, mushroom_mountains, mushroom_plateau);

		/* BASIC BIOME TYPES */

		BiomeTypeCRWG.MESA.addGroupOf(desert, desert_mountains, desert_plateau);

		BiomeTypeCRWG.DESERT.addGroupOf(desert, desert_mountains, desert_plateau);

		BiomeTypeCRWG.SAVANNA.addGroupOf(savanna, stony_peaks, savanna_plateau);

		BiomeTypeCRWG.SCRUBLAND.addGroupOf(dry_grassland, stony_peaks, savanna_plateau);

		BiomeTypeCRWG.TROPICAL_FOREST.addGroupOf(dark_oak_forest, sparse_temperate_mountains, sparse_plateau);

		BiomeTypeCRWG.RAINFOREST.addGroupOf(jungle, stony_peaks, jungle_plateau);

		BiomeTypeCRWG.WASTELAND.addGroupOf(wasteland, mountains, gravel_plateau);

		BiomeTypeCRWG.COOLER_DESERT.addGroupOf(desert, desert_mountains, desert_plateau);

		BiomeTypeCRWG.DRY_GRASSLAND.addGroupOf(dry_grassland, stony_peaks, gravel_plateau);

		BiomeTypeCRWG.GRASSLAND.addGroupOf(grassland, mountains, gravel_plateau);
		BiomeTypeCRWG.GRASSLAND.addGroupOf(sunflower_plains, mountains, gravel_plateau);

		BiomeTypeCRWG.CHAPARRAL.addGroupOf(oak_chaparral, mountains, gravel_plateau);
		BiomeTypeCRWG.CHAPARRAL.addGroupOf(flower_chaparral, mountains, gravel_plateau);
		BiomeTypeCRWG.CHAPARRAL.addGroupOf(birch_chaparral, mountains, gravel_plateau);

		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(oak_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(flower_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(birch_forest, mountains, gravel_plateau);

		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(dark_oak_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(flower_forest, mountains, gravel_plateau);
		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(old_growth_birch_forest, mountains, gravel_plateau);

		BiomeTypeCRWG.COLD_GRASSLAND.addGroupOf(cold_grassland, frozen_mountains, snowy_plateau);

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

		BiomeTypeCRWG.FROZEN_WASTELAND.addGroupOf(frozen_wasteland, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.TUNDRA.addGroupOf(tundra, frozen_mountains, snowy_plateau);

		/* MAGICAL BIOME TYPES */

		BiomeTypeCRWG.SPECIAL_TUNDRA.addGroupOf(ice_spikes, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.VIBRANT_FOREST.addGroupOf(vibrant_forest, mountains, sparse_plateau);
		BiomeTypeCRWG.VIBRANT_FOREST.addGroupOf(enchanted_ash_forest, mountains, sparse_plateau);

		BiomeTypeCRWG.COLORFUL_RAINFOREST.addGroupOf(rainbow_forest, sparse_temperate_mountains, sparse_plateau);
		BiomeTypeCRWG.COLORFUL_RAINFOREST.addGroupOf(rainbow_jungle, sparse_temperate_mountains, sparse_plateau);

		BiomeTypeCRWG.COLORFUL_BOREAL_FOREST.addGroupOf(violet_forest, mountains, gravel_plateau);

		BiomeTypeCRWG.COLORFUL_DENSE_BOREAL_FOREST.addGroupOf(old_growth_violet_forest, mountains, gravel_plateau);

		BiomeTypeCRWG.COLORFUL_SNOWY_BOREAL_FOREST.addGroupOf(snowy_violet_forest, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.COLORFUL_SNOWY_DENSE_BOREAL_FOREST.addGroupOf(snowy_old_growth_violet_forest, frozen_mountains, snowy_plateau);
	}
}

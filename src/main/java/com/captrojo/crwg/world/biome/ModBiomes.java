package com.captrojo.crwg.world.biome;

import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
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

	public static void initBiomes()
	{
		/* BASIC BIOMES */
		ocean = new BiomeCRWGOcean(0x000, "Ocean", 0x000070, 0.5F, 0.5F);
		ocean_lukewarm = new BiomeCRWGOcean(0x001, "Lukewarm Ocean", 0x102070, 0.6F, 0.5F);
		ocean_warm = new BiomeCRWGOcean(0x002, "Warm Ocean", 0x204070, 0.8F, 0.5F);
		ocean_cold = new BiomeCRWGOcean(0x003, "Cold Ocean", 0x080860, 0.3F, 0.5F);
		ocean_frozen = (new BiomeCRWGOcean(0x004, "Frozen Ocean", 0x181850, 0.1F, 0.5F)).setEnableSnow();

		beach_frozen = new BiomeCRWGBeach(0x005, "Cold Beach", 0xa2a284, 0.0F, 0.4F, BiomeCRWGBeach.TYPE_STONE);
		beach_gravel = new BiomeCRWGBeach(0x006, "Gravel Beach", 0xa3a392, 0.2F, 0.4F, BiomeCRWGBeach.TYPE_GRAVEL);
		beach = new BiomeCRWGBeach(0x007, "Beach", 0xc6c66f, 0.8F, 0.4F, BiomeCRWGBeach.TYPE_NORMAL);
		beach_tropical = new BiomeCRWGBeach(0x008, "Tropical Beach", 0xe9f46b, 1.2F, 0.3F, BiomeCRWGBeach.TYPE_TROPICAL);

		river = new BiomeCRWGRiver(0x009, "River", 0x0000ff, 0.5F, 0.5F, false);
		river_frozen = (new BiomeCRWGRiver(0x00a, "Frozen River", 0x5050ff, 0.0F, 0.5F, false)).setEnableSnow();
		river_swampy = new BiomeCRWGRiver(0x00b, "Swampy River", 0x0040ff, 0.6F, 0.7F, true);

		lake = new BiomeCRWGRiver(0x00c, "Lake", 0x2020f0, 0.5F, 0.5F, false);
		lake_frozen = (new BiomeCRWGRiver(0x00d, "Frozen Lake", 0x4040f0, 0.0F, 0.5F, false)).setEnableSnow();

		oasis = new BiomeOasis(0x00e, "Oasis", 0xc9f918, 1.5F, 0.5F);

		dark_swamp = new BiomeDarkSwamp(0x00f, "Dark Swamp", 0x07f9b2, 0.8F, 0.9F);
		/* reserved: mangrove swamp (010) */
		cypress_swamp = new BiomeCypressSwamp(0x011, "Cypress Swamp", 0x07f998, 0.8F, 0.9F);

		marsh_green = new BiomeMarsh(0x012, "Marsh G", 0x7b8440, 0.45F, 0.7F, 0);
		marsh_yellow = new BiomeMarsh(0x013, "Marsh Y", 0x628440, 0.45F, 0.65F, 1);

		bog_green = new BiomeBog(0x014, "Bog G", 0xc4d14f, 0.35F, 0.5F, 0);
		bog_yellow = new BiomeBog(0x015, "Bog Y", 0x93852a, 0.35F, 0.45F, 1);
		/* reserved: peat bog (016) */

		fen_green = new BiomeFen(0x017, "Fen G", 0x88b759, 0.55F, 0.8F, 0);
		fen_yellow = new BiomeFen(0x018, "Fen Y", 0xabb759, 0.55F, 0.75F, 1);

		grassland = new BiomeGrassland(0x019, "Grassland", 0x80ff50, 0.8F, 0.4F, BiomeGrassland.TYPE_PLAIN);
		sunflower_plains = new BiomeGrassland(0x01a, "Sunflower Plains", 0x9aff68, 0.8F, 0.4F, BiomeGrassland.TYPE_SUNFLOWER);
		dry_grassland = new BiomeGrassland(0x01b, "Dry Grassland", 0xbde547, 0.8F, 0.25F, BiomeGrassland.TYPE_DRY);
		cold_grassland = new BiomeGrassland(0x01c, "Cold Grassland", 0x5be572, 0.4F, 0.4F, BiomeGrassland.TYPE_COLD);

		sparse_temperate_mountains = new BiomeSparseTemperateMountains(0x01d, "Sparse Temperate Mountains");
		sparse_plateau = new BiomeSparseTemperateMountains(0x01e, "Sparse Plateau");

		oak_chaparral = new BiomeOakForest(0x01f, "Oak Chaparral", 0x056621, 0.7F, 0.6F, BiomeOakForest.CHAPARRAL);
		oak_forest = new BiomeOakForest(0x020, "Oak Forest", 0x056621, 0.7F, 0.8F, BiomeOakForest.NORMAL);
		flower_chaparral = new BiomeOakForest(0x021, "Flower Chaparral", 0x056621, 0.7F, 0.633F, BiomeOakForest.FLOWER_CHAPARRAL);
		flower_forest = new BiomeOakForest(0x022, "Flower Forest", 0x056621, 0.7F, 0.833F, BiomeOakForest.FLOWER);

		dark_oak_forest = new BiomeDarkOakForest(0x023, "Dark Oak Forest", 0x40511a, 0.7F, 0.8F);

		birch_chaparral = new BiomeBirchForest(0x024, "Birch Chaparral", 0x307444, 0.6F, 0.55F, BiomeBirchForest.CHAPARRAL);
		birch_forest = new BiomeBirchForest(0x025, "Birch Forest", 0x307444, 0.6F, 0.65F, BiomeBirchForest.NORMAL);
		old_growth_birch_forest = new BiomeBirchForest(0x026, "Old Growth Birch Forest", 0x307444, 0.6F, 0.65F, BiomeBirchForest.OLD_GROWTH);

		/* reserved: maple (027, 028, 029) */
		/* reserved: yellow autumn maple (030, 031, 032) */
		/* reserved: red autumn maple (033, 034, 035) */

		stony_peaks = new BiomeMountains(0x036, "Stony Peaks", 0x606060, 0.45F, 0.35F);
		mountains = new BiomeMountains(0x037, "Mountains", 0x808080, 0.25F, 0.55F);
		frozen_mountains = (new BiomeMountains(0x038, "Frozen Mountains", 0xa0a0c0, -0.55F, 0.55F)).setEnableSnow();

		gravel_plateau = new BiomeWasteland(0x039, "Gravel Plateau", 0xa0a0a0, 0.3F, 0.3F);
		snowy_plateau = (new BiomeTundra(0x03a, "Snowy Plateau", 0xdfffff, -0.45F, 0.4F, false)).setEnableSnow();

		wasteland = new BiomeWasteland(0x03b, "Wasteland", 0x404040, 0.4F, 0.0F);
		frozen_wasteland = (new BiomeFrozenWasteland(0x03c, "Frozen Wasteland", 0xd7efef, -0.7F, 0.0F)).setEnableSnow();
		tundra = (new BiomeTundra(0x03d, "Tundra", 0xc0f0f0, -0.15F, 0.4F, false)).setEnableSnow();
		ice_spikes = (new BiomeTundra(0x03e, "Ice Spike Tundra", 0xb0e0f0, -0.25F, 0.6F, true)).setEnableSnow();

		spruce_forest = new BiomeSpruceForest(0x03f, "Spruce Forest", 0x0b6659, 0.35F, 0.7F, false);
		old_growth_spruce_forest = new BiomeSpruceForest(0x040, "Old Growth Spruce Forest", 0x596651, 0.35F, 0.85F, true);
		snowy_spruce_forest = (new BiomeSpruceForest(0x041, "Snowy Spruce Forest", 0x0b6659, -0.25F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_spruce_forest = (new BiomeSpruceForest(0x042, "Snowy Old Growth Spruce Forest", 0x596651, -0.25F, 0.85F, true)).setEnableSnow();

		pine_forest = new BiomePineForest(0x043, "Pine Forest", 0x0b663e, 0.35F, 0.7F, false);
		old_growth_pine_forest = new BiomePineForest(0x044, "Old Growth Pine Forest", 0x5e6650, 0.35F, 0.85F, true);
		snowy_pine_forest = (new BiomePineForest(0x045, "Snowy Pine Forest", 0x0b663e, -0.25F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_pine_forest = (new BiomePineForest(0x046, "Snowy Old Growth Pine Forest", 0x5e6650, -0.25F, 0.85F, true)).setEnableSnow();

		cedar_forest = new BiomeCedarForest(0x047, "Cedar Forest", 0x226035, 0.35F, 0.7F, false);
		old_growth_cedar_forest = new BiomeCedarForest(0x048, "Old Growth Cedar Forest", 0x52664f, 0.35F, 0.85F, true);
		snowy_cedar_forest = (new BiomeCedarForest(0x049, "Snowy Cedar Forest", 0x426035, -0.25F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_cedar_forest = (new BiomeCedarForest(0x04a, "Snowy Old Growth Cedar Forest", 0x52664f, -0.25F, 0.85F, true)).setEnableSnow();

		/* reserved: redwood (04b, 04c, 04d, 04e) */
		/* reserved: fir (04f, 050, 051, 052) */

		desert = (new BiomeDesert(0x053, "Desert", 0xfa9418, 2.0F, 0.0F)).setDisableRain();
		desert_mountains = (new BiomeDesertMountains(0x054, "Desert Mountains", 0xffd458, 1.6F, 0.0F)).setDisableRain();
		desert_plateau = (new BiomeDesertMountains(0x055, "Desert Plateau", 0xffc448, 1.7F, 0.0F)).setDisableRain();

		jungle = new BiomeJungle(0x056, "Jungle", 0x537b09, 0.95F, 0.9F, 0);
		jungle_plateau = new BiomeJungle(0x057, "Jungle Plateau", 0x93bb49, 0.7F, 0.9F, 1);

		savanna = (new BiomeSavanna(0x058, "Savanna", 0xbdb25f, 1.2F, 0.0F)).setDisableRain();
		savanna_plateau = (new BiomeSavanna(0x059, "Savanna Plateau", 0xa79d64, 1.0F, 0.0F)).setDisableRain();

		/* MUSHROOM BIOMES */

		mushroom_shore = new BiomeMushroomField(0x100, "Mushroom Beach", 0xa000ff, 0.8F, 0.7F, 1);
		mushroom_mountains = new BiomeMushroomMountains(0x101, "Mushroom Mountains", 0xe020e0, 0.45F, 0.7F);
		mushroom_plateau = new BiomeMushroomMountains(0x102, "Mushroom Plateau", 0xe010e0, 0.6F, 0.7F);

		mushroom_desert = new BiomeMushroomField(0x103, "Mushroom Wasteland", 0xff00c0, 1.5F, 0.5F, 0);
		mushroom_field = new BiomeMushroomField(0x104, "Mushroom Field", 0xff00ff, 0.8F, 0.7F, 1);
		mushroom_forest = new BiomeMushroomField(0x105, "Mushroom Forest", 0xd000d0, 0.8F, 1.0F, 2);
		mushroom_tundra = (new BiomeMushroomField(0x106, "Mushroom Tundra", 0xff70ff, 0.0F, 1.0F, 0)).setEnableSnow();

		/* MAGICAL BIOMES */

		vibrant_forest = new BiomeVibrantForest(0x200, "Vibrant Forest", 0x30c965, 0.7F, 0.8F);
		enchanted_ash_forest = new BiomeEnchantedAshForest(0x201, "Enchanted Ash Forest", 0x80fcdd, 0.5F, 0.7F);

		rainbow_forest = new BiomeRainbowForest(0x202, "Rainbow Forest", 0x6de88f, 0.9F, 0.9F, false);
		rainbow_jungle = new BiomeRainbowForest(0x203, "Rainbow Jungle", 0x6de8b4, 0.9F, 1.0F, true);

		violet_forest = new BiomeVioletForest(0x204, "Violet Forest", 0x3c2a89, 0.3F, 0.7F, false);
		old_growth_violet_forest = new BiomeVioletForest(0x205, "Old Growth Violet Forest", 0x3e3663, 0.3F, 0.85F, true);
		snowy_violet_forest = (new BiomeVioletForest(0x206, "Snowy Violet Forest", 0x604faf, 0.0F, 0.7F, false)).setEnableSnow();
		snowy_old_growth_violet_forest = (new BiomeVioletForest(0x207, "Snowy Old Growth Violet Forest", 0x69628c, 0.0F, 0.85F, true)).setEnableSnow();
	}

	public static void registerBiomes()
	{
		/**** BIOME REGISTRATION ****/

		for (BiomeCRWGBase biome : BiomeCRWGBase.BIOMES) {
			biome.register();
		}

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

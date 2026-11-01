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
	public static BiomeCRWGBase ocean_deep;
	public static BiomeCRWGBase ocean_shallow;
	public static BiomeCRWGBase ocean_lukewarm;
	public static BiomeCRWGBase ocean_lukewarm_deep;
	public static BiomeCRWGBase ocean_lukewarm_shallow;
	public static BiomeCRWGBase ocean_warm;
	public static BiomeCRWGBase ocean_warm_deep;
	public static BiomeCRWGBase ocean_warm_shallow;
	public static BiomeCRWGBase ocean_cold;
	public static BiomeCRWGBase ocean_cold_deep;
	public static BiomeCRWGBase ocean_cold_shallow;
	public static BiomeCRWGBase ocean_frozen;
	public static BiomeCRWGBase ocean_frozen_deep;
	public static BiomeCRWGBase ocean_frozen_shallow;

	public static BiomeCRWGBase beach_frozen;
	public static BiomeCRWGBase beach_gravel;
	public static BiomeCRWGBase beach;
	public static BiomeCRWGBase beach_tropical;

	public static BiomeCRWGBase river;
	public static BiomeCRWGBase river_frozen;
	public static BiomeCRWGBase river_swampy;

	public static BiomeCRWGBase lake;
	public static BiomeCRWGBase lake_frozen;

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
	public static BiomeCRWGBase grass_hills;
	public static BiomeCRWGBase grass_highlands;
	public static BiomeCRWGBase grassland_sunflower;
	public static BiomeCRWGBase grass_hills_sunflower;
	public static BiomeCRWGBase grass_highlands_sunflower;

	public static BiomeCRWGBase sparse_temperate_mountains;
	public static BiomeCRWGBase sparse_plateau;

	public static BiomeCRWGBase oak_chaparral;
	public static BiomeCRWGBase oak_chaparral_hills;
	public static BiomeCRWGBase oak_chaparral_highlands;
	public static BiomeCRWGBase oak_forest;
	public static BiomeCRWGBase oak_forest_hills;
	public static BiomeCRWGBase oak_forest_highlands;
	public static BiomeCRWGBase flower_chaparral;
	public static BiomeCRWGBase flower_chaparral_hills;
	public static BiomeCRWGBase flower_chaparral_highlands;
	public static BiomeCRWGBase flower_forest;
	public static BiomeCRWGBase flower_forest_hills;
	public static BiomeCRWGBase flower_forest_highlands;

	public static BiomeCRWGBase dark_oak_forest;
	public static BiomeCRWGBase dark_oak_forest_hills;
	public static BiomeCRWGBase dark_oak_forest_highlands;

	public static BiomeCRWGBase birch_chaparral;
	public static BiomeCRWGBase birch_chaparral_hills;
	public static BiomeCRWGBase birch_chaparral_highlands;
	public static BiomeCRWGBase birch_forest;
	public static BiomeCRWGBase birch_forest_hills;
	public static BiomeCRWGBase birch_forest_highlands;
	public static BiomeCRWGBase old_growth_birch_forest;
	public static BiomeCRWGBase old_growth_birch_forest_hills;
	public static BiomeCRWGBase old_growth_birch_forest_highlands;

	public static BiomeCRWGBase mountains;
	public static BiomeCRWGBase frozen_mountains;

	public static BiomeCRWGBase gravel_plateau;
	public static BiomeCRWGBase snowy_plateau;

	public static BiomeCRWGBase tundra;
	public static BiomeCRWGBase tundra_hills;
	public static BiomeCRWGBase tundra_highlands;
	public static BiomeCRWGBase ice_spikes;
	public static BiomeCRWGBase ice_spikes_hills;
	public static BiomeCRWGBase ice_spikes_highlands;

	public static BiomeCRWGBase spruce_forest;
	public static BiomeCRWGBase spruce_forest_hills;
	public static BiomeCRWGBase spruce_forest_highlands;
	public static BiomeCRWGBase old_growth_spruce_forest;
	public static BiomeCRWGBase old_growth_spruce_forest_hills;
	public static BiomeCRWGBase old_growth_spruce_forest_highlands;
	public static BiomeCRWGBase snowy_spruce_forest;
	public static BiomeCRWGBase snowy_spruce_forest_hills;
	public static BiomeCRWGBase snowy_spruce_forest_highlands;
	public static BiomeCRWGBase snowy_old_growth_spruce_forest;
	public static BiomeCRWGBase snowy_old_growth_spruce_forest_hills;
	public static BiomeCRWGBase snowy_old_growth_spruce_forest_highlands;

	public static BiomeCRWGBase pine_forest;
	public static BiomeCRWGBase pine_forest_hills;
	public static BiomeCRWGBase pine_forest_highlands;
	public static BiomeCRWGBase old_growth_pine_forest;
	public static BiomeCRWGBase old_growth_pine_forest_hills;
	public static BiomeCRWGBase old_growth_pine_forest_highlands;
	public static BiomeCRWGBase snowy_pine_forest;
	public static BiomeCRWGBase snowy_pine_forest_hills;
	public static BiomeCRWGBase snowy_pine_forest_highlands;
	public static BiomeCRWGBase snowy_old_growth_pine_forest;
	public static BiomeCRWGBase snowy_old_growth_pine_forest_hills;
	public static BiomeCRWGBase snowy_old_growth_pine_forest_highlands;

	public static BiomeCRWGBase cedar_forest;
	public static BiomeCRWGBase cedar_forest_hills;
	public static BiomeCRWGBase cedar_forest_highlands;
	public static BiomeCRWGBase old_growth_cedar_forest;
	public static BiomeCRWGBase old_growth_cedar_forest_hills;
	public static BiomeCRWGBase old_growth_cedar_forest_highlands;
	public static BiomeCRWGBase snowy_cedar_forest;
	public static BiomeCRWGBase snowy_cedar_forest_hills;
	public static BiomeCRWGBase snowy_cedar_forest_highlands;
	public static BiomeCRWGBase snowy_old_growth_cedar_forest;
	public static BiomeCRWGBase snowy_old_growth_cedar_forest_hills;
	public static BiomeCRWGBase snowy_old_growth_cedar_forest_highlands;

	public static BiomeCRWGBase desert;
	public static BiomeCRWGBase desert_hills;
	public static BiomeCRWGBase desert_highlands;
	public static BiomeCRWGBase desert_mountains;
	public static BiomeCRWGBase desert_plateau;

	public static BiomeCRWGBase jungle;
	public static BiomeCRWGBase jungle_hills;
	public static BiomeCRWGBase jungle_highlands;
	public static BiomeCRWGBase jungle_mountains;
	public static BiomeCRWGBase jungle_plateau;

	public static void registerBiomes()
	{
		/* Create CRWG biomes */
		ocean = new BiomeCRWGOcean(0x000, "Ocean", 0x000070, 0.5F, 0.5F);
		ocean_deep = new BiomeCRWGOcean(0x001, "Deep Ocean", 0x000050, 0.5F, 0.5F);
		ocean_shallow = new BiomeCRWGOcean(0x002, "Shallow Ocean", 0x000090, 0.5F, 0.5F);
		ocean_lukewarm = new BiomeCRWGOcean(0x003, "Lukewarm Ocean", 0x102070, 0.6F, 0.5F);
		ocean_lukewarm_deep = new BiomeCRWGOcean(0x004, "Deep Lukewarm Ocean", 0x102050, 0.6F, 0.5F);
		ocean_lukewarm_shallow = new BiomeCRWGOcean(0x005, "Shallow Lukewarm Ocean", 0x102090, 0.6F, 0.5F);
		ocean_warm = new BiomeCRWGOcean(0x006, "Warm Ocean", 0x204070, 0.8F, 0.5F);
		ocean_warm_deep = new BiomeCRWGOcean(0x007, "Deep Warm Ocean", 0x204050, 0.8F, 0.5F);
		ocean_warm_shallow = new BiomeCRWGOcean(0x008, "Shallow Warm Ocean", 0x204090, 0.8F, 0.5F);
		ocean_cold = new BiomeCRWGOcean(0x009, "Cold Ocean", 0x080860, 0.3F, 0.5F);
		ocean_cold_deep = new BiomeCRWGOcean(0x00a, "Deep Cold Ocean", 0x080840, 0.3F, 0.5F);
		ocean_cold_shallow = new BiomeCRWGOcean(0x00b, "Shallow Cold Ocean", 0x080880, 0.3F, 0.5F);
		ocean_frozen = (new BiomeCRWGOcean(0x00c, "Frozen Ocean", 0x181850, 0.1F, 0.5F)).setEnableSnow();
		ocean_frozen_deep = (new BiomeCRWGOcean(0x00d, "Deep Frozen Ocean", 0x181830, 0.1F, 0.5F)).setEnableSnow();
		ocean_frozen_shallow = (new BiomeCRWGOcean(0x00e, "Shallow Frozen Ocean", 0x181870, 0.1F, 0.5F)).setEnableSnow();

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
		grass_hills = new BiomeGrassland(0x023, "Grass Hills", 0x80ff50, 0.8F, 0.4F, false);
		grass_highlands = new BiomeGrassland(0x024, "Grass Highlands", 0x80ff50, 0.7F, 0.4F, false);
		grassland_sunflower = new BiomeGrassland(0x0a2, "Sunflower Grassland", 0x80ff50, 0.8F, 0.4F, true);
		grass_hills_sunflower = new BiomeGrassland(0x0a3, "Sunflower Hills", 0x80ff50, 0.8F, 0.4F, true);
		grass_highlands_sunflower = new BiomeGrassland(0x0a4, "Sunflower Highlands", 0x80ff50, 0.7F, 0.4F, true);

		sparse_temperate_mountains = new BiomeSparseTemperateMountains(0x025, "Sparse Temperate Mountains");
		sparse_plateau = new BiomeSparseTemperateMountains(0x026, "Sparse Plateau");

		oak_chaparral = new BiomeOakForest(0x027, "Oak Chaparral", 0x056621, 0.7F, 0.6F, BiomeOakForest.CHAPARRAL);
		oak_chaparral_hills = new BiomeOakForest(0x028, "Oak Chaparral Hills", 0x056621, 0.7F, 0.6F, BiomeOakForest.CHAPARRAL);
		oak_chaparral_highlands = new BiomeOakForest(0x029, "Oak Chaparral Highlands", 0x056621, 0.6F, 0.6F, BiomeOakForest.CHAPARRAL);
		oak_forest = new BiomeOakForest(0x02a, "Oak Forest", 0x056621, 0.7F, 0.8F, BiomeOakForest.NORMAL);
		oak_forest_hills = new BiomeOakForest(0x02b, "Oak Forest Hills", 0x056621, 0.7F, 0.8F, BiomeOakForest.NORMAL);
		oak_forest_highlands = new BiomeOakForest(0x02c, "Oak Forest Highlands", 0x056621, 0.6F, 0.8F, BiomeOakForest.NORMAL);
		flower_chaparral = new BiomeOakForest(0x0a7, "Flower Chaparral", 0x056621, 0.7F, 0.633F, BiomeOakForest.FLOWER_CHAPARRAL);
		flower_chaparral_hills = new BiomeOakForest(0x0a8, "Flower Chaparral Hills", 0x056621, 0.7F, 0.633F, BiomeOakForest.FLOWER_CHAPARRAL);
		flower_chaparral_highlands = new BiomeOakForest(0x0a9, "Flower Chaparral Highlands", 0x056621, 0.6F, 0.633F, BiomeOakForest.FLOWER_CHAPARRAL);
		flower_forest = new BiomeOakForest(0x0aa, "Flower Forest", 0x056621, 0.7F, 0.833F, BiomeOakForest.FLOWER);
		flower_forest_hills = new BiomeOakForest(0x0ab, "Flower Forest Hills", 0x056621, 0.7F, 0.833F, BiomeOakForest.FLOWER);
		flower_forest_highlands = new BiomeOakForest(0x0ac, "Flower Forest Highlands", 0x056621, 0.6F, 0.833F, BiomeOakForest.FLOWER);

		dark_oak_forest = new BiomeDarkOakForest(0x02d, "Dark Oak Forest", 0x40511a, 0.7F, 0.8F);
		dark_oak_forest_hills = new BiomeDarkOakForest(0x02e, "Dark Oak Forest Hills", 0x50612a, 0.7F, 0.8F);
		dark_oak_forest_highlands = new BiomeDarkOakForest(0x02f, "Dark Oak Forest Highlands", 0x60713a, 0.6F, 0.8F);

		birch_chaparral = new BiomeBirchForest(0x030, "Birch Chaparral", 0x307444, 0.6F, 0.55F, BiomeBirchForest.CHAPARRAL);
		birch_chaparral_hills = new BiomeBirchForest(0x031, "Birch Chaparral Hills", 0x1f5f32, 0.6F, 0.55F, BiomeBirchForest.CHAPARRAL);
		birch_chaparral_highlands = new BiomeBirchForest(0x032, "Birch Chaparral Highlands", 0x1f5f32, 0.5F, 0.55F, BiomeBirchForest.CHAPARRAL);
		birch_forest = new BiomeBirchForest(0x033, "Birch Forest", 0x307444, 0.6F, 0.65F, BiomeBirchForest.NORMAL);
		birch_forest_hills = new BiomeBirchForest(0x034, "Birch Forest Hills", 0x1f5f32, 0.6F, 0.65F, BiomeBirchForest.NORMAL);
		birch_forest_highlands = new BiomeBirchForest(0x035, "Birch Forest Highlands", 0x1f5f32, 0.5F, 0.65F, BiomeBirchForest.NORMAL);
		old_growth_birch_forest = new BiomeBirchForest(0x0b3, "Old Growth Birch Forest", 0x307444, 0.6F, 0.65F, BiomeBirchForest.OLD_GROWTH);
		old_growth_birch_forest_hills = new BiomeBirchForest(0x0b4, "Old Growth Birch Forest Hills", 0x1f5f32, 0.6F, 0.65F, BiomeBirchForest.OLD_GROWTH);
		old_growth_birch_forest_highlands = new BiomeBirchForest(0x0b5, "Old Growth Birch Forest Highlands", 0x1f5f32, 0.5F, 0.65F, BiomeBirchForest.OLD_GROWTH);

		mountains = new BiomeMountains(0x036, "Mountains", 0x808080, 0.25F, 0.55F);
		frozen_mountains = new BiomeMountains(0x037, "Frozen Mountains", 0xa0a0c0, -0.55F, 0.55F);

		gravel_plateau = (new BiomeWasteland(0x038, "Gravel Plateau", 0xa0a0a0, 0.3F, 0.3F)).setEnableSnow();
		snowy_plateau = new BiomeTundra(0x039, "Snowy Plateau", 0xdfffff, -0.45F, 0.4F, false);

		tundra = new BiomeTundra(0x03a, "Tundra", 0xc0f0f0, -0.15F, 0.4F, false);
		tundra_hills = new BiomeTundra(0x03b, "Tundra Hills", 0xc8f8f8, -0.25F, 0.4F, false);
		tundra_highlands = new BiomeTundra(0x03c, "Tundra Highlands", 0xcfffff, -0.35F, 0.4F, false);
		ice_spikes = new BiomeTundra(0x0ba, "Ice Spike Tundra", 0xb0e0f0, -0.25F, 0.6F, true);
		ice_spikes_hills = new BiomeTundra(0x0bb, "Ice Spike Tundra Hills", 0xb8e8f8, -0.35F, 0.6F, true);
		ice_spikes_highlands = new BiomeTundra(0x0bc, "Ice Spike Tundra Highlands", 0xbfefff, -0.45F, 0.6F, true);

		spruce_forest = new BiomeSpruceForest(0x03d, "Spruce Forest", 0x0b6659, 0.35F, 0.7F, false);
		spruce_forest_hills = new BiomeSpruceForest(0x03e, "Spruce Forest Hills", 0x1b7669, 0.35F, 0.7F, false);
		spruce_forest_highlands = new BiomeSpruceForest(0x03f, "Spruce Forest Highlands", 0x2b8679, 0.25F, 0.7F, false);
		old_growth_spruce_forest = new BiomeSpruceForest(0x0bd, "Old Growth Spruce Forest", 0x596651, 0.35F, 0.85F, true);
		old_growth_spruce_forest_hills = new BiomeSpruceForest(0x0be, "Old Growth Spruce Forest Hills", 0x697661, 0.35F, 0.85F, true);
		old_growth_spruce_forest_highlands = new BiomeSpruceForest(0x0bf, "Old Growth Spruce Forest Highlands", 0x798671, 0.25F, 0.85F, true);
		snowy_spruce_forest = new BiomeSpruceForest(0x040, "Snowy Spruce Forest", 0x0b6659, -0.25F, 0.7F, false);
		snowy_spruce_forest_hills = new BiomeSpruceForest(0x041, "Snowy Spruce Forest Hills", 0x1b7669, -0.25F, 0.7F, false);
		snowy_spruce_forest_highlands = new BiomeSpruceForest(0x042, "Snowy Spruce Forest Highlands", 0x2b8679, -0.35F, 0.7F, false);
		snowy_old_growth_spruce_forest = new BiomeSpruceForest(0x0c0, "Snowy Old Growth Spruce Forest", 0x596651, -0.25F, 0.85F, true);
		snowy_old_growth_spruce_forest_hills = new BiomeSpruceForest(0x0c1, "Snowy Old Growth Spruce Forest Hills", 0x697661, -0.25F, 0.85F, true);
		snowy_old_growth_spruce_forest_highlands = new BiomeSpruceForest(0x0c2, "Snowy Old Growth Spruce Forest Highlands", 0x798671, -0.35F, 0.85F, true);

		pine_forest = new BiomePineForest(0x043, "Pine Forest", 0x0b663e, 0.35F, 0.7F, false);
		pine_forest_hills = new BiomePineForest(0x044, "Pine Forest Hills", 0x1b764e, 0.35F, 0.7F, false);
		pine_forest_highlands = new BiomePineForest(0x045, "Pine Forest Highlands", 0x2b865e, 0.25F, 0.7F, false);
		old_growth_pine_forest = new BiomePineForest(0x0c3, "Old Growth Pine Forest", 0x5e6650, 0.35F, 0.85F, true);
		old_growth_pine_forest_hills = new BiomePineForest(0x0c4, "Old Growth Pine Forest Hills", 0x6e7660, 0.35F, 0.85F, true);
		old_growth_pine_forest_highlands = new BiomePineForest(0x0c5, "Old Growth Pine Forest Highlands", 0x7e8670, 0.25F, 0.85F, true);
		snowy_pine_forest = new BiomePineForest(0x046, "Snowy Pine Forest", 0x0b663e, -0.25F, 0.7F, false);
		snowy_pine_forest_hills = new BiomePineForest(0x047, "Snowy Pine Forest Hills", 0x1b764e, -0.25F, 0.7F, false);
		snowy_pine_forest_highlands = new BiomePineForest(0x048, "Snowy Pine Forest Highlands", 0x2b865e, -0.35F, 0.7F, false);
		snowy_old_growth_pine_forest = new BiomePineForest(0x0c6, "Snowy Old Growth Pine Forest", 0x5e6650, -0.25F, 0.85F, true);
		snowy_old_growth_pine_forest_hills = new BiomePineForest(0x0c7, "Snowy Old Growth Pine Forest Hills", 0x6e7660, -0.25F, 0.85F, true);
		snowy_old_growth_pine_forest_highlands = new BiomePineForest(0x0c8, "Snowy Old Growth Pine Forest Highlands", 0x7e8670, -0.35F, 0.85F, true);

		cedar_forest = new BiomeCedarForest(0x049, "Cedar Forest", 0x226035, 0.35F, 0.7F, false);
		cedar_forest_hills = new BiomeCedarForest(0x04a, "Cedar Forest Hills", 0x327045, 0.35F, 0.7F, false);
		cedar_forest_highlands = new BiomeCedarForest(0x04b, "Cedar Forest Highlands", 0x428055, 0.25F, 0.7F, false);
		old_growth_cedar_forest = new BiomeCedarForest(0x0c9, "Old Growth Cedar Forest", 0x52664f, 0.35F, 0.85F, true);
		old_growth_cedar_forest_hills = new BiomeCedarForest(0x0ca, "Old Growth Cedar Forest Hills", 0x62765f, 0.35F, 0.85F, true);
		old_growth_cedar_forest_highlands = new BiomeCedarForest(0x0cb, "Old Growth Cedar Forest Highlands", 0x72866f, 0.25F, 0.85F, true);
		snowy_cedar_forest = new BiomeCedarForest(0x04c, "Snowy Cedar Forest", 0x226035, -0.25F, 0.7F, false);
		snowy_cedar_forest_hills = new BiomeCedarForest(0x04d, "Snowy Cedar Forest Hills", 0x327045, -0.25F, 0.7F, false);
		snowy_cedar_forest_highlands = new BiomeCedarForest(0x04e, "Snowy Cedar Forest Highlands", 0x428055, -0.35F, 0.7F, false);
		snowy_old_growth_cedar_forest = new BiomeCedarForest(0x0cc, "Snowy Old Growth Cedar Forest", 0x52664f, -0.25F, 0.85F, true);
		snowy_old_growth_cedar_forest_hills = new BiomeCedarForest(0x0cd, "Snowy Old Growth Cedar Forest Hills", 0x62765f, -0.25F, 0.85F, true);
		snowy_old_growth_cedar_forest_highlands = new BiomeCedarForest(0x0ce, "Snowy Old Growth Cedar Forest Highlands", 0x72866f, -0.35F, 0.85F, true);

		desert = (new BiomeDesert(0x055, "Desert", 0xfa9418, 2.0F, 0.0F)).setDisableRain();
		desert_hills = (new BiomeDesert(0x056, "Desert Hills", 0xffa428, 2.0F, 0.0F)).setDisableRain();
		desert_highlands = (new BiomeDesert(0x057, "Desert Highlands", 0xffb438, 1.9F, 0.0F)).setDisableRain();
		desert_mountains = (new BiomeDesertMountains(0x058, "Desert Mountains", 0xffd458, 1.6F, 0.0F)).setDisableRain();
		desert_plateau = (new BiomeDesertMountains(0x059, "Desert Plateau", 0xffc448, 1.7F, 0.0F)).setDisableRain();

		jungle = new BiomeJungle(0x05a, "Jungle", 0x537b09, 0.95F, 0.9F, 0);
		jungle_hills = new BiomeJungle(0x05b, "Jungle Hills", 0x638b19, 0.92F, 0.9F, 0);
		jungle_highlands = new BiomeJungle(0x05c, "Jungle Highlands", 0x739b29, 0.87F, 0.9F, 0);
		jungle_mountains = new BiomeJungle(0x05d, "Jungle Mountains", 0x83ab39, 0.8F, 0.9F, 2);
		jungle_plateau = new BiomeJungle(0x05e, "Jungle Plateau", 0x93bb49, 0.7F, 0.9F, 1);

		/* Register CRWG biomes with BiomeDictionary and BiomeManager */
		addOcean(ocean, Type.OCEAN);
		addOcean(ocean_deep, Type.OCEAN);
		addOcean(ocean_shallow, Type.OCEAN);
		addOcean(ocean_lukewarm, Type.OCEAN);
		addOcean(ocean_lukewarm_deep, Type.OCEAN);
		addOcean(ocean_lukewarm_shallow, Type.OCEAN);
		addOcean(ocean_warm, Type.OCEAN);
		addOcean(ocean_warm_deep, Type.OCEAN);
		addOcean(ocean_warm_shallow, Type.OCEAN);
		addOcean(ocean_cold, Type.OCEAN, Type.COLD);
		addOcean(ocean_cold_deep, Type.OCEAN, Type.COLD);
		addOcean(ocean_cold_shallow, Type.OCEAN, Type.COLD);
		addOcean(ocean_frozen, Type.OCEAN, Type.COLD, Type.SNOWY);
		addOcean(ocean_frozen_deep, Type.OCEAN, Type.COLD, Type.SNOWY);
		addOcean(ocean_frozen_shallow, Type.OCEAN, Type.COLD, Type.SNOWY);

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
		add(grass_hills, BiomeType.WARM, true, true, true, Type.PLAINS, Type.HILLS);
		add(grass_highlands, BiomeType.WARM, true, true, true, Type.PLAINS, Type.HILLS);
		add(grassland_sunflower, BiomeType.WARM, true, true, true, Type.PLAINS);
		add(grass_hills_sunflower, BiomeType.WARM, true, true, true, Type.PLAINS, Type.HILLS);
		add(grass_highlands_sunflower, BiomeType.WARM, true, true, true, Type.PLAINS, Type.HILLS);

		add(sparse_temperate_mountains, BiomeType.COOL, true, false, true, Type.SPARSE, Type.MOUNTAIN);
		add(sparse_plateau, BiomeType.COOL, true, false, true, Type.SPARSE, Type.HILLS);

		add(oak_chaparral, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE);
		add(oak_chaparral_hills, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE, Type.HILLS);
		add(oak_chaparral_highlands, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE, Type.HILLS);
		add(oak_forest_highlands, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);
		add(oak_forest, BiomeType.WARM, true, false, true, Type.FOREST);
		add(oak_forest_hills, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);
		add(flower_chaparral, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE);
		add(flower_chaparral_hills, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE, Type.HILLS);
		add(flower_chaparral_highlands, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE, Type.HILLS);
		add(flower_forest, BiomeType.WARM, true, false, true, Type.FOREST);
		add(flower_forest_hills, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);
		add(flower_forest_highlands, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);

		add(dark_oak_forest, BiomeType.WARM, true, false, true, Type.FOREST, Type.DENSE, Type.SPOOKY);
		add(dark_oak_forest_hills, BiomeType.WARM, true, false, true, Type.FOREST, Type.DENSE, Type.SPOOKY, Type.HILLS);
		add(dark_oak_forest_highlands, BiomeType.WARM, true, false, true, Type.FOREST, Type.DENSE, Type.SPOOKY, Type.HILLS);

		add(birch_chaparral, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE);
		add(birch_chaparral_hills, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE, Type.HILLS);
		add(birch_chaparral_highlands, BiomeType.WARM, true, true, true, Type.FOREST, Type.SPARSE, Type.HILLS);
		add(birch_forest, BiomeType.WARM, true, false, true, Type.FOREST);
		add(birch_forest_hills, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);
		add(birch_forest_highlands, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);
		add(old_growth_birch_forest, BiomeType.WARM, true, false, true, Type.FOREST);
		add(old_growth_birch_forest_hills, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);
		add(old_growth_birch_forest_highlands, BiomeType.WARM, true, false, true, Type.FOREST, Type.HILLS);

		add(mountains, BiomeType.COOL, false, false, true, Type.MOUNTAIN, Type.SPARSE, Type.COLD);
		add(frozen_mountains, BiomeType.ICY, false, false, true, Type.MOUNTAIN, Type.SPARSE, Type.COLD, Type.SNOWY);

		add(gravel_plateau, BiomeType.COOL, false, false, false, Type.WASTELAND, Type.HILLS, Type.COLD);
		add(snowy_plateau, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.HILLS, Type.COLD, Type.SNOWY);

		add(tundra, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY);
		add(tundra_hills, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY, Type.HILLS);
		add(tundra_highlands, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY, Type.HILLS);
		add(ice_spikes, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY);
		add(ice_spikes_hills, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY, Type.HILLS);
		add(ice_spikes_highlands, BiomeType.ICY, false, false, false, Type.WASTELAND, Type.COLD, Type.SNOWY, Type.HILLS);

		add(spruce_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(spruce_forest_hills, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(spruce_forest_highlands, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(old_growth_spruce_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(old_growth_spruce_forest_hills, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(old_growth_spruce_forest_highlands, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_spruce_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_spruce_forest_hills, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_spruce_forest_highlands, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_old_growth_spruce_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_old_growth_spruce_forest_hills, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_old_growth_spruce_forest_highlands, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);

		add(pine_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(pine_forest_hills, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(pine_forest_highlands, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(old_growth_pine_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(old_growth_pine_forest_hills, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(old_growth_pine_forest_highlands, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_pine_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_pine_forest_hills, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_pine_forest_highlands, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_old_growth_pine_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_old_growth_pine_forest_hills, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_old_growth_pine_forest_highlands, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);

		add(cedar_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(cedar_forest_hills, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(cedar_forest_highlands, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(old_growth_cedar_forest, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS);
		add(old_growth_cedar_forest_hills, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(old_growth_cedar_forest_highlands, BiomeType.COOL, true, false, true, Type.COLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_cedar_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_cedar_forest_hills, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_cedar_forest_highlands, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_old_growth_cedar_forest, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS);
		add(snowy_old_growth_cedar_forest_hills, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		add(snowy_old_growth_cedar_forest_highlands, BiomeType.ICY, false, false, true, Type.COLD, Type.SNOWY, Type.FOREST, Type.CONIFEROUS, Type.HILLS);

		add(desert, BiomeType.DESERT, false, true, true, Type.HOT, Type.DRY, Type.SANDY);
		add(desert_hills, BiomeType.DESERT, false, true, true, Type.HOT, Type.DRY, Type.SANDY, Type.HILLS);
		add(desert_highlands, BiomeType.DESERT, false, true, true, Type.HOT, Type.DRY, Type.SANDY, Type.HILLS);
		add(desert_mountains, BiomeType.DESERT, false, false, true, Type.HOT, Type.DRY, Type.SANDY, Type.MOUNTAIN);
		add(desert_plateau, BiomeType.DESERT, false, false, true, Type.HOT, Type.DRY, Type.SANDY, Type.HILLS);

		add(jungle, BiomeType.WARM, true, false, true, Type.HOT, Type.WET, Type.DENSE, Type.JUNGLE);
		add(jungle_hills, BiomeType.WARM, true, false, true, Type.HOT, Type.WET, Type.DENSE, Type.JUNGLE, Type.HILLS);
		add(jungle_highlands, BiomeType.WARM, true, false, true, Type.HOT, Type.WET, Type.DENSE, Type.JUNGLE, Type.HILLS);
		add(jungle_mountains, BiomeType.WARM, true, false, true, Type.WET, Type.DENSE, Type.JUNGLE, Type.MOUNTAIN);
		add(jungle_plateau, BiomeType.WARM, true, true, true, Type.WET, Type.DENSE, Type.JUNGLE, Type.HILLS);

		/* Init GenLayer stuff */
		GenLayerBiomesCRWG.ocean_biomes = new BiomeCRWGBase[][] {
			{ocean_frozen_deep, ocean_frozen, ocean_frozen_shallow},
			{ocean_cold_deep, ocean_cold, ocean_cold_shallow},
			{ocean_deep, ocean, ocean_shallow},
			{ocean_lukewarm_deep, ocean_lukewarm, ocean_lukewarm_shallow},
			{ocean_warm_deep, ocean_warm, ocean_warm_shallow}
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
		 * be rare, everything else must be added multiple times.
		 */

		BiomeTypeCRWG.DESERT.addGroupOf(desert, desert_hills, desert_highlands, desert_mountains, desert_plateau);

		BiomeTypeCRWG.SAVANNA.addGroupOf(desert, desert_hills, desert_highlands, desert_mountains, desert_plateau);

		BiomeTypeCRWG.SCRUBLAND.addGroupOf(desert, desert_hills, desert_highlands, desert_mountains, desert_plateau);

		BiomeTypeCRWG.TROPICAL_FOREST.addGroupOf(dark_oak_forest, dark_oak_forest_hills, dark_oak_forest_highlands, sparse_temperate_mountains, sparse_plateau);

		BiomeTypeCRWG.RAINFOREST.addGroupOf(jungle, jungle_hills, jungle_highlands, jungle_mountains, jungle_plateau);

		BiomeTypeCRWG.COOLER_DESERT.addGroupOf(desert, desert_hills, desert_highlands, desert_mountains, desert_plateau);

		BiomeTypeCRWG.GRASSLAND.addGroupOf(grassland, grass_hills, grass_highlands, mountains, gravel_plateau);
		BiomeTypeCRWG.GRASSLAND.addGroupOf(grassland_sunflower, grass_hills_sunflower, grass_highlands_sunflower, mountains, gravel_plateau);

		BiomeTypeCRWG.CHAPARRAL.addGroupOf(oak_chaparral, oak_chaparral_hills, oak_chaparral_highlands, mountains, gravel_plateau);
		BiomeTypeCRWG.CHAPARRAL.addGroupOf(flower_chaparral, flower_chaparral_hills, flower_chaparral_highlands, mountains, gravel_plateau);
		BiomeTypeCRWG.CHAPARRAL.addGroupOf(birch_chaparral, birch_chaparral_hills, birch_chaparral_highlands, mountains, gravel_plateau);

		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(oak_forest, oak_forest_hills, oak_forest_highlands, mountains, gravel_plateau);
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(flower_forest, flower_forest_hills, flower_forest_highlands, mountains, gravel_plateau);
		BiomeTypeCRWG.DECIDUOUS_FOREST.addGroupOf(birch_forest, birch_forest_hills, birch_forest_highlands, mountains, gravel_plateau);

		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(dark_oak_forest, dark_oak_forest_hills, dark_oak_forest_highlands, mountains, gravel_plateau);
		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(flower_forest, flower_forest_hills, flower_forest_highlands, mountains, gravel_plateau);
		BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST.addGroupOf(old_growth_birch_forest, old_growth_birch_forest_hills, old_growth_birch_forest_highlands, mountains, gravel_plateau);

		BiomeTypeCRWG.COLD_GRASSLAND.addGroupOf(grassland, grass_hills, grass_highlands, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.BOREAL_FOREST.addGroupOf(spruce_forest, spruce_forest_hills, spruce_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.BOREAL_FOREST.addGroupOf(pine_forest, pine_forest_hills, pine_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.BOREAL_FOREST.addGroupOf(cedar_forest, cedar_forest_hills, cedar_forest_highlands, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.DENSE_BOREAL_FOREST.addGroupOf(old_growth_spruce_forest, old_growth_spruce_forest_hills, old_growth_spruce_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.DENSE_BOREAL_FOREST.addGroupOf(old_growth_pine_forest, old_growth_pine_forest_hills, old_growth_pine_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.DENSE_BOREAL_FOREST.addGroupOf(old_growth_cedar_forest, old_growth_cedar_forest_hills, old_growth_cedar_forest_highlands, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.SNOWY_BOREAL_FOREST.addGroupOf(snowy_spruce_forest, snowy_spruce_forest_hills, snowy_spruce_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_BOREAL_FOREST.addGroupOf(snowy_pine_forest, snowy_pine_forest_hills, snowy_pine_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_BOREAL_FOREST.addGroupOf(snowy_cedar_forest, snowy_cedar_forest_hills, snowy_cedar_forest_highlands, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.SNOWY_DENSE_BOREAL_FOREST.addGroupOf(snowy_old_growth_spruce_forest, snowy_old_growth_spruce_forest_hills, snowy_old_growth_spruce_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_DENSE_BOREAL_FOREST.addGroupOf(snowy_old_growth_pine_forest, snowy_old_growth_pine_forest_hills, snowy_old_growth_pine_forest_highlands, frozen_mountains, snowy_plateau);
		BiomeTypeCRWG.SNOWY_DENSE_BOREAL_FOREST.addGroupOf(snowy_old_growth_cedar_forest, snowy_old_growth_cedar_forest_hills, snowy_old_growth_cedar_forest_highlands, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.FROZEN_WASTELAND.addGroupOf(tundra, tundra_hills, tundra_highlands, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.TUNDRA.addGroupOf(tundra, tundra_hills, tundra_highlands, frozen_mountains, snowy_plateau);

		BiomeTypeCRWG.SPECIAL_TUNDRA.addGroupOf(ice_spikes, ice_spikes_hills, ice_spikes_highlands, frozen_mountains, snowy_plateau);
	}
}

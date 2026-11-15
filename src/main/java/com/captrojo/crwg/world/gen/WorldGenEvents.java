package com.captrojo.crwg.world.gen;

import com.captrojo.crwg.world.biome.ModBiomes;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class WorldGenEvents
{
	public static WorldGenEvents instance;

	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		/* dark swamp */
		if (event.biome == ModBiomes.dark_swamp) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.log) {
				event.replacement = Blocks.log2;
			} else if (event.original == Blocks.oak_stairs) {
				event.replacement = Blocks.dark_oak_stairs;
			} else {
				event.replacement = event.original;
			}
			return;
		}

		/* birch chaparral */
		if (event.biome == ModBiomes.birch_chaparral) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.oak_stairs) {
				event.replacement = Blocks.birch_stairs;
			} else {
				event.replacement = event.original;
			}
			return;
		}

		/* cold grassland */
		if (event.biome == ModBiomes.cold_grassland) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.oak_stairs) {
				event.replacement = Blocks.spruce_stairs;
			} else {
				event.replacement = event.original;
			}
			return;
		}

		/* savanna */
		if (event.biome == ModBiomes.savanna) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.log) {
				event.replacement = Blocks.log2;
			} else if (event.original == Blocks.oak_stairs) {
				event.replacement = Blocks.acacia_stairs;
			} else {
				event.replacement = event.original;
			}
			return;
		}

		/* desert */
		if (event.biome == ModBiomes.desert) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.cobblestone) {
				event.replacement = Blocks.sandstone;
			} else if (event.original == Blocks.log) {
				event.replacement = Blocks.sandstone;
			} else if (event.original == Blocks.planks) {
				event.replacement = Blocks.sandstone;
			} else if (event.original == Blocks.oak_stairs) {
				event.replacement = Blocks.sandstone_stairs;
			} else if (event.original == Blocks.stone_stairs) {
				event.replacement = Blocks.sandstone_stairs;
			} else if (event.original == Blocks.gravel) {
				event.replacement = Blocks.sandstone;
			} else {
				event.replacement = event.original;
			}
			return;
		}

		/* jungle */
		if (event.biome == ModBiomes.jungle_plateau) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.oak_stairs) {
				event.replacement = Blocks.jungle_stairs;
			} else {
				event.replacement = event.original;
			}
			return;
		}
	}

	@SubscribeEvent
	public void getVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
	{
		/* dark swamp */
		if (event.biome == ModBiomes.dark_swamp) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.log) {
				event.replacement = 1; /* dark oak (Blocks.log2) */
			} else if (event.original == Blocks.planks) {
				event.replacement = 5; /* dark oak (Blocks.planks) */
			} else {
				event.replacement = event.type;
			}
			return;
		}

		/* birch chaparral */
		if (event.biome == ModBiomes.birch_chaparral) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.planks) {
				event.replacement = 2; /* birch (Blocks.planks) */
			} else if (event.original == Blocks.log) {
				event.replacement = 2; /* birch (Blocks.log) */
			} else {
				event.replacement = event.type;
			}
			return;
		}

		/* cold grassland */
		if (event.biome == ModBiomes.cold_grassland) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.planks) {
				event.replacement = 1; /* spruce (Blocks.planks) */
			} else if (event.original == Blocks.log) {
				event.replacement = 1; /* spruce (Blocks.log) */
			} else {
				event.replacement = event.type;
			}
			return;
		}

		/* savanna */
		if (event.biome == ModBiomes.savanna) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.planks) {
				event.replacement = 5; /* acacia (Blocks.planks) */
			} else {
				event.replacement = event.type;
			}
			/* Acacia logs have the same metadata as oak logs (different block ID) */
			return;
		}

		/* desert */
		if (event.biome == ModBiomes.desert) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.log) {
				event.replacement = 0; /* sandstone (Blocks.sandstone) */
			} else if (event.original == Blocks.planks) {
				event.replacement = 2; /* cut sandstone (Blocks.sandstone) */
			} else {
				event.replacement = event.type;
			}
			return;
		}

		/* jungle */
		if (event.biome == ModBiomes.jungle_plateau) {
			event.setResult(Result.DENY);
			if (event.original == Blocks.log) {
				event.replacement = 3; /* jungle (Blocks.log) */
			} else if (event.original == Blocks.planks) {
				event.replacement = 3; /* jungle (Blocks.planks) */
			} else {
				event.replacement = event.type;
			}
			return;
		}
	}
}

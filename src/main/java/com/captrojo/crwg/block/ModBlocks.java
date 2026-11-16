package com.captrojo.crwg.block;

import com.captrojo.crwg.block.data.Colorizer;
import com.captrojo.crwg.block.data.SaplingArrangement;
import com.captrojo.crwg.item.Item2x2FloorPlant;
import com.captrojo.crwg.item.ItemBlockCRWG;
import com.captrojo.crwg.item.ItemDoublePlantCRWG;
import com.captrojo.crwg.item.ItemLeavesCRWG;
import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.main.GameRegistry2;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.common.EnumPlantType;

public abstract class ModBlocks
{
	public static Block sapling_bald_cypress;
	public static Block sapling_cedar;
	public static Block sapling_chromaticus_red;
	public static Block sapling_chromaticus_green;
	public static Block sapling_chromaticus_blue;
	public static Block sapling_chromaticus_violet;
	public static Block sapling_enchanted_ash;
	public static Block sapling_palm;
	public static Block sapling_pine;
	public static Block sapling_tamarack;
	public static Block sapling_violet;
	public static Block sapling_willow_black;
	public static Block sapling_willow_white;

	public static Block leaf_bald_cypress;
	public static Block leaf_cedar;
	public static Block leaf_chromaticus_red;
	public static Block leaf_chromaticus_green;
	public static Block leaf_chromaticus_blue;
	public static Block leaf_chromaticus_violet;
	public static Block leaf_enchanted_ash;
	public static Block leaf_palm;
	public static Block leaf_pine;
	public static Block leaf_tamarack;
	public static Block leaf_violet;
	public static Block leaf_willow_black;
	public static Block leaf_willow_white;

	public static Block silty_dirt;
	public static Block coarse_silty_dirt;
	public static Block silty_grass;
	public static Block silty_mycelium;
	public static Block silty_podzol;
	public static Block silty_farmland;

	public static Block loamy_dirt;
	public static Block coarse_loamy_dirt;
	public static Block loamy_grass;
	public static Block loamy_mycelium;
	public static Block loamy_podzol;
	public static Block loamy_farmland;

	public static Block clay_dirt;
	public static Block coarse_clay_dirt;
	public static Block clay_grass;
	public static Block clay_mycelium;
	public static Block clay_podzol;
	public static Block clay_farmland;

	public static Block dry_short_grass;

	public static Block bush;
	public static Block purple_bush;

	public static Block flower_purple_owls_clover;
	public static Block flower_clustered_tarweed;

	public static Block flower_longspur_lupine;
	public static Block flower_silver_lupine;
	public static Block flower_sky_blue_lupine;

	public static Block flower_sage;
	public static Block flower_woodland_sage;

	public static Block flower_hyacinth_aqua;
	public static Block flower_hyacinth_cream;
	public static Block flower_hyacinth_crimson;
	public static Block flower_hyacinth_deepblue;
	public static Block flower_hyacinth_pink;
	public static Block flower_hyacinth_salmonpink;

	public static Block dflower_purple_nightshade;

	public static Block dplant_cattail;

	public static Block clover;
	public static Block clover_large;

	public static Block leaf_pile;

	public static Block small_waterlily;

	public static Block orange_sand;
	public static Block orange_sandstone;
	public static Block orange_cut_sandstone;
	public static Block orange_chiseled_sandstone;
	public static Block orange_smooth_sandstone;

	public static Block orange_sandstone_slab;
	public static Block orange_cut_sandstone_slab;
	public static Block orange_smooth_sandstone_slab;
	public static Block orange_sandstone_double_slab;
	public static Block orange_cut_sandstone_double_slab;
	public static Block orange_smooth_sandstone_double_slab;

	public static Block orange_sandstone_stairs;
	public static Block orange_smooth_sandstone_stairs;

	public static void initBlocks()
	{
		WoodBlocks.values();
		
		sapling_bald_cypress = (new BlockSaplingCRWG("bald_cypress_sapling", TreeGens::getBaldCypressSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/bald_cypress"));
		sapling_cedar = (new BlockSaplingCRWG("cedar_sapling", TreeGens::getCedarSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/cedar"));
		sapling_chromaticus_red = (new BlockSaplingCRWG("red_chromaticus_sapling", TreeGens::getChromaticusRedSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/red_chromaticus"));
		sapling_chromaticus_green = (new BlockSaplingCRWG("green_chromaticus_sapling", TreeGens::getChromaticusGreenSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/green_chromaticus"));
		sapling_chromaticus_blue = (new BlockSaplingCRWG("blue_chromaticus_sapling", TreeGens::getChromaticusBlueSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/blue_chromaticus"));
		sapling_chromaticus_violet = (new BlockSaplingCRWG("violet_chromaticus_sapling", TreeGens::getChromaticusVioletSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/violet_chromaticus"));
		sapling_enchanted_ash = (new BlockSaplingCRWG("enchanted_ash_sapling", TreeGens::getEnchantedAshSaplingGen))
			.setSaplingArrangement(SaplingArrangement.TWOBYTWO)
			.setBlockTextureName(CRWG.ident("saplings/enchanted_ash"));
		sapling_palm = (new BlockSaplingCRWG("palm_sapling", TreeGens::getPalmSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/palm"));
		sapling_pine = (new BlockSaplingCRWG("pine_sapling", TreeGens::getPineSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/pine"));
		sapling_tamarack = (new BlockSaplingCRWG("tamarack_sapling", TreeGens::getTamarackSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/tamarack"));
		sapling_violet = (new BlockSaplingCRWG("violet_sapling", TreeGens::getVioletSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/violet"));
		sapling_willow_black = (new BlockSaplingCRWG("black_willow_sapling", TreeGens::getWillowBlackSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/black_willow"));
		sapling_willow_white = (new BlockSaplingCRWG("white_willow_sapling", TreeGens::getWillowWhiteSaplingGen))
			.setBlockTextureName(CRWG.ident("saplings/white_willow"));

		leaf_bald_cypress = (new BlockLeavesCRWG("bald_cypress_leaves", sapling_bald_cypress))
			.setColorizer(Colorizer.biomeFoliageAvg(0x5bd626, 0.5F))
			.setBlockTextureName(CRWG.ident("leaves/bald_cypress"));
		leaf_cedar = (new BlockLeavesCRWG("cedar_leaves", sapling_cedar))
			.setColorizer(Colorizer.biomeFoliageAvg(0x4c964c, 0.66F))
			.setBlockTextureName(CRWG.ident("leaves/cedar"));
		leaf_chromaticus_red = (new BlockLeavesCRWG("red_chromaticus_leaves", sapling_chromaticus_red))
			.setColorizer(Colorizer.biomeFoliageAvg(0xc17ca4, 0.66F))
			.setBlockTextureName(CRWG.ident("leaves/red_chromaticus"));
		leaf_chromaticus_green = (new BlockLeavesCRWG("green_chromaticus_leaves", sapling_chromaticus_green))
			.setColorizer(Colorizer.biomeFoliageAvg(0x7cc194, 0.66F))
			.setBlockTextureName(CRWG.ident("leaves/green_chromaticus"));
		leaf_chromaticus_blue = (new BlockLeavesCRWG("blue_chromaticus_leaves", sapling_chromaticus_blue))
			.setColorizer(Colorizer.biomeFoliageAvg(0x7cbec1, 0.66F))
			.setBlockTextureName(CRWG.ident("leaves/blue_chromaticus"));
		leaf_chromaticus_violet = (new BlockLeavesCRWG("violet_chromaticus_leaves", sapling_chromaticus_violet))
			.setColorizer(Colorizer.biomeFoliageAvg(0x887cc1, 0.66F))
			.setBlockTextureName(CRWG.ident("leaves/violet_chromaticus"));
		leaf_enchanted_ash = (new BlockLeavesCRWG("enchanted_ash_leaves", sapling_enchanted_ash))
			.setColorizer(Colorizer.NONE)
			.setBlockTextureName(CRWG.ident("leaves/enchanted_ash"));
		leaf_palm = (new BlockLeavesCRWG("palm_leaves", sapling_palm))
			.setColorizer(Colorizer.BIOME_FOLIAGE)
			.setBlockTextureName(CRWG.ident("leaves/palm"));
		leaf_pine = (new BlockLeavesCRWG("pine_leaves", sapling_pine))
			.setColorizer(Colorizer.fixed(0x609970))
			.setBlockTextureName(CRWG.ident("leaves/pine"));
		leaf_tamarack = (new BlockLeavesCRWG("tamarack_leaves", sapling_tamarack))
			.setColorizer(Colorizer.biomeFoliageAvg(0x5db73c, 0.5F))
			.setBlockTextureName(CRWG.ident("leaves/tamarack"));
		leaf_violet = (new BlockLeavesCRWG("violet_leaves", sapling_violet))
			.setColorizer(Colorizer.BIOME_FOLIAGE)
			.setBlockTextureName(CRWG.ident("leaves/violet"));
		leaf_willow_black = (new BlockLeavesCRWG("black_willow_leaves", sapling_willow_black))
			.setColorizer(Colorizer.BIOME_FOLIAGE)
			.setBlockTextureName(CRWG.ident("leaves/black_willow"));
		leaf_willow_white = (new BlockLeavesCRWG("white_willow_leaves", sapling_willow_white))
			.setColorizer(Colorizer.BIOME_FOLIAGE)
			.setBlockTextureName(CRWG.ident("leaves/white_willow"));

		/* SOILS */

		silty_dirt = (new BlockDirtCRWG("silty_dirt", Material.ground))
			.setHarvestTool("shovel", 0)
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/silty_dirt"))
			.setStepSound(Block.soundTypeGravel);
		coarse_silty_dirt = (new BlockDirtCRWG("coarse_silty_dirt", Material.ground))
			.setHarvestTool("shovel", 0)
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/coarse_silty_dirt"))
			.setStepSound(Block.soundTypeGravel);
		silty_grass = (new BlockGrassCRWG("silty_grass", silty_dirt))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/silty_grass"))
			.setStepSound(Block.soundTypeGrass);
		silty_mycelium = (new BlockMyceliumCRWG("silty_mycelium", silty_dirt))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/silty_mycelium"))
			.setStepSound(Block.soundTypeGrass);
		silty_podzol = (new BlockPodzol("silty_podzol", silty_grass))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/silty"))
			.setStepSound(Block.soundTypeGravel);
		silty_farmland = (new BlockFarmlandCRWG("silty_farmland", silty_dirt))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/silty_farmland"))
			.setStepSound(Block.soundTypeGravel);

		loamy_dirt = (new BlockDirtCRWG("loamy_dirt", Material.ground))
			.setHarvestTool("shovel", 0)
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/loamy_dirt"))
			.setStepSound(Block.soundTypeGravel);
		coarse_loamy_dirt = (new BlockDirtCRWG("coarse_loamy_dirt", Material.ground))
			.setHarvestTool("shovel", 0)
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/coarse_loamy_dirt"))
			.setStepSound(Block.soundTypeGravel);
		loamy_grass = (new BlockGrassCRWG("loamy_grass", loamy_dirt))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/loamy_grass"))
			.setStepSound(Block.soundTypeGrass);
		loamy_mycelium = (new BlockMyceliumCRWG("loamy_mycelium", silty_dirt))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/loamy_mycelium"))
			.setStepSound(Block.soundTypeGrass);
		loamy_podzol = (new BlockPodzol("loamy_podzol", loamy_grass))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/loamy"))
			.setStepSound(Block.soundTypeGravel);
		loamy_farmland = (new BlockFarmlandCRWG("loamy_farmland", loamy_dirt))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/loamy_farmland"))
			.setStepSound(Block.soundTypeGravel);

		clay_dirt = (new BlockDirtCRWG("clay_dirt", Material.ground))
			.setHarvestTool("shovel", 0)
			.setHardness(0.7F)
			.setBlockTextureName(CRWG.ident("soil/clay_dirt"))
			.setStepSound(Block.soundTypeGravel);
		coarse_clay_dirt = (new BlockDirtCRWG("coarse_clay_dirt", Material.ground))
			.setHarvestTool("shovel", 0)
			.setHardness(0.7F)
			.setBlockTextureName(CRWG.ident("soil/coarse_clay_dirt"))
			.setStepSound(Block.soundTypeGravel);
		clay_grass = (new BlockGrassCRWG("clay_grass", clay_dirt))
			.setHardness(0.7F)
			.setBlockTextureName(CRWG.ident("soil/clay_grass"))
			.setStepSound(Block.soundTypeGrass);
		clay_mycelium = (new BlockMyceliumCRWG("clay_mycelium", silty_dirt))
			.setHardness(0.5F)
			.setBlockTextureName(CRWG.ident("soil/clay_mycelium"))
			.setStepSound(Block.soundTypeGrass);
		clay_podzol = (new BlockPodzol("clay_podzol", clay_grass))
			.setHardness(0.7F)
			.setBlockTextureName(CRWG.ident("soil/clay"))
			.setStepSound(Block.soundTypeGravel);
		clay_farmland = (new BlockFarmlandCRWG("clay_farmland", clay_dirt))
			.setHardness(0.7F)
			.setBlockTextureName(CRWG.ident("soil/clay_farmland"))
			.setStepSound(Block.soundTypeGravel);

		/* PLANTS */

		dry_short_grass = (new BlockBushCRWG("dry_short_grass", Material.plants, EnumPlantType.Desert))
			.setVariantCount(4, false)
			.setShearsRequired()
			.setSize(0.8F, 0.6F)
			.setBlockTextureName(CRWG.ident("plants/dry_short_grass"))
			.setStepSound(Block.soundTypeGrass);

		bush = (new BlockBushCRWG("bush", Material.plants, EnumPlantType.Plains))
			.setSize(0.8F, 0.8F)
			.setColorizer(Colorizer.BIOME_GRASS)
			.setBlockTextureName(CRWG.ident("plants/bush"));
		purple_bush = (new BlockBushCRWG("purple_bush", Material.plants, EnumPlantType.Plains))
			.setSize(0.8F, 0.8F)
			.setBlockTextureName(CRWG.ident("plants/purple_bush"));

		flower_purple_owls_clover = (new BlockBushCRWG("purple_owls_clover"))
			.setBlockTextureName(CRWG.ident("plants/flower_purple_owls_clover"));
		flower_clustered_tarweed = (new BlockBushCRWG("clustered_tarweed"))
			.setBlockTextureName(CRWG.ident("plants/flower_clustered_tarweed"));

		flower_longspur_lupine = (new BlockBushCRWG("longspur_lupine"))
			.setBlockTextureName(CRWG.ident("plants/flower_longspur_lupine"));
		flower_silver_lupine = (new BlockBushCRWG("silver_lupine"))
			.setBlockTextureName(CRWG.ident("plants/flower_silver_lupine"));
		flower_sky_blue_lupine = (new BlockBushCRWG("sky_blue_lupine"))
			.setBlockTextureName(CRWG.ident("plants/flower_sky_blue_lupine"));

		flower_sage = (new BlockBushCRWG("sage"))
			.setBlockTextureName(CRWG.ident("plants/flower_sage"));
		flower_woodland_sage = (new BlockBushCRWG("woodland_sage"))
			.setBlockTextureName(CRWG.ident("plants/flower_woodland_sage"));

		flower_hyacinth_aqua = (new BlockBushCRWG("aqua_hyacinth"))
			.setSize(0.6F, 1.0F)
			.setBlockTextureName(CRWG.ident("plants/flower_hyacinth_aqua"));
		flower_hyacinth_cream = (new BlockBushCRWG("cream_hyacinth"))
			.setSize(0.6F, 1.0F)
			.setBlockTextureName(CRWG.ident("plants/flower_hyacinth_cream"));
		flower_hyacinth_crimson = (new BlockBushCRWG("crimson_hyacinth"))
			.setSize(0.6F, 1.0F)
			.setBlockTextureName(CRWG.ident("plants/flower_hyacinth_crimson"));
		flower_hyacinth_deepblue = (new BlockBushCRWG("deep_blue_hyacinth"))
			.setSize(0.6F, 1.0F)
			.setBlockTextureName(CRWG.ident("plants/flower_hyacinth_deepblue"));
		flower_hyacinth_pink = (new BlockBushCRWG("pink_hyacinth"))
			.setSize(0.6F, 1.0F)
			.setBlockTextureName(CRWG.ident("plants/flower_hyacinth_pink"));
		flower_hyacinth_salmonpink = (new BlockBushCRWG("salmon_pink_hyacinth"))
			.setSize(0.6F, 1.0F)
			.setBlockTextureName(CRWG.ident("plants/flower_hyacinth_salmonpink"));

		dflower_purple_nightshade = (new BlockDoublePlantCRWG("purple_nightshade"))
			.setBlockTextureName(CRWG.ident("plants/double_flower_purple_nightshade"));

		dplant_cattail = (new BlockDoublePlantCRWG("cattail"))
			.setColorizer(Colorizer.BIOME_GRASS, true)
			.setItemTextureName(CRWG.ident("plants/cattail"))
			.setBlockTextureName(CRWG.ident("plants/cattail"));

		clover = (new Block2x2FloorPlant("clover"))
			.setColorizer(Colorizer.BIOME_GRASS)
			.setBlockTextureName(CRWG.ident("plants/clover"));
		clover_large = (new BlockFloorPlant("large_clover"))
			.setColorizer(Colorizer.BIOME_GRASS)
			.setBlockTextureName(CRWG.ident("plants/large_clover"));

		leaf_pile = (new Block2x2FloorPlant("leaf_pile"))
			.setColorizer(Colorizer.BIOME_FOLIAGE)
			.setBlockTextureName(CRWG.ident("plants/leaf_pile"));

		small_waterlily = (new Block2x2FloorPlant("small_waterlily", Material.plants, EnumPlantType.Water))
			.setColorizer(Colorizer.fixed(0x208030))
			.setBlockTextureName(CRWG.ident("plants/small_waterlily"));

		/* STONE BLOCKS */

		orange_sand = (new BlockOrangeSand("orange_sand"))
			.setBlockTextureName(CRWG.ident("orange_sand"))
			.setStepSound(Block.soundTypeSand);
		orange_sand.setHarvestLevel("shovel", 0);
		orange_sandstone = (new BlockWTopBottom("orange_sandstone", Material.rock))
			.setHarvestTool("pickaxe", 0)
			.setHardness(0.8F)
			.setTopIconName(CRWG.ident("orange_sandstone_smooth"))
			.setBottomIconName(CRWG.ident("orange_sandstone_bottom"))
			.setBlockTextureName(CRWG.ident("orange_sandstone_side"))
			.setStepSound(Block.soundTypeStone);
		orange_cut_sandstone = (new BlockWTopBottom("cut_orange_sandstone", Material.rock))
			.setHarvestTool("pickaxe", 0)
			.setHardness(0.8F)
			.setTopIconName(CRWG.ident("orange_sandstone_smooth"))
			.setBottomIconName(CRWG.ident("orange_sandstone_smooth"))
			.setBlockTextureName(CRWG.ident("orange_sandstone_cut"))
			.setStepSound(Block.soundTypeStone);
		orange_chiseled_sandstone = (new BlockWTopBottom("chiseled_orange_sandstone", Material.rock))
			.setHarvestTool("pickaxe", 0)
			.setHardness(0.8F)
			.setTopIconName(CRWG.ident("orange_sandstone_smooth"))
			.setBottomIconName(CRWG.ident("orange_sandstone_smooth"))
			.setBlockTextureName(CRWG.ident("orange_sandstone_chiseled"))
			.setStepSound(Block.soundTypeStone);
		orange_smooth_sandstone = (new BlockCRWG("smooth_orange_sandstone", Material.rock))
			.setHarvestTool("pickaxe", 0)
			.setHardness(0.8F)
			.setBlockTextureName(CRWG.ident("orange_sandstone_smooth"))
			.setStepSound(Block.soundTypeStone);

		orange_sandstone_slab = new BlockSlabCRWG("orange_sandstone_slab", (BlockCRWG) orange_sandstone);
		orange_cut_sandstone_slab = new BlockSlabCRWG("cut_orange_sandstone_slab", (BlockCRWG) orange_cut_sandstone);
		orange_smooth_sandstone_slab = new BlockSlabCRWG("smooth_orange_sandstone_slab", (BlockCRWG) orange_smooth_sandstone);
		orange_sandstone_double_slab = new BlockSlabCRWG("orange_sandstone_double_slab", (BlockSlabCRWG) orange_sandstone_slab);
		orange_cut_sandstone_double_slab = new BlockSlabCRWG("cut_orange_sandstone_double_slab", (BlockSlabCRWG) orange_cut_sandstone_slab);
		orange_smooth_sandstone_double_slab = new BlockSlabCRWG("smooth_orange_sandstone_double_slab", (BlockSlabCRWG) orange_smooth_sandstone_slab);

		orange_sandstone_stairs = new BlockStairsCRWG("orange_sandstone_stairs", (BlockCRWG) orange_sandstone, 0);
		orange_smooth_sandstone_stairs = new BlockStairsCRWG("smooth_orange_sandstone_stairs", (BlockCRWG) orange_smooth_sandstone, 0);
	}

	public static void registerBlocks()
	{
		for (WoodBlocks type : WoodBlocks.values()) {
			type.registerBlocks();
		}

		registerBlock(sapling_bald_cypress);
		registerBlock(sapling_cedar);
		registerBlock(sapling_chromaticus_red);
		registerBlock(sapling_chromaticus_green);
		registerBlock(sapling_chromaticus_blue);
		registerBlock(sapling_chromaticus_violet);
		registerBlock(sapling_enchanted_ash);
		registerBlock(sapling_palm);
		registerBlock(sapling_pine);
		registerBlock(sapling_tamarack);
		registerBlock(sapling_violet);
		registerBlock(sapling_willow_black);
		registerBlock(sapling_willow_white);
		
		registerBlock(leaf_bald_cypress, new ItemLeavesCRWG(leaf_bald_cypress));
		registerBlock(leaf_cedar, new ItemLeavesCRWG(leaf_cedar));
		registerBlock(leaf_chromaticus_red, new ItemLeavesCRWG(leaf_chromaticus_red));
		registerBlock(leaf_chromaticus_green, new ItemLeavesCRWG(leaf_chromaticus_green));
		registerBlock(leaf_chromaticus_blue, new ItemLeavesCRWG(leaf_chromaticus_blue));
		registerBlock(leaf_chromaticus_violet, new ItemLeavesCRWG(leaf_chromaticus_violet));
		registerBlock(leaf_enchanted_ash, new ItemLeavesCRWG(leaf_enchanted_ash));
		registerBlock(leaf_palm, new ItemLeavesCRWG(leaf_palm));
		registerBlock(leaf_pine, new ItemLeavesCRWG(leaf_pine));
		registerBlock(leaf_tamarack, new ItemLeavesCRWG(leaf_tamarack));
		registerBlock(leaf_violet, new ItemLeavesCRWG(leaf_violet));
		registerBlock(leaf_willow_black, new ItemLeavesCRWG(leaf_willow_black));
		registerBlock(leaf_willow_white, new ItemLeavesCRWG(leaf_willow_white));

		registerBlock(silty_dirt);
		registerBlock(coarse_silty_dirt);
		registerBlock(silty_grass);
		registerBlock(silty_mycelium);
		registerBlock(silty_podzol);
		registerBlock(silty_farmland);

		registerBlock(loamy_dirt);
		registerBlock(coarse_loamy_dirt);
		registerBlock(loamy_grass);
		registerBlock(loamy_mycelium);
		registerBlock(loamy_podzol);
		registerBlock(loamy_farmland);

		registerBlock(clay_dirt);
		registerBlock(coarse_clay_dirt);
		registerBlock(clay_grass);
		registerBlock(clay_mycelium);
		registerBlock(clay_podzol);
		registerBlock(clay_farmland);

		registerBlock(dry_short_grass, new ItemBlockWithMetadata(dry_short_grass, dry_short_grass));

		registerBlock(bush);
		registerBlock(purple_bush);

		registerBlock(flower_purple_owls_clover);
		registerBlock(flower_clustered_tarweed);

		registerBlock(flower_longspur_lupine);
		registerBlock(flower_silver_lupine);
		registerBlock(flower_sky_blue_lupine);

		registerBlock(flower_sage);
		registerBlock(flower_woodland_sage);

		registerBlock(flower_hyacinth_aqua);
		registerBlock(flower_hyacinth_cream);
		registerBlock(flower_hyacinth_crimson);
		registerBlock(flower_hyacinth_deepblue);
		registerBlock(flower_hyacinth_pink);
		registerBlock(flower_hyacinth_salmonpink);

		registerBlock(dflower_purple_nightshade, ItemDoublePlantCRWG.class);

		registerBlock(dplant_cattail, ItemDoublePlantCRWG.class);

		registerBlock(clover, new Item2x2FloorPlant(clover, false));
		registerBlock(clover_large);

		registerBlock(leaf_pile, new Item2x2FloorPlant(leaf_pile, false));

		registerBlock(small_waterlily, new Item2x2FloorPlant(small_waterlily, true));

		registerBlock(orange_sand);
		registerBlock(orange_sandstone);
		registerBlock(orange_cut_sandstone);
		registerBlock(orange_chiseled_sandstone);
		registerBlock(orange_smooth_sandstone);

		registerSlabs(orange_sandstone_slab, orange_sandstone_double_slab);
		registerSlabs(orange_cut_sandstone_slab, orange_cut_sandstone_double_slab);
		registerSlabs(orange_smooth_sandstone_slab, orange_smooth_sandstone_double_slab);

		registerBlock(orange_sandstone_stairs);
		registerBlock(orange_smooth_sandstone_stairs);
	}

	static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, ItemBlockCRWG.class, block.getUnlocalizedName());
	}

	static void registerBlock(Block block, Class<? extends ItemBlock> item_class)
	{
		GameRegistry.registerBlock(block, item_class, block.getUnlocalizedName());
	}

	static void registerBlock(Block block, ItemBlock item)
	{
		GameRegistry2.registerBlock(block, item, block.getUnlocalizedName());
	}

	static void registerSlabs(Block single_slab, Block double_slab)
	{
		GameRegistry2.registerBlock(single_slab, makeSlabItem(single_slab, double_slab, false), single_slab.getUnlocalizedName());
		GameRegistry2.registerBlock(double_slab, makeSlabItem(single_slab, double_slab, true), double_slab.getUnlocalizedName());
	}

	static Item makeSlabItem(Block single_slab, Block double_slab, boolean is_double)
	{
		if (is_double) {
			return new ItemSlab(double_slab, (BlockSlab) single_slab, (BlockSlab) double_slab, false);
		}
		return new ItemSlab(single_slab, (BlockSlab) single_slab, (BlockSlab) double_slab, true);
	}

	public static boolean isModGrassBlock(Block block)
	{
		return (block instanceof BlockGrassCRWG);
	}

	public static boolean isModDirtBlock(Block block)
	{
		/* The Eclipse formatter can be dumb sometimes, and this code is in a place
		 * that is edited frequently.
		 */

		/* @formatter:off */
		return (
			block instanceof BlockPodzol
			|| block == silty_dirt
			|| block == coarse_silty_dirt
			|| block == loamy_dirt
			|| block == coarse_loamy_dirt
			|| block == clay_dirt
			|| block == coarse_clay_dirt
		);
		/* @formatter:on */
	}

	public static boolean isModFarmlandBlock(Block block)
	{
		return (block instanceof BlockFarmlandCRWG);
	}
}

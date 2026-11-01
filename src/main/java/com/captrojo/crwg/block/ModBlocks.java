package com.captrojo.crwg.block;

import com.captrojo.crwg.block.data.Colorizer;
import com.captrojo.crwg.item.Item2x2FloorPlant;
import com.captrojo.crwg.item.ItemBlockCRWG;
import com.captrojo.crwg.item.ItemDoublePlantCRWG;
import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.main.GameRegistry2;

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
	public static Block silty_dirt;
	public static Block coarse_silty_dirt;
	public static Block silty_grass;
	public static Block silty_podzol;
	public static Block silty_farmland;

	public static Block loamy_dirt;
	public static Block coarse_loamy_dirt;
	public static Block loamy_grass;
	public static Block loamy_podzol;
	public static Block loamy_farmland;

	public static Block clay_dirt;
	public static Block coarse_clay_dirt;
	public static Block clay_grass;
	public static Block clay_podzol;
	public static Block clay_farmland;

	public static Block dry_short_grass;
	
	public static Block flower_purple_owls_clover;
	public static Block flower_clustered_tarweed;
	
	public static Block flower_longspur_lupine;
	public static Block flower_silver_lupine;
	public static Block flower_sky_blue_lupine;
	
	public static Block flower_sage;
	public static Block flower_woodland_sage;
	
	public static Block dflower_purple_nightshade;
	
	public static Block clover;
	public static Block clover_large;
	
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
			.setHardness(0.7f)
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
		clay_podzol = (new BlockPodzol("clay_podzol", clay_grass))
			.setHardness(0.7F)
			.setBlockTextureName(CRWG.ident("soil/clay"))
			.setStepSound(Block.soundTypeGravel);
		clay_farmland = (new BlockFarmlandCRWG("clay_farmland", clay_dirt))
			.setHardness(0.7F)
			.setBlockTextureName(CRWG.ident("soil/clay_farmland"))
			.setStepSound(Block.soundTypeGravel);

		/* PLANTS */
		
		dry_short_grass = (new BlockShortGrass("dry_short_grass", 4, EnumPlantType.Desert))
			.setBlockTextureName(CRWG.ident("plants/dry_short_grass"))
			.setStepSound(Block.soundTypeGrass);
		
		flower_purple_owls_clover = (new BlockBushCRWG("purple_owls_clover", 1))
			.setBlockTextureName(CRWG.ident("plants/flower_purple_owls_clover"));
		flower_clustered_tarweed = (new BlockBushCRWG("clustered_tarweed", 1))
			.setBlockTextureName(CRWG.ident("plants/flower_clustered_tarweed"));
		
		flower_longspur_lupine = (new BlockBushCRWG("longspur_lupine", 1))
			.setBlockTextureName(CRWG.ident("plants/flower_longspur_lupine"));
		flower_silver_lupine = (new BlockBushCRWG("silver_lupine", 1))
			.setBlockTextureName(CRWG.ident("plants/flower_silver_lupine"));
		flower_sky_blue_lupine = (new BlockBushCRWG("sky_blue_lupine", 1))
			.setBlockTextureName(CRWG.ident("plants/flower_sky_blue_lupine"));
		
		flower_sage = (new BlockBushCRWG("sage", 1))
			.setBlockTextureName(CRWG.ident("plants/flower_sage"));
		flower_woodland_sage = (new BlockBushCRWG("woodland_sage", 1))
			.setBlockTextureName(CRWG.ident("plants/flower_woodland_sage"));
		
		dflower_purple_nightshade = (new BlockDoublePlantCRWG("purple_nightshade", 1, false))
			.setBlockTextureName(CRWG.ident("plants/double_flower_purple_nightshade"));
		
		clover = (new Block2x2FloorPlant("clover"))
			.setColorizer(Colorizer.BIOME_GRASS)
			.setBlockTextureName(CRWG.ident("plants/clover"));
		clover_large = (new BlockFloorPlant("large_clover", 1))
			.setColorizer(Colorizer.BIOME_GRASS)
			.setBlockTextureName(CRWG.ident("plants/large_clover"));
		
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

		registerBlock(silty_dirt);
		registerBlock(coarse_silty_dirt);
		registerBlock(silty_grass);
		registerBlock(silty_podzol);
		registerBlock(silty_farmland);

		registerBlock(loamy_dirt);
		registerBlock(coarse_loamy_dirt);
		registerBlock(loamy_grass);
		registerBlock(loamy_podzol);
		registerBlock(loamy_farmland);

		registerBlock(clay_dirt);
		registerBlock(coarse_clay_dirt);
		registerBlock(clay_grass);
		registerBlock(clay_podzol);
		registerBlock(clay_farmland);

		registerBlock(dry_short_grass, new ItemBlockWithMetadata(dry_short_grass, dry_short_grass));

		registerBlock(flower_purple_owls_clover);
		registerBlock(flower_clustered_tarweed);
		
		registerBlock(flower_longspur_lupine);
		registerBlock(flower_silver_lupine);
		registerBlock(flower_sky_blue_lupine);
		
		registerBlock(flower_sage);
		registerBlock(flower_woodland_sage);
		
		registerBlock(dflower_purple_nightshade, ItemDoublePlantCRWG.class);
		
		registerBlock(clover, new Item2x2FloorPlant(clover, false));
		registerBlock(clover_large);
		
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
		return (
			block instanceof BlockPodzol ||
			block == silty_dirt ||
			block == coarse_silty_dirt ||
			block == loamy_dirt ||
			block == coarse_loamy_dirt ||
			block == clay_dirt ||
			block == coarse_clay_dirt
		);
	}

	public static boolean isModFarmlandBlock(Block block)
	{
		return (block instanceof BlockFarmlandCRWG);
	}
}

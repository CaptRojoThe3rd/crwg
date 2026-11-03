package com.captrojo.crwg.util;

import com.captrojo.crwg.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BlockState
{
	public static BlockState GRASS;
	public static BlockState DIRT;
	public static BlockState COARSE_DIRT;
	public static BlockState PODZOL;
	public static BlockState FARMLAND;

	public static BlockState SILTY_DIRT;
	public static BlockState COARSE_SILTY_DIRT;
	public static BlockState SILTY_GRASS;
	public static BlockState SILTY_PODZOL;
	public static BlockState SILTY_FARMLAND;

	public static BlockState LOAMY_DIRT;
	public static BlockState COARSE_LOAMY_DIRT;
	public static BlockState LOAMY_GRASS;
	public static BlockState LOAMY_PODZOL;
	public static BlockState LOAMY_FARMLAND;

	public static BlockState CLAY_DIRT;
	public static BlockState COARSE_CLAY_DIRT;
	public static BlockState CLAY_GRASS;
	public static BlockState CLAY_PODZOL;
	public static BlockState CLAY_FARMLAND;

	public static BlockState STONE;
	public static BlockState GRAVEL;

	public static BlockState SAND;
	public static BlockState RED_SAND;
	public static BlockState ORANGE_SAND;

	public static BlockState ORANGE_SANDSTONE;

	public static BlockState WATER;
	public static BlockState ICE;
	public static BlockState PACKED_ICE;
	public static BlockState SNOW_BLOCK;

	public static void initPreMade()
	{
		GRASS = new BlockState(Blocks.grass, 0);
		DIRT = new BlockState(Blocks.dirt, 0);
		COARSE_DIRT = new BlockState(Blocks.dirt, 1);
		PODZOL = new BlockState(Blocks.dirt, 2);
		FARMLAND = new BlockState(Blocks.farmland, 0);

		SILTY_DIRT = new BlockState(ModBlocks.silty_dirt);
		COARSE_SILTY_DIRT = new BlockState(ModBlocks.coarse_silty_dirt);
		SILTY_GRASS = new BlockState(ModBlocks.silty_grass);
		SILTY_PODZOL = new BlockState(ModBlocks.silty_podzol);
		SILTY_FARMLAND = new BlockState(ModBlocks.silty_farmland);

		LOAMY_DIRT = new BlockState(ModBlocks.loamy_dirt);
		COARSE_LOAMY_DIRT = new BlockState(ModBlocks.coarse_loamy_dirt);
		LOAMY_GRASS = new BlockState(ModBlocks.loamy_grass);
		LOAMY_PODZOL = new BlockState(ModBlocks.loamy_podzol);
		LOAMY_FARMLAND = new BlockState(ModBlocks.loamy_farmland);

		CLAY_DIRT = new BlockState(ModBlocks.clay_dirt);
		COARSE_CLAY_DIRT = new BlockState(ModBlocks.coarse_clay_dirt);
		CLAY_GRASS = new BlockState(ModBlocks.clay_grass);
		CLAY_PODZOL = new BlockState(ModBlocks.clay_podzol);
		CLAY_FARMLAND = new BlockState(ModBlocks.clay_farmland);

		STONE = new BlockState(Blocks.stone, 0);
		GRAVEL = new BlockState(Blocks.gravel, 0);

		SAND = new BlockState(Blocks.sand, 0);
		RED_SAND = new BlockState(Blocks.sand, 1);
		ORANGE_SAND = new BlockState(ModBlocks.orange_sand);

		ORANGE_SANDSTONE = new BlockState(ModBlocks.orange_sandstone);

		WATER = new BlockState(Blocks.water, 0);
		ICE = new BlockState(Blocks.ice, 0);
		PACKED_ICE = new BlockState(Blocks.packed_ice, 0);
		SNOW_BLOCK = new BlockState(Blocks.snow, 0);
	}

	public Block block;
	public int meta;

	public BlockState(Block block)
	{
		this(block, 0);
	}

	public BlockState(Block block, int meta)
	{
		this.block = block;
		this.meta = meta;
	}
}

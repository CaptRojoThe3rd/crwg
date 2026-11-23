package com.captrojo.crwg.util.block;

import com.captrojo.crwg.block.ModBlocks;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockStatePool;
import com.gtnewhorizon.gtnhlib.blockstate.registry.BlockPropertyRegistry;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class BlockStates
{
	public static BlockState DIRT;
	public static BlockState COARSE_DIRT;
	public static BlockState GRASS;
	public static BlockState MYCELIUM;
	public static BlockState PODZOL;
	public static BlockState FARMLAND;

	public static BlockState SILTY_DIRT;
	public static BlockState COARSE_SILTY_DIRT;
	public static BlockState SILTY_GRASS;
	public static BlockState SILTY_MYCELIUM;
	public static BlockState SILTY_PODZOL;
	public static BlockState SILTY_FARMLAND;

	public static BlockState LOAMY_DIRT;
	public static BlockState COARSE_LOAMY_DIRT;
	public static BlockState LOAMY_GRASS;
	public static BlockState LOAMY_MYCELIUM;
	public static BlockState LOAMY_PODZOL;
	public static BlockState LOAMY_FARMLAND;

	public static BlockState CLAY_DIRT;
	public static BlockState COARSE_CLAY_DIRT;
	public static BlockState CLAY_GRASS;
	public static BlockState CLAY_MYCELIUM;
	public static BlockState CLAY_PODZOL;
	public static BlockState CLAY_FARMLAND;

	public static BlockState STONE;
	public static BlockState GRAVEL;

	public static BlockState SAND;
	public static BlockState RED_SAND;
	public static BlockState ORANGE_SAND;

	public static BlockState SANDSTONE;
	public static BlockState ORANGE_SANDSTONE;

	public static BlockState WATER;
	public static BlockState ICE;
	public static BlockState PACKED_ICE;
	public static BlockState SNOW_BLOCK;

	public static void init()
	{
		DIRT = BlockPropertyRegistry.getBlockState(Blocks.dirt, 0);
		COARSE_DIRT = BlockPropertyRegistry.getBlockState(Blocks.dirt, 1);
		GRASS = BlockPropertyRegistry.getBlockState(Blocks.grass, 0);
		MYCELIUM = BlockPropertyRegistry.getBlockState(Blocks.mycelium, 0);
		PODZOL = BlockPropertyRegistry.getBlockState(Blocks.dirt, 2);
		FARMLAND = BlockPropertyRegistry.getBlockState(Blocks.farmland, 0);

		SILTY_DIRT = BlockPropertyRegistry.getBlockState(ModBlocks.silty_dirt, 0);
		COARSE_SILTY_DIRT = BlockPropertyRegistry.getBlockState(ModBlocks.coarse_silty_dirt, 0);
		SILTY_GRASS = BlockPropertyRegistry.getBlockState(ModBlocks.silty_grass, 0);
		SILTY_MYCELIUM = BlockPropertyRegistry.getBlockState(ModBlocks.silty_mycelium, 0);
		SILTY_PODZOL = BlockPropertyRegistry.getBlockState(ModBlocks.silty_podzol, 0);
		SILTY_FARMLAND = BlockPropertyRegistry.getBlockState(ModBlocks.silty_farmland, 0);

		LOAMY_DIRT = BlockPropertyRegistry.getBlockState(ModBlocks.loamy_dirt, 0);
		COARSE_LOAMY_DIRT = BlockPropertyRegistry.getBlockState(ModBlocks.coarse_loamy_dirt, 0);
		LOAMY_GRASS = BlockPropertyRegistry.getBlockState(ModBlocks.loamy_grass, 0);
		LOAMY_MYCELIUM = BlockPropertyRegistry.getBlockState(ModBlocks.loamy_mycelium, 0);
		LOAMY_PODZOL = BlockPropertyRegistry.getBlockState(ModBlocks.loamy_podzol, 0);
		LOAMY_FARMLAND = BlockPropertyRegistry.getBlockState(ModBlocks.loamy_farmland, 0);

		CLAY_DIRT = BlockPropertyRegistry.getBlockState(ModBlocks.clay_dirt, 0);
		COARSE_CLAY_DIRT = BlockPropertyRegistry.getBlockState(ModBlocks.coarse_clay_dirt, 0);
		CLAY_GRASS = BlockPropertyRegistry.getBlockState(ModBlocks.clay_grass, 0);
		CLAY_MYCELIUM = BlockPropertyRegistry.getBlockState(ModBlocks.clay_mycelium, 0);
		CLAY_PODZOL = BlockPropertyRegistry.getBlockState(ModBlocks.clay_podzol, 0);
		CLAY_FARMLAND = BlockPropertyRegistry.getBlockState(ModBlocks.clay_farmland, 0);

		STONE = BlockPropertyRegistry.getBlockState(Blocks.stone, 0);
		GRAVEL = BlockPropertyRegistry.getBlockState(Blocks.gravel, 0);

		SAND = BlockPropertyRegistry.getBlockState(Blocks.sand, 0);
		RED_SAND = BlockPropertyRegistry.getBlockState(Blocks.sand, 1);
		ORANGE_SAND = BlockPropertyRegistry.getBlockState(ModBlocks.orange_sand, 0);

		SANDSTONE = BlockPropertyRegistry.getBlockState(Blocks.sandstone, 0);
		ORANGE_SANDSTONE = BlockPropertyRegistry.getBlockState(ModBlocks.orange_sandstone, 0);

		WATER = BlockPropertyRegistry.getBlockState(Blocks.water, 0);
		ICE = BlockPropertyRegistry.getBlockState(Blocks.ice, 0);
		PACKED_ICE = BlockPropertyRegistry.getBlockState(Blocks.packed_ice, 0);
		SNOW_BLOCK = BlockPropertyRegistry.getBlockState(Blocks.snow, 0);
	}

	public static BlockState getState(final BlockStatePool pool, final World world, final int x, final int y, final int z)
	{
		return BlockPropertyRegistry.getBlockState(pool, world, x, y, z);
	}
}

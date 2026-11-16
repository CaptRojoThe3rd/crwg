package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TreeGens
{
	public static final WorldGenAbstractTree Oak = new WorldGenTrees(false);
	public static final WorldGenAbstractTree Oak_Big = new WorldGenBigTreeF(false, 7, 20);
	public static final WorldGenAbstractTree Oak_Swamp = new WorldGenSwampF(false, Blocks.log, Blocks.leaves);
	public static final WorldGenAbstractTree Oak_Shrub = new WorldGenShrubF(Blocks.log, 0, Blocks.leaves, 0);

	public static final WorldGenAbstractTree Birch = new WorldGenForest(false, false);
	public static final WorldGenAbstractTree Birch_Tall = new WorldGenForest(false, true);

	public static final WorldGenAbstractTree Spruce_Tiny = new WorldGenTaiga2F(false, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenAbstractTree Spruce = new WorldGenTallTree(false, 9, 17, false, false, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenAbstractTree Spruce_Big = new WorldGenTallTree(false, 18, 35, true, true, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenAbstractTree Spruce_Shrub = new WorldGenShrubF(Blocks.log, 1, Blocks.leaves, 1);

	public static final WorldGenAbstractTree Jungle_Small = new WorldGenTrees(false, 9, 3, 3, true);
	public static final WorldGenAbstractTree Jungle_Large = new WorldGenMegaJungle(false, 10, 20, 3, 3);
	public static final WorldGenAbstractTree Jungle_Shrub = new WorldGenShrubF(3, 3);

	public static final WorldGenAbstractTree Acacia = new WorldGenSavannaTree(false);
	public static final WorldGenAbstractTree Acacia_Shrub = new WorldGenShrubF(Blocks.log2, 0, Blocks.leaves2, 0);

	public static final WorldGenAbstractTree Dark_Oak = new WorldGenCanopyTree(false);

	public static final WorldGenAbstractTree Bald_Cypress = new WorldGenBaldCypress(false);
	public static final WorldGenAbstractTree Bald_Cypress_N = new WorldGenBaldCypress(true);

	public static final WorldGenAbstractTree Cedar_Tiny_N = new WorldGenTaiga2F(true, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenAbstractTree Cedar_Tiny = new WorldGenTaiga2F(false, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenAbstractTree Cedar_Small = new WorldGenTallTree(false, 9, 16, false, false, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenAbstractTree Cedar_Big = new WorldGenTallTree(false, 18, 35, true, true, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenAbstractTree Cedar_Shrub = new WorldGenShrubF(WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);

	public static final WorldGenAbstractTree Chromaticus_Red_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);
	public static final WorldGenAbstractTree Chromaticus_Red = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);
	public static final WorldGenAbstractTree Chromaticus_Red_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red)).big();
	public static final WorldGenAbstractTree Chromaticus_Red_Shrub = new WorldGenShrubF(WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);

	public static final WorldGenAbstractTree Chromaticus_Green_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);
	public static final WorldGenAbstractTree Chromaticus_Green = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);
	public static final WorldGenAbstractTree Chromaticus_Green_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green)).big();
	public static final WorldGenAbstractTree Chromaticus_Green_Shrub = new WorldGenShrubF(WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);

	public static final WorldGenAbstractTree Chromaticus_Blue_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);
	public static final WorldGenAbstractTree Chromaticus_Blue = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);
	public static final WorldGenAbstractTree Chromaticus_Blue_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue)).big();
	public static final WorldGenAbstractTree Chromaticus_Blue_Shrub = new WorldGenShrubF(WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);

	public static final WorldGenAbstractTree Chromaticus_Violet_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);
	public static final WorldGenAbstractTree Chromaticus_Violet = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);
	public static final WorldGenAbstractTree Chromaticus_Violet_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet)).big();
	public static final WorldGenAbstractTree Chromaticus_Violet_Shrub = new WorldGenShrubF(WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);

	public static final WorldGenAbstractTree Enchanted_Ash_N = (new WorldGenBigTreeF(true, 12, 18, WoodBlocks.ENCHANTED_ASH.log, ModBlocks.leaf_enchanted_ash)).big();
	public static final WorldGenAbstractTree Enchanted_Ash = (new WorldGenBigTreeF(false, 12, 18, WoodBlocks.ENCHANTED_ASH.log, ModBlocks.leaf_enchanted_ash)).big();

	public static final WorldGenAbstractTree Palm_N = new WorldGenTreePalm(true);
	public static final WorldGenAbstractTree Palm = new WorldGenTreePalm(false);

	public static final WorldGenAbstractTree Pine_Tiny_N = new WorldGenTaiga2F(true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenAbstractTree Pine_Tiny = new WorldGenTaiga2F(false, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenAbstractTree Pine_Small = new WorldGenTallTree(false, 9, 17, false, false, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenAbstractTree Pine_Med = new WorldGenTallTree(false, 18, 25, true, true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenAbstractTree Pine_Big = new WorldGenTallTree(false, 25, 42, true, true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenAbstractTree Pine_Shrub = new WorldGenShrubF(WoodBlocks.PINE.log, ModBlocks.leaf_pine);

	public static final WorldGenAbstractTree Tamarack_Tiny_N = new WorldGenTaiga2F(true, WoodBlocks.TAMARACK.log, ModBlocks.leaf_tamarack);
	public static final WorldGenAbstractTree Tamarack_Tiny = new WorldGenTaiga2F(false, WoodBlocks.TAMARACK.log, ModBlocks.leaf_tamarack);

	public static final WorldGenAbstractTree Violet_Tiny_N = new WorldGenTaiga2F(true, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet);
	public static final WorldGenAbstractTree Violet_Small = new WorldGenTallThinPine(false, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet, 20, 28);
	public static final WorldGenAbstractTree Violet_Big = new WorldGenTallThinPine(false, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet, 25, 35);

	public static final WorldGenAbstractTree Willow_Black = new WorldGenSwampF(false, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_black);
	public static final WorldGenAbstractTree Willow_Black_N = new WorldGenSwampF(true, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_black);

	public static final WorldGenAbstractTree Willow_White = new WorldGenSwampF(false, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_white);
	public static final WorldGenAbstractTree Willow_White_N = new WorldGenSwampF(true, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_white);

	public static WorldGenerator getBaldCypressSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Bald_Cypress_N;
	}

	public static WorldGenerator getCedarSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Cedar_Tiny_N;
	}

	public static WorldGenerator getChromaticusRedSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Red_Small_N;
	}

	public static WorldGenerator getChromaticusGreenSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Green_Small_N;
	}

	public static WorldGenerator getChromaticusBlueSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Blue_Small_N;
	}

	public static WorldGenerator getChromaticusVioletSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Violet_Small_N;
	}

	public static WorldGenerator getEnchantedAshSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Enchanted_Ash_N;
	}

	public static WorldGenerator getPalmSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Palm_N;
	}

	public static WorldGenerator getPineSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Pine_Tiny_N;
	}

	public static WorldGenerator getTamarackSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Tamarack_Tiny_N;
	}

	public static WorldGenerator getVioletSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Violet_Tiny_N;
	}

	public static WorldGenerator getWillowBlackSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Willow_Black_N;
	}

	public static WorldGenerator getWillowWhiteSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Willow_White_N;
	}
}

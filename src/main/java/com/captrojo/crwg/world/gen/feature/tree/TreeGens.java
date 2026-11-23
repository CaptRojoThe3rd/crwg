package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;
import com.captrojo.crwg.world.gen.feature.Feature;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class TreeGens
{
	public static final Feature Oak = (Feature) new WorldGenTrees(false);
	public static final Feature Oak_Big = (Feature) new WorldGenBigTreeF(false, 7, 20);
	public static final Feature Oak_Swamp = (Feature) new WorldGenSwampTree(false, Blocks.log, Blocks.leaves);
	public static final Feature Oak_Shrub = (Feature) new WorldGenTreeShrub(Blocks.log, 0, Blocks.leaves, 0);

	public static final Feature Birch = (Feature) new WorldGenForest(false, false);
	public static final Feature Birch_Tall = (Feature) new WorldGenForest(false, true);

	public static final Feature Spruce_Tiny = (Feature) new WorldGenSmallSpruceTree(false, Blocks.log, 1, Blocks.leaves, 1);
	public static final Feature Spruce = (Feature) new WorldGenTallTree(false, 9, 17, false, false, Blocks.log, 1, Blocks.leaves, 1);
	public static final Feature Spruce_Big = (Feature) new WorldGenTallTree(false, 18, 35, true, true, Blocks.log, 1, Blocks.leaves, 1);
	public static final Feature Spruce_Shrub = (Feature) new WorldGenTreeShrub(Blocks.log, 1, Blocks.leaves, 1);

	public static final Feature Jungle_Small = (Feature) new WorldGenTrees(false, 9, 3, 3, true);
	public static final Feature Jungle_Large = (Feature) new WorldGenMegaJungle(false, 10, 20, 3, 3);
	public static final Feature Jungle_Shrub = (Feature) new WorldGenTreeShrub(3, 3);

	public static final Feature Acacia = (Feature) new WorldGenSavannaTree(false);
	public static final Feature Acacia_Shrub = (Feature) new WorldGenTreeShrub(Blocks.log2, 0, Blocks.leaves2, 0);

	public static final Feature Dark_Oak = (Feature) new WorldGenCanopyTree(false);

	public static final Feature Bald_Cypress = (Feature) new WorldGenBaldCypressTree(false);
	public static final Feature Bald_Cypress_N = (Feature) new WorldGenBaldCypressTree(true);

	public static final Feature Cedar_Tiny_N = (Feature) new WorldGenSmallSpruceTree(true, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final Feature Cedar_Tiny = (Feature) new WorldGenSmallSpruceTree(false, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final Feature Cedar_Small = (Feature) new WorldGenTallTree(false, 9, 16, false, false, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final Feature Cedar_Big = (Feature) new WorldGenTallTree(false, 18, 35, true, true, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final Feature Cedar_Shrub = (Feature) new WorldGenTreeShrub(WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);

	public static final Feature Chromaticus_Red_Small_N = (Feature) new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);
	public static final Feature Chromaticus_Red = (Feature) new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);
	public static final Feature Chromaticus_Red_Big = (Feature) (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red)).big();
	public static final Feature Chromaticus_Red_Shrub = (Feature) new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);

	public static final Feature Chromaticus_Green_Small_N = (Feature) new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);
	public static final Feature Chromaticus_Green = (Feature) new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);
	public static final Feature Chromaticus_Green_Big = (Feature) (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green)).big();
	public static final Feature Chromaticus_Green_Shrub = (Feature) new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);

	public static final Feature Chromaticus_Blue_Small_N = (Feature) new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);
	public static final Feature Chromaticus_Blue = (Feature) new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);
	public static final Feature Chromaticus_Blue_Big = (Feature) (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue)).big();
	public static final Feature Chromaticus_Blue_Shrub = (Feature) new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);

	public static final Feature Chromaticus_Violet_Small_N = (Feature) new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);
	public static final Feature Chromaticus_Violet = (Feature) new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);
	public static final Feature Chromaticus_Violet_Big = (Feature) (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet)).big();
	public static final Feature Chromaticus_Violet_Shrub = (Feature) new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);

	public static final Feature Enchanted_Ash_N = (Feature) (new WorldGenBigTreeF(true, 12, 18, WoodBlocks.ENCHANTED_ASH.log, ModBlocks.leaf_enchanted_ash)).big();
	public static final Feature Enchanted_Ash = (Feature) (new WorldGenBigTreeF(false, 12, 18, WoodBlocks.ENCHANTED_ASH.log, ModBlocks.leaf_enchanted_ash)).big();

	public static final Feature Palm_N = (Feature) new WorldGenPalmTree(true);
	public static final Feature Palm = (Feature) new WorldGenPalmTree(false);

	public static final Feature Pine_Tiny_N = (Feature) new WorldGenSmallSpruceTree(true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final Feature Pine_Tiny = (Feature) new WorldGenSmallSpruceTree(false, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final Feature Pine_Small = (Feature) new WorldGenTallTree(false, 9, 17, false, false, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final Feature Pine_Med = (Feature) new WorldGenTallTree(false, 18, 25, true, true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final Feature Pine_Big = (Feature) new WorldGenTallTree(false, 25, 42, true, true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final Feature Pine_Shrub = (Feature) new WorldGenTreeShrub(WoodBlocks.PINE.log, ModBlocks.leaf_pine);

	public static final Feature Tamarack_Tiny_N = (Feature) new WorldGenSmallSpruceTree(true, WoodBlocks.TAMARACK.log, ModBlocks.leaf_tamarack);
	public static final Feature Tamarack_Tiny = (Feature) new WorldGenSmallSpruceTree(false, WoodBlocks.TAMARACK.log, ModBlocks.leaf_tamarack);

	public static final Feature Violet_Tiny_N = (Feature) new WorldGenSmallSpruceTree(true, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet);
	public static final Feature Violet_Small = (Feature) new WorldGenTallThinPineTree(false, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet, 20, 28);
	public static final Feature Violet_Big = (Feature) new WorldGenTallThinPineTree(false, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet, 25, 35);

	public static final Feature Willow_Black = (Feature) new WorldGenSwampTree(false, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_black);
	public static final Feature Willow_Black_N = (Feature) new WorldGenSwampTree(true, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_black);

	public static final Feature Willow_White = (Feature) new WorldGenSwampTree(false, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_white);
	public static final Feature Willow_White_N = (Feature) new WorldGenSwampTree(true, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_white);

	public static Feature getBaldCypressSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Bald_Cypress_N;
	}

	public static Feature getCedarSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Cedar_Tiny_N;
	}

	public static Feature getChromaticusRedSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Red_Small_N;
	}

	public static Feature getChromaticusGreenSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Green_Small_N;
	}

	public static Feature getChromaticusBlueSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Blue_Small_N;
	}

	public static Feature getChromaticusVioletSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Chromaticus_Violet_Small_N;
	}

	public static Feature getEnchantedAshSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Enchanted_Ash_N;
	}

	public static Feature getPalmSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Palm_N;
	}

	public static Feature getPineSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Pine_Tiny_N;
	}

	public static Feature getTamarackSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Tamarack_Tiny_N;
	}

	public static Feature getVioletSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Violet_Tiny_N;
	}

	public static Feature getWillowBlackSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Willow_Black_N;
	}

	public static Feature getWillowWhiteSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Willow_White_N;
	}
}

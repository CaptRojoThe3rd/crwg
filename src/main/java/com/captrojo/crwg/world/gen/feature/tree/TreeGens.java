package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TreeGens
{
	public static final WorldGenerator Oak = new WorldGenTrees(false);
	public static final WorldGenerator Oak_Big = new WorldGenBigTreeF(false, 7, 20);
	public static final WorldGenerator Oak_Swamp = new WorldGenSwampTree(false, Blocks.log, Blocks.leaves);
	public static final WorldGenerator Oak_Shrub = new WorldGenTreeShrub(Blocks.log, 0, Blocks.leaves, 0);

	public static final WorldGenerator Birch = new WorldGenForest(false, false);
	public static final WorldGenerator Birch_Tall = new WorldGenForest(false, true);

	public static final WorldGenerator Spruce_Tiny = new WorldGenSmallSpruceTree(false, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenerator Spruce = new WorldGenTallTree(false, 9, 17, false, false, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenerator Spruce_Big = new WorldGenTallTree(false, 18, 35, true, true, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenerator Spruce_Shrub = new WorldGenTreeShrub(Blocks.log, 1, Blocks.leaves, 1);

	public static final WorldGenerator Jungle_Small = new WorldGenTrees(false, 9, 3, 3, true);
	public static final WorldGenerator Jungle_Large = new WorldGenMegaJungle(false, 10, 20, 3, 3);
	public static final WorldGenerator Jungle_Shrub = new WorldGenTreeShrub(3, 3);

	public static final WorldGenerator Acacia = new WorldGenSavannaTree(false);
	public static final WorldGenerator Acacia_Shrub = new WorldGenTreeShrub(Blocks.log2, 0, Blocks.leaves2, 0);

	public static final WorldGenerator Dark_Oak = new WorldGenCanopyTree(false);

	public static final WorldGenerator Bald_Cypress = new WorldGenBaldCypressTree(false);
	public static final WorldGenerator Bald_Cypress_N = new WorldGenBaldCypressTree(true);

	public static final WorldGenerator Cedar_Tiny_N = new WorldGenSmallSpruceTree(true, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenerator Cedar_Tiny = new WorldGenSmallSpruceTree(false, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenerator Cedar_Small = new WorldGenTallTree(false, 9, 16, false, false, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenerator Cedar_Big = new WorldGenTallTree(false, 18, 35, true, true, WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);
	public static final WorldGenerator Cedar_Shrub = new WorldGenTreeShrub(WoodBlocks.CEDAR.log, ModBlocks.leaf_cedar);

	public static final WorldGenerator Chromaticus_Red_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);
	public static final WorldGenerator Chromaticus_Red = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);
	public static final WorldGenerator Chromaticus_Red_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red)).big();
	public static final WorldGenerator Chromaticus_Red_Shrub = new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_RED.log, ModBlocks.leaf_chromaticus_red);

	public static final WorldGenerator Chromaticus_Green_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);
	public static final WorldGenerator Chromaticus_Green = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);
	public static final WorldGenerator Chromaticus_Green_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green)).big();
	public static final WorldGenerator Chromaticus_Green_Shrub = new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_GREEN.log, ModBlocks.leaf_chromaticus_green);

	public static final WorldGenerator Chromaticus_Blue_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);
	public static final WorldGenerator Chromaticus_Blue = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);
	public static final WorldGenerator Chromaticus_Blue_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue)).big();
	public static final WorldGenerator Chromaticus_Blue_Shrub = new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_BLUE.log, ModBlocks.leaf_chromaticus_blue);

	public static final WorldGenerator Chromaticus_Violet_Small_N = new WorldGenBigTreeF(true, 5, 8, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);
	public static final WorldGenerator Chromaticus_Violet = new WorldGenBigTreeF(false, 6, 16, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);
	public static final WorldGenerator Chromaticus_Violet_Big = (new WorldGenBigTreeF(false, 10, 18, WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet)).big();
	public static final WorldGenerator Chromaticus_Violet_Shrub = new WorldGenTreeShrub(WoodBlocks.CHROMATICUS_VIOLET.log, ModBlocks.leaf_chromaticus_violet);

	public static final WorldGenerator Enchanted_Ash_N = (new WorldGenBigTreeF(true, 12, 18, WoodBlocks.ENCHANTED_ASH.log, ModBlocks.leaf_enchanted_ash)).big();
	public static final WorldGenerator Enchanted_Ash = (new WorldGenBigTreeF(false, 12, 18, WoodBlocks.ENCHANTED_ASH.log, ModBlocks.leaf_enchanted_ash)).big();

	public static final WorldGenerator Palm_N = new FeaturePalmTree(true);
	public static final WorldGenerator Palm = new FeaturePalmTree(false);

	public static final WorldGenerator Pine_Tiny_N = new WorldGenSmallSpruceTree(true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenerator Pine_Tiny = new WorldGenSmallSpruceTree(false, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenerator Pine_Small = new WorldGenTallTree(false, 9, 17, false, false, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenerator Pine_Med = new WorldGenTallTree(false, 18, 25, true, true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenerator Pine_Big = new WorldGenTallTree(false, 25, 42, true, true, WoodBlocks.PINE.log, ModBlocks.leaf_pine);
	public static final WorldGenerator Pine_Shrub = new WorldGenTreeShrub(WoodBlocks.PINE.log, ModBlocks.leaf_pine);

	public static final WorldGenerator Tamarack_Tiny_N = new WorldGenSmallSpruceTree(true, WoodBlocks.TAMARACK.log, ModBlocks.leaf_tamarack);
	public static final WorldGenerator Tamarack_Tiny = new WorldGenSmallSpruceTree(false, WoodBlocks.TAMARACK.log, ModBlocks.leaf_tamarack);

	public static final WorldGenerator Violet_Tiny_N = new WorldGenSmallSpruceTree(true, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet);
	public static final WorldGenerator Violet_Small = new WorldGenTallThinPineTree(false, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet, 20, 28);
	public static final WorldGenerator Violet_Big = new WorldGenTallThinPineTree(false, WoodBlocks.VIOLET.log, ModBlocks.leaf_violet, 25, 35);

	public static final WorldGenerator Willow_Black = new WorldGenSwampTree(false, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_black);
	public static final WorldGenerator Willow_Black_N = new WorldGenSwampTree(true, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_black);

	public static final WorldGenerator Willow_White = new WorldGenSwampTree(false, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_white);
	public static final WorldGenerator Willow_White_N = new WorldGenSwampTree(true, WoodBlocks.WILLOW.log, ModBlocks.leaf_willow_white);

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

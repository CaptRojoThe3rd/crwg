package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.WoodBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TreeGens
{
	public static final WorldGenAbstractTree Oak = new WorldGenTrees(false);
	public static final WorldGenAbstractTree Oak_Big = new WorldGenBigTreeF(false, 7, 20);
	public static final WorldGenAbstractTree Oak_Swamp = new WorldGenSwamp();

	public static final WorldGenAbstractTree Dark_Oak = new WorldGenCanopyTree(false);

	public static final WorldGenAbstractTree Birch = new WorldGenForest(false, false);
	public static final WorldGenAbstractTree Birch_Tall = new WorldGenForest(false, true);

	public static final WorldGenAbstractTree Spruce_Tiny = new WorldGenTaiga2F(false, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenAbstractTree Spruce = new WorldGenTallTree(false, 9, 17, false, false, Blocks.log, 1, Blocks.leaves, 1);
	public static final WorldGenAbstractTree Spruce_Big = new WorldGenTallTree(false, 18, 35, true, true, Blocks.log, 1, Blocks.leaves, 1);

	public static final WorldGenAbstractTree Jungle_Small = new WorldGenTrees(false, 12, 3, 3, true);
	public static final WorldGenAbstractTree Jungle_Large = new WorldGenMegaJungle(false, 10, 20, 3, 3);
	public static final WorldGenAbstractTree Jungle_Shrub = new WorldGenShrubF(3, 3);

	public static final WorldGenAbstractTree Bald_Cypress = new WorldGenBaldCypress(false);
	public static final WorldGenAbstractTree Bald_Cypress_N = new WorldGenBaldCypress(true);

	public static final WorldGenAbstractTree Palm_N = new WorldGenTreePalm(true);
	public static final WorldGenAbstractTree Palm = new WorldGenTreePalm(false);

	public static final WorldGenAbstractTree Pine_Tiny_N = new WorldGenTaiga2F(true, WoodBlocks.PINE);
	public static final WorldGenAbstractTree Pine_Small = new WorldGenTallTree(false, 9, 17, false, false, WoodBlocks.PINE);
	public static final WorldGenAbstractTree Pine_Med = new WorldGenTallTree(false, 18, 25, true, true, WoodBlocks.PINE);
	public static final WorldGenAbstractTree Pine_Big = new WorldGenTallTree(false, 25, 42, true, true, WoodBlocks.PINE);

	public static final WorldGenAbstractTree Cedar_Tiny_N = new WorldGenTaiga2F(true, WoodBlocks.CEDAR);
	public static final WorldGenAbstractTree Cedar_Tiny = new WorldGenTaiga2F(false, WoodBlocks.CEDAR);
	public static final WorldGenAbstractTree Cedar_Small = new WorldGenTallTree(false, 9, 16, false, false, WoodBlocks.CEDAR);
	public static final WorldGenAbstractTree Cedar_Big = new WorldGenTallTree(false, 18, 35, true, true, WoodBlocks.CEDAR);
	public static final WorldGenAbstractTree Cedar_Shrub = new WorldGenShrubF(WoodBlocks.CEDAR);

	public static WorldGenerator getBaldCypressSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Bald_Cypress_N;
	}

	public static WorldGenerator getCedarSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Cedar_Tiny_N;
	}

	public static WorldGenerator getPalmSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Palm_N;
	}

	public static WorldGenerator getPineSaplingGen(World world, Random rand, int x, int y, int z)
	{
		return Pine_Tiny_N;
	}
}

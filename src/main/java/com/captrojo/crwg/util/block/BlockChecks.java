package com.captrojo.crwg.util.block;

import com.captrojo.crwg.block.BlockPodzol;
import com.captrojo.crwg.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;

public abstract class BlockChecks
{
	public static boolean isAnyDirt(Block block)
	{
		return (block == Blocks.dirt || ModBlocks.isModDirtBlock(block) || block instanceof BlockPodzol); 
	}

	public static boolean isGrass(Block block)
	{
		return (block instanceof BlockGrass);
	}
}

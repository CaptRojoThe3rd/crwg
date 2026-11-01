package com.captrojo.crwg.util;

import com.captrojo.crwg.block.BlockPodzol;
import com.captrojo.crwg.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public abstract class BlockChks
{
	public static boolean isDirt(Block block)
	{
		return (block == Blocks.dirt || ModBlocks.isModDirtBlock(block) || block instanceof BlockPodzol); 
	}
}

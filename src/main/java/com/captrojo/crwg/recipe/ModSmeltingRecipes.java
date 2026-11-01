package com.captrojo.crwg.recipe;

import com.captrojo.crwg.block.ModBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public abstract class ModSmeltingRecipes
{
	public static void registerRecipes()
	{
		add(ModBlocks.orange_sandstone, new ItemStack(ModBlocks.orange_smooth_sandstone, 1), 0.1F);
	}
	
	private static void add(Block input, ItemStack output, float xp)
	{
		GameRegistry.addSmelting(input, output, xp);
	}
}

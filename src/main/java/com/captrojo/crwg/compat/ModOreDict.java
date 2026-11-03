package com.captrojo.crwg.compat;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public abstract class ModOreDict
{
	public static void registerModOres()
	{
		final int WILD = OreDictionary.WILDCARD_VALUE;

		for (WoodBlocks wood : WoodBlocks.values()) {
			OreDictionary.registerOre("logWood", new ItemStack(wood.log, 1, WILD));
			OreDictionary.registerOre("plankWood", new ItemStack(wood.planks, 1, WILD));
			OreDictionary.registerOre("slabWood", new ItemStack(wood.slab, 1, WILD));
			OreDictionary.registerOre("stairWood", new ItemStack(wood.stairs, 1, WILD));
			OreDictionary.registerOre("treeSapling", new ItemStack(wood.sapling, 1, WILD));
			OreDictionary.registerOre("treeLeaves", new ItemStack(wood.leaf, 1, WILD));
		}

		OreDictionary.registerOre("sand", ModBlocks.orange_sand);
		OreDictionary.registerOre("sandstone", ModBlocks.orange_sandstone);
		OreDictionary.registerOre("sandstone", ModBlocks.orange_cut_sandstone);
		OreDictionary.registerOre("sandstone", ModBlocks.orange_chiseled_sandstone);
		OreDictionary.registerOre("sandstone", ModBlocks.orange_smooth_sandstone);
	}
}

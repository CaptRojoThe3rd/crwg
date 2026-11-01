package com.captrojo.crwg.recipe;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public abstract class ModCraftingRecipes
{
	public static void registerRecipes()
	{
		for (WoodBlocks wood : WoodBlocks.values()) {
			addShaped(new ItemStack(wood.log, 3, 12), "aa", "aa", 'a', new ItemStack(wood.log, 1, 0));
			addShaped(new ItemStack(wood.log, 3, 13), "aa", "aa", 'a', new ItemStack(wood.log, 1, 1));
			addShapeless(new ItemStack(wood.planks, 4), new ItemStack(wood.log, 1, 0));
			addShapeless(new ItemStack(wood.planks, 4), new ItemStack(wood.log, 1, 1));
			addShapeless(new ItemStack(wood.planks, 4), new ItemStack(wood.log, 1, 12));
			addShapeless(new ItemStack(wood.planks, 4), new ItemStack(wood.log, 1, 13));
			addShaped(new ItemStack(wood.slab, 6), "aaa", 'a', wood.planks);
			addShaped(new ItemStack(wood.stairs, 4), "a  ", "aa ", "aaa", 'a', wood.planks);
			addShapedOre(new ItemStack(wood.fence, 2), "aba", "aba", 'a', wood.planks, 'b', "stickWood");
			addShapedOre(new ItemStack(wood.fence_gate, 1), "bab", "bab", 'a', wood.planks, 'b', "stickWood");
			addShapeless(new ItemStack(wood.button, 1), wood.planks);
			addShaped(new ItemStack(wood.pressure_plate, 1), "aa", 'a', wood.planks);
			addShaped(new ItemStack(wood.door, 1), "aa", "aa", "aa", 'a', wood.planks);
			addShaped(new ItemStack(wood.trapdoor, 1), "aaa", "aaa", 'a', wood.planks);
			addShapedOre(new ItemStack(wood.sign_standing, 1), "aaa", "aaa", " b ", 'a', wood.planks, 'b', "stickWood");
		}
		
		addShaped(new ItemStack(ModBlocks.orange_sandstone, 1), "aa", "aa", 'a', ModBlocks.orange_sand);
		addShaped(new ItemStack(ModBlocks.orange_cut_sandstone, 4), "aa", "aa", 'a', ModBlocks.orange_sandstone);
		addShaped(new ItemStack(ModBlocks.orange_sandstone_slab, 6), "aaa", 'a', ModBlocks.orange_sandstone);
		addShaped(new ItemStack(ModBlocks.orange_cut_sandstone_slab, 6), "aaa", 'a', ModBlocks.orange_cut_sandstone);
		addShaped(new ItemStack(ModBlocks.orange_smooth_sandstone_slab, 6), "aaa", 'a', ModBlocks.orange_smooth_sandstone_slab);
		addShaped(new ItemStack(ModBlocks.orange_chiseled_sandstone, 1), "a", "a", 'a', ModBlocks.orange_sandstone_slab);
		addShaped(new ItemStack(ModBlocks.orange_sandstone_stairs, 4), "a  ", "aa ", "aaa", 'a', ModBlocks.orange_sandstone);
		addShaped(new ItemStack(ModBlocks.orange_sandstone_stairs, 4), "a  ", "aa ", "aaa", 'a', ModBlocks.orange_cut_sandstone);
		addShaped(new ItemStack(ModBlocks.orange_smooth_sandstone_stairs, 4), "a  ", "aa ", "aaa", 'a', ModBlocks.orange_smooth_sandstone);
	}
	
	private static void addShaped(ItemStack result, Object...objs)
	{
		GameRegistry.addShapedRecipe(result, objs);
	}
	
	private static void addShapedOre(ItemStack result, Object...objs)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(result, objs));
	}
	
	private static void addShapeless(ItemStack result, Object...objs)
	{
		GameRegistry.addShapelessRecipe(result, objs);
	}
}

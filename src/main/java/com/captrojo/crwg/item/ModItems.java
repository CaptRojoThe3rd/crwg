package com.captrojo.crwg.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public abstract class ModItems
{
	public static Item debug_item;

	public static void initItems()
	{
		debug_item = new ItemDebug();
	}

	public static void registerItems()
	{
		GameRegistry.registerItem(debug_item, debug_item.getUnlocalizedName());
	}
}

package com.captrojo.crwg.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public abstract class ItemUtil
{
	public static ArrayList<ItemStack> createListWithStack(ItemStack stack)
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(stack);
		return list;
	}
}

package com.captrojo.crwg.creativetab;

import java.util.List;

import com.captrojo.crwg.block.WoodBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabBlocks extends CreativeTabs
{
	public TabBlocks()
	{
		super("crwg_blocks");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllReleventItems(List<ItemStack> list)
	{
		for (WoodBlocks type : WoodBlocks.values()) {
			type.addBlocksToList(list);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(WoodBlocks.PALM.planks);
	}
}

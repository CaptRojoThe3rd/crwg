package com.captrojo.crwg.creativetab;

import java.lang.reflect.Field;
import java.util.List;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabBlocks extends CreativeTabs
{
	public TabBlocks()
	{
		super("crwg_blocks");
	}

	@SideOnly(Side.CLIENT)
	public void addBlock(Block block, List<ItemStack> list)
	{
		block.getSubBlocks(Item.getItemFromBlock(block), this, list);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllReleventItems(List<ItemStack> list)
	{
		for (WoodBlocks type : WoodBlocks.values()) {
			type.addWoodBlocksToList(list);
		}
		for (WoodBlocks type : WoodBlocks.values()) {
			type.leaf.getSubBlocks(Item.getItemFromBlock(type.leaf), this, list);
		}
		for (WoodBlocks type : WoodBlocks.values()) {
			type.sapling.getSubBlocks(Item.getItemFromBlock(type.sapling), this, list);
		}

		for (Field field : ModBlocks.class.getFields()) {
			Object obj;
			try {
				obj = field.get(null);
			} catch (Throwable t) {
				continue;
			}
			if (obj instanceof Block) {
				this.addBlock((Block) obj, list);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(WoodBlocks.PALM.planks);
	}
}

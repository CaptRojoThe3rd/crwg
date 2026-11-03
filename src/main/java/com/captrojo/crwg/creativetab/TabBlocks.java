package com.captrojo.crwg.creativetab;

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
		
		this.addBlock(ModBlocks.silty_dirt, list);
		this.addBlock(ModBlocks.coarse_silty_dirt, list);
		this.addBlock(ModBlocks.silty_grass, list);
		this.addBlock(ModBlocks.silty_mycelium, list);
		this.addBlock(ModBlocks.silty_podzol, list);
		this.addBlock(ModBlocks.silty_farmland, list);

		this.addBlock(ModBlocks.loamy_dirt, list);
		this.addBlock(ModBlocks.coarse_loamy_dirt, list);
		this.addBlock(ModBlocks.loamy_grass, list);
		this.addBlock(ModBlocks.loamy_mycelium, list);
		this.addBlock(ModBlocks.loamy_podzol, list);
		this.addBlock(ModBlocks.loamy_farmland, list);

		this.addBlock(ModBlocks.clay_dirt, list);
		this.addBlock(ModBlocks.coarse_clay_dirt, list);
		this.addBlock(ModBlocks.clay_grass, list);
		this.addBlock(ModBlocks.clay_mycelium, list);
		this.addBlock(ModBlocks.clay_podzol, list);
		this.addBlock(ModBlocks.clay_farmland, list);
		
		this.addBlock(ModBlocks.dry_short_grass, list);
		
		this.addBlock(ModBlocks.bush, list);
		
		this.addBlock(ModBlocks.flower_purple_owls_clover, list);
		this.addBlock(ModBlocks.flower_clustered_tarweed, list);
		
		this.addBlock(ModBlocks.flower_longspur_lupine, list);
		this.addBlock(ModBlocks.flower_silver_lupine, list);
		this.addBlock(ModBlocks.flower_sky_blue_lupine, list);
		
		this.addBlock(ModBlocks.flower_sage, list);
		this.addBlock(ModBlocks.flower_woodland_sage, list);
		
		this.addBlock(ModBlocks.dflower_purple_nightshade, list);
		
		this.addBlock(ModBlocks.clover, list);
		this.addBlock(ModBlocks.clover_large, list);
		
		this.addBlock(ModBlocks.leaf_pile, list);
		
		this.addBlock(ModBlocks.small_waterlily, list);
		
		this.addBlock(ModBlocks.orange_sand, list);
		this.addBlock(ModBlocks.orange_sandstone, list);
		this.addBlock(ModBlocks.orange_cut_sandstone, list);
		this.addBlock(ModBlocks.orange_chiseled_sandstone, list);
		this.addBlock(ModBlocks.orange_smooth_sandstone, list);
		
		this.addBlock(ModBlocks.orange_sandstone_slab, list);
		this.addBlock(ModBlocks.orange_cut_sandstone_slab, list);
		this.addBlock(ModBlocks.orange_smooth_sandstone_slab, list);
		
		this.addBlock(ModBlocks.orange_sandstone_stairs, list);
		this.addBlock(ModBlocks.orange_smooth_sandstone_stairs, list);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(WoodBlocks.PALM.planks);
	}
}

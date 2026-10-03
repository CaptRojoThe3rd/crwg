package com.captrojo.crwg.block;

import java.util.List;

import com.captrojo.crwg.item.ItemModLog;
import com.captrojo.crwg.main.CRWG;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public enum WoodBlocks
{
	PALM("palm");
	
	public final Block log;
	public final Block planks;
	
	WoodBlocks(String name)
	{
		this.log = (new BlockModLog(name + "_log"))
			.setBlockTextureName(CRWG.ident("wood/" + name + "_log"));
		this.planks = (new BlockBase(name + "_planks", Material.wood))
			.setFlammable(5, 20)
			.setHardness(2.0f)
			.setResistance(5.0f)
			.setBlockTextureName(CRWG.ident("wood/" + name + "_planks"))
			.setStepSound(Block.soundTypeWood);
		
	}
	
	void registerBlocks()
	{
		GameRegistry.registerBlock(this.log, ItemModLog.class, this.log.getUnlocalizedName());
		GameRegistry.registerBlock(this.planks, this.planks.getUnlocalizedName());
	}
	
	public void addBlocksToList(List<ItemStack> list)
	{
		list.add(new ItemStack(this.log, 1, 0x0));
		list.add(new ItemStack(this.log, 1, 0x1));
		list.add(new ItemStack(this.log, 1, 0xc));
		list.add(new ItemStack(this.log, 1, 0xd));
		list.add(new ItemStack(this.planks));
	}
}

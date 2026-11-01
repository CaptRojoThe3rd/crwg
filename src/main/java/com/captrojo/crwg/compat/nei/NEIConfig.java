package com.captrojo.crwg.compat.nei;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;
import com.captrojo.crwg.main.CRWG;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * NEI will automatically load this due to the name "NEIConfig"
 */
public class NEIConfig implements IConfigureNEI
{
	@Override
	public void loadConfig()
	{
		final int WILD = OreDictionary.WILDCARD_VALUE;
		
		for (WoodBlocks wood : WoodBlocks.values()) {
			API.hideItem(new ItemStack(wood.double_slab, 1, WILD));
		}
		
		API.hideItem(new ItemStack(ModBlocks.orange_sandstone_double_slab, 1, WILD));
		API.hideItem(new ItemStack(ModBlocks.orange_smooth_sandstone_double_slab, 1, WILD));
	}

	@Override
	public String getName()
	{
		return CRWG.NAME;
	}

	@Override
	public String getVersion()
	{
		return null;
	}
}

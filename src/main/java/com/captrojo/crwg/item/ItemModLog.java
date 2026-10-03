package com.captrojo.crwg.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemModLog extends ItemBlock
{
	public ItemModLog(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		int meta = stack.getItemDamage() & 0xd;
		
		switch (meta) {
		default:
		case 0x0:
			return this.getUnlocalizedName() + ".regular";
		case 0x1:
			return this.getUnlocalizedName() + ".regular_stripped";
		case 0xc:
			return this.getUnlocalizedName() + ".fullbark";
		case 0xd:
			return this.getUnlocalizedName() + ".fullbark_stripped";
		}
	}
}

package com.captrojo.crwg.item;

import java.util.List;

import com.captrojo.crwg.util.I18nUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemLogCRWG extends ItemBlock
{
	public ItemLogCRWG(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced_tooltips)
	{
		I18nUtil.addItemDesc(stack, list);
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

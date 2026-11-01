package com.captrojo.crwg.item;

import java.util.List;

import com.captrojo.crwg.util.I18nUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCRWG extends ItemBlock
{
	public ItemBlockCRWG(Block block)
	{
		super(block);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		return this.field_150939_a.getRenderColor(stack.getItemDamage());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced_tooltips)
	{
		I18nUtil.addItemDesc(stack, list);
	}
}

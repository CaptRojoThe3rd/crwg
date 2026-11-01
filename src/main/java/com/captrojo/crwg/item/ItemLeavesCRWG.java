package com.captrojo.crwg.item;

import java.util.List;

import com.captrojo.crwg.util.I18nUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;

public class ItemLeavesCRWG extends ItemLeaves
{
	public ItemLeavesCRWG(BlockLeaves block)
	{
		super(block);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced_tooltips)
	{
		I18nUtil.addItemDesc(stack, list);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.getUnlocalizedName();
	}
}

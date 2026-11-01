package com.captrojo.crwg.item;

import java.util.List;

import com.captrojo.crwg.block.BlockDoublePlantCRWG;
import com.captrojo.crwg.util.I18nUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoublePlant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;

public class ItemDoublePlantCRWG extends ItemDoublePlant
{
	public ItemDoublePlantCRWG(Block block)
	{
		super(block, (BlockDoublePlant) block, null);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced_tooltips)
	{
		I18nUtil.addItemDesc(stack, list);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return ((BlockDoublePlant) this.field_150939_a).func_149888_a(true, meta);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par1)
	{
		if (((BlockDoublePlantCRWG) this.field_150939_a).is_grass) {
			return ColorizerGrass.getGrassColor(0.5D, 1.0D);
		}
		return 0xffffff;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName();
	}
}

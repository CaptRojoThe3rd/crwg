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
		if (this.field_150938_b != null) {
			return this.field_150938_b;
		}
		return ((BlockDoublePlant) this.field_150939_a).func_149888_a(true, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par1)
	{
		if (((BlockDoublePlantCRWG) this.field_150939_a).non_colored_layer) {
			return 0xffffff;
		}
		return ((BlockDoublePlantCRWG) this.field_150939_a).getRenderColor(stack.getItemDamage());
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName();
	}
}

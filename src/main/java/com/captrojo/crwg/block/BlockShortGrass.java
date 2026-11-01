package com.captrojo.crwg.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.captrojo.crwg.util.RefStrs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;

public class BlockShortGrass extends BlockBush implements IShearable
{
	final int variants;
	final EnumPlantType type;

	@SideOnly(Side.CLIENT)
	IIcon[] icons;

	public BlockShortGrass(String name, int variants, EnumPlantType type)
	{
		this.variants = variants;
		this.type = type;

		this.setBlockName(name);
		this.setCreativeTab(null);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.icons = new IIcon[this.variants];

		for (int i = 0; i < this.variants; i++) {
			this.icons[i] = reg.registerIcon(this.textureName + "_" + RefStrs.LETTERS[i]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.icons[meta % this.variants];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (int i = 0; i < this.variants; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return null;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return list;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return this.type;
	}
}

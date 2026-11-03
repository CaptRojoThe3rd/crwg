package com.captrojo.crwg.block;

import java.util.List;
import java.util.Random;

import com.captrojo.crwg.block.data.Colorizer;
import com.captrojo.crwg.main.CRWG;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockLeavesCRWG extends BlockLeaves
{
	final String[] a_wood_name;
	final Block sapling;
	
	Colorizer colorizer = Colorizer.BIOME_FOLIAGE;

	public BlockLeavesCRWG(String wood_name, Block sapling, Colorizer colorizer)
	{
		this.setBlockName(wood_name + "_leaves");
		this.setCreativeTab(null);
		this.setStepSound(Block.soundTypeGrass);

		this.sapling = sapling;
		this.a_wood_name = new String[] {wood_name};
		
		this.colorizer = colorizer;

		this.textureName = CRWG.ident("leaves/" + wood_name);
	}

	@Override
	public String[] func_150125_e()
	{
		return this.a_wood_name;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this.sapling);
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		list.add(new ItemStack(item));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.field_150129_M[0] = new IIcon[] {reg.registerIcon(this.textureName)};
		this.field_150129_M[1] = new IIcon[] {reg.registerIcon(this.textureName + "_opaque")};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		this.field_150121_P = Minecraft.getMinecraft().gameSettings.fancyGraphics;
		int i = this.field_150121_P ? 0 : 1;
		return this.field_150129_M[i][0];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		return this.colorizer.getRenderColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		return this.colorizer.colorMultipler(world, x, y, z);
	}
}

package com.captrojo.crwg.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLogCRWG extends BlockLog
{
	private int flammability = 5;
	private int fire_spread_speed = 5;

	public BlockLogCRWG(String name)
	{
		super();
		this.setBlockName(name);
		this.setCreativeTab(null);
	}

	public BlockLogCRWG setFlammable(int fire_spread_speed, int flammability)
	{
		this.flammability = flammability;
		this.fire_spread_speed = fire_spread_speed;
		return this;
	}

	public BlockLogCRWG setHarvestTool(String tool, int level)
	{
		this.setHarvestLevel(tool, level);
		return this;
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return this.flammability;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return this.fire_spread_speed;
	}

	@Override
	public int damageDropped(int meta)
	{
		if ((meta & 0xc) == 0xc) {
			return meta;
		}
		return meta & 0x1;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float sx, float sy, float sz, int meta)
	{
		if ((meta & 0xc) == 0xc) {
			return meta;
		}
		return super.onBlockPlaced(world, x, y, z, side, sx, sy, sz, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		list.add(new ItemStack(item, 1, 0x0));
		list.add(new ItemStack(item, 1, 0x1));
		list.add(new ItemStack(item, 1, 0xc));
		list.add(new ItemStack(item, 1, 0xd));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.field_150167_a = new IIcon[] {
			reg.registerIcon(this.textureName + "_side"),
			reg.registerIcon(this.textureName + "_side_stripped")
		};
		this.field_150166_b = new IIcon[] {
			reg.registerIcon(this.textureName + "_end"),
			reg.registerIcon(this.textureName + "_end_stripped")
		};
	}
}

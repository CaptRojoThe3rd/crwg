package com.captrojo.crwg.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFenceGateCRWG extends BlockFenceGate
{
	public int flammability = 0;
	public int fire_spread_speed = 0;

	public BlockFenceGateCRWG(String name, BlockCRWG parent)
	{
		this.setBlockName(name);
		this.setCreativeTab(null);

		this.textureName = parent.textureName;
		this.flammability = parent.flammability;
		this.fire_spread_speed = parent.fire_spread_speed;
		this.blockHardness = parent.blockHardness;
		this.blockResistance = parent.blockResistance;
		this.stepSound = parent.stepSound;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = reg.registerIcon(this.textureName);
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
}

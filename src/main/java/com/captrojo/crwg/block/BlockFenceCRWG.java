package com.captrojo.crwg.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFenceCRWG extends BlockFence
{
	public int flammability = 0;
	public int fire_spread_speed = 0;
	
	public BlockFenceCRWG(String name, BlockCRWG parent)
	{
		super(parent.textureName, parent.getMaterial());
		this.setBlockName(name);
		this.setCreativeTab(null);
		
		this.flammability = parent.flammability;
		this.fire_spread_speed = parent.fire_spread_speed;
		this.textureName = parent.textureName;
		this.blockHardness = parent.blockHardness;
		this.blockResistance = parent.blockResistance;
		this.stepSound = parent.stepSound;
	}
	
	@Override
	public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if (block instanceof BlockFence || block instanceof BlockFenceGate) {
			return true;
		}
		if (block.getMaterial().isOpaque() && block.renderAsNormalBlock()) {
			return block.getMaterial() != Material.gourd;
		}
		return false;
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

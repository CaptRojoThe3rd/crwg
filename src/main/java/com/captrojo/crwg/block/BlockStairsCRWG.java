package com.captrojo.crwg.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockStairsCRWG extends BlockStairs
{
	public int flammability = 0;
	public int fire_spread_speed = 0;
	
	public BlockStairsCRWG(String name, BlockCRWG parent_block, int parent_meta)
	{
		super(parent_block, parent_meta);
		this.setBlockName(name);
		this.setCreativeTab(null);
		
		this.setHarvestLevel(parent_block.getHarvestTool(0), parent_block.getHarvestLevel(0));
		
		this.flammability = parent_block.flammability;
		this.fire_spread_speed = parent_block.fire_spread_speed;
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

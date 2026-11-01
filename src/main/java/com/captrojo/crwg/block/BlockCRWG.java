package com.captrojo.crwg.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCRWG extends Block
{
	public int flammability = 0;
	public int fire_spread_speed = 0;

	public BlockCRWG(String name, Material material)
	{
		super(material);
		this.setBlockName(name);
	}
	
	public BlockCRWG setFlammable(int fire_spread_speed, int flammability)
	{
		this.flammability = flammability;
		this.fire_spread_speed = fire_spread_speed;
		return this;
	}
	
	public BlockCRWG setHarvestTool(String tool_class, int level)
	{
		this.setHarvestLevel(tool_class, level);
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
}

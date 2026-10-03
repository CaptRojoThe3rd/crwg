package com.captrojo.crwg.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBase extends Block
{
	private int flammability = 0;
	private int fire_spread_speed = 0;

	public BlockBase(String name, Material material)
	{
		super(material);
		this.setBlockName(name);
	}
	
	public final BlockBase setFlammable(int fire_spread_speed, int flammability)
	{
		this.flammability = flammability;
		this.fire_spread_speed = fire_spread_speed;
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

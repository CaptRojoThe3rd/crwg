package com.captrojo.crwg.block;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockTrapDoorCRWG extends BlockTrapDoor
{
	public int flammability = 0;
	public int fire_spread_speed = 0;

	public BlockTrapDoorCRWG(String name, Material material)
	{
		super(material);
		this.setBlockName(name);
		this.setCreativeTab(null);

		if (material == Material.wood) {
			this.flammability = 5;
			this.fire_spread_speed = 20;
		}
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

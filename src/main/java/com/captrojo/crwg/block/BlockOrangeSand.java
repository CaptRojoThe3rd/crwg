package com.captrojo.crwg.block;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockOrangeSand extends BlockFalling
{
	public BlockOrangeSand(String name)
	{
		super(Material.sand);
		this.setBlockName(name);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		switch (plantable.getPlantType(world, x, y, z)) {
		case Desert:
			return true;
		case Beach:
			boolean hasWater = (
				world.getBlock(x - 1, y, z).getMaterial() == Material.water ||
				world.getBlock(x + 1, y, z).getMaterial() == Material.water ||
				world.getBlock(x, y, z - 1).getMaterial() == Material.water ||
				world.getBlock(x, y, z + 1).getMaterial() == Material.water
			);
			return hasWater;
		default:
			return super.canSustainPlant(world, x, y, z, direction, plantable);
		}
	}
}

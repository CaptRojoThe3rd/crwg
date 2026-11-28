package com.captrojo.crwg.world.gen.placement;

import com.captrojo.crwg.util.block.BlockChecks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class PlacementChecks
{
	public static boolean isValidSoil(final World world, final int x, final int y, final int z)
	{
		Block block = world.getBlock(x, y, z);
		return BlockChecks.isAnyDirt(block) || BlockChecks.isGrass(block);
	}

	public static boolean isValidSoilFor(
		final World world,
		final int x,
		final int y,
		final int z,
		final ForgeDirection dir,
		final IPlantable plant
	)
	{
		return world.getBlock(x, y, z).canSustainPlant(world, x, y, z, dir, plant);
	}

	public static boolean isReplaceableByLeaves(final World world, final int x, final int y, final int z)
	{
		Block block = world.getBlock(x, y, z);
		if (block.isAir(world, x, y, z)) {
			return true;
		}
		if (block.isLeaves(world, x, y, z)) {
			return true;
		}
		if (block instanceof BlockBush) {
			return true;
		}
		return false;
	}
}

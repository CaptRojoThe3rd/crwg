package com.captrojo.crwg.world.gen.feature;

import com.captrojo.crwg.util.block.BlockChecks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.world.World;

public interface PlantFeature
{
	default boolean isValidSoil(final World world, final int x, final int y, final int z)
	{
		Block block = world.getBlock(x, y, z);
		return BlockChecks.isAnyDirt(block) || BlockChecks.isGrass(block);
	}

	default boolean isReplaceableByLeaves(final World world, final int x, final int y, final int z)
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

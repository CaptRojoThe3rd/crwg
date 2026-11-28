package com.captrojo.crwg.world.gen.feature;

import com.captrojo.crwg.util.func.WorldXYZPredicate;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenFeature extends WorldGenerator
{
	private WorldXYZPredicate block_placement_test;

	public WorldGenFeature()
	{
		super();
	}

	public WorldGenFeature(boolean notify)
	{
		super(notify);
	}

	protected void initBlockPlacementTest(WorldXYZPredicate test)
	{
		this.block_placement_test = test;
	}

	protected void setBlock(final World world, final int x, final int y, final int z, final BlockState block)
	{
		if (this.doBlockNotify) {
			block.place(world, x, y, z, 3);
		} else {
			block.place(world, x, y, z, 2);
		}
	}

	protected void setBlock(final World world, final int x, final int y, final int z, final Block block)
	{
		if (this.doBlockNotify) {
			world.setBlock(x, y, z, block, 0, 3);
		} else {
			world.setBlock(x, y, z, block, 0, 2);
		}
	}

	protected void setBlock(final World world, final int x, final int y, final int z, final Block block, final int meta)
	{
		if (this.doBlockNotify) {
			world.setBlock(x, y, z, block, meta, 3);
		} else {
			world.setBlock(x, y, z, block, meta, 2);
		}
	}

	protected void setBlockChecked(final World world, final int x, final int y, final int z, final BlockState block)
	{
		if (this.block_placement_test.test(world, x, y, z)) {
			this.setBlock(world, x, y, z, block);
		}
	}

	protected void setBlockChecked(final World world, final int x, final int y, final int z, final Block block)
	{
		if (this.block_placement_test.test(world, x, y, z)) {
			this.setBlock(world, x, y, z, block);
		}
	}

	protected void setBlockChecked(final World world, final int x, final int y, final int z, final Block block, final int meta)
	{
		if (this.block_placement_test.test(world, x, y, z)) {
			this.setBlock(world, x, y, z, block, meta);
		}
	}
}

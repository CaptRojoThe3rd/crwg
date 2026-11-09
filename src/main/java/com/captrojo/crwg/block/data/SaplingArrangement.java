package com.captrojo.crwg.block.data;

import com.captrojo.crwg.block.BlockSaplingCRWG;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public enum SaplingArrangement
{
	SINGLE(0, 0),
	TWOBYTWO(-1, -1);

	/** offsets for finding saplings */
	public final int xo, zo;
	/** size of area of saplings */
	public final int xs, zs;

	SaplingArrangement(int xo, int zo)
	{
		this.xo = xo;
		this.zo = zo;
		this.xs = 1 - xo;
		this.zs = 1 - zo;
	}

	public boolean check(World world, int x, int y, int z, BlockSaplingCRWG sapling)
	{
		for (int x1 = 0; x1 < this.xs; x1++) {
			for (int z1 = 0; z1 < this.zs; z1++) {
				if (!sapling.func_149880_a(world, x + x1, y, z1 + z, 0)) {
					return false;
				}
			}
		}
		return true;
	}

	public void clear(World world, int x, int y, int z)
	{
		for (int x1 = 0; x1 < this.xs; x1++) {
			for (int z1 = 0; z1 < this.zs; z1++) {
				world.setBlock(x + x1, y, z + z1, Blocks.air, 0, 4);
			}
		}
	}

	public void unclear(World world, int x, int y, int z, BlockSaplingCRWG sapling)
	{
		for (int x1 = 0; x1 < this.xs; x1++) {
			for (int z1 = 0; z1 < this.zs; z1++) {
				world.setBlock(x + x1, y, z + z1, sapling, 8, 4);
			}
		}
	}
}
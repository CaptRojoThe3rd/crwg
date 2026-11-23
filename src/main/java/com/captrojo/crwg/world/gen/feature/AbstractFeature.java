package com.captrojo.crwg.world.gen.feature;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public abstract class AbstractFeature implements Feature
{
	private final boolean notify;
	
	public AbstractFeature()
	{
		this(false);
	}

	public AbstractFeature(boolean notify)
	{
		this.notify = notify;
	}

	protected void setBlock(final World world, final int x, final int y, final int z, final BlockState block)
	{
		if (this.notify) {
			block.place(world, x, y, z, 3);
		} else {
			block.place(world, x, y, z, 2);
		}
	}

	/* These two functions already exist, but with annoying names. */

	protected void setBlock(final World world, final int x, final int y, final int z, final Block block)
	{
		if (this.notify) {
			world.setBlock(x, y, z, block, 0, 3);
		} else {
			world.setBlock(x, y, z, block, 0, 2);
		}
	}

	protected void setBlock(final World world, final int x, final int y, final int z, final Block block, final int meta)
	{
		if (this.notify) {
			world.setBlock(x, y, z, block, meta, 3);
		} else {
			world.setBlock(x, y, z, block, meta, 2);
		}
	}
}

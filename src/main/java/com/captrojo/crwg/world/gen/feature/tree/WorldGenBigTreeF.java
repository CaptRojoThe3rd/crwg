package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.WoodBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class WorldGenBigTreeF extends WorldGenBigTree
{
	private int min_height;

	private Block log_block = Blocks.log;
	private Block leaf_block = Blocks.leaves;

	public WorldGenBigTreeF(boolean notify)
	{
		this(notify, 5, 17);
	}

	public WorldGenBigTreeF(boolean notify, int min_height, int max_height)
	{
		super(notify);
		this.min_height = min_height;
		this.heightLimitLimit = max_height - min_height + 1;
	}

	public WorldGenBigTreeF(boolean notify, int min_height, int max_height, WoodBlocks wood)
	{
		this(notify, min_height, max_height);
		this.log_block = wood.log;
		this.leaf_block = wood.leaf;
	}

	public WorldGenBigTreeF big()
	{
		this.trunkSize = 2;
		return this;
	}

	public WorldGenBigTreeF tweak()
	{
		this.leafDistanceLimit = 3;
		return this;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (this.worldObj != null) {
			return false; /* because for some reason Mojang wrote it this way */
		}
		this.heightLimit = this.min_height + rand.nextInt(this.heightLimitLimit);
		return super.generate(world, rand, x, y, z);
	}

	@Override
	protected void setBlockAndNotifyAdequately(World world, int x, int y, int z, Block block, int meta)
	{
		if (block == Blocks.log) {
			block = this.log_block;
		} else if (block == Blocks.leaves) {
			block = this.leaf_block;
		}
		super.setBlockAndNotifyAdequately(world, x, y, z, block, meta);
	}
}

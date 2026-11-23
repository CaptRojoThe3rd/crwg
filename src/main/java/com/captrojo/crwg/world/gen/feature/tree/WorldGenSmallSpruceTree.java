package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.feature.PlantFeature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenSmallSpruceTree extends WorldGenerator implements PlantFeature
{
	private final Block log_block;
	private final int log_meta;
	private final Block leaf_block;
	private final int leaf_meta;

	public WorldGenSmallSpruceTree(boolean notify, Block log, Block leaf)
	{
		this(notify, log, 0, leaf, 0);
	}

	public WorldGenSmallSpruceTree(boolean notify, Block log, int log_meta, Block leaf, int leaf_meta)
	{
		super(notify);
		this.log_block = log;
		this.log_meta = log_meta;
		this.leaf_block = leaf;
		this.leaf_meta = leaf_meta;
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		int height = rand.nextInt(4) + 6;
		int adj = 1 + rand.nextInt(2);
		int adj_height = height - adj;
		int rng22 = 2 + rand.nextInt(2);

		int rad;

		for (int y1 = y; y1 <= y + 1 + height; y1++) {
			if (y1 - y < adj) {
				rad = 0;
			} else {
				rad = rng22;
			}

			for (int x1 = (x - rad); x1 <= (x + rad); x1++) {
				for (int z1 = (z - rad); z1 <= (z + rad); z1++) {
					if (y1 < YConst.MIN_Y_LVL || y1 >= YConst.MAX_Y_LVL) {
						return false;
					}
					Block block = world.getBlock(x1, y1, z1);
					if (!block.isAir(world, x1, y1, z1) && !block.isLeaves(world, x1, y1, z1)) {
						return false;
					}
				}
			}
		}

		Block gnd = world.getBlock(x, y - 1, z);
		if (!gnd.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) Blocks.sapling)) {
			return false;
		}
		gnd.onPlantGrow(world, x, y - 1, z, x, y, z);

		rad = rand.nextInt(2);
		int rad_lim = 1;
		byte b0 = 0;
		int y2, ya;

		for (ya = 0; ya <= adj_height; ya++) {
			y2 = y + height - ya;

			for (int x2 = (x - rad); x2 <= (x + rad); x2++) {
				int xd = x2 - x;

				for (int z2 = (z - rad); z2 <= (z + rad); z2++) {
					int zd = z2 - z;

					if (!(Math.abs(xd) != rad || Math.abs(zd) != rad || rad <= 0)) {
						continue;
					}

					Block block = world.getBlock(x2, y2, z2);
					if (!block.canBeReplacedByLeaves(world, x2, y2, z2)) {
						continue;
					}

					this.setBlockAndNotifyAdequately(world, x2, y2, z2, this.leaf_block, this.leaf_meta);
				}
			}

			if (rad >= rad_lim) {
				rad = b0;
				b0 = 1;
				rad_lim++;

				if (rad_lim > rng22) {
					rad_lim = rng22;
				}
			} else {
				rad++;
			}
		}

		ya = rand.nextInt(3);

		for (y2 = 0; y2 < height - ya; y2++) {
			Block block = world.getBlock(x, y + y2, z);

			if (block.isAir(world, x, y + y2, z) || block.isLeaves(world, x, y + y2, z)) {
				this.setBlockAndNotifyAdequately(world, x, y + y2, z, this.log_block, this.log_meta);
			}
		}

		return true;
	}
}

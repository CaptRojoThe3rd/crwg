package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.main.CRWG;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMycelium;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;

public class WorldGenBigMushroomF extends WorldGenBigMushroom
{
	public WorldGenBigMushroomF(int type)
	{
		super(type);
	}

	public WorldGenBigMushroomF()
	{
		super();
	}

	protected boolean isValidSoil(Block block)
	{
		if (block instanceof BlockGrass) {
			return true;
		}
		if (block instanceof BlockMycelium) {
			return true;
		}
		if (block == Blocks.dirt || ModBlocks.isModDirtBlock(block)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int height = rand.nextInt(3) + 4;

		final int min_y = CRWG.funcs.getMinHeight();
		final int max_y = CRWG.funcs.getMaxHeight();

		if (!(y >= (min_y + 1) && y + height + 1 < max_y)) {
			return false;
		}

		int type;
		if (this.mushroomType >= 0) {
			type = this.mushroomType;
		} else {
			type = rand.nextInt(2);
		}

		final Block mushroom_block = (type == 0) ? Blocks.brown_mushroom_block : Blocks.red_mushroom_block;

		int x1, z1;

		for (int y1 = y; y1 <= y + 1 + height; ++y1) {
			byte chk_range = 3;
			if (y1 <= y + 3) {
				chk_range = 0;
			}

			for (x1 = x - chk_range; x1 <= x + chk_range; ++x1) {
				for (z1 = z - chk_range; z1 <= z + chk_range; ++z1) {
					if (y1 >= min_y && y1 < max_y) {
						Block block = world.getBlock(x1, y1, z1);

						if (!block.isAir(world, x1, y1, z1) && !block.isLeaves(world, x1, y1, z1)) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}

		Block gnd_block = world.getBlock(x, y - 1, z);
		if (!this.isValidSoil(gnd_block)) {
			return false;
		}

		int ya = y + height;
		if (type == 1) {
			ya -= 3;
		}

		for (int y1 = ya; y1 <= y + height; y1++) {
			int r = 1;

			if (y1 < y + height) {
				r++;
			}

			if (type == 0) {
				r = 3;
			}

			for (x1 = x - r; x1 <= x + r; ++x1) {
				for (z1 = z - r; z1 <= z + r; ++z1) {
					int meta = 5;

					if (x1 == x - r) {
						meta--;
					}

					if (x1 == x + r) {
						meta++;
					}

					if (z1 == z - r) {
						meta -= 3;
					}

					if (z1 == z + r) {
						meta += 3;
					}

					if (type == 0 || y1 < y + height) {
						if ((x1 == x - r || x1 == x + r) && (z1 == z - r || z1 == z + r)) {
							continue;
						}

						if (x1 == x - (r - 1) && z1 == z - r) {
							meta = 1;
						}

						if (x1 == x - r && z1 == z - (r - 1)) {
							meta = 1;
						}

						if (x1 == x + (r - 1) && z1 == z - r) {
							meta = 3;
						}

						if (x1 == x + r && z1 == z - (r - 1)) {
							meta = 3;
						}

						if (x1 == x - (r - 1) && z1 == z + r) {
							meta = 7;
						}

						if (x1 == x - r && z1 == z + (r - 1)) {
							meta = 7;
						}

						if (x1 == x + (r - 1) && z1 == z + r) {
							meta = 9;
						}

						if (x1 == x + r && z1 == z + (r - 1)) {
							meta = 9;
						}
					}

					if (meta == 5 && y1 < y + height) {
						meta = 0;
					}

					if (!(meta != 0 || y >= y + height - 1)) {
						continue;
					}
					if (world.getBlock(x1, y1, z1).canBeReplacedByLeaves(world, x1, y1, z1)) {
						this.setBlockAndNotifyAdequately(world, x1, y1, z1, mushroom_block, meta);
					}
				}
			}
		}

		for (int y1 = 0; y1 < height; y1++) {
			Block block = world.getBlock(x, y + y1, z);

			if (!block.canBeReplacedByLeaves(world, x, y + y1, z)) {
				continue;
			}

			this.setBlockAndNotifyAdequately(world, x, y + y1, z, mushroom_block, 10);
		}

		return true;
	}
}

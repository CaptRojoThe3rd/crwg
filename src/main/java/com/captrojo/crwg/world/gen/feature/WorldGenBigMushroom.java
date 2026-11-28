package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.placement.PlacementChecks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigMushroom extends WorldGenerator
{
	public WorldGenBigMushroom()
	{
		super(false);
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		int height = rand.nextInt(3) + 4;

		if (!(y >= (YConst.MIN_Y_LVL + 1) && y + height + 1 < YConst.MAX_Y_LVL)) {
			return false;
		}

		int type = rand.nextInt(2);

		final Block mushroom_block = (type == 0) ? Blocks.brown_mushroom_block : Blocks.red_mushroom_block;

		int x1, z1;

		for (int y1 = y; y1 <= y + 1 + height; ++y1) {
			byte chk_range = 3;
			if (y1 <= y + 3) {
				chk_range = 0;
			}

			for (x1 = x - chk_range; x1 <= x + chk_range; ++x1) {
				for (z1 = z - chk_range; z1 <= z + chk_range; ++z1) {
					if (y1 >= YConst.MIN_Y_LVL && y1 < YConst.MAX_Y_LVL) {
						if (!PlacementChecks.isReplaceableByLeaves(world, x1, y1, z1)) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}

		if (!PlacementChecks.isValidSoil(world, x, y - 1, z)) {
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

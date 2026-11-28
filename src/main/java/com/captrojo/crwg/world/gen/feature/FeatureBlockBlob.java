package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.util.block.BlockChecks;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class FeatureBlockBlob extends WorldGenFeature
{
	private final Block block;

	public FeatureBlockBlob(final Block block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		final int min_y = YConst.MIN_Y_LVL + 3;

		while (y > min_y) {
			Block block = world.getBlock(x, y - 1, z);
			if (!block.isAir(world, x, y - 1, z)) {
				if (block == Blocks.stone) {
					break;
				}
				if (BlockChecks.isGrass(block)) {
					break;
				}
				if (BlockChecks.isAnyDirt(block)) {
					break;
				}
			}
			y--;
		}
		if (y <= min_y) {
			return false;
		}

		for (int l = 0; l < 3; l++) {
			int xs = rand.nextInt(2);
			int ys = rand.nextInt(2);
			int zs = rand.nextInt(2);
			float avg_d = (float) (xs + ys + zs) * 0.333F + 0.5F;

			for (BlockPos pos : BlockPos.getAllInBox(x - xs, y - ys, z - zs, x + xs, y + ys, z + zs)) {
				if (pos.distanceSquared(x, y, z) < avg_d * avg_d) {
					world.setBlock(pos.x, pos.y, pos.z, this.block, 0, 2);
				}
			}

			x += rand.nextInt(2) - 1;
			z += rand.nextInt(2) - 1;
			y -= rand.nextInt(2);
		}

		return true;
	}
}

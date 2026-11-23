package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class FeatureShallowPond extends AbstractFeature
{
	private final int radius = 4;
	private final int radius_variance = 3;

	@Override
	public boolean generate(final World world, final Random rand, final BlockPos origin)
	{
		final int max_rad = this.radius + this.radius_variance;

		for (int xo = -max_rad; xo <= max_rad; xo++) {
			int xo_sq = xo * xo;
		z_loop:
			for (int zo = -max_rad; zo <= max_rad; zo++) {
				int local_rad = this.radius + rand.nextInt(this.radius_variance);
				if ((xo_sq + zo * zo) > (local_rad * local_rad)) {
					continue;
				}

				int x1 = origin.x + xo;
				int z1 = origin.z + zo;

				if (!world.isAirBlock(x1, origin.y + 1, z1)) {
					continue;
				}

				for (int d = 0; d < 4; d++) {
					int x2 = x1 + Direction.offsetX[d];
					int z2 = z1 + Direction.offsetZ[d];
					Block block = world.getBlock(x2, origin.y, z2);
					if (!block.isOpaqueCube() && !block.getMaterial().isLiquid()) {
						continue z_loop;
					}
				}

				world.setBlock(x1, origin.y, z1, Blocks.water, 0, 2);
			}
		}

		return true;
	}
}

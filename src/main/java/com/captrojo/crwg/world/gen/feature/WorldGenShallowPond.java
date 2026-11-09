package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenShallowPond extends WorldGenerator
{
	final int radius = 4;
	final int radius_variance = 3;

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		final int max_rad = this.radius + this.radius_variance;

		for (int xo = -max_rad; xo <= max_rad; xo++) {
		z_loop:
			for (int zo = -max_rad; zo <= max_rad; zo++) {
				int local_rad = this.radius + rand.nextInt(this.radius_variance);
				if ((xo * xo + zo * zo) > (local_rad * local_rad)) {
					continue;
				}

				int x1 = x + xo;
				int z1 = z + zo;

				if (!world.isAirBlock(x1, y + 1, z1)) {
					continue;
				}

				for (int d = 0; d < 4; d++) {
					int x2 = x1 + Direction.offsetX[d];
					int z2 = z1 + Direction.offsetZ[d];
					Block block = world.getBlock(x2, y, z2);
					if (!block.isOpaqueCube() && !block.getMaterial().isLiquid()) {
						continue z_loop;
					}
				}

				world.setBlock(x1, y, z1, Blocks.water, 0, 2);
			}
		}

		return true;
	}
}

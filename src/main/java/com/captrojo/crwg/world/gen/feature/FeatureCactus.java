package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class FeatureCactus extends WorldGenFeature
{
	@Override
	public boolean generate(final World world, final Random rand, int ox, int oy, int oz)
	{
		for (int l = 0; l < 10; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = ox + rand.nextInt(8) - rand.nextInt(8);
			int z = oz + rand.nextInt(8) - rand.nextInt(8);
			int y = world.getHeightValue(x, z);

			if (world.isAirBlock(x, y, z)) {
				int height = 1 + rand.nextInt(rand.nextInt(3) + 1);

				for (int h = 0; h < height; h++) {
					if (Blocks.cactus.canBlockStay(world, x, y + h, z)) {
						world.setBlock(x, y + h, z, Blocks.cactus, 0, 2);
					}
				}
			}
		}

		return true;
	}
}

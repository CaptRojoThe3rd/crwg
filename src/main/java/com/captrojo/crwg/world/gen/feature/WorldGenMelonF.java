package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMelon;

public class WorldGenMelonF extends WorldGenMelon
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		for (int l = 0; l < 64; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			int z1 = z + rand.nextInt(8) - rand.nextInt(8);
			int y1 = world.getHeightValue(x1, z1);

			if (
				world.getBlock(x1, y1 - 1, z1) instanceof BlockGrass &&
				Blocks.melon_block.canPlaceBlockAt(world, x1, y1, z1)
			) {
				world.setBlock(x1, y1, z1, Blocks.melon_block, 0, 2);
			}
		}

		return true;
	}
}

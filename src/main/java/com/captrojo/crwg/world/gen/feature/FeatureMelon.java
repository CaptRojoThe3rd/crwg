package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockChecks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class FeatureMelon extends WorldGenFeature
{
	private final Block block;

	public FeatureMelon(Block block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(final World world, final Random rand, int ox, int oy, int oz)
	{
		for (int l = 0; l < 64; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = ox + rand.nextInt(8) - rand.nextInt(8);
			int z = oz + rand.nextInt(8) - rand.nextInt(8);
			int y = world.getHeightValue(x, z);

			if (
				world.isAirBlock(x, y, z)
				&& BlockChecks.isGrass(world.getBlock(x, y - 1, z))
				&& Blocks.melon_block.canPlaceBlockAt(world, x, y, z)
			) {
				world.setBlock(x, y, z, this.block, 0, 2);
			}
		}

		return true;
	}
}

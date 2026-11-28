package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class FeatureWaterlily extends WorldGenFeature
{
	private final Block block;

	public FeatureWaterlily()
	{
		this(Blocks.waterlily);
	}

	public FeatureWaterlily(Block block)
	{
		this.block = block;
	}

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

			if (world.isAirBlock(x, y, z) && this.block.canPlaceBlockAt(world, x, y, z)) {
				int meta = (this.block == ModBlocks.small_waterlily) ? (rand.nextInt(15) + 1) : 0;
				world.setBlock(x, y, z, this.block, meta, 2);
			}
		}

		return true;
	}
}

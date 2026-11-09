package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenWaterlily;

public class WorldGenWaterlilyF extends WorldGenWaterlily
{
	final Block block;

	public WorldGenWaterlilyF()
	{
		this(Blocks.waterlily);
	}

	public WorldGenWaterlilyF(Block block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		for (int l = 0; l < 10; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			int z1 = z + rand.nextInt(8) - rand.nextInt(8);
			int y1 = world.getHeightValue(x1, z1);

			if (world.isAirBlock(x1, y1, z1) && this.block.canPlaceBlockAt(world, x1, y1, z1)) {
				int meta = (this.block == ModBlocks.small_waterlily) ? (rand.nextInt(15) + 1) : 0;
				world.setBlock(x1, y1, z1, this.block, meta, 2);
			}
		}

		return true;
	}
}

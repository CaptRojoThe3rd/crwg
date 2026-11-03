package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTallGrassF extends WorldGenerator
{
	final Block block;
	final int meta;
	final int rng_min_meta;

	public WorldGenTallGrassF(Block block, int meta)
	{
		this(block, meta, -1);
	}

	public WorldGenTallGrassF(Block block, int meta, int rng_min_meta)
	{
		this.block = block;
		this.meta = meta;
		this.rng_min_meta = rng_min_meta;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		for (int l = 0; l < 128; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			int z1 = z + rand.nextInt(8) - rand.nextInt(8);
			int y1 = world.getHeightValue(x1, z1);

			if (this.block.canBlockStay(world, x1, y1, z1)) {
				int meta;
				if (this.rng_min_meta == -1) {
					meta = this.meta;
				} else {
					meta = this.rng_min_meta + rand.nextInt(this.meta);
				}
				world.setBlock(x1, y1, z1, this.block, meta, 2);
			}
		}

		return true;
	}
}

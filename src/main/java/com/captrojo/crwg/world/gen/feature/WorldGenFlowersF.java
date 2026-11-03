package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFlowers;

public class WorldGenFlowersF extends WorldGenFlowers
{
	public WorldGenFlowersF(Block block, int meta)
	{
		super(block);
		this.field_150551_b = meta;
	}

	@Override
	public boolean generate(World world, Random rand, int x0, int y0, int z0)
	{
		for (int i = 0; i < 64; i++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = x0 + rand.nextInt(8) - rand.nextInt(8);
			int z = z0 + rand.nextInt(8) - rand.nextInt(8);
			int y = world.getHeightValue(x, z);

			if (world.isAirBlock(x, y, z) && this.field_150552_a.canBlockStay(world, x, y, z)) {
				world.setBlock(x, y, z, this.field_150552_a, this.field_150551_b, 2);
			}
		}

		return true;
	}
}

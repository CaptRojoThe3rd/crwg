package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

public class WorldGenDeadBushF extends WorldGenDeadBush
{
	public WorldGenDeadBushF(Block block)
	{
		super(block);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		Block block;

		do {
			block = world.getBlock(x, y, z);
			if (!(block.isLeaves(world, x, y, z) || block.isAir(world, x, y, z))) {
				break;
			}
			y--;
		} while (y > 0);

		for (int l = 0; l < 4; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}
			
			int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			int z1 = z + rand.nextInt(8) - rand.nextInt(8);
			int y1 = world.getHeightValue(x1, z1);

			if (world.isAirBlock(x1, y1, z1) && this.field_150547_a.canBlockStay(world, x1, y1, z1)) {
				world.setBlock(x1, y1, z1, this.field_150547_a, 0, 2);
			}
		}

		return true;
	}
}

package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class FeatureBush extends WorldGenFeature
{
	private final Block block;

	public FeatureBush(final Block block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(final World world, final Random rand, int ox, int oy, int oz)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;

		for (int l = 0; l < 4; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = ox + rand.nextInt(8) - rand.nextInt(8);
			int z = oz + rand.nextInt(8) - rand.nextInt(8);
			int y = world_crwg.crwg$getGndHeight(x, z);

			if (world.isAirBlock(x, y, z) && this.block.canBlockStay(world, x, y, z)) {
				world.setBlock(x, y, z, this.block, 0, 2);
			}
		}

		return true;
	}
}

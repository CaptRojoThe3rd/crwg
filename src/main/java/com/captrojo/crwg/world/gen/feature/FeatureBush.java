package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class FeatureBush extends AbstractFeature
{
	private final Block block;

	public FeatureBush(final Block block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(final World world, final Random rand, final BlockPos origin)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;

		for (int l = 0; l < 4; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = origin.x + rand.nextInt(8) - rand.nextInt(8);
			int z = origin.z + rand.nextInt(8) - rand.nextInt(8);
			int y = world_crwg.crwg$getGndHeight(x, z);

			if (world.isAirBlock(x, y, z) && this.block.canBlockStay(world, x, y, z)) {
				world.setBlock(x, y, z, this.block, 0, 2);
			}
		}

		return true;
	}
}

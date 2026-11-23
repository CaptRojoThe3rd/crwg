package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class FeatureFlowers extends AbstractFeature
{
	public final Block block;
	public final int meta;

	public FeatureFlowers(Block block, int meta)
	{
		this.block = block;
		this.meta = meta;
	}

	@Override
	public boolean generate(final World world, final Random rand, final BlockPos origin)
	{
		for (int i = 0; i < 64; i++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = origin.x + rand.nextInt(8) - rand.nextInt(8);
			int z = origin.z + rand.nextInt(8) - rand.nextInt(8);
			int y = world.getHeightValue(x, z);

			if (world.isAirBlock(x, y, z) && this.block.canBlockStay(world, x, y, z)) {
				world.setBlock(x, y, z, this.block, this.meta, 2);
			}
		}

		return true;
	}
}

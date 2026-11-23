package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class FeatureTallGrass extends AbstractFeature
{
	private final Block block;
	private final int meta;
	private final int rng_min_meta;

	public FeatureTallGrass(final Block block, final int meta)
	{
		this(block, meta, -1);
	}

	public FeatureTallGrass(final Block block, final int meta, final int rng_min_meta)
	{
		this.block = block;
		this.meta = meta;
		this.rng_min_meta = rng_min_meta;
	}

	@Override
	public boolean generate(final World world, final Random rand, final BlockPos origin)
	{
		for (int l = 0; l < 128; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = origin.x + rand.nextInt(8) - rand.nextInt(8);
			int z = origin.z + rand.nextInt(8) - rand.nextInt(8);
			int y = world.getHeightValue(x, z);

			if (world.isAirBlock(x, y, z) && this.block.canBlockStay(world, x, y, z)) {
				int meta;
				if (this.rng_min_meta == -1) {
					meta = this.meta;
				} else {
					meta = this.rng_min_meta + rand.nextInt(this.meta);
				}
				world.setBlock(x, y, z, this.block, meta, 2);
			}
		}

		return true;
	}
}

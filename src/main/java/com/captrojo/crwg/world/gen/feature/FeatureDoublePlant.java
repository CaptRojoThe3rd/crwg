package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class FeatureDoublePlant extends AbstractFeature
{
	private final BlockDoublePlant block;
	private final int meta;

	public FeatureDoublePlant(int meta)
	{
		this.block = Blocks.double_plant;
		this.meta = meta;
	}

	public FeatureDoublePlant(Block block, int meta)
	{
		this.block = (BlockDoublePlant) block;
		this.meta = meta;
	}

	@Override
	public boolean generate(final World world, final Random rand, final BlockPos origin)
	{
		boolean flag = false;

		for (int i = 0; i < 64; i++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = origin.x + rand.nextInt(8) - rand.nextInt(8);
			int z = origin.z + rand.nextInt(8) - rand.nextInt(8);
			int y = world.getHeightValue(x, z);

			if (world.isAirBlock(x, y, z) && this.block.canPlaceBlockAt(world, x, y, z)) {
				this.block.func_149889_c(world, x, y, z, this.meta, 2);
				flag = true;
			}
		}

		return flag;
	}
}

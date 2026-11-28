package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.util.YConst;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class FeatureCattail extends FeatureDoublePlant
{
	public FeatureCattail(Block block, int meta)
	{
		super(block, meta);
	}

	@Override
	public boolean generate(final World world, final Random rand, int ox, int oy, int oz)
	{
		final WorldCRWG world_crwg = (WorldCRWG) world;
		boolean flag = false;

		for (int i = 0; i < 64; i++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x = ox + rand.nextInt(8) - rand.nextInt(8);
			int z = oz + rand.nextInt(8) - rand.nextInt(8);
			int y = world_crwg.crwg$getGndHeight(x, z);
			if (y != (YConst.SEA_LVL_Y + 1)) {
				continue;
			}

			if (world.isAirBlock(x, y, z) && this.block.canPlaceBlockAt(world, x, y, z)) {
				this.block.func_149889_c(world, x, y, z, this.meta, 2);
				flag = true;
			}
		}

		return flag;
	}
}

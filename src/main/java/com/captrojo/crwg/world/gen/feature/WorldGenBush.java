package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBush extends WorldGenerator
{
	final Block block;
	
	public WorldGenBush(Block block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;
		
		for (int l = 0; l < 4; l++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}

			int x1 = x + rand.nextInt(8) - rand.nextInt(8);
			int z1 = z + rand.nextInt(8) - rand.nextInt(8);
			int y1 = world_crwg.crwg$getGndHeight(x1, z1);

			if (world.isAirBlock(x1, y1, z1) && this.block.canBlockStay(world, x1, y1, z1)) {
				world.setBlock(x1, y1, z1, this.block, 0, 2);
			}
		}

		return true;
	}
}

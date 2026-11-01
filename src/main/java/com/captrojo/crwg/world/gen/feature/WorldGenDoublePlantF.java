package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;

public class WorldGenDoublePlantF extends WorldGenDoublePlant
{
	BlockDoublePlant block;
	
	public WorldGenDoublePlantF(int meta)
	{
		this.block = Blocks.double_plant;
		this.field_150549_a = meta;
	}
	
	public WorldGenDoublePlantF(Block block, int meta)
	{
		this.block = (BlockDoublePlant) block;
		this.field_150549_a = meta;
	}

	@Override
	public boolean generate(World world, Random rand, int x0, int y0, int z0)
	{
		boolean flag = false;

		for (int i = 0; i < 64; i++) {
			if (rand.nextInt(7) != 0) {
				continue;
			}
			
			int x = x0 + rand.nextInt(8) - rand.nextInt(8);
			int z = z0 + rand.nextInt(8) - rand.nextInt(8);
			int y = world.getHeightValue(x, z);

			if (world.isAirBlock(x, y, z) && this.block.canPlaceBlockAt(world, x, y, z)) {
				this.block.func_149889_c(world, x, y, z, this.field_150549_a, 2);
				flag = true;
			}
		}

		return flag;
	}
}

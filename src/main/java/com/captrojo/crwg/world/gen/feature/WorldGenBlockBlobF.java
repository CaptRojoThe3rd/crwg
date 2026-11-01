package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.main.CRWG;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class WorldGenBlockBlobF extends WorldGenBlockBlob
{
	public WorldGenBlockBlobF(Block block, int size)
	{
		super(block, size);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		final int min_y = CRWG.funcs.getMinHeight() + 3;
		while (y > min_y) {
			if (!world.isAirBlock(x, y - 1, z)) {
				Block block = world.getBlock(x, y - 1, z);
				if (block == Blocks.stone) {
					break;
				}
				if (block instanceof BlockGrass) {
					break;
				}
				if (block == Blocks.dirt || ModBlocks.isModDirtBlock(block)) {
					break;
				}
			}
			y--;
		}

		if (y <= 3) {
			return false;
		}

		int size = this.field_150544_b;

		for (int l = 0; size >= 0 && l < 3; l++) {
			int xs = size + rand.nextInt(2);
			int ys = size + rand.nextInt(2);
			int zs = size + rand.nextInt(2);
			float avg_d = (float) (xs + ys + zs) * 0.333F + 0.5F;

			for (int x1 = x - xs; x1 <= x + xs; ++x1) {
				for (int z1 = z - zs; z1 <= z + zs; ++z1) {
					for (int y1 = y - ys; y1 <= y + ys; ++y1) {
						float xd = (float) (x1 - x);
						float zd = (float) (z1 - z);
						float yd = (float) (y1 - y);

						if (xd * xd + zd * zd + yd * yd <= avg_d * avg_d) {
							world.setBlock(x1, y1, z1, this.field_150545_a, 0, 4);
						}
					}
				}
			}

			x += -(size + 1) + rand.nextInt(2 + size * 2);
			z += -(size + 1) + rand.nextInt(2 + size * 2);
			y += 0 - rand.nextInt(2);
		}

		return true;
	}
}

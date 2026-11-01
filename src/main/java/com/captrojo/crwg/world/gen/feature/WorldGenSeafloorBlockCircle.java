package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.captrojo.crwg.util.BlockChks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * Those gravel, sand, and clay blobs in shallow water
 */
public class WorldGenSeafloorBlockCircle extends WorldGenerator
{
	protected final Block block;
	protected final int size;
	protected final boolean clay;
	
	public WorldGenSeafloorBlockCircle(Block block, int size, boolean clay)
	{
		this.block = block;
		this.size = size;
		this.clay = clay;
	}
	
	boolean isValidBlock(Block block)
	{
		if (BlockChks.isDirt(block)) {
			return true;
		}
		return this.clay ? (block == Blocks.clay) : (block instanceof BlockGrass);
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getMaterial() != Material.water) {
			return false;
		}

		int r = rand.nextInt(this.size - 2) + 2;
		byte b0 = 2;

		for (int x1 = (x - r); x1 <= (x + r); x1++) {
			for (int z1 = (z - r); z1 <= (z + r); z1++) {
				int xd = x1 - x;
				int zd = z1 - z;
				if ((xd * xd + zd * zd) > (r * r)) {
					continue;
				}

				for (int y1 = (y - b0); y1 <= (y + b0); y1++) {
					Block block = world.getBlock(x1, y1, z1);

					if (BlockChks.isDirt(block) || block instanceof BlockGrass) {
						world.setBlock(x1, y1, z1, this.block, 0, 2);
					}
				}
			}
		}

		return true;

	}
}

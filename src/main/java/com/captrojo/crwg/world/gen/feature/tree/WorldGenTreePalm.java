package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.WoodBlocks;
import com.captrojo.crwg.main.CRWG;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTreePalm extends WorldGenAbstractTree
{
	private final int min_height, add_height;
	
	public WorldGenTreePalm(boolean notify)
	{
		this(notify, 9, 14);
	}
	
	public WorldGenTreePalm(boolean notify, int min_height, int max_height)
	{
		super(notify);
		this.min_height = min_height;
		this.add_height = max_height - min_height;
	}
	
	private void placeBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block existing = world.getBlock(x, y, z);
		if (existing.isAir(world, x, y, z) || existing.isLeaves(world, x, y, z)) {
			this.setBlockAndNotifyAdequately(world, x, y, z, block, meta);
		}
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		final int height = rand.nextInt(this.add_height) + this.min_height;
		
		if (y < CRWG.funcs.getMinHeight() && (y + height) > CRWG.funcs.getMaxHeight()) {
			return false;
		}
		
		Block ground = world.getBlock(x, y - 1, z);
		if (!ground.canSustainPlant(world, x, y, z, ForgeDirection.UP, WoodBlocks.PALM.sapling)) {
			return false;
		}
		
		for (int x1 = -1; x1 <= 1; x1++) {
			for (int z1 = -1; z1 <= 1; z1++) {
				for (int y1 = 0; y1 < height; y1++) {
					if (!this.isReplaceable(world, x + x1, y + y1, z + z1)) {
						return false;
					}
				}
			}
		}
		
		ground.onPlantGrow(world, x, y - 1, z, x, y, z);
		
		final Block log = WoodBlocks.PALM.log;
		final Block leaf = WoodBlocks.PALM.leaf;
		
		float f = -0.1f, fa = 0.2f;
		int t = -1;
		for (int y1 = 0; y1 < (height - 2); y1++) {
			this.placeBlock(world, x, y + y1, z, log, 0);
			if (y1 < (height - 3) && rand.nextFloat() < f) {
				if (t == -1) {
					t = rand.nextInt(4);
				}
				switch (t) {
				case 0:
					x++;
					break;
				case 1:
					x--;
					break;
				case 2:
					z++;
					break;
				case 3:
					z--;
					break;
				}
				fa -= 0.1f;
				f = -0.1f;
			}
			f += fa;
		}
		
		y += height - 2;
		this.placeBlock(world, x, y, z, leaf, 0);
		this.placeBlock(world, x - 1, y, z, leaf, 0);
		this.placeBlock(world, x, y, z - 1, leaf, 0);
		this.placeBlock(world, x + 1, y, z, leaf, 0);
		this.placeBlock(world, x, y, z + 1, leaf, 0);
		y++;
		
		boolean b = true;
		final int dc = (height > 11) ? 4 : 3;
		final int dm = (height > 11) ? 7 : 6;
		for (int d = 1; d < dm; d++) {
			if (d > 1) {
				this.placeBlock(world, x - d, y, z, leaf, 0);
				this.placeBlock(world, x, y, z - d, leaf, 0);
				this.placeBlock(world, x + d, y, z, leaf, 0);
				this.placeBlock(world, x, y, z + d, leaf, 0);
			}
			if (d < (dm - 1)) {
				this.placeBlock(world, x - d, y, z - d, leaf, 0);
				this.placeBlock(world, x - d, y, z + d, leaf, 0);
				this.placeBlock(world, x + d, y, z - d, leaf, 0);
				this.placeBlock(world, x + d, y, z + d, leaf, 0);
			}
			if (d == dc && b) {
				b = false;
				d--;
				y--;
			}
		}
		
		return true;
	}
}

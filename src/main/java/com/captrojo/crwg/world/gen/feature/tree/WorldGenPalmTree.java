package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.feature.PlantFeature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenPalmTree extends WorldGenerator implements PlantFeature
{
	private final int min_height, add_height;

	public WorldGenPalmTree(final boolean notify)
	{
		this(notify, 9, 14);
	}

	public WorldGenPalmTree(final boolean notify, final int min_height, final int max_height)
	{
		super(notify);
		this.min_height = min_height;
		this.add_height = max_height - min_height;
	}

	private void placeBlock(final World world, final int x, final int y, final int z, final Block block, final int meta)
	{
		if (this.isReplaceableByLeaves(world, x, y, z)) {
			this.setBlockAndNotifyAdequately(world, x, y, z, block, meta);
		}
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		final int height = rand.nextInt(this.add_height) + this.min_height;

		if (y < YConst.MIN_Y_LVL && (y + height) > YConst.MAX_Y_LVL) {
			return false;
		}

		Block ground = world.getBlock(x, y - 1, z);
		if (!ground.canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable) ModBlocks.sapling_palm)) {
			return false;
		}

		for (int x1 = -1; x1 <= 1; x1++) {
			for (int z1 = -1; z1 <= 1; z1++) {
				for (int y1 = 0; y1 < height; y1++) {
					if (!this.isReplaceableByLeaves(world, x + x1, y + y1, z + z1)) {
						return false;
					}
				}
			}
		}

		ground.onPlantGrow(world, x, y - 1, z, x, y, z);

		final Block log = WoodBlocks.PALM.log;
		final Block leaf = ModBlocks.leaf_palm;

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

package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.world.gen.placement.PlacementChecks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTallThinPineTree extends WorldGenerator
{
	private final Block log_block;
	private final Block leaf_block;

	private final int min_height;
	private final int max_height;

	public WorldGenTallThinPineTree(
		final boolean notify,
		final Block log,
		final Block leaf,
		final int min_height,
		final int max_height
	)
	{
		super(notify);

		this.log_block = log;
		this.leaf_block = leaf;

		this.min_height = min_height;
		this.max_height = max_height;
	}

	public void placeBlock(final World world, final int x, final int y, final int z, final Block block, final int meta)
	{
		if (PlacementChecks.isReplaceableByLeaves(world, x, y, z)) {
			this.setBlockAndNotifyAdequately(world, x, y, z, block, meta);
		}
	}

	@Override
	public boolean generate(final World world, final Random rand, final int x, final int y, final int z)
	{
		int height = this.min_height + rand.nextInt(this.max_height - this.min_height + 1);
		int current_len = (height - 10) / 5;
		int current_step = 8;
		int noleaf_buffer = 8;
		int leaf_width = 0;

		Block gnd = world.getBlock(x, y - 1, z);
		if (!gnd.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) Blocks.sapling)) {
			return false;
		}

		int h;
		for (h = 0; h < height; h++) {
			this.placeBlock(world, x, y + h, z, this.log_block, 0);

			if (h == current_step) {
				current_step += 6;
				if (current_len <= 2) {
					current_step--;
				}

				for (int l = current_len; l > 0; l--) {
					this.placeBlock(world, x - l, y + h, z, this.log_block, 4);
					this.placeBlock(world, x + l, y + h, z, this.log_block, 4);
					this.placeBlock(world, x, y + h, z - l, this.log_block, 8);
					this.placeBlock(world, x, y + h, z + l, this.log_block, 8);
				}

				current_len--;
				if (current_len == 1) {
					height = h + 5;
				}
			}

			if ((h & 0xfe) != h) {
				leaf_width--;
			} else if ((h & 0xfd) != h) {
				leaf_width--;
			}
			if (leaf_width == 0) {
				leaf_width++;
			}
			if (h == (current_step - 1)) {
				leaf_width = current_len + 2;
			}

			noleaf_buffer--;
			if (noleaf_buffer <= 0) {
				for (int lw = leaf_width; lw > 0; lw--) {
					int px = lw;
					int pz = 0;
					while (px >= 0) {
						this.placeBlock(world, x + px, y + h, z + pz, this.leaf_block, 0);
						this.placeBlock(world, x - px, y + h, z - pz, this.leaf_block, 0);
						this.placeBlock(world, x + px, y + h, z - pz, this.leaf_block, 0);
						this.placeBlock(world, x - px, y + h, z + pz, this.leaf_block, 0);
						pz++;
						px--;
					}
				}
			}
			if (noleaf_buffer == 1) {
				for (int lw = current_len; lw > 0; lw--) {
					int px = lw;
					int pz = 0;
					while (px >= 0) {
						this.placeBlock(world, x + px, y + h, z + pz, this.leaf_block, 0);
						this.placeBlock(world, x - px, y + h, z - pz, this.leaf_block, 0);
						this.placeBlock(world, x + px, y + h, z - pz, this.leaf_block, 0);
						this.placeBlock(world, x - px, y + h, z + pz, this.leaf_block, 0);
						pz++;
						px--;
					}
				}
			}
		}

		while (leaf_width > 0) {
			if ((h & 1) != 0) {
				leaf_width--;
			} else if ((h & 3) != 0) {
				leaf_width--;
			}

			for (int lw = leaf_width; lw > 0; lw--) {
				int px = lw;
				int pz = 0;
				while (px >= 0) {
					this.placeBlock(world, x + px, y + h, z + pz, this.leaf_block, 0);
					this.placeBlock(world, x - px, y + h, z - pz, this.leaf_block, 0);
					this.placeBlock(world, x + px, y + h, z - pz, this.leaf_block, 0);
					this.placeBlock(world, x - px, y + h, z + pz, this.leaf_block, 0);
					pz++;
					px--;
				}
			}

			h++;
		}

		for (int hh = h - 3; hh < h + 4; hh++) {
			this.placeBlock(world, x, y + hh, z, this.leaf_block, 0);
		}
		this.placeBlock(world, x + 1, y + h, z, this.leaf_block, 0);
		this.placeBlock(world, x - 1, y + h, z, this.leaf_block, 0);
		this.placeBlock(world, x, y + h, z + 1, this.leaf_block, 0);
		this.placeBlock(world, x, y + h, z - 1, this.leaf_block, 0);
		this.placeBlock(world, x + 1, y + h, z, this.leaf_block, 0);
		this.placeBlock(world, x - 1, y + h, z, this.leaf_block, 0);
		this.placeBlock(world, x, y + h, z + 1, this.leaf_block, 0);
		this.placeBlock(world, x, y + h, z - 1, this.leaf_block, 0);

		return true;
	}
}
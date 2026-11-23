package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.feature.PlantFeature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenSwampTree extends WorldGenerator implements PlantFeature
{
	private final Block log_block;
	private final Block leaf_block;

	public WorldGenSwampTree(final boolean notify, final Block log, final Block leaf)
	{
		super(notify);
		this.log_block = log;
		this.leaf_block = leaf;
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		int height;
		for (height = rand.nextInt(4) + 5; world.getBlock(x, y - 1, z).getMaterial() == Material.water; y--) {
			;
		}

		if (y < (YConst.MIN_Y_LVL + 1) || (y + height + 1) > YConst.MAX_Y_LVL) {
			return false;
		}

		for (int ya = y; ya <= y + 1 + height; ya++) {
			byte b0 = 1;

			if (ya == y) {
				b0 = 0;
			}

			if (ya >= y + 1 + height - 2) {
				b0 = 3;
			}

			for (int xa = x - b0; xa <= x + b0; xa++) {
				for (int za = z - b0; za <= z + b0; za++) {
					if (ya < YConst.MIN_Y_LVL && ya > YConst.MAX_Y_LVL) {
						return false;
					}
					Block block = world.getBlock(xa, ya, za);

					if (block.isAir(world, xa, ya, za)) {
						continue;
					}
					if (block.isLeaves(world, xa, ya, za)) {
						continue;
					}
					if (block instanceof BlockBush) {
						continue;
					}

					if (block != Blocks.water && block != Blocks.flowing_water) {
						return false;
					} else if (ya > y) {
						return false;
					}
				}
			}
		}

		Block gnd = world.getBlock(x, y - 1, z);

		boolean is_soil = gnd.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) Blocks.sapling);
		if (!is_soil) {
			return false;
		}

		if (y > (YConst.MAX_Y_LVL - height)) {
			return false;
		}

		gnd.onPlantGrow(world, x, y - 1, z, x, y, z);
		int xa, ya, za;
		int w, h;

		for (ya = y - 3 + height; ya <= y + height; ya++) {
			h = ya - (y + height);
			w = 2 - h / 2;

			for (xa = x - w; xa <= x + w; xa++) {
				int dx = xa - x;

				for (za = z - w; za <= z + w; za++) {
					int dz = za - z;

					if (!world.getBlock(xa, ya, za).canBeReplacedByLeaves(world, xa, ya, za)) {
						continue;
					}

					if (Math.abs(dx) != w || Math.abs(dz) != w || rand.nextInt(2) != 0 && h != 0) {
						if (this.isReplaceableByLeaves(world, xa, ya, za)) {
							this.setBlockAndNotifyAdequately(world, xa, ya, za, this.leaf_block, 0);
						}
					}
				}
			}
		}

		for (ya = 0; ya < height; ya++) {
			Block block = world.getBlock(x, y + ya, z);

			if (
				block.isAir(world, x, y + ya, z)
				|| block.isLeaves(world, x, y + ya, z)
				|| block == Blocks.flowing_water
				|| block == Blocks.water
			) {
				this.setBlockAndNotifyAdequately(world, x, y + ya, z, this.log_block, 0);
			}
		}

		for (ya = y - 3 + height; ya <= y + height; ++ya) {
			h = ya - (y + height);
			w = 2 - h / 2;

			for (xa = x - w; xa <= x + w; xa++) {
				for (za = z - w; za <= z + w; za++) {
					if (!world.getBlock(xa, ya, za).isLeaves(world, xa, ya, za)) {
						continue;
					}

					if (rand.nextInt(4) == 0 && world.getBlock(xa - 1, ya, za).isAir(world, xa - 1, ya, za)) {
						this.generateVines(world, xa - 1, ya, za, 8);
					}
					if (rand.nextInt(4) == 0 && world.getBlock(xa + 1, ya, za).isAir(world, xa + 1, ya, za)) {
						this.generateVines(world, xa + 1, ya, za, 2);
					}
					if (rand.nextInt(4) == 0 && world.getBlock(xa, ya, za - 1).isAir(world, xa, ya, za - 1)) {
						this.generateVines(world, xa, ya, za - 1, 1);
					}
					if (rand.nextInt(4) == 0 && world.getBlock(xa, ya, za + 1).isAir(world, xa, ya, za + 1)) {
						this.generateVines(world, xa, ya, za + 1, 4);
					}
				}
			}
		}

		return true;
	}

	private void generateVines(World world, int x, int y, int z, int meta)
	{
		this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, meta);
		int i1 = 4;

		while (true) {
			y--;

			if (!(world.getBlock(x, y, z).isAir(world, x, y, z)) || i1 <= 0) {
				return;
			}

			this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, meta);
			i1--;
		}
	}
}

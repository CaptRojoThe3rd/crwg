package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.block.WoodBlocks;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.feature.WorldGenFeature;
import com.captrojo.crwg.world.gen.placement.PlacementChecks;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class FeaturePalmTree extends WorldGenFeature
{
	private final byte min_height, add_height;

	public FeaturePalmTree(final boolean notify)
	{
		this(notify, (byte) 9, (byte) 5);
	}

	public FeaturePalmTree(final boolean notify, final byte min_height, final byte add_height)
	{
		super(notify);
		this.initBlockPlacementTest(PlacementChecks::isReplaceableByLeaves);

		this.min_height = min_height;
		this.add_height = add_height;
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		final int height = this.min_height + rand.nextInt(this.add_height);
		final int top_y = height + y;

		if (y < YConst.MIN_Y_LVL && (y + top_y) > YConst.MAX_Y_LVL) {
			return false;
		}

		for (BlockPos pos : BlockPos.getAllInBox(x - 1, y + 1, z - 1, x + 1, top_y, z + 1)) {
			if (!PlacementChecks.isReplaceableByLeaves(world, pos.x, pos.y, pos.z)) {
				return false;
			}
		}

		final Block gnd = world.getBlock(x, y - 1, z);
		if (!gnd.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) ModBlocks.sapling_palm)) {
			return false;
		}
		gnd.onPlantGrow(world, x, y - 1, z, x, y, z);

		final Block log = WoodBlocks.PALM.log;
		final Block leaf = ModBlocks.leaf_palm;

		int x_tilt, z_tilt;
		switch (rand.nextInt(4)) {
		default:
		case 0:
			x_tilt = -1;
			z_tilt = 0;
			break;
		case 1:
			x_tilt = 1;
			z_tilt = 0;
			break;
		case 2:
			x_tilt = 0;
			z_tilt = -1;
			break;
		case 3:
			x_tilt = 0;
			z_tilt = 1;
			break;
		}
		float tilt_chance = -0.1F, tilt_chance_Δ = 0.2F;
		for (; y < (top_y - 2); y++) {
			this.setBlock(world, x, y, z, log);
			if (y < (top_y - 3) && rand.nextFloat() < tilt_chance) {
				x += x_tilt;
				z += z_tilt;
				tilt_chance_Δ -= 0.1F;
				tilt_chance = -0.1F;
			}
			tilt_chance += tilt_chance_Δ;
		}

		this.setBlockChecked(world, x, y, z, leaf);
		this.setBlockChecked(world, x - 1, y, z, leaf);
		this.setBlockChecked(world, x + 1, y, z, leaf);
		this.setBlockChecked(world, x, y, z - 1, leaf);
		this.setBlockChecked(world, x, y, z + 1, leaf);
		y++;

		boolean b = true;
		final int dc = (height > 11) ? 4 : 3;
		final int dm = (height > 11) ? 7 : 6;
		for (int d = 1; d < dm; d++) {
			if (d > 1) {
				this.setBlockChecked(world, x - d, y, z, leaf);
				this.setBlockChecked(world, x, y, z - d, leaf);
				this.setBlockChecked(world, x + d, y, z, leaf);
				this.setBlockChecked(world, x, y, z + d, leaf);
			}
			if (d < (dm - 1)) {
				this.setBlockChecked(world, x - d, y, z - d, leaf);
				this.setBlockChecked(world, x - d, y, z + d, leaf);
				this.setBlockChecked(world, x + d, y, z - d, leaf);
				this.setBlockChecked(world, x + d, y, z + d, leaf);
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

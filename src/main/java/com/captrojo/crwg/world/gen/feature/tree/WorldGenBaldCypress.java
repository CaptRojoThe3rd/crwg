package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.WoodBlocks;
import com.captrojo.crwg.world.ChunkProviderCRWG;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBaldCypress extends WorldGenAbstractTree
{
	/* log metadata */
	private static final int BARK = 12;
	private static final int NORTHSOUTH = 8;
	private static final int EASTWEST = 4;
	private static final int VERTICAL = 0;
	private static final int[] DIR_TO_LOG = {NORTHSOUTH, EASTWEST, NORTHSOUTH, EASTWEST};

	private static final int[] SOIL_X_OFFS = {-1, 1, 0, 0};
	private static final int[] SOIL_Z_OFFS = {0, 0, -1, 1};

	Block log = WoodBlocks.BALD_CYPRESS.log;
	Block leaf = WoodBlocks.BALD_CYPRESS.leaf;

	int min_height = 8;
	int add_height = 8;

	public WorldGenBaldCypress(boolean notify)
	{
		super(notify);
	}

	private void placeBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block existing = world.getBlock(x, y, z);
		if (block == Blocks.air && existing.getMaterial() == Material.water) {
			return;
		}
		if (
			existing.isAir(world, x, y, z) ||
			existing.isLeaves(world, x, y, z) ||
			existing.getMaterial() == Material.water ||
			existing == Blocks.waterlily
		) {
			this.setBlockAndNotifyAdequately(world, x, y, z, block, meta);
		}
	}

	private void placeBigBranchLeaves_A(World world, Random rand, int x, int y, int z)
	{
		for (int xa = -2; xa <= 2; xa++) {
			for (int za = -2; za <= 2; za++) {
				int axa = Math.abs(xa);
				int aza = Math.abs(za);
				if (axa == 2 && aza == 2) {
					continue;
				}
				this.placeBlock(world, x + xa, y, z + za, this.leaf, 0);
				if (axa <= 1 && aza <= 1) {
					this.placeBlock(world, x + xa, y + 1, z + za, this.leaf, 0);
				}
			}
		}
	}

	private void placeBigBranchLeaves_B(World world, Random rand, int x, int y, int z)
	{
		for (int xa = -2; xa <= 2; xa++) {
			for (int za = -2; za <= 2; za++) {
				int axa = Math.abs(xa);
				int aza = Math.abs(za);
				if ((axa + aza) > 2) {
					continue;
				}
				this.placeBlock(world, x + xa, y, z + za, this.leaf, 0);
				if ((axa + aza) < 2) {
					this.placeBlock(world, x + xa, y + 1, z + za, this.leaf, 0);
				}
			}
		}
	}

	private void placeBigBranch(World world, Random rand, int x, int y, int z, int dir)
	{
		int xo = Direction.offsetX[dir];
		int zo = Direction.offsetZ[dir];

		this.placeBlock(world, x + xo, y, z + zo, this.log, DIR_TO_LOG[dir]);
		this.placeBlock(world, x + (xo * 2), y, z + (zo * 2), this.log, DIR_TO_LOG[dir]);
		this.placeBlock(world, x + (xo * 3), y + 1, z + (zo * 3), this.log, VERTICAL);

		int xleaf = x + (xo * 3);
		int yleaf = y + 1;
		int zleaf = z + (zo * 3);

		switch (rand.nextInt(2)) {
		case 0:
			this.placeBigBranchLeaves_A(world, rand, xleaf, yleaf, zleaf);
			break;
		case 1:
			this.placeBigBranchLeaves_B(world, rand, xleaf, yleaf, zleaf);
			break;
		}
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		final int MIN_Y = ChunkProviderCRWG.SEA_LVL_Y - 10;

		Block gnd = world.getBlock(x, y - 1, z);
		while (gnd.getMaterial() == Material.water && y > MIN_Y) {
			y--;
			gnd = world.getBlock(x, y - 1, z);
		}

		if (!gnd.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, WoodBlocks.BALD_CYPRESS.sapling)) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			int x1 = x + SOIL_X_OFFS[i];
			int z1 = z + SOIL_Z_OFFS[i];
			if (!gnd.canSustainPlant(world, x1, y - 1, z1, ForgeDirection.UP, WoodBlocks.BALD_CYPRESS.sapling)) {
				return false;
			}
		}

		int height = rand.nextInt(this.add_height) + this.min_height;
		int root_base_height = (height > 12) ? 3 : 2;
		int onethirdheight = Math.max(height / 3, 3);

		int prev_branch_dir = rand.nextInt(4);
		int lvls_since_branch = 0;

		for (int yh = 0; yh < height; yh++) {
			int y1 = y + yh;

			this.placeBlock(world, x, y1, z, this.log, VERTICAL);
			if (yh < root_base_height) {
				this.placeBlock(world, x - 1, y1, z, this.log, BARK);
				this.placeBlock(world, x + 1, y1, z, this.log, BARK);
				this.placeBlock(world, x, y1, z - 1, this.log, BARK);
				this.placeBlock(world, x, y1, z + 1, this.log, BARK);
			}
			if (yh == root_base_height) {
				this.placeBlock(world, x - 1, y1, z, this.log, BARK);
				this.placeBlock(world, x + 1, y1, z, this.log, BARK);
				this.placeBlock(world, x, y1, z - 1, this.log, BARK);
				this.placeBlock(world, x, y1, z + 1, this.log, BARK);
			}

			if (yh < onethirdheight) {
				continue;
			}

			if (rand.nextInt(2) == 0 || lvls_since_branch >= 3) {
				lvls_since_branch = 0;

				int dir = prev_branch_dir ^ (rand.nextInt(3) + 1);
				prev_branch_dir = dir;

				this.placeBigBranch(world, rand, x, y1, z, dir);
			} else {
				lvls_since_branch++;
			}
		}

		this.placeBigBranchLeaves_A(world, rand, x, y + height - 1, z);

		return true;
	}
}

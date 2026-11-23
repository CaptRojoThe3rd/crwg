package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.feature.PlantFeature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTreeShrub extends WorldGenerator implements PlantFeature
{
	private final Block log_block;
	private final int log_meta;
	private final Block leaf_block;
	private final int leaf_meta;

	public WorldGenTreeShrub(final Block log, final Block leaf)
	{
		this(log, 0, leaf, 0);
	}

	public WorldGenTreeShrub(final int log_meta, final int leaf_meta)
	{
		this(Blocks.log, log_meta, Blocks.leaves, leaf_meta);
	}

	public WorldGenTreeShrub(final Block log, final int log_meta, final Block leaf, final int leaf_meta)
	{
		super(false);
		this.log_block = log;
		this.log_meta = log_meta;
		this.leaf_block = leaf;
		this.leaf_meta = leaf_meta;
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		y = ((WorldCRWG) (Object) world).crwg$getGndHeight(x, z);
		if (y <= YConst.SEA_LVL_Y) {
			return false;
		}

		Block gnd = world.getBlock(x, y - 1, z);
		if (!gnd.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) Blocks.sapling)) {
			return false;
		}

		this.setBlockAndNotifyAdequately(world, x, y, z, this.log_block, this.log_meta);

		for (int y1 = y; y1 <= (y + 2); y1++) {
			int h = y1 - y;
			int rad = 2 - h;

			for (int x1 = (x - rad); x1 <= (x + rad); x1++) {
				int xd = x1 - x;

				for (int z1 = (z - rad); z1 <= (z + rad); z1++) {
					int zd = z1 - z;

					if (!(Math.abs(xd) != rad || Math.abs(zd) != rad || rand.nextInt(2) != 0)) {
						continue;
					}
					if (!this.isReplaceableByLeaves(world, x1, y1, z1)) {
						continue;
					}
					this.setBlockAndNotifyAdequately(world, x1, y1, z1, this.leaf_block, this.leaf_meta);
				}
			}
		}

		return true;
	}
}

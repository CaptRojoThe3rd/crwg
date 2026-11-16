package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.util.YConst;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenShrubF extends WorldGenShrub
{
	final Block log_block;
	final Block leaf_block;

	public WorldGenShrubF(Block log, Block leaf)
	{
		this(log, 0, leaf, 0);
	}

	public WorldGenShrubF(int log_meta, int leaf_meta)
	{
		this(Blocks.log, log_meta, Blocks.leaves, leaf_meta);
	}

	public WorldGenShrubF(Block log, int log_meta, Block leaf, int leaf_meta)
	{
		super(log_meta, leaf_meta);
		this.log_block = log;
		this.leaf_block = leaf;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		y = ((WorldCRWG) (Object) world).crwg$getGndHeight(x, z);
		if (y <= YConst.SEA_LVL_Y) {
			return false;
		}
		
		Block gnd = world.getBlock(x, y - 1, z);
		if (!gnd.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) Blocks.sapling)) {
			return false;
		}

		this.setBlockAndNotifyAdequately(world, x, y, z, this.log_block, this.field_150527_b);

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
					Block block = world.getBlock(x1, y1, z1);
					if (!(
						block.canBeReplacedByLeaves(world, x1, y1, z1) ||
						block instanceof BlockBush
					)) {
						continue;
					}
					this.setBlockAndNotifyAdequately(world, x1, y1, z1, this.leaf_block, this.field_150528_a);
				}
			}
		}

		return true;
	}
}

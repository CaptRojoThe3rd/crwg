package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import com.captrojo.crwg.block.WoodBlocks;
import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.util.World1IFunction;
import com.captrojo.crwg.world.biome.data.Soil;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTallTree extends WorldGenAbstractTree
{
	/* log metadata */
	private static final int NORTHSOUTH = 8;
	private static final int EASTWEST = 4;
	private static final int VERTICAL = 0;

	private final World1IFunction[][] br_octagon = {
		{
			this::buildBranch_N,
			this::buildBranch_NE,
			this::buildBranch_E,
			this::buildBranch_SE,
			this::buildBranch_S,
			this::buildBranch_SW,
			this::buildBranch_W,
			this::buildBranch_NW
		},
		{
			this::buildBranch_NNE,
			this::buildBranch_ENE,
			this::buildBranch_ESE,
			this::buildBranch_SSE,
			this::buildBranch_SSW,
			this::buildBranch_WSW,
			this::buildBranch_WNW,
			this::buildBranch_NNW
		}
	};

	private final World1IFunction[][] br_hexagon = {
		{
			this::buildBranch_N,
			this::buildBranch_ENE,
			this::buildBranch_ESE,
			this::buildBranch_S,
			this::buildBranch_WSW,
			this::buildBranch_WNW
		},
		{
			this::buildBranch_E,
			this::buildBranch_SSE,
			this::buildBranch_SSW,
			this::buildBranch_W,
			this::buildBranch_NNW,
			this::buildBranch_NNE
		}
	};

	private final World1IFunction[][] br_diamond = {
		{
			this::buildBranch_N,
			this::buildBranch_E,
			this::buildBranch_S,
			this::buildBranch_W
		},
		{
			this::buildBranch_NNE,
			this::buildBranch_ESE,
			this::buildBranch_SSW,
			this::buildBranch_WNW
		},
		{
			this::buildBranch_NE,
			this::buildBranch_SE,
			this::buildBranch_SW,
			this::buildBranch_NW
		},
		{
			this::buildBranch_ENE,
			this::buildBranch_SSE,
			this::buildBranch_WSW,
			this::buildBranch_NNW
		}
	};

	private final int min_height;
	private final int add_height;
	private final boolean thick;
	private final boolean podzol;

	private final Block log;
	private final int log_meta;
	private final Block leaf;
	private final int leaf_meta;

	public WorldGenTallTree(boolean notify)
	{
		this(notify, 10, 20, false, false, Blocks.log, 1, Blocks.leaves, 1);
	}
	
	public WorldGenTallTree(
		boolean notify,
		int min_height,
		int max_height,
		boolean thick,
		boolean podzol,
		WoodBlocks wood
	)
	{
		this(notify, min_height, max_height, thick, podzol, wood.log, 0, wood.leaf, 0);
	}

	public WorldGenTallTree(
		boolean notify,
		int min_height,
		int max_height,
		boolean thick,
		boolean podzol,
		Block log,
		int log_meta,
		Block leaf,
		int leaf_meta
	)
	{
		super(notify);

		this.min_height = min_height;
		this.add_height = max_height - min_height + 1;
		this.thick = thick;
		this.podzol = podzol;

		this.log = log;
		this.log_meta = log_meta;
		this.leaf = leaf;
		this.leaf_meta = leaf_meta;
	}

	private void placeBlock(World world, int x, int y, int z, Block block, int meta)
	{
		Block existing = world.getBlock(x, y, z);
		if (existing.isAir(world, x, y, z) || existing.isLeaves(world, x, y, z)) {
			this.setBlockAndNotifyAdequately(world, x, y, z, block, meta);
		}
	}

	private void placeLeavesAround(World world, int x, int y, int z)
	{
		this.placeBlock(world, x - 1, y, z, this.leaf, this.leaf_meta);
		this.placeBlock(world, x + 1, y, z, this.leaf, this.leaf_meta);
		this.placeBlock(world, x, y - 1, z, this.leaf, this.leaf_meta);
		this.placeBlock(world, x, y + 1, z, this.leaf, this.leaf_meta);
		this.placeBlock(world, x, y, z - 1, this.leaf, this.leaf_meta);
		this.placeBlock(world, x, y, z + 1, this.leaf, this.leaf_meta);
	}

	private void placeLogAndLeaves(World world, int x, int y, int z, int facing)
	{
		this.placeBlock(world, x, y, z, this.log, this.log_meta | facing);
		this.placeLeavesAround(world, x, y, z);
	}

	private int switchDir(int i)
	{
		return ((i & 1) == 0) ? NORTHSOUTH : EASTWEST;
	}

	private void buildBranch_N(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			z--;
			this.placeLogAndLeaves(world, x, y, z, NORTHSOUTH);
		}
	}

	private void buildBranch_NNE(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			if ((i & 1) == 0) {
				x++;
			}
			z--;
			this.placeLogAndLeaves(world, x, y, z, NORTHSOUTH);
		}
	}

	private void buildBranch_NE(World world, int x, int y, int z, int r)
	{
		r -= (r / 3);
		for (int i = 0; i < r; i++) {
			x++;
			z--;
			this.placeLogAndLeaves(world, x, y, z, this.switchDir(i));
		}
	}

	private void buildBranch_ENE(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			x++;
			if ((i & 1) == 0) {
				z--;
			}
			this.placeLogAndLeaves(world, x, y, z, EASTWEST);
		}
	}

	private void buildBranch_E(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			x++;
			this.placeLogAndLeaves(world, x, y, z, EASTWEST);
		}
	}

	private void buildBranch_ESE(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			x++;
			if ((i & 1) == 0) {
				z++;
			}
			this.placeLogAndLeaves(world, x, y, z, EASTWEST);
		}
	}

	private void buildBranch_SE(World world, int x, int y, int z, int r)
	{
		r -= (r / 3);
		for (int i = 0; i < r; i++) {
			x++;
			z++;
			this.placeLogAndLeaves(world, x, y, z, this.switchDir(i));
		}
	}

	private void buildBranch_SSE(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			if ((i & 1) == 0) {
				x++;
			}
			z++;
			this.placeLogAndLeaves(world, x, y, z, NORTHSOUTH);
		}
	}

	private void buildBranch_S(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			z++;
			this.placeLogAndLeaves(world, x, y, z, NORTHSOUTH);
		}
	}

	private void buildBranch_SSW(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			if ((i & 1) == 0) {
				x--;
			}
			z++;
			this.placeLogAndLeaves(world, x, y, z, NORTHSOUTH);
		}
	}

	private void buildBranch_SW(World world, int x, int y, int z, int r)
	{
		r -= (r / 3);
		for (int i = 0; i < r; i++) {
			x--;
			z++;
			this.placeLogAndLeaves(world, x, y, z, this.switchDir(i));
		}
	}

	private void buildBranch_WSW(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			x--;
			if ((i & 1) == 0) {
				z++;
			}
			this.placeLogAndLeaves(world, x, y, z, EASTWEST);
		}
	}

	private void buildBranch_W(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			x--;
			this.placeLogAndLeaves(world, x, y, z, EASTWEST);
		}
	}

	private void buildBranch_WNW(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			x--;
			if ((i & 1) == 0) {
				z--;
			}
			this.placeLogAndLeaves(world, x, y, z, EASTWEST);
		}
	}

	private void buildBranch_NW(World world, int x, int y, int z, int r)
	{
		r -= (r / 3);
		for (int i = 0; i < r; i++) {
			x--;
			z--;
			this.placeLogAndLeaves(world, x, y, z, this.switchDir(i));
		}
	}

	private void buildBranch_NNW(World world, int x, int y, int z, int r)
	{
		for (int i = 0; i < r; i++) {
			if ((i & 1) == 0) {
				x--;
			}
			z--;
			this.placeLogAndLeaves(world, x, y, z, NORTHSOUTH);
		}
	}

	public void generatePodzol(World world, Random rand, int x, int y, int z)
	{
		this.placePodzolBlob(world, rand, x - 1, y, z - 1);
		this.placePodzolBlob(world, rand, x + 2, y, z - 1);
		this.placePodzolBlob(world, rand, x - 1, y, z + 2);
		this.placePodzolBlob(world, rand, x + 2, y, z + 2);

		for (int l = 0; l < 5; ++l) {
			int r = rand.nextInt(64);
			int x1 = r % 8;
			int z1 = r / 8;

			if (x1 == 0 || x1 == 7 || z1 == 0 || z1 == 7) {
				this.placePodzolBlob(world, rand, x - 3 + x1, y, z - 3 + z1);
			}
		}
	}

	private void placePodzolBlob(World world, Random rand, int x, int y, int z)
	{
		for (int x1 = -2; x1 <= 2; ++x1) {
			for (int z1 = -2; z1 <= 2; ++z1) {
				if (Math.abs(x1) != 2 || Math.abs(z1) != 2) {
					this.placePodzolBlock(world, x + x1, y, z + z1);
				}
			}
		}
	}

	private void placePodzolBlock(World world, int x, int y, int z)
	{
		for (int ya = y + 2; ya >= y - 3; --ya) {
			Block existing = world.getBlock(x, ya, z);

			if (existing.canSustainPlant(world, x, ya, z, ForgeDirection.UP, (IPlantable) Blocks.sapling)) {
				BlockState podzol = Soil.getPodzolFor(existing);
				this.setBlockAndNotifyAdequately(world, x, ya, z, podzol.block, podzol.meta);
				break;
			}

			if (existing.isAir(world, x, ya, z) && ya < y) {
				break;
			}
		}
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		Block ground = world.getBlock(x, y - 1, z);
		if (!ground.canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable) Blocks.sapling)) {
			return false;
		}

		final int height = rand.nextInt(this.add_height) + this.min_height;
		final int threefifths = (int) ((float) height * 0.6f);
		final int base_height = Math.max(height / 3, 4);

		boolean branch_lvl = false;
		boolean increased_radius_last = false;
		int radius = 1;
		int tip_remains = (height > 12) ? 2 : 1;

		int diamond_idx = rand.nextInt(4);
		int hexagon_idx = rand.nextInt(2);

		for (int yh = (height - 1); yh >= 0; yh--) {
			this.placeBlock(world, x, y + yh, z, this.log, VERTICAL | this.log_meta);
			if (this.thick && yh < threefifths) {
				this.placeBlock(world, x - 1, y + yh, z, this.log, VERTICAL | this.log_meta);
				this.placeBlock(world, x + 1, y + yh, z, this.log, VERTICAL | this.log_meta);
				this.placeBlock(world, x, y + yh, z - 1, this.log, VERTICAL | this.log_meta);
				this.placeBlock(world, x, y + yh, z + 1, this.log, VERTICAL | this.log_meta);
				if (yh > base_height) {
					this.placeLeavesAround(world, x - 1, y + yh, z);
					this.placeLeavesAround(world, x + 1, y + yh, z);
					this.placeLeavesAround(world, x, y + yh, z - 1);
					this.placeLeavesAround(world, x, y + yh, z + 1);
				}
			} else {
				if (yh > base_height) {
					this.placeLeavesAround(world, x, y + yh, z);
				}
			}

			if (tip_remains > 0) {
				tip_remains--;
				continue;
			}

			if (yh < base_height) {
				continue;
			}

			branch_lvl = !branch_lvl;
			if (!branch_lvl) {
				continue;
			}

			World1IFunction[] funcs;
			if (radius > 5) {
				funcs = this.br_octagon[hexagon_idx];
				hexagon_idx ^= 1;
			} else if (radius > 3) {
				funcs = this.br_hexagon[hexagon_idx];
				hexagon_idx ^= 1;
			} else {
				funcs = this.br_diamond[diamond_idx];
				diamond_idx ^= 1;
			}

			for (World1IFunction func : funcs) {
				func.run(world, x, y + yh, z, radius);
			}

			if (radius == 1 || !increased_radius_last) {
				radius++;
				increased_radius_last = false;
			} else {
				increased_radius_last = true;
			}
		}
		
		if (this.podzol) {
			this.generatePodzol(world, rand, x, y, z);
		}

		return true;
	}
}

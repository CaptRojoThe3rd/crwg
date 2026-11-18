package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeCRWGBeach extends BiomeCRWGBase
{
	static final int TYPE_STONE = 0;
	static final int TYPE_GRAVEL = 1;
	static final int TYPE_NORMAL = 2;
	static final int TYPE_TROPICAL = 3;

	private final int type;

	public BiomeCRWGBeach(int id, String name, int color, float temperature, float rainfall, int type)
	{
		super(id, name, color, temperature, rainfall);
		this.type = type;

		if (type == TYPE_TROPICAL) {
			this.decorator.extra_tree_chance = 0.75f;
		}
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		if (this.type == TYPE_STONE) {
			return BlockState.STONE;
		}
		if (this.type == TYPE_GRAVEL) {
			return BlockState.GRAVEL;
		}
		return BlockState.SAND;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		return this.getTopBlock(world, x, y, z, rand, vals);
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return TreeGens.Palm;
	}
}

package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeCRWGBeach extends BiomeCRWGBase
{
	final BlockState top_block;
	
	public BiomeCRWGBeach(int id, String name, int color, float temperature, float rainfall, BlockState block, boolean tropical)
	{
		super(id, name, color, temperature, rainfall);
		this.top_block = block;
		
		if (tropical) {
			this.decorator_lowlands.extra_tree_chance = 0.75f;
		}
	}
	
	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		return this.top_block;
	}
	
	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		return this.top_block;
	}
	
	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return TreeGens.Palm;
	}
}

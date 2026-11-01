package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.world.World;

public class BiomeWasteland extends BiomeCRWGBase
{
	public BiomeWasteland(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);
		
		this.decorator_lowlands.grass = 0;
		this.decorator_lowlands.flowers = 0;
		this.decorator_lowlands.reeds = 0;
		this.decorator_lowlands.pumpkins = 0;
		
		this.spawnableCreatureList.clear();
	}
	
	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		return BlockState.GRAVEL;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		return BlockState.GRAVEL;
	}
}

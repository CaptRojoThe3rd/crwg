package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.layer.GenLayerRand;

import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class BiomeMushroomMountains extends BiomeMushroomField
{
	GenLayer genlayer_soil;

	{
		this.genlayer_soil = new GenLayerRand(100L, 2);
		this.genlayer_soil = GenLayerZoom.magnify(110L, this.genlayer_soil, 5);
		this.genlayer_soil = new GenLayerSmooth(120L, this.genlayer_soil);
	}

	public BiomeMushroomMountains(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall, 1);
	}

	@Override
	public void initWithWorldSeed(long world_seed)
	{
		super.initWithWorldSeed(world_seed);
		this.genlayer_soil.initWorldGenSeed(world_seed);
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(x, z, 1, 1)[0];
		if (a == 0) {
			return BlockState.STONE;
		}
		return this.getSoilType(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species).mycelium;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(x, z, 1, 1)[0];
		if (a == 0) {
			return BlockState.STONE;
		}
		return super.getFillerBlock(world, x, y, z, rand, vals, low);
	}
}

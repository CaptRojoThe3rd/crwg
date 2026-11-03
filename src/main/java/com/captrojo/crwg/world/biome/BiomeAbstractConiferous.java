package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.biome.data.Soil;
import com.captrojo.crwg.world.biome.data.SoilIdx;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRand;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public abstract class BiomeAbstractConiferous extends BiomeCRWGBase
{
	static final SoilIdx[] TOP_BLOCKS = {SoilIdx.GRASS, SoilIdx.GRASS, SoilIdx.COARSE_DIRT, SoilIdx.COARSE_DIRT, SoilIdx.PODZOL};
	static final SoilIdx[] FILL_BLOCKS = {SoilIdx.DIRT, SoilIdx.DIRT, SoilIdx.COARSE_DIRT, SoilIdx.COARSE_DIRT, SoilIdx.DIRT};

	GenLayer genlayer_soil;

	{
		this.genlayer_soil = new GenLayerRand(100L, TOP_BLOCKS.length);
		this.genlayer_soil = GenLayerZoom.magnify(110L, this.genlayer_soil, 4);
		this.genlayer_soil = new GenLayerSmooth(120L, this.genlayer_soil);
	}

	protected final boolean old_growth;
	
	public BiomeAbstractConiferous(int id, String name, int color, float temperature, float rainfall, boolean old_growth)
	{
		super(id, name, color, temperature, rainfall);
		
		this.old_growth = old_growth;

		this.decorator.boulders = 1;
		this.decorator.boulder_chance = 0.1f;
		this.decorator.trees = 4;
		this.decorator.extra_tree_chance = 0.0f;
		
		this.decorator_hills.copyFrom(this.decorator);
		this.decorator_highlands.copyFrom(this.decorator);
	}
	
	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		if (!this.old_growth) {
			return super.getTopBlock(world, x, y, z, rand, vals);
		}
		Soil soil = this.getSoil(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species);
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(x, z, 1, 1)[0];
		BlockState block = soil.get(TOP_BLOCKS[a]);
		return block;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		if (!this.old_growth) {
			return super.getFillerBlock(world, x, y, z, rand, vals, low);
		}
		Soil soil = this.getSoil(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species);
		if (low) {
			return soil.get(SoilIdx.COARSE_DIRT);
		}
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(x, z, 1, 1)[0];
		BlockState block = soil.get(FILL_BLOCKS[a]);
		return block;
	}
	
	@Override
	public abstract WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand);
}

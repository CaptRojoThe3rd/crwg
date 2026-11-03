package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.biome.data.Soil;
import com.captrojo.crwg.world.biome.data.SoilIdx;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;
import com.captrojo.crwg.world.gen.genlayer.GenLayerRand;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class BiomeSparseTemperateMountains extends BiomeCRWGBase
{
	static final SoilIdx[] TOP_BLOCKS = {SoilIdx.GRASS, SoilIdx.GRASS, SoilIdx.COARSE_DIRT, null};
	static final SoilIdx[] FILL_BLOCKS = {SoilIdx.DIRT, SoilIdx.DIRT, SoilIdx.COARSE_DIRT, null};

	GenLayer genlayer_soil;

	{
		this.genlayer_soil = new GenLayerRand(100L, TOP_BLOCKS.length);
		this.genlayer_soil = GenLayerZoom.magnify(110L, this.genlayer_soil, 4);
		this.genlayer_soil = new GenLayerSmooth(120L, this.genlayer_soil);
	}

	public BiomeSparseTemperateMountains(int id, String name)
	{
		super(id, name, 0x607060, 0.6f, 0.5f);

		this.decorator.trees = 0;
		this.decorator.extra_tree_chance = 0.4f;
		this.decorator.grass = 5;
		this.decorator.flowers = 2;

		this.decorator.liquid_waters = 0;
		this.decorator.liquid_lavas = 0;

		this.decorator.emerald_amount = 2;
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
		if (a <= 1 && y > (CRWG.funcs.getSeaLevel() + 192)) {
			return BlockState.STONE;
		}
		Soil soil = this.getSoil(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species);
		BlockState block = soil.get(TOP_BLOCKS[a]);
		if (block == null) {
			return BlockState.GRAVEL;
		}
		return block;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(x, z, 1, 1)[0];
		if (a <= 1 && y > (CRWG.funcs.getSeaLevel() + 192)) {
			return BlockState.STONE;
		}
		Soil soil = this.getSoil(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species);
		BlockState block = soil.get(FILL_BLOCKS[a]);
		if (block == null) {
			return BlockState.STONE;
		}
		if (low) {
			return soil.get(SoilIdx.COARSE_DIRT);
		}
		return block;
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return rand.nextBoolean() ? TreeGens.Spruce : TreeGens.Oak_Big;
	}
}

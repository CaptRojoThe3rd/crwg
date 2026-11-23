package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.data.SoilIdx;
import com.captrojo.crwg.world.biome.data.SoilType;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;
import com.captrojo.crwg.world.gen.layer.GenLayerRand;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class BiomeSparseTemperateMountains extends BiomeCRWGBase
{
	private static final SoilIdx[] TOP_BLOCKS = {SoilIdx.GRASS, SoilIdx.GRASS, SoilIdx.COARSE_DIRT, null};
	private static final SoilIdx[] FILL_BLOCKS = {SoilIdx.DIRT, SoilIdx.DIRT, SoilIdx.COARSE_DIRT, null};

	private final GenLayer genlayer_soil;

	public BiomeSparseTemperateMountains(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);

		GenLayer layer = new GenLayerRand(100L, TOP_BLOCKS.length);
		layer = GenLayerZoom.magnify(110L, layer, 4);
		layer = new GenLayerSmooth(120L, layer);
		this.genlayer_soil = layer;
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.grass = 5;
		optns.flowers = 2;

		optns.trees = 0;
		optns.extra_tree_chance = 0.4F;

		optns.liquid_waters = 0;
		optns.liquid_lavas = 0;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public void initWithWorldSeed(final long world_seed)
	{
		super.initWithWorldSeed(world_seed);
		this.genlayer_soil.initWorldGenSeed(world_seed);
	}

	@Override
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(vals.pos.x, vals.pos.z, 1, 1)[0];
		if (a <= 1 && vals.pos.y > (YConst.SEA_LVL_Y + 192)) {
			return BlockStates.STONE;
		}
		SoilType soil = this.getSoilType(world, rand, vals);
		BlockState block = soil.get(TOP_BLOCKS[a]);
		if (block == null) {
			return BlockStates.GRAVEL;
		}
		return block;
	}

	@Override
	public BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(vals.pos.x, vals.pos.z, 1, 1)[0];
		if (a <= 1 && vals.pos.y > (YConst.SEA_LVL_Y + 192)) {
			return BlockStates.STONE;
		}
		SoilType soil = this.getSoilType(world, rand, vals);
		BlockState block = soil.get(FILL_BLOCKS[a]);
		if (block == null) {
			return BlockStates.STONE;
		}
		if (low) {
			return soil.get(SoilIdx.COARSE_DIRT);
		}
		return block;
	}

	@Override
	public Feature getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return rand.nextBoolean() ? TreeGens.Spruce : TreeGens.Oak_Big;
	}
}

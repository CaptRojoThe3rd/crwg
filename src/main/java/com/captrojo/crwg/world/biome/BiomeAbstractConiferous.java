package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.data.SoilIdx;
import com.captrojo.crwg.world.biome.data.SoilType;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.layer.GenLayerRand;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public abstract class BiomeAbstractConiferous extends BiomeCRWGBase
{
	private static final SoilIdx[] TOP_BLOCKS = {
		SoilIdx.GRASS,
		SoilIdx.GRASS,
		SoilIdx.COARSE_DIRT,
		SoilIdx.COARSE_DIRT,
		SoilIdx.PODZOL
	};
	private static final SoilIdx[] FILL_BLOCKS = {
		SoilIdx.DIRT,
		SoilIdx.DIRT,
		SoilIdx.COARSE_DIRT,
		SoilIdx.COARSE_DIRT,
		SoilIdx.DIRT
	};

	private final GenLayer genlayer_soil;

	protected final boolean old_growth;

	public BiomeAbstractConiferous(
		final BiomeInfo info,
		final float temperature,
		final float rainfall,
		final boolean old_growth
	) {
		super(info, temperature, rainfall);
		this.old_growth = old_growth;

		if (old_growth) {
			GenLayer layer = new GenLayerRand(100L, TOP_BLOCKS.length);
			layer = GenLayerZoom.magnify(110L, layer, 4);
			layer = new GenLayerSmooth(120L, layer);
			this.genlayer_soil = layer;
		} else {
			this.genlayer_soil = null;
		}
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.boulders = 4;
		optns.boulder_chance = 0.1F;

		optns.trees = 4;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public void initWithWorldSeed(final long world_seed)
	{
		super.initWithWorldSeed(world_seed);
		if (this.genlayer_soil != null) {
			this.genlayer_soil.initWorldGenSeed(world_seed);
		}
	}

	@Override
	protected BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		if (!this.old_growth) {
			return super.getTopBlock(world, rand, vals);
		}
		SoilType soil = this.getSoilType(world, rand, vals);
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(vals.pos.x, vals.pos.z, 1, 1)[0];
		BlockState block = soil.get(TOP_BLOCKS[a]);
		return block;
	}

	@Override
	protected BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		if (!this.old_growth) {
			return super.getFillerBlock(world, rand, vals, low);
		}
		SoilType soil = this.getSoilType(world, rand, vals);
		if (low) {
			return soil.get(SoilIdx.COARSE_DIRT);
		}
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(vals.pos.x, vals.pos.z, 1, 1)[0];
		BlockState block = soil.get(FILL_BLOCKS[a]);
		return block;
	}

	@Override
	public abstract WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin);
}

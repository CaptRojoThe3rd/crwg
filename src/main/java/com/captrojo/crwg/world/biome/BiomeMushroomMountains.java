package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.layer.GenLayerRand;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class BiomeMushroomMountains extends BiomeMushroomField
{
	private final GenLayer genlayer_soil;

	public BiomeMushroomMountains(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall, 1);

		GenLayer layer = new GenLayerRand(100L, 2);
		layer = GenLayerZoom.magnify(110L, layer, 5);
		layer = new GenLayerSmooth(120L, layer);
		this.genlayer_soil = layer;
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
		if (a == 0) {
			return BlockStates.STONE;
		}
		return this.getSoilType(world, rand, vals).mycelium;
	}

	@Override
	public BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		IntCache.resetIntCache();
		int a = this.genlayer_soil.getInts(vals.pos.x, vals.pos.z, 1, 1)[0];
		if (a == 0) {
			return BlockStates.STONE;
		}
		return super.getFillerBlock(world, rand, vals, low);
	}
}

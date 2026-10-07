package com.captrojo.crwg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerCRWG extends GenLayer
{
	public static GenLayer[] initCRWGBiomeGenLayers(long seed)
	{
		GenLayer layer;

		layer = new GenLayerIsland(100L);
		layer = new GenLayerFuzzyZoom(110L, layer);
		
		layer = GenLayerZoom.magnify(200L, layer, 2);
		layer = new GenLayerAddIsland(210L, layer);
		
		layer = new GenLayerFuzzyZoom(300L, layer);
		layer = GenLayerZoom.magnify(310L, layer, 2);
		layer = new GenLayerSmooth(320L, layer);
		
		layer.initWorldGenSeed(seed);

		return new GenLayer[] {layer};
	}

	public GenLayerCRWG(long base_seed)
	{
		super(base_seed);
	}
}

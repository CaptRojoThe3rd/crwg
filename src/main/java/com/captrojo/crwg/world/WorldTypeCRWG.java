package com.captrojo.crwg.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.common.DimensionManager;

public class WorldTypeCRWG extends WorldType
{
	public static WorldTypeCRWG TYPE_CRWG;

	public WorldTypeCRWG()
	{
		super("CRWG");

		DimensionManager.unregisterProviderType(0);
		DimensionManager.registerProviderType(0, WorldProviderSurfaceCRWG.class, true);
	}

	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions)
	{
		return new ChunkProviderCRWG(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public WorldChunkManager getChunkManager(World world)
	{
		return new WorldChunkManagerCRWG(world);
	}

	@Override
	public GenLayer getBiomeLayer(long seed, GenLayer parent)
	{
		return super.getBiomeLayer(seed, parent);
	}
}

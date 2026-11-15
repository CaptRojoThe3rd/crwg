package com.captrojo.crwg.world;

import com.captrojo.crwg.util.YConst;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldTypeCRWG extends WorldType
{
	public static final int SUBTYPE_NORMAL = 0;
	public static final int SUBTYPE_ISLANDS = 1;

	public static WorldTypeCRWG TYPE_CRWG;
	public static WorldTypeCRWG TYPE_CRWG_ISLANDS;

	private final int subtype;

	public WorldTypeCRWG(String name, int subtype)
	{
		super(name);
		this.subtype = subtype;
	}

	@Override
	public IChunkProvider getChunkGenerator(World world, String generatorOptions)
	{
		return new ChunkProviderCRWG(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
	}

	@Override
	public WorldChunkManager getChunkManager(World world)
	{
		return new WorldChunkManagerCRWG(world, this.subtype);
	}

	@Override
	public float getCloudHeight()
	{
		return (float) (YConst.SEA_LVL_Y + 224);
	}
}

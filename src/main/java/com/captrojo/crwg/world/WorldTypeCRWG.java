package com.captrojo.crwg.world;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.cubic.CubicWorldGenerator;
import com.cardinalstar.cubicchunks.api.IntRange;
import com.cardinalstar.cubicchunks.api.world.ICubicWorldType;
import com.cardinalstar.cubicchunks.api.worldgen.IWorldGenerator;

import cpw.mods.fml.common.Optional;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

@Optional.Interface(
	iface = "com.cardinalstar.cubicchunks.api.world.ICubicWorldType",
	modid = "cubicchunks"
)
public class WorldTypeCRWG extends WorldType implements ICubicWorldType
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

	@Optional.Method(modid = "cubicchunks")
	@Override
	public IWorldGenerator createCubeGenerator(WorldServer world, IChunkProvider chunk_gen)
	{
		return new CubicWorldGenerator(world, (ChunkProviderCRWG) chunk_gen);
	}

	@Optional.Method(modid = "cubicchunks")
	@Override
	public IntRange getGenerationRange(World world)
	{
		return new IntRange(YConst.MIN_Y_LVL, YConst.MAX_Y_LVL + 1);
	}

	@Optional.Method(modid = "cubicchunks")
	@Override
	public boolean hasCubicGeneratorForWorld(World world)
	{
		return (world.provider.dimensionId == 0);
	}
}

package com.captrojo.crwg.world.chunk;

import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.mixins.interfaces.ChunkCRWG;
import com.falsepattern.chunk.api.DataManager;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.chunk.Chunk;

public class ChunkDataMgr implements DataManager.ChunkDataManager, DataManager.PacketDataManager
{
	@Override
	public String domain()
	{
		return CRWG.MOD_ID;
	}

	@Override
	public String id()
	{
		return "CRWG";
	}

	@Override
	public @Nonnull String version()
	{
		return CRWG.DATA_VERSION;
	}

	@Override
	public @Nullable String newInstallDescription()
	{
		return "CRWG chunk data. Stores things like biome type, soil type, and land type.";
	}

	@Override
	public @Nonnull String uninstallMessage()
	{
		return "CRWG uninstalled, this world probably shouldn't be loaded";
	}

	@Override
	public @Nullable String versionChangeMessage(String prior_version)
	{
		return null;
	}

	@Override
	public void writeChunkToNBT(Chunk chunk, NBTTagCompound nbt)
	{
		ChunkCRWG chunk_crwg = (ChunkCRWG) (Object) chunk;

		nbt.setIntArray("SurfDataA", chunk_crwg.crwg$getSurfDataMapA());
		nbt.setIntArray("GndHeightMap", chunk_crwg.crwg$getGndHeightMap());
	}

	@Override
	public void readChunkFromNBT(Chunk chunk, NBTTagCompound nbt)
	{
		ChunkCRWG chunk_crwg = (ChunkCRWG) (Object) chunk;

		int[] map_a = nbt.getIntArray("SurfDataA");
		if (map_a == null || map_a.length == 0) {
			map_a = new int[256];
		}
		chunk_crwg.crwg$setSurfDataMapA(map_a);

		int[] gnd_height_map = nbt.getIntArray("GndHeightMap");
		if (gnd_height_map == null || gnd_height_map.length == 0) {
			gnd_height_map = new int[256];
		}
		chunk_crwg.crwg$setGndHeightMap(gnd_height_map);
	}

	@Override
	public void cloneChunk(Chunk from, Chunk to)
	{
		ChunkCRWG from_crwg = (ChunkCRWG) (Object) from;
		ChunkCRWG to_crwg = (ChunkCRWG) (Object) to;

		to_crwg.crwg$setSurfDataMapA(Arrays.copyOf(from_crwg.crwg$getSurfDataMapA(), 256));
		to_crwg.crwg$setGndHeightMap(Arrays.copyOf(from_crwg.crwg$getGndHeightMap(), 256));
	}

	@Override
	public int maxPacketSize()
	{
		return 16 * 16 * 4;
	}

	@Override
	public void writeToBuffer(Chunk chunk, int sub_chunk_mask, boolean force_update, ByteBuffer buf)
	{
		ChunkCRWG chunk_crwg = (ChunkCRWG) (Object) chunk;
		int[] surf_data_map_a = chunk_crwg.crwg$getSurfDataMapA();

		for (int i = 0; i < 256; i++) {
			buf.putInt(surf_data_map_a[i]);
		}
	}

	@Override
	public void readFromBuffer(Chunk chunk, int sub_chunk_mask, boolean force_update, ByteBuffer buf)
	{
		ChunkCRWG chunk_crwg = (ChunkCRWG) (Object) chunk;
		int[] surf_data_map_a = chunk_crwg.crwg$getSurfDataMapA();

		for (int i = 0; i < 256; i++) {
			surf_data_map_a[i] = buf.getInt();
		}
	}
}

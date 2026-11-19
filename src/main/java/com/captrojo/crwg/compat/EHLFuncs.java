package com.captrojo.crwg.compat;

import com.captrojo.crwg.mixins.interfaces.ChunkCRWG;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.falsepattern.endlessids.mixin.helpers.ChunkBiomeHook;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class EHLFuncs implements ICRWGFuncs
{
	@Override
	public Chunk createChunkFromArrays(
		World world,
		int chunk_x,
		int chunk_z,
		Block[] ablock,
		short[] ameta,
		BiomeGenBase[] abiome,
		short[] abiomeid,
		GenArrays arrays
	)
	{
		Chunk chunk = new Chunk(world, chunk_x, chunk_z);
		ExtendedBlockStorage[] aebs = chunk.getBlockStorageArray();
		int height = ablock.length / 256;

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xz = x | (z << 4);
				for (int y = 0; y < height; y++) {
					int idx = xz | (y << 8);

					Block block = ablock[idx];
					if (block == null || block == Blocks.air) {
						continue;
					}

					int chunk_y = y >> 4;
					if (aebs[chunk_y] == null) {
						aebs[chunk_y] = new ExtendedBlockStorage(chunk_y << 4, true);
					}

					aebs[chunk_y].func_150818_a(x, y & 0xf, z, ablock[idx]);
					aebs[chunk_y].setExtBlockMetadata(x, y & 0xf, z, ameta[idx]);
				}
			}
		}

		((ChunkBiomeHook) (Object) chunk).setBiomeShortArray(abiomeid);

		arrays.addDataToChunk((ChunkCRWG) (Object) chunk);

		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public boolean isWithinWorld(int x, int y, int z)
	{
		if (!this.isWithinWorld(x, z)) {
			return false;
		}
		if (y < 0 || y > 511) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isWithinWorld(int x, int z)
	{
		if (-30_000_000 > x || x >= 30_000_000) {
			return false;
		}
		if (-30_000_000 > z || z >= 30_000_000) {
			return false;
		}
		return true;
	}
}

/*
 * This file was basically just copy-and-pasted (with minor tweaks) from the
 * Cubic Chunks mod, which is licesned under the MIT License (MIT).
 * Copyright (c) 2015-2021 OpenCubicChunks
 * Copyright (c) 2015-2021 contributors
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.captrojo.crwg.world.cubic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joml.Vector3ic;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.ChunkProviderCRWG;
import com.cardinalstar.cubicchunks.CubicChunks;
import com.cardinalstar.cubicchunks.api.ICube;
import com.cardinalstar.cubicchunks.api.util.Box;
import com.cardinalstar.cubicchunks.api.worldgen.GenerationResult;
import com.cardinalstar.cubicchunks.api.worldgen.IWorldGenerator;
import com.cardinalstar.cubicchunks.mixin.api.ICubicWorldInternal;
import com.cardinalstar.cubicchunks.mixin.early.common.IGameRegistry;
import com.cardinalstar.cubicchunks.server.CubeProviderServer;
import com.cardinalstar.cubicchunks.server.chunkio.ICubeLoader;
import com.cardinalstar.cubicchunks.world.api.ICubeProviderServer.Requirement;
import com.cardinalstar.cubicchunks.world.core.IColumnInternal;
import com.cardinalstar.cubicchunks.world.cube.Cube;
import com.cardinalstar.cubicchunks.world.cube.blockview.UniformBlockView;
import com.cardinalstar.cubicchunks.worldgen.WorldgenHangWatchdog;
import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class CubicWorldGenerator implements IWorldGenerator
{
	private static final UniformBlockView EMPTY_CUBE = new UniformBlockView(new ImmutableBlockMeta()
	{
		@Override
		public Block getBlock()
		{
			return Blocks.air;
		}

		@Override
		public int getBlockMeta()
		{
			return 0;
		}

	});

	@Nonnull private final World world;
	@Nonnull private final ChunkProviderCRWG chunk_gen;

	public CubicWorldGenerator(World world, ChunkProviderCRWG chunk_gen)
	{
		this.world = world;
		this.chunk_gen = chunk_gen;
	}

	private Box getRelevantCubesForDeco(int x, int y, int z)
	{
		if (y >= YConst.MIN_CHUNK_Y && y <= YConst.MAX_CHUNK_Y) {
			return new Box(x - 1, YConst.MIN_CHUNK_Y - 1, z - 1, x + 1, YConst.MAX_CHUNK_Y + 1, z + 1);
		}
		return new Box(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1);
	}

	private Box getCubesToPopulate(int x, int y, int z)
	{
		if (y >= YConst.MIN_CHUNK_Y && y <= YConst.MAX_CHUNK_Y) {
			return new Box(x - 1, YConst.MIN_CHUNK_Y, z - 1, x, YConst.MAX_CHUNK_Y, z);
		}
		return new Box(x - 1, y - 1, z - 1, x, y, z);
	}

	private Box getFullyPopulatedCubes(int x, int y, int z)
	{
		if (y >= YConst.MIN_CHUNK_Y && y <= YConst.MAX_CHUNK_Y) {
			return new Box(x, YConst.MIN_CHUNK_Y, z, x, YConst.MAX_CHUNK_Y, z);
		}
		return new Box(x, y, z, x, y, z);
	}

	private ICubeLoader getCubeLoader()
	{
		return this.getCubeProviderServer().getCubeLoader();
	}

	private CubeProviderServer getCubeProviderServer()
	{
		return ((ICubicWorldInternal.Server) this.world).getCubeCache();
	}

	private void preparePopulationTerrain(ICubeLoader loader, int chunk_x, int chunk_z)
	{
		for (int dx = 0; dx <= 1; dx++) {
			for (int dz = 0; dz <= 1; dz++) {
				for (int chunk_y = YConst.MIN_CHUNK_Y; chunk_y <= YConst.MAX_CHUNK_Y; chunk_y++) {
					loader.getCube(chunk_x + dx, chunk_y, chunk_z + dz, Requirement.GENERATE);
				}
			}
		}
	}

	private void populateChunk(ICubeLoader loader, int chunk_x, int chunk_z)
	{
		Chunk column = loader.getColumn(chunk_x, chunk_z, Requirement.GENERATE);

		column.isTerrainPopulated = true;
		column.isModified = true;

		try {
			((ICubicWorldInternal.Server) this.world).fakeWorldHeight(YConst.MAX_Y_LVL + 1);
			this.chunk_gen.populate(this.chunk_gen, chunk_x, chunk_z);
			this.applyModGenerators(chunk_x, chunk_z, this.world, this.chunk_gen, this.world.getChunkProvider());
		} catch (Throwable t) {
			CubicChunks.LOGGER.error("Could not populate column {},{}", chunk_x, chunk_z, t);
		} finally {
			((ICubicWorldInternal.Server) this.world).fakeWorldHeight(YConst.MIN_Y_LVL);
		}
	}

	private void applyModGenerators(int x, int z, World world, IChunkProvider chunk_gen, IChunkProvider chunk_prov)
	{
		List<cpw.mods.fml.common.IWorldGenerator> generators = IGameRegistry.getSortedGeneratorList();
		if (generators == null) {
			IGameRegistry.computeGenerators();
			generators = IGameRegistry.getSortedGeneratorList();
			assert generators != null;
		}
		long seed = world.getSeed();
		Random rand = new Random(seed);
		long l1 = rand.nextLong() >> 2 + 1L;
		long l2 = rand.nextLong() >> 2 + 1L;
		long chunk_seed = (l1 * x + l2 * z) ^ seed;

		for (cpw.mods.fml.common.IWorldGenerator generator : generators) {
			rand.setSeed(chunk_seed);
			try {
				((ICubicWorldInternal.Server) world).fakeWorldHeight(YConst.MAX_Y_LVL + 1);
				generator.generate(rand, x, z, world, chunk_gen, chunk_prov);
			} finally {
				((ICubicWorldInternal.Server) world).fakeWorldHeight(YConst.MIN_Y_LVL);
			}
		}
	}

	@Override
	public GenerationResult<Cube> provideCube(@Nullable Chunk chunk, int chunk_x, int chunk_y, int chunk_z)
	{
		try {
			WorldgenHangWatchdog.startWorldGen();

			List<Cube> gen_cubes = new ArrayList<>();
			List<Chunk> gen_chunks = new ArrayList<>();

			if (chunk_y < YConst.MIN_CHUNK_Y || chunk_y > YConst.MAX_CHUNK_Y) {
				Cube cube = new Cube(chunk, chunk_y, EMPTY_CUBE);
				return new GenerationResult<>(cube, gen_chunks, gen_cubes);
			}

			if (chunk == null) {
				chunk = this.chunk_gen.provideChunk(chunk_x, chunk_z);
				gen_chunks.add(chunk);

				ExtendedBlockStorage[] aebs = chunk.getBlockStorageArray();
				for (int ebs_idx = 0; ebs_idx < YConst.SUBCHUNK_COUNT; ebs_idx++) {
					ExtendedBlockStorage ebs = aebs[ebs_idx];
					Cube cube = new Cube(chunk, ebs_idx - YConst.NEG_CHUNK_Y_OFFS, ebs);
					gen_cubes.add(cube);
				}

				Arrays.fill(aebs, null);
				((IColumnInternal) chunk).setColumn(true);
			}

			Cube cube = null;
			for (int i = 0; i < YConst.SUBCHUNK_COUNT; i++) {
				Cube c = gen_cubes.get(i);
				if (c.getY() == chunk_y) {
					cube = c;
					gen_cubes.remove(i);
					break;
				}
			}
			return new GenerationResult<>(cube, gen_chunks, gen_cubes);
		} finally {
			WorldgenHangWatchdog.endWorldGen();
		}
	}

	@Override
	public GenerationResult<Chunk> provideColumn(World world, int chunk_x, int chunk_z)
	{
		try {
			WorldgenHangWatchdog.startWorldGen();

			Chunk chunk = this.chunk_gen.provideChunk(chunk_x, chunk_z);
			List<Cube> gen_cubes = new ArrayList<>();

			ExtendedBlockStorage[] aebs = chunk.getBlockStorageArray();
			for (int ebs_idx = 0; ebs_idx < YConst.SUBCHUNK_COUNT; ebs_idx++) {
				ExtendedBlockStorage ebs = aebs[ebs_idx];
				Cube cube = new Cube(chunk, ebs_idx - YConst.NEG_CHUNK_Y_OFFS, ebs);
				gen_cubes.add(cube);
			}

			Arrays.fill(aebs, null);
			((IColumnInternal) chunk).setColumn(true);

			return new GenerationResult<>(chunk, null, gen_cubes);
		} finally {
			WorldgenHangWatchdog.endWorldGen();
		}
	}

	@Override
	public void populate(Cube cube)
	{
		ICubeLoader loader = this.getCubeLoader();

		int chunk_x = cube.getX();
		int chunk_y = cube.getY();
		int chunk_z = cube.getZ();

		try {
			WorldgenHangWatchdog.startWorldGen();

			loader.cacheCubes(this.getRelevantCubesForDeco(chunk_x, chunk_y, chunk_z));

			if (chunk_y >= YConst.MIN_CHUNK_Y && chunk_y <= YConst.MAX_CHUNK_Y) {
				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						Chunk chunk = loader.getColumn(chunk_x + x, chunk_z + z, Requirement.GENERATE);
						((IColumnInternal) chunk).recalculateStagingHeightmap();
					}
				}
			}

			for (Vector3ic v : this.getCubesToPopulate(chunk_x, chunk_y, chunk_z)) {
				Cube center = loader.getCube(v.x(), v.y(), v.z(), Requirement.GENERATE);

				if (!center.isPopulated()) {
					if (v.y() == 0) {
						this.preparePopulationTerrain(loader, v.x(), v.z());
						this.populateChunk(loader, v.x(), v.z());
					}

					center.markPopulated(Cube.POP_000);
				}
			}

			for (Vector3ic v : this.getFullyPopulatedCubes(chunk_x, chunk_y, chunk_z)) {
				Cube center = loader.getCube(v.x(), v.y(), v.z(), Requirement.GENERATE);

				center.markPopulated(Cube.POP_ALL);
				loader.onCubeGenerated(center);
			}
		} catch (Throwable t) {
			CRWG.log.error("Failed to decorate cube ({}, {}, {})", chunk_x, chunk_y, chunk_z);
		} finally {
			WorldgenHangWatchdog.endWorldGen();
			loader.uncacheCubes();
		}
	}

	@Override
	public void recreateStructures(ICube cube)
	{
	}

	@Override
	public void recreateStructures(Chunk chunk)
	{
		this.chunk_gen.recreateStructures(chunk.xPosition, chunk.zPosition);
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType type, int x, int y, int z)
	{
		return this.chunk_gen.getPossibleCreatures(type, x, y, z);
	}

	@Override
	public ChunkPosition getNearestStructure(String name, int x, int y, int z)
	{
		return this.chunk_gen.func_147416_a(this.world, name, x, y, z);
	}
}

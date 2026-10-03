package com.captrojo.crwg.world;

import com.captrojo.crwg.world.gen.MapGenCavesCRWG;
import com.falsepattern.endlessids.mixin.helpers.ChunkBiomeHook;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;

public class ChunkProviderCRWG implements IChunkProvider
{
	public static Chunk createChunkFromArrays(Block[] ablock, short[] ameta, World world, int chunk_x, int chunk_z)
	{
		Chunk chunk = new Chunk(world, chunk_x, chunk_z);
		ExtendedBlockStorage[] aebs = chunk.getBlockStorageArray();
		int height = ablock.length / 256;

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < height; y++) {
					int idx = (z | (x << 4) | (y << 8));

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

		chunk.generateSkylightMap();
		return chunk;
	}

	World world;
	Random rand;
	boolean map_features_enabled;

	BiomeGenBase[] biomes_for_gen;

	private MapGenBase caveGenerator = new MapGenCavesCRWG();
	private MapGenStronghold mapgen_stronghold = new MapGenStronghold();
	private MapGenVillage mapgen_village = new MapGenVillage();
	private MapGenMineshaft mapgen_mineshaft = new MapGenMineshaft();
	private MapGenScatteredFeature mapgen_scattered_feature = new MapGenScatteredFeature();
//	private MapGenBase ravineGenerator = new MapGenRavine();

	{
		this.caveGenerator = TerrainGen.getModdedMapGen(this.caveGenerator, CAVE);
		this.mapgen_stronghold = (MapGenStronghold) TerrainGen.getModdedMapGen(this.mapgen_stronghold, STRONGHOLD);
		this.mapgen_village = (MapGenVillage) TerrainGen.getModdedMapGen(this.mapgen_village, VILLAGE);
		this.mapgen_mineshaft = (MapGenMineshaft) TerrainGen.getModdedMapGen(this.mapgen_mineshaft, MINESHAFT);
		this.mapgen_scattered_feature = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(this.mapgen_scattered_feature, SCATTERED_FEATURE);
//		this.ravineGenerator = TerrainGen.getModdedMapGen(this.ravineGenerator, RAVINE);
	}

	public ChunkProviderCRWG(World world, long seed, boolean map_features_enabled)
	{
		this.world = world;
		this.rand = new Random(seed);
		this.map_features_enabled = map_features_enabled;
	}

	public void genBiomeTerrain(BiomeGenBase biome, Block[] ablock, short[] ameta, int x1, int z1, double noise)
	{
		Block top_block = biome.topBlock;
		short top_meta = (short) (biome.field_150604_aj & 0xffff);
		Block filler_block = biome.fillerBlock;

		int k = -1;
		int l = (int) (noise / 3.0 + 3.0 + this.rand.nextDouble() * 0.25);
		if (biome.rootHeight < -0.2f) {
			l = 0;
		}

		int x = x1 & 15;
		int z = z1 & 15;

		for (int y = 511; y >= 0; y--) {
			int idx = z | (x << 4) | (y << 8);

			if (y <= this.rand.nextInt(5)) {
				ablock[idx] = Blocks.bedrock;
				continue;
			}

			Block existing = ablock[idx];
			if (existing == null || existing.getMaterial() == Material.air) {
				k = -1;
				continue;
			}

			if (existing != Blocks.stone) {
				continue;
			}

			if (k == -1) {
				if (l <= 0) {
					top_block = null;
					top_meta = 0;
					filler_block = Blocks.stone;
				} else if (y >= 118 && y <= 128) {
					top_block = biome.topBlock;
					top_meta = (short) (biome.field_150604_aj & 0xffff);
					filler_block = biome.fillerBlock;
				}

				if (y < 127 && (top_block == null || top_block.getMaterial() == Material.air)) {
					if (biome.getFloatTemperature(x1, y, z1) < 0.15f) {
						top_block = Blocks.ice;
						top_meta = 0;
					} else {
						top_block = Blocks.water;
						top_meta = 0;
					}
				}

				k = l;

				if (y >= 127) {
					ablock[idx] = top_block;
					ameta[idx] = top_meta;
				} else if (y < 127 - l) {
					top_block = null;
					filler_block = Blocks.stone;
					ablock[idx] = Blocks.gravel;
				} else {
					ablock[idx] = filler_block;
				}
			} else if (k > 0) {
				k--;
				ablock[idx] = filler_block;

				if (k == 0 && filler_block == Blocks.sand) {
					k = this.rand.nextInt(4) + Math.max(0, y - 126);
					filler_block = Blocks.sandstone;
				}
			}
		}
	}

	@Override
	public boolean chunkExists(int chunk_x, int chunk_z)
	{
		return true;
	}

	@Override
	public Chunk provideChunk(int chunk_x, int chunk_z)
	{
		this.rand.setSeed((long) chunk_x * 341873128712L + (long) chunk_z * 132897987541L);
		Block[] ablock = new Block[16 * 16 * 512];
		short[] ameta = new short[16 * 16 * 512];

		WorldChunkManagerCRWG wcm = (WorldChunkManagerCRWG) this.world.getWorldChunkManager();

		this.biomes_for_gen = wcm.getBiomesForGeneration(this.biomes_for_gen, chunk_x << 4, chunk_z << 4, 16, 16);
		short[] abiomeid = new short[256];
		BiomeGenBase[] abiome = new BiomeGenBase[256];
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xz = z | (x << 4);
				int xzb = x | (z << 4);
				BiomeGenBase biome = this.biomes_for_gen[xz];
				abiome[xz] = biome;
				abiomeid[xzb] = (short) biome.biomeID;
			}
		}

		double[] height_map = wcm.nl_height.generate(chunk_x << 4, chunk_z << 4, 16, 16, 256);

		for (int xz = 0; xz < 256; xz++) {
			double n = height_map[xz] + 1.0;
			int h = (int) (n * 128.0);

			for (int y = 0; y < h && y < 512; y++) {
				int i = xz | (y << 8);
				ablock[i] = Blocks.stone;
			}

			for (int y = 0; y < 127; y++){
				int i = xz | (y << 8);
				if (ablock[i] == null) {
					ablock[i] = Blocks.water;
				}
			}
		}

//		this.caveGenerator.func_151539_a(this, this.world, chunk_x, chunk_z, ablock);
//		this.ravineGenerator.func_151539_a(this, this.world, chunk_x, chunk_z, ablock);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xz = z | (x << 4);
				BiomeGenBase biome = abiome[xz];
				this.genBiomeTerrain(biome, ablock, ameta, x, z, 0.0);
			}
		}

		Chunk chunk = ChunkProviderCRWG.createChunkFromArrays(ablock, ameta, this.world, chunk_x, chunk_z);
		((ChunkBiomeHook) (Object) chunk).setBiomeShortArray(abiomeid);

		return chunk;
	}

	@Override
	public Chunk loadChunk(int chunk_x, int chunk_z)
	{
		return this.provideChunk(chunk_x, chunk_z);
	}

	/* @formatter off */

	@Override
	public void populate(IChunkProvider chunk_prov, int chunk_x, int chunk_z)
	{
		BlockFalling.fallInstantly = true;
		int x0 = chunk_x << 4;
		int z0 = chunk_z << 4;
		BiomeGenBase biome = this.world.getBiomeGenForCoords(x0 + 16, z0 + 16);

		this.rand.setSeed(this.world.getSeed());
		long l1 = this.rand.nextLong() / 2L * 2L + 1L;
		long l2 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) chunk_x * l1 + (long) chunk_z * l2 ^ this.world.getSeed());

		boolean village_flag = false;

//		MinecraftForge.EVENT_BUS.post(
//			new PopulateChunkEvent.Pre(chunk_prov, this.world, this.rand, chunk_x, chunk_z, village_flag)
//		);

//		if (this.map_features_enabled) {
//			this.mineshaftGenerator.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
//			village_flag = this.villageGenerator.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
//			this.strongholdGenerator.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
//			this.scatteredFeatureGenerator.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
//		}

		boolean do_gen;
		int i;
		int x;
		int y;
		int z;

//		if (
//			biome != BiomeGenBase.desert &&
//			biome != BiomeGenBase.desertHills &&
//			!village_flag &&
//			this.rand.nextInt(4) == 0 &&
//			TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, LAKE)
//		) {
//			x = x0 + this.rand.nextInt(16) + 8;
//			y = this.rand.nextInt(512);
//			z = z0 + this.rand.nextInt(16) + 8;
//			(new WorldGenLakes(Blocks.water)).generate(this.world, this.rand, x, y, z);
//		}

//		if (
//			TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, LAVA) &&
//			!village_flag &&
//			this.rand.nextInt(8) == 0
//		) {
//			x = x0 + this.rand.nextInt(16) + 8;
//			y = this.rand.nextInt(this.rand.nextInt(504) + 8);
//			z = z0 + this.rand.nextInt(16) + 8;
//
//			if (y < 127 || this.rand.nextInt(10) == 0) {
//				(new WorldGenLakes(Blocks.lava)).generate(this.world, this.rand, x, y, z);
//			}
//		}

//		do_gen = TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, DUNGEON);
//		for (i = 0; do_gen && i < 8; i++) {
//			x = x0 + this.rand.nextInt(16) + 8;
//			y = this.rand.nextInt(512);
//			z = z0 + this.rand.nextInt(16) + 8;
//			(new WorldGenDungeons()).generate(this.world, this.rand, x, y, z);
//		}

//		biome.decorate(this.world, this.rand, x0, z0);

//		if (TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, ANIMALS)) {
//			SpawnerAnimals.performWorldGenSpawning(this.world, biome, x0 + 8, z0 + 8, 16, 16, this.rand);
//		}

		x0 += 8;
		z0 += 8;

//		do_gen = TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, ICE);
//		for (x = 0; do_gen && x < 16; x++) {
//			for (z = 0; z < 16; z++) {
//				y = this.world.getPrecipitationHeight(x0 + x, z0 + z);
//
//				if (this.world.isBlockFreezable(x + x0, y - 1, z + z0)) {
//					this.world.setBlock(x + x0, y - 1, z + z0, Blocks.ice, 0, 2);
//				}
//
//				if (this.world.func_147478_e(x + x0, y, z + z0, true)) {
//					this.world.setBlock(x + x0, y, z + z0, Blocks.snow_layer, 0, 2);
//				}
//			}
//		}

//		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunk_prov, world, rand, chunk_x, chunk_z, village_flag));

		BlockFalling.fallInstantly = false;
	}

	/* @formatter on */

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	{
		return true;
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return "RandomLevelSource";
	}

	@Override
	public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType p_73155_1_, int x, int y, int z)
	{
		return new ArrayList<BiomeGenBase.SpawnListEntry>();
	}

	@Override
	public ChunkPosition func_147416_a(World world, String name, int x, int y, int z)
	{
		return "Stronghold".equals(name) && this.mapgen_stronghold != null ? this.mapgen_stronghold.func_151545_a(world, x, y, z) : null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(int chunk_x, int chunk_z)
	{
		if (this.map_features_enabled)
		{
			this.mapgen_mineshaft.func_151539_a(this, this.world, chunk_x, chunk_z, (Block[]) null);
			this.mapgen_village.func_151539_a(this, this.world, chunk_x, chunk_z, (Block[]) null);
			this.mapgen_stronghold.func_151539_a(this, this.world, chunk_x, chunk_z, (Block[]) null);
			this.mapgen_scattered_feature.func_151539_a(this, this.world, chunk_x, chunk_z, (Block[]) null);
		}
	}

	@Override
	public void saveExtraData()
	{
	}
}

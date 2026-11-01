package com.captrojo.crwg.world;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.gen.MapGenCavesCRWG;
import com.captrojo.crwg.world.gen.MapGenRavineCRWG;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.captrojo.crwg.world.gen.data.GenVals;

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
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderCRWG implements IChunkProvider
{
	/** Extra underground size (index) */
	public static int EXT_UG_I;
	/** Sea level Y */
	public static int SEA_LVL_Y;
	/** Sea level index */
	public static int SEA_LVL_I;
	
	private static class InitBlockArrCallable implements Callable<Block[]>
	{
		final ChunkProviderCRWG prov;
		final Random rand = new Random();

		InitBlockArrCallable(ChunkProviderCRWG prov)
		{
			this.prov = prov;
		}

		@Override
		public Block[] call()
		{
			this.rand.setSeed(this.prov.rand.nextLong());

			Block[] ablock = new Block[16 * 16 * (512 + EXT_UG_I)];

			for (int i = 0; i < 256; i++) {
				ablock[i] = Blocks.bedrock;
			}

			for (int y = 1; y < 5; y++) {
				for (int xz = 0; xz < 256; xz++) {
					int i = xz | (y << 8);
					if (y < this.rand.nextInt(5)) {
						ablock[i] = Blocks.bedrock;
						continue;
					}
					ablock[i] = Blocks.stone;
				}
			}

			for (int i = (5 << 8); i < ((20 + EXT_UG_I) << 8); i++) {
				ablock[i] = Blocks.stone;
			}

			for (int i = ((20 + EXT_UG_I) << 8); i < ((128 + EXT_UG_I) << 8); i++) {
				ablock[i] = Blocks.water;
			}

			for (int i = ((128 + EXT_UG_I) << 8); i < ablock.length; i++) {
				ablock[i] = Blocks.air;
			}

			return ablock;
		}
	}

	private ExecutorService exec_svc;
	private ChunkProviderCRWG.InitBlockArrCallable call_init_ablock;

	World world;
	WorldChunkManagerCRWG wcm;
	Random rand;
	boolean map_features_enabled;

	private MapGenCavesCRWG cave_generator = new MapGenCavesCRWG();
	private MapGenRavineCRWG ravine_generator = new MapGenRavineCRWG();

	private MapGenStronghold mapgen_stronghold;
	private MapGenVillage mapgen_village;
	private MapGenMineshaft mapgen_mineshaft;
	/** Desert/jungle temples and witch huts */
	private MapGenScatteredFeature mapgen_scattered_feature;

	{
		this.mapgen_stronghold = new MapGenStronghold();
		this.mapgen_stronghold = (MapGenStronghold) TerrainGen.getModdedMapGen(this.mapgen_stronghold, STRONGHOLD);
		
		Map<String, String> village_config = new HashMap<String, String>();
		village_config.put("size", "4");
		this.mapgen_village = new MapGenVillage(village_config);
		this.mapgen_village = (MapGenVillage) TerrainGen.getModdedMapGen(this.mapgen_village, VILLAGE);
		this.mapgen_village.field_82666_h = 24;
		this.mapgen_village.field_82665_g = 48;
		
		this.mapgen_mineshaft = new MapGenMineshaft();
		this.mapgen_mineshaft = (MapGenMineshaft) TerrainGen.getModdedMapGen(this.mapgen_mineshaft, MINESHAFT);
		
		this.mapgen_scattered_feature = new MapGenScatteredFeature();
		this.mapgen_scattered_feature = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(this.mapgen_scattered_feature, SCATTERED_FEATURE);
	}

	public ChunkProviderCRWG(World world, long seed, boolean map_features_enabled)
	{
		EXT_UG_I = CRWG.funcs.getExtraUndergroundSize();
		SEA_LVL_Y = CRWG.funcs.getSeaLevel();
		SEA_LVL_I = SEA_LVL_Y - CRWG.funcs.getMinHeight();

		this.exec_svc = Executors.newFixedThreadPool(1);
		this.call_init_ablock = new ChunkProviderCRWG.InitBlockArrCallable(this);

		this.world = world;
		this.rand = new Random(seed);
		this.map_features_enabled = map_features_enabled;

		BiomeCRWGBase.initAllBiomesWithWorldSeed(seed);
	}

	public void shutdown()
	{
		this.exec_svc.shutdown();
	}

	public void genBiomeTerrain(BiomeGenBase biome, Block[] ablock, short[] ameta, int x1, int z1, double noise, int thread)
	{
		Block top_block = biome.topBlock;
		short top_meta = (short) (biome.field_150604_aj & 0xffff);
		Block filler_block = biome.fillerBlock;

		int k = -1;
		int l = (int) (noise / 3.0 + 3.0 + this.rand.nextDouble() * 0.25);

		int x = x1 & 15;
		int z = z1 & 15;

		for (int y = 511; y >= 0; y--) {
			int idx = x | (z << 4) | ((y + EXT_UG_I) << 8);

			Block existing = ablock[idx];
			if (existing.getMaterial() == Material.air) {
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

	public void genTerrain(int chunk_x, int chunk_z, Block[] ablock, GenArrays arrays)
	{
		final double[][] noise_arrays = {
			arrays.deep_ocean_noise,
			arrays.ocean_noise,
			arrays.shallow_ocean_noise,
			arrays.beach_noise,

			arrays.lowland_noise,
			arrays.hill_noise,
			arrays.highland_noise,
			arrays.mountain_noise,

			arrays.plateau_noise,
			arrays.foothill_noise,

			arrays.river_noise,
			arrays.lake_noise,
			arrays.wetlands_noise
		};

		final double[][] sel_arrays = {
			arrays.deep_ocean_sel,
			arrays.ocean_sel,
			arrays.shallow_ocean_sel,
			arrays.beach_sel,

			arrays.lowland_sel,
			arrays.hill_sel,
			arrays.highland_sel,
			arrays.mountain_sel,

			arrays.plateau_sel,
			arrays.foothill_sel,

			arrays.river_sel,
			arrays.lake_sel,
			arrays.wetlands_sel
		};

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xz = x | (z << 4);

				/* calculate height */
				double noise_sum = 0.0;
				double sel_sum = 0.0;
				for (int j = 0; j < noise_arrays.length; j++) {
					double n = noise_arrays[j][xz];
					double s = sel_arrays[j][xz];
					noise_sum += (n * s);
					sel_sum += s;
				}
				noise_sum /= sel_sum;
				int h = (int) (noise_sum * 100.0);
				
				arrays.gnd_height_map[xz] = h + 1;

				/* fill in blocks */
				for (int y = 20; y <= h; y++) {
					int i = xz | ((y + EXT_UG_I) << 8);
					ablock[i] = Blocks.stone;
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
		final int x0 = chunk_x << 4;
		final int z0 = chunk_z << 4;

		Future<Block[]> futureb = this.exec_svc.submit(this.call_init_ablock);

		this.wcm = (WorldChunkManagerCRWG) this.world.getWorldChunkManager();
		GenArrays arrays = this.wcm.generateArrays(chunk_x << 4, chunk_z << 4, 16, 16);

		BiomeGenBase[] abiome = arrays.biome_array;
		short[] abiomeid = new short[256];
		for (int i = 0; i < 256; i++) {
			abiomeid[i] = (short) arrays.biome_id_array[i];
		}

		Block[] ablock;
		short[] ameta = new short[16 * 16 * 512];

		try {
			ablock = futureb.get();
		} catch (Exception e) {
			CRWG.log.error("Failed to init chunk on separate thread");
			e.printStackTrace();
			ablock = this.call_init_ablock.call();
		}

		this.genTerrain(chunk_x, chunk_z, ablock, arrays);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xz = x | (z << 4);
				BiomeGenBase biome = abiome[xz];
				GenVals gen_vals = arrays.getVals(xz);
				if (biome instanceof BiomeCRWGBase biome_crwg) {
					biome_crwg.genTerrainExt(
						this.world,
						this.rand,
						ablock,
						ameta,
						x0 + x,
						z0 + z,
						arrays,
						gen_vals
					);
					continue;
				}
				this.genBiomeTerrain(biome, ablock, ameta, x0 + x, z0 + z, 0.0, 0);
			}
		}

		if (CRWG.funcs.shouldGenerateCaves()) {
			this.cave_generator.func_151539_a(this, this.world, chunk_x, chunk_z, ablock);
		}
		if (CRWG.funcs.shouldGenerateRavines()) {
			this.ravine_generator.func_151539_a(this, this.world, chunk_x, chunk_z, ablock);
		}
		
		if (this.map_features_enabled) {
			this.mapgen_mineshaft.func_151539_a(this, world, chunk_x, chunk_z, ablock);
			this.mapgen_village.func_151539_a(this, world, chunk_x, chunk_z, ablock);
			this.mapgen_stronghold.func_151539_a(this, world, chunk_x, chunk_z, ablock);
			this.mapgen_scattered_feature.func_151539_a(this, world, chunk_x, chunk_z, ablock);
		}

		Chunk chunk = CRWG.funcs.createChunkFromArrays(this.world, chunk_x, chunk_z, ablock, ameta, abiome, abiomeid, arrays);
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
		BiomeGenBase biome = this.world.getBiomeGenForCoords(x0 + 15, z0 + 15);

		this.rand.setSeed(this.world.getSeed());
		long l1 = this.rand.nextLong() / 2L * 2L + 1L;
		long l2 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) chunk_x * l1 + (long) chunk_z * l2 ^ this.world.getSeed());

		boolean village_flag = false;

//		MinecraftForge.EVENT_BUS.post(
//			new PopulateChunkEvent.Pre(chunk_prov, this.world, this.rand, chunk_x, chunk_z, village_flag)
//		);

		if (this.map_features_enabled) {
			this.mapgen_mineshaft.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
			village_flag = this.mapgen_village.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
			this.mapgen_stronghold.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
			this.mapgen_scattered_feature.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
		}

		boolean do_gen;
		int i;
		int x, y, z;

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

		biome.decorate(this.world, this.rand, x0, z0);

//		if (TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, ANIMALS)) {
//			SpawnerAnimals.performWorldGenSpawning(this.world, biome, x0 + 8, z0 + 8, 16, 16, this.rand);
//		}

		x0 += 8;
		z0 += 8;

		if (TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, Populate.EventType.ICE)) {
			for (x = 0; x < 16; x++) {
				for (z = 0; z < 16; z++) {
					y = this.world.getPrecipitationHeight(x0 + x, z0 + z);

					if (this.world.isBlockFreezable(x + x0, y - 1, z + z0)) {
						this.world.setBlock(x + x0, y - 1, z + z0, Blocks.ice, 0, 2);
					}

					if (this.world.func_147478_e(x + x0, y, z + z0, true)) {
						this.world.setBlock(x + x0, y, z + z0, Blocks.snow_layer, 0, 2);
					}
				}
			}
		}

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
	public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, int x, int y, int z)
	{
		return new ArrayList<BiomeGenBase.SpawnListEntry>();
	}

	@Override
	public ChunkPosition func_147416_a(World world, String name, int x, int y, int z)
	{
		if ("Stronghold".equals(name) && this.mapgen_stronghold != null) {
			return this.mapgen_stronghold.func_151545_a(world, x, y, z);
		}
		return null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(int chunk_x, int chunk_z)
	{
		if (this.map_features_enabled) {
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

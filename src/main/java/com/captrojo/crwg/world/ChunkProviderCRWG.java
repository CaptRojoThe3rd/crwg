package com.captrojo.crwg.world;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.gen.cave.CaveBase;
import com.captrojo.crwg.world.gen.cave.CaveWorm;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderCRWG implements IChunkProvider
{
	private World world;
	private WorldChunkManagerCRWG wcm;
	private Random rand;
	private final boolean map_features_enabled;

	private CaveBase cavegen_worm = new CaveWorm();

	private MapGenStronghold mapgen_stronghold;
	private MapGenVillage mapgen_village;
	private MapGenMineshaft mapgen_mineshaft;
	/* Desert/jungle temples and witch huts */
	private MapGenScatteredFeature mapgen_scattered_feature;

	private WorldGenDungeons worldgen_dungeons = new WorldGenDungeons();

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
		this.world = world;
		this.rand = new Random(seed);
		this.map_features_enabled = map_features_enabled;

		this.cavegen_worm.initWorld(world);

		BiomeCRWGBase.initAllBiomesWithWorldSeed(seed);
	}

	private Block[] initBlockArray(Random rand)
	{
		Block[] ablock = new Block[16 * 16 * YConst.CHUNK_HEIGHT];

		Arrays.fill(ablock, 0, 256, Blocks.bedrock);

		for (int y = 1; y < 5; y++) {
			for (int xz = 0; xz < 256; xz++) {
				int i = xz | (y << 8);
				if (y < rand.nextInt(5)) {
					ablock[i] = Blocks.bedrock;
					continue;
				}
				ablock[i] = Blocks.stone;
			}
		}

		int ystart = 5 << 8;
		int yend = (20 + YConst.BOTTOM_H_LVL) << 8;
		Arrays.fill(ablock, ystart, yend, Blocks.stone);

		ystart = yend;
		yend = (128 + YConst.BOTTOM_H_LVL) << 8;
		Arrays.fill(ablock, ystart, yend, Blocks.water);

		ystart = yend;
		yend = ablock.length;
		Arrays.fill(ablock, ystart, yend, Blocks.air);

		return ablock;
	}

	private void genTerrain(int chunk_x, int chunk_z, Block[] ablock, GenArrays arrays)
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

		arrays.gnd_height_avg = 0;

		for (int xz = 0; xz < 256; xz++) {
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
			int h = (int) (noise_sum * 100.0) + YConst.BOTTOM_H_LVL;

			int gnd_height = h + 1 + YConst.BOTTOM_H_LVL;
			arrays.gnd_height_map[xz] = gnd_height;
			arrays.gnd_height_avg += gnd_height;

			/* fill in blocks */
			for (int y = 20; y <= h; y++) {
				int i = xz | ((y + YConst.BOTTOM_H_LVL) << 8);
				ablock[i] = Blocks.stone;
			}
		}

		arrays.gnd_height_avg /= 256;
	}

	public GenArrays provideArrays(int chunk_x, int chunk_z)
	{
		this.rand.setSeed((long) chunk_x * 341873128712L + (long) chunk_z * 132897987541L);
		final int cx0 = chunk_x << 4;
		final int cz0 = chunk_z << 4;

		this.wcm = (WorldChunkManagerCRWG) this.world.getWorldChunkManager();
		GenArrays arrays = this.wcm.calcArrays(chunk_x, chunk_z);

		BiomeGenBase[] abiome = arrays.biome_array;

		arrays.ablock = this.initBlockArray(rand);
		arrays.ameta = new short[16 * 16 * 512];

		this.genTerrain(chunk_x, chunk_z, arrays.ablock, arrays);

		this.cavegen_worm.generateAll(this.world, chunk_x, chunk_z, arrays);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xz = x | (z << 4);
				BiomeGenBase biome = abiome[xz];
				GenVals gen_vals = arrays.getVals(xz);

				BiomeCRWGBase biome_crwg;
				if (biome instanceof BiomeCRWGBase) {
					biome_crwg = (BiomeCRWGBase) biome;
				} else {
					biome_crwg = ModBiomes.grassland;
				}
				
				gen_vals.pos.x = cx0 + x;
				gen_vals.pos.z = cz0 + z;

				biome_crwg.genTerrainExt(this.world, this.rand, arrays, gen_vals);
				continue;
			}
		}

		if (this.map_features_enabled) {
			this.mapgen_mineshaft.func_151539_a(this, this.world, chunk_x, chunk_z, null);
			this.mapgen_village.func_151539_a(this, this.world, chunk_x, chunk_z, null);
			this.mapgen_stronghold.func_151539_a(this, this.world, chunk_x, chunk_z, null);
			this.mapgen_scattered_feature.func_151539_a(this, this.world, chunk_x, chunk_z, null);
		}

		return arrays;
	}

	@Override
	public boolean chunkExists(int chunk_x, int chunk_z)
	{
		return true;
	}

	@Override
	public Chunk provideChunk(int chunk_x, int chunk_z)
	{
		GenArrays arrays = this.provideArrays(chunk_x, chunk_z);
		short[] abiome = new short[256];
		for (int i = 0; i < 256; i++) {
			abiome[i] = (short) arrays.biome_id_array[i];
		}
		Chunk chunk = CRWG.funcs.createChunkFromArrays(this.world, chunk_x, chunk_z, arrays.ablock, arrays.ameta, arrays.biome_array, abiome, arrays);
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
		int cx0 = chunk_x << 4;
		int cz0 = chunk_z << 4;
		BiomeGenBase biome = this.world.getBiomeGenForCoords(cx0 + 15, cz0 + 15);

		this.rand.setSeed(this.world.getSeed());
		long l1 = this.rand.nextLong() / 2L * 2L + 1L;
		long l2 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) chunk_x * l1 + (long) chunk_z * l2 ^ this.world.getSeed());

		boolean village_flag = false;

		MinecraftForge.EVENT_BUS.post(
			new PopulateChunkEvent.Pre(chunk_prov, this.world, this.rand, chunk_x, chunk_z, village_flag)
		);

		if (this.map_features_enabled) {
			this.mapgen_mineshaft.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
			village_flag = this.mapgen_village.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
			this.mapgen_stronghold.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
			this.mapgen_scattered_feature.generateStructuresInChunk(this.world, this.rand, chunk_x, chunk_z);
		}

		int i;
		int x, y, z;

		if (TerrainGen.populate(chunk_prov, this.world, this.rand, chunk_x, chunk_z, village_flag, DUNGEON)) {
			for (i = 0; i < 8; i++) {
				x = cx0 + this.rand.nextInt(16) + 8;
				y = this.rand.nextInt(192);
				z = cz0 + this.rand.nextInt(16) + 8;
				this.worldgen_dungeons.generate(this.world, this.rand, x, y, z);
			}
		}

		biome.decorate(this.world, this.rand, cx0, cz0);

		if (TerrainGen.populate(chunk_prov, this.world, this.rand, chunk_x, chunk_z, village_flag, ANIMALS)) {
			SpawnerAnimals.performWorldGenSpawning(this.world, biome, cx0 + 8, cz0 + 8, 16, 16, this.rand);
		}

		cx0 += 8;
		cz0 += 8;

		if (TerrainGen.populate(chunk_prov, world, rand, chunk_x, chunk_z, village_flag, Populate.EventType.ICE)) {
			for (x = 0; x < 16; x++) {
				for (z = 0; z < 16; z++) {
					y = this.world.getPrecipitationHeight(cx0 + x, cz0 + z);

					if (this.world.isBlockFreezable(x + cx0, y - 1, z + cz0)) {
						this.world.setBlock(x + cx0, y - 1, z + cz0, Blocks.ice, 0, 2);
					}

					if (this.world.func_147478_e(x + cx0, y, z + cz0, true)) {
						this.world.setBlock(x + cx0, y, z + cz0, Blocks.snow_layer, 0, 2);
					}
				}
			}
		}

		MinecraftForge.EVENT_BUS.post(
			new PopulateChunkEvent.Post(chunk_prov, this.world, this.rand, chunk_x, chunk_z, village_flag)
		);

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
		BiomeGenBase biome = this.world.getBiomeGenForCoords(x, z);
		if (type == EnumCreatureType.monster && this.mapgen_scattered_feature.func_143030_a(x, y, z)) {
			return this.mapgen_scattered_feature.getScatteredFeatureSpawnList();
		}
		return biome.getSpawnableList(type);
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

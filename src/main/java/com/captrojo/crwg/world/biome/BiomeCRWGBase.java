package com.captrojo.crwg.world.biome;

import java.util.ArrayList;
import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.config.BiomeConfig;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.ChunkProviderCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.feature.WorldGenBigMushroomF;
import com.captrojo.crwg.world.gen.feature.WorldGenBlockBlobF;
import com.captrojo.crwg.world.gen.feature.WorldGenCactusF;
import com.captrojo.crwg.world.gen.feature.WorldGenDeadBushF;
import com.captrojo.crwg.world.gen.feature.WorldGenDoublePlantF;
import com.captrojo.crwg.world.gen.feature.WorldGenFlowersF;
import com.captrojo.crwg.world.gen.feature.WorldGenMelonF;
import com.captrojo.crwg.world.gen.feature.WorldGenPumpkinF;
import com.captrojo.crwg.world.gen.feature.WorldGenTallGrassF;
import com.captrojo.crwg.world.gen.feature.WorldGenWaterlilyF;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BiomeCRWGBase extends BiomeGenBase
{
	static final ArrayList<BiomeCRWGBase> BIOMES = new ArrayList<BiomeCRWGBase>();

	public static final WorldGenBigMushroom Gen_BigMushroom = new WorldGenBigMushroomF();

	public static final WorldGenBlockBlob Gen_Boulder_MossyCobble = new WorldGenBlockBlobF(Blocks.mossy_cobblestone, 0);

	public static final WorldGenCactus Gen_Cactus = new WorldGenCactusF();

	public static final WorldGenerator Gen_Clover = new WorldGenTallGrassF(ModBlocks.clover, 15, 1);
	public static final WorldGenerator Gen_Clover_Large = new WorldGenTallGrassF(ModBlocks.clover_large, 0);

	public static final WorldGenDeadBush Gen_DeadBush = new WorldGenDeadBushF(Blocks.deadbush);

	public static final WorldGenDoublePlant Gen_DPlant_Sunflower = new WorldGenDoublePlantF(0);
	public static final WorldGenDoublePlant Gen_DPlant_Lilac = new WorldGenDoublePlantF(1);
	public static final WorldGenDoublePlant Gen_DPlant_TallGrass = new WorldGenDoublePlantF(2);
	public static final WorldGenDoublePlant Gen_DPlant_LargeFern = new WorldGenDoublePlantF(3);
	public static final WorldGenDoublePlant Gen_DPlant_RoseBush = new WorldGenDoublePlantF(4);
	public static final WorldGenDoublePlant Gen_DPlant_Peony = new WorldGenDoublePlantF(5);

	public static final WorldGenDoublePlant Gen_DPlant_PurpleNightshade = new WorldGenDoublePlantF(ModBlocks.dflower_purple_nightshade, 0);

	public static final WorldGenFlowers Gen_Flower_Dandelion = new WorldGenFlowersF(Blocks.yellow_flower, 0);
	public static final WorldGenFlowers Gen_Flower_Poppy = new WorldGenFlowersF(Blocks.red_flower, 0);
	public static final WorldGenFlowers Gen_Flower_BlueOrchid = new WorldGenFlowersF(Blocks.red_flower, 1);
	public static final WorldGenFlowers Gen_Flower_Allium = new WorldGenFlowersF(Blocks.red_flower, 2);
	public static final WorldGenFlowers Gen_Flower_Houstonia = new WorldGenFlowersF(Blocks.red_flower, 3);
	public static final WorldGenFlowers Gen_Flower_TulipRed = new WorldGenFlowersF(Blocks.red_flower, 4);
	public static final WorldGenFlowers Gen_Flower_TulipOrange = new WorldGenFlowersF(Blocks.red_flower, 5);
	public static final WorldGenFlowers Gen_Flower_TulipWhite = new WorldGenFlowersF(Blocks.red_flower, 6);
	public static final WorldGenFlowers Gen_Flower_TulipPink = new WorldGenFlowersF(Blocks.red_flower, 7);
	public static final WorldGenFlowers Gen_Flower_OxeyeDaisy = new WorldGenFlowersF(Blocks.red_flower, 8);

	public static final WorldGenFlowers Gen_Flower_Sage = new WorldGenFlowersF(ModBlocks.flower_sage, 0);
	public static final WorldGenFlowers Gen_Flower_WoodlandSage = new WorldGenFlowersF(ModBlocks.flower_woodland_sage, 0);

	public static final WorldGenLiquids Gen_Liquid_Water = new WorldGenLiquids(Blocks.flowing_water);
	public static final WorldGenLiquids Gen_Liquid_Lava = new WorldGenLiquids(Blocks.flowing_lava);

	public static final WorldGenerator Gen_Melon = new WorldGenMelonF();

	public static final WorldGenFlowers Gen_Mushroom_Brown = new WorldGenFlowersF(Blocks.brown_mushroom, 0);
	public static final WorldGenFlowers Gen_Mushroom_Red = new WorldGenFlowersF(Blocks.red_mushroom, 0);

	public static final WorldGenerator Gen_Pumpkin = new WorldGenPumpkinF();

	public static final WorldGenerator Gen_ShortGrass_Dry_A = new WorldGenTallGrassF(ModBlocks.dry_short_grass, 0);
	public static final WorldGenerator Gen_ShortGrass_Dry_B = new WorldGenTallGrassF(ModBlocks.dry_short_grass, 1);
	public static final WorldGenerator Gen_ShortGrass_Dry_C = new WorldGenTallGrassF(ModBlocks.dry_short_grass, 2);
	public static final WorldGenerator Gen_ShortGrass_Dry_D = new WorldGenTallGrassF(ModBlocks.dry_short_grass, 3);

	public static final WorldGenerator Gen_Sugarcane = new WorldGenReed();

	public static final WorldGenerator Gen_TallGrass = new WorldGenTallGrassF(Blocks.tallgrass, 1);

	public static final WorldGenerator Gen_Vines = new WorldGenVines();

	public static final WorldGenerator Gen_Waterlily = new WorldGenWaterlilyF();

	public static void initAllBiomesWithWorldSeed(long world_seed)
	{
		for (BiomeCRWGBase biome : BIOMES) {
			biome.initWithWorldSeed(world_seed);
		}
	}

	public static int getAdjID(int id)
	{
		return id + (BiomeConfig.id_page_a << 8);
	}

	public BiomeDecoratorCRWG decorator;
	public BiomeDecoratorCRWG decorator_hills;
	public BiomeDecoratorCRWG decorator_highlands;

	public BiomeCRWGBase(int id, String name, int color, float temperature, float rainfall)
	{
		super(BiomeCRWGBase.getAdjID(id));
		BiomeCRWGBase.BIOMES.add(this);

		this.setBiomeName(name);
		this.setColor(color);
		this.setTemperatureRainfall(temperature, rainfall);

		this.decorator = this.createCRWGBiomeDecorator();
		this.decorator_hills = this.createCRWGBiomeDecorator();
		this.decorator_highlands = this.createCRWGBiomeDecorator();

		/* I clear out everything I don't use to save (a tiny amount of) memory. */
		this.worldGeneratorTrees = null;
		this.worldGeneratorBigTree = null;
		this.worldGeneratorSwamp = null;
	}

	public void initWithWorldSeed(long world_seed)
	{
		this.decorator.init(world_seed);
		this.decorator_hills.init(world_seed);
		this.decorator_highlands.init(world_seed);
	}

	public void genTerrainExt(
		World world,
		Random rand,
		Block[] ablock,
		short[] ameta,
		int x,
		int z,
		GenArrays arrays,
		GenVals vals
	)
	{
		BlockState tmp;

		int fill_amount = (int) ((vals.soil_bumpiness_noise / 3.0) + 3.0 + (rand.nextDouble() * 0.25));
		int fill_remain = -1;

		final float temperature = this.getFloatTemperature(x, 127, z);
		int ice_remain = (temperature < 0.15F) ? 1 : -1;
		for (float t = temperature; t < 0.0F; t += 0.15F) {
			ice_remain++;
		}

		Block ext_fill_blk = null;
		short ext_fill_meta = 0;
		int ext_fill_remain = 0;

		boolean sea_gnd_lockout = false;

		int xc = x & 0xf;
		int zc = z & 0xf;

		for (int y = 511; y >= 20; y--) {
			int idx = xc | (zc << 4) | ((y + ChunkProviderCRWG.EXT_UG_I) << 8);

			Block existing = ablock[idx];
			if (existing == null || existing.getMaterial() == Material.air) {
				continue;
			}

			final boolean below_sea_level = (y < 127);

			if (existing != Blocks.stone) {
				if (ice_remain > 0) {
					ice_remain--;
					ablock[idx] = Blocks.ice;
				}
				continue;
			}

			if (fill_remain == -1) {
				fill_remain = fill_amount;
				if (below_sea_level && y < 120 && !sea_gnd_lockout) {
					ablock[idx] = Blocks.gravel;
				} else if (below_sea_level) {
					sea_gnd_lockout = true;
					tmp = this.getFillerBlock(world, x, y, z, rand, vals, false);
					ablock[idx] = tmp.block;
					ameta[idx] = (short) tmp.meta;
				} else {
					tmp = this.getTopBlock(world, x, y, z, rand, vals);
					ablock[idx] = tmp.block;
					ameta[idx] = (short) tmp.meta;
				}
				continue;
			} else if (fill_remain > 0) {
				fill_remain--;
				boolean low = (fill_remain == 0);
				if (below_sea_level && y < 120 && !sea_gnd_lockout) {
					ablock[idx] = Blocks.gravel;
				} else {
					tmp = this.getFillerBlock(world, x, y, z, rand, vals, low);
					ablock[idx] = tmp.block;
					ameta[idx] = (short) tmp.meta;

					if (fill_remain == 0) {
						if (tmp.block == Blocks.sand) {
							ext_fill_blk = Blocks.sandstone;
							ext_fill_remain = rand.nextInt(4) + Math.max(0, y - 126);
						} else if (tmp.block == ModBlocks.orange_sand) {
							ext_fill_blk = ModBlocks.orange_sandstone;
							ext_fill_remain = rand.nextInt(4) + Math.max(0, y - 126);
						}
					}
				}
				continue;
			}

			if (ext_fill_remain > 0) {
				ext_fill_remain--;
				ablock[idx] = ext_fill_blk;
				ameta[idx] = ext_fill_meta;
			}
		}
	}

	public BiomeDecoratorCRWG createCRWGBiomeDecorator()
	{
		return new BiomeDecoratorCRWG(this);
	}

	public Soil getSoil(World world, int x, int y, int z, Random rand, int s_temp, int s_rain, int species_val)
	{
		return Soil.Soil_Map[s_temp][s_rain][(species_val >> 16) & 0x3];
	}

	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		return this.getSoil(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species).grass;
	}

	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		Soil soil = this.getSoil(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species);
		return low ? soil.coarse_dirt : soil.dirt;
	}

	public WorldGenerator getBoulderGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Boulder_MossyCobble;
	}

	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		return (rand.nextInt(10) == 0) ? TreeGens.Oak_Big : TreeGens.Oak;
	}

	public WorldGenerator getBigMushroomGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_BigMushroom;
	}

	public WorldGenerator getFlowerGen(World world, int x, int y, int z, Random rand)
	{
		return (rand.nextInt(3) > 0) ? BiomeCRWGBase.Gen_Flower_Dandelion : BiomeCRWGBase.Gen_Flower_Poppy;
	}

	public WorldGenerator getGrassGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_TallGrass;
	}

	public WorldGenerator getTallGrassGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_DPlant_TallGrass;
	}

	public WorldGenerator getDeadBushGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_DeadBush;
	}

	public WorldGenerator getWaterlilyGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Waterlily;
	}

	public WorldGenerator getBrownMushroomGen(World world, int x, int y, int z, Random rand, boolean always)
	{
		return BiomeCRWGBase.Gen_Mushroom_Brown;
	}

	public WorldGenerator getRedMushroomGen(World world, int x, int y, int z, Random rand, boolean always)
	{
		return BiomeCRWGBase.Gen_Mushroom_Red;
	}

	public WorldGenerator getReedGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Sugarcane;
	}

	public WorldGenerator getPumpkinGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Pumpkin;
	}

	public WorldGenerator getCactusGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Cactus;
	}

	public WorldGenerator getLiquidWaterGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Liquid_Water;
	}

	public WorldGenerator getLiquidLavaGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Liquid_Lava;
	}

	public void decorate(World world, WorldCRWG world_crwg, Random rand, int x0, int z0)
	{
		LandType land_type = world_crwg.crwg$getLandType(x0 + 15, z0 + 15);
		switch (land_type) {
		case HILLS:
			this.decorator_hills.decorate(world, rand, x0, z0);
			break;
		case HIGHLANDS:
			this.decorator_highlands.decorate(world, rand, x0, z0);
			break;
		default:
			this.decorator.decorate(world, rand, x0, z0);
			break;
		}
	}

	@Override
	public BiomeCRWGBase setDisableRain()
	{
		super.setDisableRain();
		return this;
	}

	@Override
	public BiomeCRWGBase setEnableSnow()
	{
		super.setEnableSnow();
		return this;
	}

	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		/* just remove the vanilla decorator */
		return null;
	}

	@Override
	public float getFloatTemperature(int x, int y, int z)
	{
		if (y > ChunkProviderCRWG.SEA_LVL_Y) {
			float noise = (float) BiomeGenBase.temperatureNoise.func_151601_a((double) x / 8.0, (double) z / 8.0);
			return this.temperature - (noise * 4.0F + (float) (y - ChunkProviderCRWG.SEA_LVL_Y)) * 0.05F / 60.0F;
		}
		return this.temperature;
	}

	@Override
	public void decorate(World world, Random rand, int x0, int z0)
	{
		WorldCRWG world_crwg = (WorldCRWG) (Object) world;
		this.decorate(world, world_crwg, rand, x0, z0);
	}
}

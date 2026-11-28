package com.captrojo.crwg.world.biome;

import java.util.ArrayList;
import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.data.SoilType;
import com.captrojo.crwg.world.biome.decoration.Decorator;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.feature.FeatureBlockBlob;
import com.captrojo.crwg.world.gen.feature.FeatureBush;
import com.captrojo.crwg.world.gen.feature.FeatureCactus;
import com.captrojo.crwg.world.gen.feature.FeatureDoublePlant;
import com.captrojo.crwg.world.gen.feature.FeatureFlowers;
import com.captrojo.crwg.world.gen.feature.FeatureMelon;
import com.captrojo.crwg.world.gen.feature.FeatureShallowPond;
import com.captrojo.crwg.world.gen.feature.FeatureTallGrass;
import com.captrojo.crwg.world.gen.feature.FeatureWaterlily;
import com.captrojo.crwg.world.gen.feature.WorldGenBigMushroom;
import com.captrojo.crwg.world.gen.feature.WorldGenFeature;
import com.captrojo.crwg.world.gen.feature.WorldGenUnderwaterBlockBlob;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BiomeCRWGBase extends BiomeGenBase
{
	public static final ArrayList<BiomeCRWGBase> BIOMES = new ArrayList<BiomeCRWGBase>();

	public static final WorldGenerator Gen_BigMushroom = new WorldGenBigMushroom();

	public static final WorldGenerator Gen_Boulder_MossyCobble = new FeatureBlockBlob(Blocks.mossy_cobblestone);

	public static final WorldGenerator Gen_Bush = new FeatureBush(ModBlocks.bush);
	public static final WorldGenerator Gen_Bush_Dead = new FeatureBush(Blocks.deadbush);

	public static final WorldGenerator Gen_Cactus = new FeatureCactus();

	private static final WorldGenFeature Gen_Cattail = new FeatureDoublePlant(ModBlocks.dplant_cattail, 0);

	public static final WorldGenerator Gen_Clover = new FeatureTallGrass(ModBlocks.clover, 15, 1);
	public static final WorldGenerator Gen_Clover_Large = new FeatureTallGrass(ModBlocks.clover_large, 0);

	public static final FeatureDoublePlant Gen_DPlant_Sunflower = new FeatureDoublePlant(0);
	public static final FeatureDoublePlant Gen_DPlant_Lilac = new FeatureDoublePlant(1);
	public static final FeatureDoublePlant Gen_DPlant_TallGrass = new FeatureDoublePlant(2);
	public static final FeatureDoublePlant Gen_DPlant_LargeFern = new FeatureDoublePlant(3);
	public static final FeatureDoublePlant Gen_DPlant_RoseBush = new FeatureDoublePlant(4);
	public static final FeatureDoublePlant Gen_DPlant_Peony = new FeatureDoublePlant(5);

	public static final FeatureDoublePlant Gen_DPlant_PurpleNightshade = new FeatureDoublePlant(ModBlocks.dflower_purple_nightshade, 0);

	public static final FeatureFlowers Gen_Flower_Dandelion = new FeatureFlowers(Blocks.yellow_flower, 0);
	public static final FeatureFlowers Gen_Flower_Poppy = new FeatureFlowers(Blocks.red_flower, 0);
	public static final FeatureFlowers Gen_Flower_BlueOrchid = new FeatureFlowers(Blocks.red_flower, 1);
	public static final FeatureFlowers Gen_Flower_Allium = new FeatureFlowers(Blocks.red_flower, 2);
	public static final FeatureFlowers Gen_Flower_Houstonia = new FeatureFlowers(Blocks.red_flower, 3);
	public static final FeatureFlowers Gen_Flower_TulipRed = new FeatureFlowers(Blocks.red_flower, 4);
	public static final FeatureFlowers Gen_Flower_TulipOrange = new FeatureFlowers(Blocks.red_flower, 5);
	public static final FeatureFlowers Gen_Flower_TulipWhite = new FeatureFlowers(Blocks.red_flower, 6);
	public static final FeatureFlowers Gen_Flower_TulipPink = new FeatureFlowers(Blocks.red_flower, 7);
	public static final FeatureFlowers Gen_Flower_OxeyeDaisy = new FeatureFlowers(Blocks.red_flower, 8);

	public static final FeatureFlowers Gen_Flower_PurpleOwlsClover = new FeatureFlowers(ModBlocks.flower_purple_owls_clover, 0);

	public static final FeatureFlowers Gen_Flower_LongspurLupine = new FeatureFlowers(ModBlocks.flower_longspur_lupine, 0);
	public static final FeatureFlowers Gen_Flower_SilverLupine = new FeatureFlowers(ModBlocks.flower_silver_lupine, 0);
	public static final FeatureFlowers Gen_Flower_SkyBlueLupine = new FeatureFlowers(ModBlocks.flower_sky_blue_lupine, 0);

	public static final FeatureFlowers Gen_Flower_Sage = new FeatureFlowers(ModBlocks.flower_sage, 0);
	public static final FeatureFlowers Gen_Flower_WoodlandSage = new FeatureFlowers(ModBlocks.flower_woodland_sage, 0);

	public static final FeatureFlowers Gen_Flower_AquaHyacinth = new FeatureFlowers(ModBlocks.flower_hyacinth_aqua, 0);
	public static final FeatureFlowers Gen_Flower_CreamHyacinth = new FeatureFlowers(ModBlocks.flower_hyacinth_cream, 0);
	public static final FeatureFlowers Gen_Flower_CrimsonHyacinth = new FeatureFlowers(ModBlocks.flower_hyacinth_crimson, 0);
	public static final FeatureFlowers Gen_Flower_DeepBlueHyacinth = new FeatureFlowers(ModBlocks.flower_hyacinth_deepblue, 0);
	public static final FeatureFlowers Gen_Flower_PinkHyacinth = new FeatureFlowers(ModBlocks.flower_hyacinth_pink, 0);
	public static final FeatureFlowers Gen_Flower_SalmonPinkHyacinth = new FeatureFlowers(ModBlocks.flower_hyacinth_salmonpink, 0);

	public static final WorldGenerator Gen_Liquid_Water = new WorldGenLiquids(Blocks.flowing_water);
	public static final WorldGenerator Gen_Liquid_Lava = new WorldGenLiquids(Blocks.flowing_lava);

	public static final WorldGenerator Gen_Melon = new FeatureMelon(Blocks.melon_block);

	public static final WorldGenerator Gen_Mushroom_Brown = new FeatureFlowers(Blocks.brown_mushroom, 0);
	public static final WorldGenerator Gen_Mushroom_Red = new FeatureFlowers(Blocks.red_mushroom, 0);

	public static final WorldGenerator Gen_Plant_PurpleBush = new FeatureFlowers(ModBlocks.purple_bush, 0);

	public static final WorldGenerator Gen_Pumpkin = new FeatureMelon(Blocks.pumpkin);

	public static final WorldGenerator Gen_ShallowPond = new FeatureShallowPond();

	public static final WorldGenerator Gen_ShortGrass_Dry_A = new FeatureTallGrass(ModBlocks.dry_short_grass, 0);
	public static final WorldGenerator Gen_ShortGrass_Dry_B = new FeatureTallGrass(ModBlocks.dry_short_grass, 1);
	public static final WorldGenerator Gen_ShortGrass_Dry_C = new FeatureTallGrass(ModBlocks.dry_short_grass, 2);
	public static final WorldGenerator Gen_ShortGrass_Dry_D = new FeatureTallGrass(ModBlocks.dry_short_grass, 3);

	public static final WorldGenerator Gen_Sugarcane = new WorldGenReed();

	public static final WorldGenerator Gen_TallGrass = new FeatureTallGrass(Blocks.tallgrass, 1);

	public static final WorldGenerator Gen_UnderwaterSand = new WorldGenUnderwaterBlockBlob(Blocks.sand, 7, false);
	public static final WorldGenerator Gen_UnderwaterClay = new WorldGenUnderwaterBlockBlob(Blocks.clay, 4, true);
	public static final WorldGenerator Gen_UnderwaterGravel = new WorldGenUnderwaterBlockBlob(Blocks.gravel, 6, false);

	public static final WorldGenerator Gen_Vines = new WorldGenVines();

	public static final WorldGenerator Gen_Waterlily = new FeatureWaterlily();
	public static final WorldGenerator Gen_Waterlily_Small = new FeatureWaterlily(ModBlocks.small_waterlily);

	public static void initAllBiomesWithWorldSeed(final long world_seed)
	{
		for (BiomeCRWGBase biome : BIOMES) {
			biome.initWithWorldSeed(world_seed);
		}
	}

	public final BiomeInfo info;

	public Decorator decorator;

	public BiomeCRWGBase(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info.id);
		this.info = info;
		BiomeCRWGBase.BIOMES.add(this);

		this.setBiomeName(info.unlocalized_name);
		this.setColor(info.color);
		this.setTemperatureRainfall(temperature, rainfall);

		/* I clear out everything I don't use to save (a tiny amount of) memory. */
		this.worldGeneratorTrees = null;
		this.worldGeneratorBigTree = null;
		this.worldGeneratorSwamp = null;
	}

	public abstract void initDecorators(final long world_seed);

	public void initWithWorldSeed(final long world_seed)
	{
		this.decorator = new Decorator(this, world_seed);
		this.initDecorators(world_seed);
		this.decorator.compileDecorations();
	}

	public void genTerrainExt(
		final World world,
		final Random rand,
		final GenArrays arrays,
		final GenVals vals
	)
	{
		BlockState tmp;

		final Block[] ablock = arrays.ablock;
		final short[] ameta = arrays.ameta;

		int fill_amount = (int) ((vals.soil_bumpiness_noise / 3.0) + 3.0 + (rand.nextDouble() * 0.25));
		int fill_remain = -1;

		final float temperature = this.getFloatTemperature(vals.pos.x, 127, vals.pos.z);
		int ice_remain = (temperature < 0.15F) ? 1 : -1;
		for (float t = temperature; t < 0.0F; t += 0.15F) {
			ice_remain++;
		}

		Block ext_fill_blk = null;
		short ext_fill_meta = 0;
		int ext_fill_remain = 0;

		boolean sea_gnd_lockout = false;

		final int xc = vals.pos.x & 0xf;
		final int zc = vals.pos.z & 0xf;

		for (int y = 511; y >= 20; y--) {
			int idx = xc | (zc << 4) | ((y + YConst.BOTTOM_H_LVL) << 8);

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
					tmp = this.getFillerBlock(world, rand, vals, false);
					if (tmp != null) {
						ablock[idx] = tmp.getBlock();
						ameta[idx] = (short) tmp.getBlockMeta(0);
					}
				} else {
					tmp = this.getTopBlock(world, rand, vals);
					if (tmp != null) {
						ablock[idx] = tmp.getBlock();
						ameta[idx] = (short) tmp.getBlockMeta(0);
					}
				}
				continue;
			} else if (fill_remain > 0) {
				fill_remain--;
				boolean low = (fill_remain == 0);
				if (below_sea_level && y < 120 && !sea_gnd_lockout) {
					ablock[idx] = Blocks.gravel;
				} else {
					tmp = this.getFillerBlock(world, rand, vals, low);
					if (tmp != null) {
						ablock[idx] = tmp.getBlock();
						ameta[idx] = (short) tmp.getBlockMeta(0);
					}

					if (fill_remain == 0) {
						tmp = this.getExtFillerBlock(world, rand, vals, tmp);
						if (tmp != null) {
							ext_fill_blk = tmp.getBlock();
							ext_fill_meta = (short) tmp.getBlockMeta(0);
							ext_fill_remain = rand.nextInt(4) + 1;
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

	protected SoilType getSoilType(final World world, final Random rand, final GenVals vals)
	{
		return SoilType.Soil_Map[vals.s_temp][vals.s_rain][(vals.species >> 16) & 0x3];
	}

	protected BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		return this.getSoilType(world, rand, vals).grass;
	}

	protected BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		SoilType soil = this.getSoilType(world, rand, vals);
		return low ? soil.coarse_dirt : soil.dirt;
	}

	protected BlockState getExtFillerBlock(final World world, final Random rand, final GenVals vals, final BlockState filler)
	{
		if (filler.getBlock() == Blocks.sand) {
			return BlockStates.SANDSTONE;
		}
		if (filler.getBlock() == ModBlocks.orange_sand) {
			return BlockStates.ORANGE_SANDSTONE;
		}
		return null;
	}

	public WorldGenerator getUnderwaterSandGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_UnderwaterSand;
	}

	public WorldGenerator getUnderwaterClayGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_UnderwaterClay;
	}

	public WorldGenerator getUnderwaterGravelGen(final World world, final Random rand, final BlockPos pis)
	{
		return BiomeCRWGBase.Gen_UnderwaterGravel;
	}

	public WorldGenerator getBoulderGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Boulder_MossyCobble;
	}

	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos pos)
	{
		return (rand.nextInt(10) == 0) ? TreeGens.Oak_Big : TreeGens.Oak;
	}

	public WorldGenerator getBigMushroomGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_BigMushroom;
	}

	public WorldGenerator getFlowerGen(final World world, final Random rand, final BlockPos pos)
	{
		return (rand.nextInt(3) > 0) ? BiomeCRWGBase.Gen_Flower_Dandelion : BiomeCRWGBase.Gen_Flower_Poppy;
	}

	public WorldGenerator getGrassGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_TallGrass;
	}

	public WorldGenerator getTallGrassGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_DPlant_TallGrass;
	}

	public WorldGenerator getCattailGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Cattail;
	}

	public WorldGenerator getBushGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Bush;
	}

	public WorldGenerator getWaterlilyGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Waterlily;
	}

	public WorldGenerator getMushroomGen(final World world, final Random rand, final BlockPos pos)
	{
		if (rand.nextInt(3) == 0) {
			return BiomeCRWGBase.Gen_Mushroom_Red;
		}
		return BiomeCRWGBase.Gen_Mushroom_Brown;
	}

	public WorldGenerator getBrownMushroomGen(final World world, final Random rand, final BlockPos pos, boolean always)
	{
		return BiomeCRWGBase.Gen_Mushroom_Brown;
	}

	public WorldGenerator getRedMushroomGen(final World world, final Random rand, final BlockPos pos, boolean always)
	{
		return BiomeCRWGBase.Gen_Mushroom_Red;
	}

	public WorldGenerator getReedGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Sugarcane;
	}

	public WorldGenerator getPumpkinGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Pumpkin;
	}

	public WorldGenerator getCactusGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Cactus;
	}

	public WorldGenerator getLiquidWaterGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Liquid_Water;
	}

	public WorldGenerator getLiquidLavaGen(final World world, final Random rand, final BlockPos pos)
	{
		return BiomeCRWGBase.Gen_Liquid_Lava;
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
	public float getFloatTemperature(final int x, final int y, final int z)
	{
		if (y > YConst.SEA_LVL_Y) {
			float noise = (float) BiomeGenBase.temperatureNoise.func_151601_a((double) x / 8.0, (double) z / 8.0);
			return this.temperature - (noise * 4.0F + (float) (y - YConst.SEA_LVL_Y)) * 0.05F / 60.0F;
		}
		return this.temperature;
	}

	@Override
	public void decorate(final World world, final Random rand, final int cx0, final int cz0)
	{
		this.decorator.decorate(world, rand, cx0, cz0);
	}
}

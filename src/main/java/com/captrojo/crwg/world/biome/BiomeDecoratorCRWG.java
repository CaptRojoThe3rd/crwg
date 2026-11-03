package com.captrojo.crwg.world.biome;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

import com.captrojo.crwg.config.GeneralConfig;
import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.world.gen.feature.WorldGenSeafloorBlockCircle;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorCRWG
{
	private World _cur_world;
	private Random _cur_rand;
	private int _cur_x0;
	private int _cur_z0;

	private final BiomeCRWGBase biome;

	private boolean initialized = false;

	public WorldGenerator gen_underwater_sand = new WorldGenSeafloorBlockCircle(Blocks.sand, 7, false);
	public WorldGenerator gen_underwater_gravel = new WorldGenSeafloorBlockCircle(Blocks.gravel, 6, false);
	public WorldGenerator gen_underwater_clay = new WorldGenSeafloorBlockCircle(Blocks.clay, 4, true);
	public int underwater_sand = 3;
	public int underwater_gravel = 1;
	public int underwater_clay = 1;

	public int boulders = 0;
	public float boulder_chance = 0.0F;

	public int flowers = 1;
	public float flower_chance = 0.2F;
	public int grass = 2;
	public float grass_chance = 0.5F;
	public int tall_grass = 0;
	public float tall_grass_chance = 0.5F;
	public int dead_bushes = 0;
	public float dead_bush_chance = 0.2F;
	public int waterlilies = 0;
	public float waterlily_chance = 0.2F;

	public int mushrooms = 0;
	public float mushroom_chance = 1.0F;
	public float brown_mushroom_chance = 0.25F;
	public float red_mushroom_chance = 0.125F;

	public int trees = 0;
	public float extra_tree_chance = 0.0F;
	public int big_mushrooms = 0;
	public float extra_big_mushroom_chance = 0.0F;

	public int reeds = 10;
	public float reed_chance = 0.03125F;
	public int pumpkins = 1;
	public float pumpkin_chance = 0.03125F;
	public int cacti = 0;
	public float cacti_chance = 0.0625F;

	public int liquid_waters = 50;
	public int liquid_lavas = 20;

	public WorldGenerator gen_dirt_blob;
	public WorldGenerator gen_gravel_blob;
	public WorldGenerator gen_coal;
	public WorldGenerator gen_iron;
	public WorldGenerator gen_gold;
	public WorldGenerator gen_redstone;
	public WorldGenerator gen_lapis;
	public WorldGenerator gen_diamond;
	public WorldGenerator gen_emerald;

	public int dirt_blob_amount = 40;
	public int dirt_blob_size = 32;
	public int dirt_blob_min_y = 0;
	public int dirt_blob_max_y = 512;

	public int gravel_blob_amount = 20;
	public int gravel_blob_size = 32;
	public int gravel_blob_min_y = 0;
	public int gravel_blob_max_y = 512;

	public int coal_amount = 40;
	public int coal_size = 32;
	public int coal_min_y = 0;
	public int coal_max_y = 256;

	public int iron_amount = 40;
	public int iron_size = 8;
	public int iron_min_y = 0;
	public int iron_max_y = 192;

	public int gold_amount = 20;
	public int gold_size = 8;
	public int gold_min_y = 0;
	public int gold_max_y = 72;

	public int redstone_amount = 16;
	public int redstone_size = 7;
	public int redstone_min_y = 0;
	public int redstone_max_y = 56;

	public int lapis_amount = 2;
	public int lapis_size = 6;
	public int lapis_min_y = 0;
	public int lapis_max_y = 56;

	public int diamond_amount = 2;
	public int diamond_size = 7;
	public int diamond_min_y = 0;
	public int diamond_max_y = 56;

	public int emerald_amount = 0;
	public int emerald_size = 2;
	public int emerald_min_y = 0;
	public int emerald_max_y = 64;

	public BiomeDecoratorCRWG(BiomeCRWGBase biome)
	{
		this.biome = biome;
	}

	public void copyFrom(BiomeDecoratorCRWG that)
	{
		/* I don't want to copy every variable manually */
		try {
			Field[] fields = BiomeDecoratorCRWG.class.getFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isFinal(mod) || Modifier.isPrivate(mod)) {
					/* ignore the _cur_* variables and the biome */
					continue;
				}
				Object val = field.get(that);
				field.set(this, val);
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	private ReportedException crash(int x0, int z0, Throwable t)
	{
		CrashReport crash = CrashReport.makeCrashReport(t, t.getMessage());
		CrashReportCategory cat = crash.makeCategory("DecorationChunk");
		cat.addCrashSection("x0", Integer.valueOf(x0));
		cat.addCrashSection("z0", Integer.valueOf(z0));
		cat.addCrashSection("biome", this.biome.biomeName);
		cat.addCrashSection("biome id", Integer.valueOf(this.biome.biomeID));
		cat.addCrashSection("biome id (hex)", Integer.toString(this.biome.biomeID, 16));
		return new ReportedException(crash);
	}

	public void init(long world_seed)
	{
		this.initialized = true;
		this.gen_dirt_blob = new WorldGenMinable(Blocks.dirt, this.dirt_blob_size);
		this.gen_gravel_blob = new WorldGenMinable(Blocks.gravel, this.gravel_blob_size);
		this.gen_coal = new WorldGenMinable(Blocks.coal_ore, this.coal_size);
		this.gen_iron = new WorldGenMinable(Blocks.iron_ore, this.iron_size);
		this.gen_gold = new WorldGenMinable(Blocks.gold_ore, this.gold_size);
		this.gen_redstone = new WorldGenMinable(Blocks.redstone_ore, this.redstone_size);
		this.gen_lapis = new WorldGenMinable(Blocks.lapis_ore, this.lapis_size);
		this.gen_diamond = new WorldGenMinable(Blocks.diamond_ore, this.diamond_size);
		this.gen_emerald = new WorldGenMinable(Blocks.emerald_ore, this.emerald_size);
	}

	public void decorate(World world, Random rand, int x0, int z0)
	{
		if (this._cur_world != null) {
			CRWG.log.error("Already decorating! Chunk: {}, {} (Pos: {}, {})", x0 >> 4, z0 >> 4, x0, z0);
			if (!GeneralConfig.yeet_decorator_crash) {
				throw this.crash(x0, z0, new RuntimeException("Already decorating!!"));
			}
			return;
		}
		if (!this.initialized) {
			throw this.crash(x0, z0, new RuntimeException("CRWG decorator not initialized"));
		}
		this._cur_world = world;
		this._cur_rand = rand;
		this._cur_x0 = x0;
		this._cur_z0 = z0;
		this.genDecorations(world, rand, x0, z0);
		this._cur_world = null;
	}

	protected void genDecorations(World world, Random rand, int x0, int z0)
	{
		int x, y, z;

		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, rand, x0, z0));

		if (TerrainGen.generateOre(world, rand, this.gen_dirt_blob, x0, z0, GenerateMinable.EventType.DIRT)) {
			this.genOreEvenly(this.gen_dirt_blob, this.dirt_blob_min_y, this.dirt_blob_max_y, this.diamond_amount);
		}
		if (TerrainGen.generateOre(world, rand, this.gen_gravel_blob, x0, z0, GenerateMinable.EventType.GRAVEL)) {
			this.genOreEvenly(this.gen_gravel_blob, this.gravel_blob_min_y, this.gravel_blob_max_y, this.gravel_blob_amount);
		}
		if (TerrainGen.generateOre(world, rand, this.gen_coal, x0, z0, GenerateMinable.EventType.COAL)) {
			this.genOreEvenly(this.gen_coal, this.coal_min_y, this.coal_max_y, this.coal_amount);
		}
		if (TerrainGen.generateOre(world, rand, this.gen_iron, x0, z0, GenerateMinable.EventType.IRON)) {
			this.genOreEvenly(this.gen_iron, this.iron_min_y, this.iron_max_y, this.iron_amount);
		}
		if (TerrainGen.generateOre(world, rand, this.gen_gold, x0, z0, GenerateMinable.EventType.GOLD)) {
			this.genOreEvenly(this.gen_gold, this.gold_min_y, this.gold_max_y, this.gold_amount);
		}
		if (TerrainGen.generateOre(world, rand, this.gen_redstone, x0, z0, GenerateMinable.EventType.REDSTONE)) {
			this.genOreEvenly(this.gen_redstone, this.redstone_min_y, this.redstone_max_y, this.redstone_amount);
		}
		if (TerrainGen.generateOre(world, rand, this.gen_lapis, x0, z0, GenerateMinable.EventType.LAPIS)) {
			this.genOreEvenly(this.gen_lapis, this.lapis_min_y, this.lapis_max_y, this.lapis_amount);
		}
		if (TerrainGen.generateOre(world, rand, this.gen_diamond, x0, z0, GenerateMinable.EventType.DIAMOND)) {
			this.genOreEvenly(this.gen_diamond, this.diamond_min_y, this.diamond_max_y, this.diamond_amount);
		}
		this.genOreEvenly(this.gen_emerald, this.emerald_min_y, this.emerald_max_y, this.emerald_amount);

		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, rand, x0, z0));

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.SAND)) {
			for (int i = 0; i < this.underwater_sand; i++) {
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getTopSolidOrLiquidBlock(x, z);
				this.gen_underwater_sand.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.CLAY)) {
			for (int i = 0; i < this.underwater_clay; i++) {
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getTopSolidOrLiquidBlock(x, z);
				this.gen_underwater_clay.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.SAND_PASS2)) {
			for (int i = 0; i < this.underwater_gravel; i++) {
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getTopSolidOrLiquidBlock(x, z);
				this.gen_underwater_gravel.generate(world, rand, x, y, z);
			}
		}

		for (int i = 0; i < this.boulders; i++) {
			if (rand.nextFloat() < this.boulder_chance) {
				continue;
			}
			x = this.randCoord(rand, x0);
			z = this.randCoord(rand, z0);
			y = world.getHeightValue(x, z);
			WorldGenerator gen = biome.getBoulderGen(world, x, y, z, rand);
			gen.generate(world, rand, x, y, z);
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.FLOWERS)) {
			for (int i = 0; i < this.flowers; i++) {
				if (rand.nextFloat() > this.flower_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator flower = this.biome.getFlowerGen(world, x, y, z, rand);
				flower.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.GRASS)) {
			for (int i = 0; i < this.grass; i++) {
				if (rand.nextFloat() > this.grass_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator grass = this.biome.getGrassGen(world, x, y, z, rand);
				grass.generate(world, rand, x, y, z);
			}

			for (int i = 0; i < this.tall_grass; i++) {
				if (rand.nextFloat() > this.tall_grass_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator tall_grass = this.biome.getTallGrassGen(world, x, y, z, rand);
				tall_grass.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.DEAD_BUSH)) {
			for (int i = 0; i < this.dead_bushes; i++) {
				if (rand.nextFloat() > this.dead_bush_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator deadbush = this.biome.getDeadBushGen(world, x, y, z, rand);
				deadbush.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.LILYPAD)) {
			for (int i = 0; i < this.waterlilies; i++) {
				if (rand.nextFloat() > this.waterlily_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator waterlily = this.biome.getWaterlilyGen(world, x, y, z, rand);
				waterlily.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.SHROOM)) {
			for (int i = 0; i < this.mushrooms; i++) {
				if (rand.nextFloat() <= this.mushroom_chance) {
					this.genMushrooms(world, rand, x0, z0, false);
				}
			}
			if (rand.nextFloat() <= this.mushroom_chance) {
				this.genMushrooms(world, rand, x0, z0, true);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.TREE)) {
			int t = this.trees;
			if (this.extra_tree_chance > 0.0f && rand.nextFloat() <= this.extra_tree_chance) {
				t++;
			}
			for (int i = 0; i < t; i++) {
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);

				WorldGenAbstractTree tree = this.biome.getTreeGen(world, x, y, z, rand);
				tree.setScale(1.0, 1.0, 1.0);
				if (tree.generate(world, rand, x, y, z)) {
					/* mega spruce trees use this function for podzol placement */
					tree.func_150524_b(world, rand, x, y, z);
				}
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.BIG_SHROOM)) {
			int s = this.big_mushrooms;
			if (this.extra_big_mushroom_chance > 0.0f && rand.nextFloat() <= this.extra_big_mushroom_chance) {
				s++;
			}
			for (int i = 0; i < s; i++) {
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);

				WorldGenerator shroom = this.biome.getBigMushroomGen(world, x, y, z, rand);
				shroom.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.REED)) {
			for (int i = 0; i < this.reeds; i++) {
				if (rand.nextFloat() > this.reed_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator reeds = this.biome.getReedGen(world, x, y, z, rand);
				reeds.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.PUMPKIN)) {
			for (int i = 0; i < this.pumpkins; i++) {
				if (rand.nextFloat() > this.pumpkin_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator pumpkin = this.biome.getPumpkinGen(world, x, y, z, rand);
				pumpkin.generate(world, rand, x, y, z);
			}
		}

		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.CACTUS)) {
			for (int i = 0; i < this.cacti; i++) {
				if (rand.nextFloat() > this.cacti_chance) {
					continue;
				}
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = world.getHeightValue(x, z);
				WorldGenerator cactus = this.biome.getCactusGen(world, x, y, z, rand);
				cactus.generate(world, rand, x, y, z);
			}
		}

		/* The EventType is LAKE, but we actually are placing singular liquid blocks. */
		/* Good job, Forge. */
		if (TerrainGen.decorate(world, rand, x0, z0, Decorate.EventType.LAKE)) {
			for (int i = 0; i < this.liquid_waters; i++) {
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = rand.nextInt(world.getHeight() - 8) + 8;
				WorldGenerator liquid = this.biome.getLiquidWaterGen(world, x, y, z, rand);
				liquid.generate(world, rand, x, y, z);
			}
			for (int i = 0; i < this.liquid_lavas; i++) {
				x = this.randCoord(rand, x0);
				z = this.randCoord(rand, z0);
				y = rand.nextInt(world.getHeight() - 8) + 8;
				WorldGenerator liquid = this.biome.getLiquidLavaGen(world, x, y, z, rand);
				liquid.generate(world, rand, x, y, z);
			}
		}
	}

	private void genOreEvenly(WorldGenerator gen, int min_y, int max_y, int amount)
	{
		int x, y, z;
		for (int i = 0; i < amount; i++) {
			x = this._cur_rand.nextInt(16) + this._cur_x0;
			y = this._cur_rand.nextInt(max_y - min_y) + min_y;
			z = this._cur_rand.nextInt(16) + this._cur_z0;
			gen.generate(this._cur_world, this._cur_rand, x, y, z);
		}
	}

	private void genMushrooms(World world, Random rand, int x0, int z0, boolean always)
	{
		int x, y, z;
		if (rand.nextFloat() < this.brown_mushroom_chance) {
			x = this.randCoord(rand, x0);
			z = this.randCoord(rand, z0);
			y = rand.nextInt(world.getHeightValue(x, z) * 2);
			WorldGenerator shroom = this.biome.getBrownMushroomGen(world, x, y, z, rand, always);
			shroom.generate(world, rand, x, y, z);
		}
		if (rand.nextFloat() < this.red_mushroom_chance) {
			x = this.randCoord(rand, x0);
			z = this.randCoord(rand, z0);
			y = rand.nextInt(world.getHeightValue(x, z) * 2);
			WorldGenerator shroom = this.biome.getRedMushroomGen(world, x, y, z, rand, always);
			shroom.generate(world, rand, x, y, z);
		}
	}

	private int randCoord(Random rand, int c)
	{
		return c + rand.nextInt(16) + 8;
	}
}

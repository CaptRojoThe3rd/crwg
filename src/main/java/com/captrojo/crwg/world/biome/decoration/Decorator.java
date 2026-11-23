package com.captrojo.crwg.world.biome.decoration;

import java.util.Random;

import com.captrojo.crwg.config.GeneralConfig;
import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.gen.feature.Feature;
import com.captrojo.crwg.world.gen.feature.WorldGenSeafloorBlockCircle;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;

public class Decorator
{
	private boolean _decorating = false;

	private final BiomeCRWGBase biome;

	public WorldGenerator gen_underwater_sand = new WorldGenSeafloorBlockCircle(Blocks.sand, 7, false);
	public WorldGenerator gen_underwater_gravel = new WorldGenSeafloorBlockCircle(Blocks.gravel, 6, false);
	public WorldGenerator gen_underwater_clay = new WorldGenSeafloorBlockCircle(Blocks.clay, 4, true);

	public DecoratorOptions optns_lowland;
	public DecoratorOptions optns_hills;
	public DecoratorOptions optns_highlands;

	public Decorator(final BiomeCRWGBase biome, final long seed)
	{
		this.biome = biome;
	}

	public void setAllOptions(DecoratorOptions optns)
	{
		this.optns_lowland = optns;
		this.optns_hills = optns;
		this.optns_highlands = optns;
	}

	public void decorate(final World world, final Random rand, final int cx0, final int cz0)
	{
		if (this._decorating) {
			CRWG.log.error("Already decorating! Chunk: {}, {} (Pos: {}, {})", cx0 >> 4, cz0 >> 4, cx0, cz0);
			Thread.dumpStack();
			if (!GeneralConfig.yeet_decorator_crash) {
				throw this.crash(cx0, cz0, new RuntimeException("Already decorating!!"));
			}
			return;
		}
		this._decorating = true;

		DecoratorOptions optns;
		switch (((WorldCRWG) world).crwg$getLandType(cx0 + 15, cz0 + 15)) {
		default ->
			optns = this.optns_lowland;
		case HILLS ->
			optns = this.optns_hills;
		case HIGHLANDS ->
			optns = this.optns_highlands;
		}
		this.genDecorations(world, rand, cx0, cz0, optns);

		this._decorating = false;
	}

	protected void genDecorations(final World world, final Random rand, final int cx0, final int cz0, final DecoratorOptions optns)
	{
		final BlockPos pos = new BlockPos();

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.SAND)) {
			for (int i = 0; i < optns.underwater_sand; i++) {
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getTopSolidOrLiquidBlock(pos.x, pos.z);
				this.gen_underwater_sand.generate(world, rand, pos.x, pos.y, pos.z);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.CLAY)) {
			for (int i = 0; i < optns.underwater_clay; i++) {
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getTopSolidOrLiquidBlock(pos.x, pos.z);
				this.gen_underwater_clay.generate(world, rand, pos.x, pos.y, pos.z);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.SAND_PASS2)) {
			for (int i = 0; i < optns.underwater_gravel; i++) {
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getTopSolidOrLiquidBlock(pos.x, pos.z);
				this.gen_underwater_gravel.generate(world, rand, pos.x, pos.y, pos.z);
			}
		}

		for (int i = 0; i < optns.boulders; i++) {
			if (rand.nextFloat() < optns.boulder_chance) {
				continue;
			}
			pos.x = this.randCoord(rand, cx0);
			pos.z = this.randCoord(rand, cz0);
			pos.y = world.getHeightValue(pos.x, pos.z);
			Feature gen = biome.getBoulderGen(world, rand, pos);
			gen.generate(world, rand, pos);
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.FLOWERS)) {
			for (int i = 0; i < optns.flowers; i++) {
				if (rand.nextFloat() > optns.flower_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getFlowerGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.GRASS)) {
			for (int i = 0; i < optns.grass; i++) {
				if (rand.nextFloat() > optns.grass_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getGrassGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}

			for (int i = 0; i < optns.tall_grass; i++) {
				if (rand.nextFloat() > optns.tall_grass_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getTallGrassGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.DEAD_BUSH)) {
			for (int i = 0; i < optns.shrubs; i++) {
				if (rand.nextFloat() > optns.shrub_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getBushGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.LILYPAD)) {
			for (int i = 0; i < optns.waterlilies; i++) {
				if (rand.nextFloat() > optns.waterlily_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getWaterlilyGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.SHROOM)) {
			for (int i = 0; i < optns.mushrooms; i++) {
				if (rand.nextFloat() <= optns.mushroom_chance) {
					this.genMushrooms(world, rand, cx0, cz0, false, optns);
				}
			}
			if (rand.nextFloat() <= optns.mushroom_chance) {
				this.genMushrooms(world, rand, cx0, cz0, true, optns);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.TREE)) {
			int t = optns.sup_trees.get(world, cx0, cz0, rand);
			if (optns.extra_tree_chance > 0.0F && rand.nextFloat() <= optns.extra_tree_chance) {
				t++;
			}
			for (int i = 0; i < t; i++) {
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);

				Feature gen = this.biome.getTreeGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.BIG_SHROOM)) {
			int s = optns.big_mushrooms;
			if (optns.extra_big_mushroom_chance > 0.0F && rand.nextFloat() <= optns.extra_big_mushroom_chance) {
				s++;
			}
			for (int i = 0; i < s; i++) {
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);

				Feature gen = this.biome.getBigMushroomGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.REED)) {
			for (int i = 0; i < optns.reeds; i++) {
				if (rand.nextFloat() > optns.reed_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getReedGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.PUMPKIN)) {
			for (int i = 0; i < optns.pumpkins; i++) {
				if (rand.nextFloat() > optns.pumpkin_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getPumpkinGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.CACTUS)) {
			for (int i = 0; i < optns.cacti; i++) {
				if (rand.nextFloat() > optns.cacti_chance) {
					continue;
				}
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = world.getHeightValue(pos.x, pos.z);
				Feature gen = this.biome.getCactusGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}

		/* The EventType is LAKE, but we actually are placing singular liquid blocks. */
		/* Good job, Forge. */
		if (TerrainGen.decorate(world, rand, cx0, cz0, Decorate.EventType.LAKE)) {
			for (int i = 0; i < optns.liquid_waters; i++) {
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = rand.nextInt(world.getHeight() - 8) + 8;
				Feature gen = this.biome.getLiquidWaterGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
			for (int i = 0; i < optns.liquid_lavas; i++) {
				pos.x = this.randCoord(rand, cx0);
				pos.z = this.randCoord(rand, cz0);
				pos.y = rand.nextInt(world.getHeight() - 8) + 8;
				Feature gen = this.biome.getLiquidLavaGen(world, rand, pos);
				gen.generate(world, rand, pos);
			}
		}
	}

	private void genMushrooms(
		final World world,
		final Random rand,
		final int cx0,
		final int cz0,
		final boolean always,
		final DecoratorOptions optns
	)
	{
		final BlockPos pos = new BlockPos();
		if (rand.nextFloat() < optns.brown_mushroom_chance) {
			pos.x = this.randCoord(rand, cx0);
			pos.z = this.randCoord(rand, cz0);
			pos.y = rand.nextInt(world.getHeightValue(pos.x, pos.z) * 2);
			Feature gen = this.biome.getBrownMushroomGen(world, rand, pos, always);
			gen.generate(world, rand, pos);
		}
		if (rand.nextFloat() < optns.red_mushroom_chance) {
			pos.x = this.randCoord(rand, cx0);
			pos.z = this.randCoord(rand, cz0);
			pos.y = rand.nextInt(world.getHeightValue(pos.x, pos.z) * 2);
			Feature gen = this.biome.getRedMushroomGen(world, rand, pos, always);
			gen.generate(world, rand, pos);
		}
	}

	protected int randCoord(final Random rand, final int c)
	{
		return c + rand.nextInt(16) + 8;
	}

	private ReportedException crash(final int cx0, final int cz0, final Throwable t)
	{
		CrashReport crash = CrashReport.makeCrashReport(t, t.getMessage());
		CrashReportCategory cat = crash.makeCategory("DecorationChunk");
		cat.addCrashSection("cx0", Integer.valueOf(cx0));
		cat.addCrashSection("cz0", Integer.valueOf(cz0));
		cat.addCrashSection("biome", this.biome.biomeName);
		cat.addCrashSection("biome id", Integer.valueOf(this.biome.biomeID));
		cat.addCrashSection("biome id (hex)", Integer.toString(this.biome.biomeID, 16));
		return new ReportedException(crash);
	}
}

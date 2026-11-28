package com.captrojo.crwg.world.biome.decoration.decors;

import java.util.Random;

import com.captrojo.crwg.event.worldgen.CRWGDecorateEvent;
import com.captrojo.crwg.util.func.TriArgFloatProvider;
import com.captrojo.crwg.util.func.TriArgIntProvider;
import com.captrojo.crwg.util.func.TriArgProvider;
import com.captrojo.crwg.world.biome.decoration.util.DecoCoords;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import cpw.mods.fml.common.eventhandler.Event.Result;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

public abstract class Decoration
{
	public static Decoration newRandXZ(
		final EventType type, 
		final String desc,
		final TriArgProvider<World, Random, BlockPos, WorldGenerator> p_gen,
		final int count,
		final float chance,
		TriArgIntProvider<World, Random, DecoCoords> p_count,
		TriArgFloatProvider<World, Random, DecoCoords> p_chance
	) {
		if (p_count == null) {
			p_count = (final World w, final Random r, final DecoCoords d) -> {
				return count;
			};
		}
		if (p_chance == null) {
			p_chance = (final World w, final Random r, final DecoCoords d) -> {
				return chance;
			};
		}
		return new RandXZDecoration(type, desc, p_gen, p_count, p_chance);
	}

	public static Decoration newRandXZGnd(
		final EventType type, 
		final String desc,
		final TriArgProvider<World, Random, BlockPos, WorldGenerator> p_gen,
		final int count,
		final float chance,
		TriArgIntProvider<World, Random, DecoCoords> p_count,
		TriArgFloatProvider<World, Random, DecoCoords> p_chance
	) {
		if (p_count == null) {
			p_count = (final World w, final Random r, final DecoCoords d) -> {
				return count;
			};
		}
		if (p_chance == null) {
			p_chance = (final World w, final Random r, final DecoCoords d) -> {
				return chance;
			};
		}
		return new RandXZGndDecoration(type, desc, p_gen, p_count, p_chance);
	}

	public static Decoration newRandXYZ(
		final EventType type, 
		final String desc,
		final TriArgProvider<World, Random, BlockPos, WorldGenerator> p_gen,
		final int count,
		final float chance,
		TriArgIntProvider<World, Random, DecoCoords> p_count,
		TriArgFloatProvider<World, Random, DecoCoords> p_chance
	) {
		if (p_count == null) {
			p_count = (final World w, final Random r, final DecoCoords d) -> {
				return count;
			};
		}
		if (p_chance == null) {
			p_chance = (final World w, final Random r, final DecoCoords d) -> {
				return chance;
			};
		}
		return new RandXYZDecoration(type, desc, p_gen, p_count, p_chance);
	}

	private final EventType event_type;
	private final String event_desc;

	public Decoration(final EventType event_type, final String event_desc)
	{
		this.event_type = event_type;
		this.event_desc = event_desc;
	}

	public abstract void decorate(World world, Random rand, DecoCoords dpos);

	/* A return value of `true` indiciates decoration can proceed */
	public boolean preDecoEvent(final World world, final Random rand, final DecoCoords dpos)
	{
		CRWGDecorateEvent event = new CRWGDecorateEvent(world, rand, dpos, this.event_type, this.event_desc);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.getResult() != Result.DENY;
	}
}

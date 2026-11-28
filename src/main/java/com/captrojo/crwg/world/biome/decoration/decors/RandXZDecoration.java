package com.captrojo.crwg.world.biome.decoration.decors;

import java.util.Random;

import com.captrojo.crwg.util.func.TriArgFloatProvider;
import com.captrojo.crwg.util.func.TriArgIntProvider;
import com.captrojo.crwg.util.func.TriArgProvider;
import com.captrojo.crwg.world.biome.decoration.util.DecoCoords;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

public class RandXZDecoration extends Decoration
{
	private final TriArgProvider<World, Random, BlockPos, WorldGenerator> gen;
	private final TriArgIntProvider<World, Random, DecoCoords> count;
	private final TriArgFloatProvider<World, Random, DecoCoords> chance;

	public RandXZDecoration(
		final EventType event_type,
		final String event_desc,
		final TriArgProvider<World, Random, BlockPos, WorldGenerator> gen,
		final TriArgIntProvider<World, Random, DecoCoords> count,
		final TriArgFloatProvider<World, Random, DecoCoords> chance
	) {
		super(event_type, event_desc);
		this.gen = gen;
		this.count = count;
		this.chance = chance;
	}

	@Override
	public void decorate(final World world, final Random rand, final DecoCoords dpos)
	{
		final BlockPos pos = new BlockPos();
		final int count = this.count.getInt(world, rand, dpos);
		final float chance = this.chance.getFloat(world, rand, dpos);
		for (int c = 0; c < count; c++) {
			if (chance < 1.0F && rand.nextFloat() > chance) {
				continue;
			}
			dpos.applyRandPos(pos);
			WorldGenerator gen = this.gen.get(world, rand, pos);
			gen.generate(world, rand, pos.x, 0, pos.z);
		}
	}
}

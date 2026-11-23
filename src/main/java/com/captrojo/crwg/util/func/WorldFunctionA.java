package com.captrojo.crwg.util.func;

import java.util.Random;

import net.minecraft.world.World;

@FunctionalInterface
public interface WorldFunctionA<R>
{
	R get(final World world, final Random rand, int x, int y, int z);
}

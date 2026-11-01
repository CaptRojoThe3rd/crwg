package com.captrojo.crwg.util;

import java.util.Random;

import net.minecraft.world.World;

@FunctionalInterface
public interface WorldFunction<R>
{
	R get(World world, Random rand, int x, int y, int z);
}

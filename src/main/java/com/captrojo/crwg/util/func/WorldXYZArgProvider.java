package com.captrojo.crwg.util.func;

import java.util.Random;

import net.minecraft.world.World;

@FunctionalInterface
public interface WorldXYZArgProvider<R>
{
	R get(World world, Random rand, int x, int y, int z);
}

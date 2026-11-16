package com.captrojo.crwg.util.func;

import java.util.Random;

import net.minecraft.world.World;

@FunctionalInterface
public interface WorldCPosIntSupplier
{
	int get(World world, int x0, int z0, Random rand);
}

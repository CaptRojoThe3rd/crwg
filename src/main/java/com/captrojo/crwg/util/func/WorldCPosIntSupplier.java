package com.captrojo.crwg.util.func;

import java.util.Random;

import net.minecraft.world.World;

@FunctionalInterface
public interface WorldCPosIntSupplier
{
	int get(World world, int cx0, int cz0, Random rand);
}

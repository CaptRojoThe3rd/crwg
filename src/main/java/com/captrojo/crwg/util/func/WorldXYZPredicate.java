package com.captrojo.crwg.util.func;

import net.minecraft.world.World;

@FunctionalInterface
public interface WorldXYZPredicate
{
	boolean test(World world, int x, int y, int z);
}

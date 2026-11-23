package com.captrojo.crwg.util.func;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;

@FunctionalInterface
public interface WorldFunctionB<R>
{
	R get(final World world, final Random rand, final BlockPos pos);
}

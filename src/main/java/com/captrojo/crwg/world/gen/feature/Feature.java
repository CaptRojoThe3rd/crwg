package com.captrojo.crwg.world.gen.feature;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;

public interface Feature
{
	boolean generate(final World world, final Random rand, final BlockPos origin);
}

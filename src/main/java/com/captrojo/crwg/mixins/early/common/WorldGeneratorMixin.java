package com.captrojo.crwg.mixins.early.common;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;

import com.captrojo.crwg.world.gen.feature.Feature;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

@Mixin(WorldGenerator.class)
public abstract class WorldGeneratorMixin implements Feature
{
	@Override
	public boolean generate(final World world, final Random rand, final BlockPos origin)
	{
		return ((WorldGenerator) (Object) this).generate(world, rand, origin.x, origin.y, origin.z);
	}
}

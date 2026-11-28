package com.captrojo.crwg.world.gen.feature.ore;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockReplacer;
import com.captrojo.crwg.world.gen.feature.WorldGenFeature;
import com.captrojo.crwg.world.gen.placement.HeightProvider;

import net.minecraft.util.Facing;
import net.minecraft.world.World;

public abstract class FeatureAbstractOre extends WorldGenFeature
{
	private final HeightProvider height_prov;
	protected final BlockReplacer block_replacer;
	protected final int size;
	protected final float discard_on_exposure_chance;

	protected FeatureAbstractOre(
		final HeightProvider height_prov,
		final BlockReplacer block_replacer,
		final int size,
		final float discard_on_exposure_chance
	)
	{
		super(false);
		this.height_prov = height_prov;
		this.block_replacer = block_replacer;
		this.size = size;
		this.discard_on_exposure_chance = discard_on_exposure_chance;
	}

	protected abstract void doGeneration(final World world, final Random rand, int x, int y, int z);

	protected boolean shouldDiscard(final World world, final Random rand, final int x, final int y, final int z)
	{
		if (this.discard_on_exposure_chance <= 0.0F) {
			return false;
		}
		for (int d = 0; d < 6; d++) {
			int xa = Facing.offsetsXForSide[d] + x;
			int ya = Facing.offsetsXForSide[d] + y;
			int za = Facing.offsetsXForSide[d] + z;
			if (world.isAirBlock(xa, ya, za)) {
				if (this.discard_on_exposure_chance >= 1.0F) {
					return true;
				}
				if (rand.nextFloat() <= this.discard_on_exposure_chance) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean generate(final World world, final Random rand, int x, int y, int z)
	{
		y = this.height_prov.getHeight(rand);
		this.doGeneration(world, rand, x, y, z);
		return true;
	}
}

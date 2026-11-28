package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeBeach extends BiomeCRWGBase
{
	static final byte TYPE_STONE = 0;
	static final byte TYPE_GRAVEL = 1;
	static final byte TYPE_NORMAL = 2;
	static final byte TYPE_TROPICAL = 3;

	private final byte type;

	public BiomeBeach(
		final BiomeInfo info,
		final float temperature,
		final float rainfall,
		final byte type
	) {
		super(info, temperature, rainfall);
		this.type = type;
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		if (this.type == TYPE_TROPICAL) {
			optns.trees = 1;
			optns.tree_chance = 0.75F;
		}

		this.decorator.setAllOptions(optns);
	}

	@Override
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		if (this.type == TYPE_STONE) {
			return BlockStates.STONE;
		}
		if (this.type == TYPE_GRAVEL) {
			return BlockStates.GRAVEL;
		}
		return BlockStates.SAND;
	}

	@Override
	public BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		return this.getTopBlock(world, rand, vals);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return TreeGens.Palm;
	}
}

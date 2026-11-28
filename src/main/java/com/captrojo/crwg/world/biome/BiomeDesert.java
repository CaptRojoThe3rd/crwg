package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDesert extends BiomeCRWGBase
{
	static final WorldGenerator[] GRASS_GENS = {
		BiomeCRWGBase.Gen_ShortGrass_Dry_A,
		BiomeCRWGBase.Gen_ShortGrass_Dry_B,
		BiomeCRWGBase.Gen_ShortGrass_Dry_C,
		BiomeCRWGBase.Gen_ShortGrass_Dry_D
	};

	public BiomeDesert(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
		this.spawnableCreatureList.clear();
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.flowers = 0;
		optns.grass = 1;
		optns.grass_chance = 0.25F;
		optns.shrubs = 8;

		optns.reeds = 50;
		optns.cacti = 10;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		return BlockStates.SAND;
	}

	@Override
	public BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		return BlockStates.SAND;
	}

	@Override
	public WorldGenerator getGrassGen(final World world, final Random rand, final BlockPos origin)
	{
		return GRASS_GENS[rand.nextInt(GRASS_GENS.length)];
	}

	@Override
	public WorldGenerator getBushGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_Bush_Dead;
	}
}

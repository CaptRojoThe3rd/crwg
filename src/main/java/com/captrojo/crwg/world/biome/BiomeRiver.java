package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeRiver extends BiomeCRWGBase
{
	private final boolean swampy;

	public BiomeRiver(final BiomeInfo info, final float temperature, final float rainfall, final boolean swampy)
	{
		super(info, temperature, rainfall);
		this.swampy = swampy;
		this.spawnableCreatureList.clear();
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();
		if (this.swampy) {
			optns.waterlilies = 2;
		}
		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getWaterlilyGen(final World world, final Random rand, final BlockPos origin)
	{
		return rand.nextBoolean() ? BiomeCRWGBase.Gen_Waterlily : BiomeCRWGBase.Gen_Waterlily_Small;
	}
}

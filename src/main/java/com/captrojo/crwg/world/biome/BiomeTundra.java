package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;

public class BiomeTundra extends BiomeCRWGBase
{
	private final boolean is_very_snowy;
	private final boolean is_ice_spikes;

	private final WorldGenIceSpike gen_ice_spike = new WorldGenIceSpike();
	private final WorldGenIcePath gen_ice_path = new WorldGenIcePath(4);

	public BiomeTundra(final BiomeInfo info, final float temperature, final float rainfall, final boolean ice_spikes)
	{
		super(info, temperature, rainfall);
		this.is_ice_spikes = ice_spikes;
		this.is_very_snowy = ice_spikes || (temperature < -0.5f);

		this.setEnableSnow();
		this.spawnableCreatureList.clear();
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.grass = 0;
		optns.flowers = 0;
		optns.reeds = 0;
		optns.pumpkins = 0;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		if (this.is_very_snowy) {
			return BlockStates.SNOW_BLOCK;
		}
		return super.getTopBlock(world, rand, vals);
	}

	@Override
	public void decorate(final World world, final Random rand, final int cx0, final int cz0)
	{
		if (this.is_ice_spikes) {
			int i, x, z;
			for (i = 0; i < 3; i++) {
				x = cx0 + rand.nextInt(16) + 8;
				z = cz0 + rand.nextInt(16) + 8;
				this.gen_ice_spike.generate(world, rand, x, world.getHeightValue(x, z), z);
			}
			for (i = 0; i < 2; i++) {
				x = cx0 + rand.nextInt(16) + 8;
				z = cz0 + rand.nextInt(16) + 8;
				this.gen_ice_path.generate(world, rand, x, world.getHeightValue(x, z), z);
			}
		}
		super.decorate(world, rand, cx0, cz0);
	}
}

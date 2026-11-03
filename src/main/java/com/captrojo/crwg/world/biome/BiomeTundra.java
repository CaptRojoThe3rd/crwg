package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;

public class BiomeTundra extends BiomeCRWGBase
{
	final boolean is_very_snowy;
	final boolean is_ice_spikes;

	private WorldGenIceSpike gen_ice_spike = new WorldGenIceSpike();
	private WorldGenIcePath gen_ice_path = new WorldGenIcePath(4);

	public BiomeTundra(int id, String name, int color, float temperature, float rainfall, boolean ice_spikes)
	{
		super(id, name, color, temperature, rainfall);
		this.is_ice_spikes = ice_spikes;
		this.is_very_snowy = ice_spikes || (temperature < -0.5f);

		this.decorator.grass = 0;
		this.decorator.flowers = 0;
		this.decorator.reeds = 0;
		this.decorator.pumpkins = 0;

		this.decorator_hills.copyFrom(this.decorator);
		this.decorator_highlands.copyFrom(this.decorator);

		this.setEnableSnow();
		this.spawnableCreatureList.clear();
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		if (this.is_very_snowy) {
			return BlockState.SNOW_BLOCK;
		}
		return super.getTopBlock(world, x, y, z, rand, vals);
	}

	@Override
	public void decorate(World world, Random rand, int x0, int z0)
	{
		if (this.is_ice_spikes) {
			int i, x, z;
			for (i = 0; i < 3; i++) {
				x = x0 + rand.nextInt(16) + 8;
				z = z0 + rand.nextInt(16) + 8;
				this.gen_ice_spike.generate(world, rand, x, world.getHeightValue(x, z), z);
			}
			for (i = 0; i < 2; i++) {
				x = x0 + rand.nextInt(16) + 8;
				z = z0 + rand.nextInt(16) + 8;
				this.gen_ice_path.generate(world, rand, x, world.getHeightValue(x, z), z);
			}
		}
		super.decorate(world, rand, x0, z0);
	}
}

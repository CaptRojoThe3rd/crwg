package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.noise.NoisePerlin;

import net.minecraft.world.World;

public class BiomeMountains extends BiomeCRWGBase
{
	private static final int TYPE_FROZEN = 0;
	private static final int TYPE_STONY = 1;
	private static final int TYPE_STANDARD = 2;

	private final int type;

	private Random noise_rand;
	private NoisePerlin snow_noise;
	private NoisePerlin ice_noise;

	public BiomeMountains(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		if (temperature <= 0.0F) {
			this.type = TYPE_FROZEN;
		} else if (temperature >= 0.4F) {
			this.type = TYPE_STONY;
		} else {
			this.type = TYPE_STANDARD;
		}

		this.decorator.trees = 0;
		this.decorator.grass = 0;
		this.decorator.flowers = 0;

		this.decorator.liquid_waters = 0;
		this.decorator.liquid_lavas = 0;

		this.decorator.emerald_amount = 3;
	}

	@Override
	public void initWithWorldSeed(long world_seed)
	{
		super.initWithWorldSeed(world_seed);

		if (this.noise_rand == null) {
			this.noise_rand = new Random();
		}
		this.noise_rand.setSeed(world_seed);

		if (this.type == TYPE_FROZEN) {
			this.ice_noise = new NoisePerlin(this.noise_rand);
			this.snow_noise = new NoisePerlin(this.noise_rand);
		} else if (this.type == TYPE_STANDARD) {
			this.snow_noise = new NoisePerlin(this.noise_rand);
		}
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		if (this.type == TYPE_STONY) {
			return BlockState.STONE;
		}

		double sn = this.snow_noise.calcPoint((double) x / 50.0, (double) z / 50.0);
		int snb = (int) (sn * 20.0);

		if (this.type == TYPE_FROZEN) {
			double in = this.ice_noise.calcPoint((double) x / 50.0, (double) z / 50.0);
			double inb = (int) (in * 20.0);
			if (y > (YConst.SEA_LVL_Y + 224 + inb)) {
				return BlockState.PACKED_ICE;
			}
			if (y > (YConst.SEA_LVL_Y + 168 + snb)) {
				return BlockState.SNOW_BLOCK;
			}
			return BlockState.STONE;
		}

		if (y > (YConst.SEA_LVL_Y + 192 + snb)) {
			return BlockState.SNOW_BLOCK;
		}
		return BlockState.STONE;
	}

	@Override
	public BlockState getFillerBlock(World world, int x, int y, int z, Random rand, GenVals vals, boolean low)
	{
		if (this.type == TYPE_FROZEN && !low) {
			double in = this.ice_noise.calcPoint((double) x / 50.0, (double) z / 50.0);
			double inb = (int) (in * 20.0);
			if (y > (YConst.SEA_LVL_Y + 224 + inb)) {
				return BlockState.PACKED_ICE;
			}
		}
		return BlockState.STONE;
	}
}

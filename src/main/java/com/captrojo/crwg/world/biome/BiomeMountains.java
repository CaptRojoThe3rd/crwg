package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.util.block.BlockStates;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;
import com.captrojo.crwg.world.gen.noise.NoisePerlin;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.world.World;

public class BiomeMountains extends BiomeCRWGBase
{
	private static final byte TYPE_FROZEN = 0;
	private static final byte TYPE_STONY = 1;
	private static final byte TYPE_STANDARD = 2;

	private final byte type;

	private Random noise_rand;
	private NoisePerlin snow_noise;
	private NoisePerlin ice_noise;

	public BiomeMountains(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);

		if (temperature <= 0.0F) {
			this.type = TYPE_FROZEN;
		} else if (temperature >= 0.4F) {
			this.type = TYPE_STONY;
		} else {
			this.type = TYPE_STANDARD;
		}
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.grass = 0;
		optns.flowers = 0;

		optns.trees = 0;

		optns.liquid_waters = 0;
		optns.liquid_lavas = 0;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public void initWithWorldSeed(final long world_seed)
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
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		if (this.type == TYPE_STONY) {
			return BlockStates.STONE;
		}

		double sn = this.snow_noise.calcPoint((double) vals.pos.x / 50.0, (double) vals.pos.z / 50.0);
		int snb = (int) (sn * 20.0);

		if (this.type == TYPE_FROZEN) {
			double in = this.ice_noise.calcPoint((double) vals.pos.x / 50.0, (double) vals.pos.z / 50.0);
			double inb = (int) (in * 20.0);
			if (vals.pos.y > (YConst.SEA_LVL_Y + 224 + inb)) {
				return BlockStates.PACKED_ICE;
			}
			if (vals.pos.y > (YConst.SEA_LVL_Y + 168 + snb)) {
				return BlockStates.SNOW_BLOCK;
			}
			return BlockStates.STONE;
		}

		if (vals.pos.y > (YConst.SEA_LVL_Y + 192 + snb)) {
			return BlockStates.SNOW_BLOCK;
		}
		return BlockStates.STONE;
	}

	@Override
	public BlockState getFillerBlock(final World world, final Random rand, final GenVals vals, final boolean low)
	{
		if (this.type == TYPE_FROZEN && !low) {
			double in = this.ice_noise.calcPoint((double) vals.pos.x / 50.0, (double) vals.pos.z / 50.0);
			double inb = (int) (in * 20.0);
			if (vals.pos.y > (YConst.SEA_LVL_Y + 224 + inb)) {
				return BlockStates.PACKED_ICE;
			}
		}
		return BlockStates.STONE;
	}
}

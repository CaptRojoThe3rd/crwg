package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenArrays;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeAbstractSwamp extends BiomeCRWGBase
{
	protected boolean generate_waterlilies = true;
	protected double waterlily_chance = 0.1;

	public BiomeAbstractSwamp(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
		this.waterColorMultiplier = 0xcbfc7b;
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.underwater_gravel = 0;
		optns.underwater_sand = 0;
		optns.underwater_clay = 1;

		optns.trees = 2;

		optns.grass = 5;
		optns.cattails = 5;
		optns.flowers = 1;
		optns.shrubs = 1;

		optns.mushrooms = 8;

		optns.reeds = 10;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public void genTerrainExt(
		final World world,
		final Random rand,
		final GenArrays arrays,
		final GenVals vals
	) {
		if (this.generate_waterlilies) {
			double d1 = BiomeGenBase.plantNoise.func_151601_a((double) vals.pos.x * 0.25, (double) vals.pos.z * 0.25);
			if (d1 > 0.0) {
				final Block[] ablock = arrays.ablock;
				final short[] ameta = arrays.ameta;
				int xc = vals.pos.x & 0xf;
				int zc = vals.pos.z & 0xf;
				int xz = xc | (zc << 4);
				if (d1 < this.waterlily_chance) {
					if (vals.gnd_height < 128) {
						int idx = xz | (128 << 8);
						ablock[idx] = Blocks.waterlily;
					}
				} else if (d1 < (this.waterlily_chance * 2.0)) {
					if (vals.gnd_height < 128) {
						int idx = xz | (128 << 8);
						ablock[idx] = ModBlocks.small_waterlily;
						ameta[idx] = (short) (rand.nextInt(15) + 1);
					}
				}
			}
		}

		super.genTerrainExt(world, rand, arrays, vals);
	}
}

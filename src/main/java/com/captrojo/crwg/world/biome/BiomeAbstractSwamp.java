package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
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

	public BiomeAbstractSwamp(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.waterColorMultiplier = 0xcbfc7b;

		this.decorator.underwater_gravel = 0;
		this.decorator.underwater_sand = 0;
		this.decorator.underwater_clay = 1;

		this.decorator.trees = 2;
		
		this.decorator.grass = 5;
		this.decorator.flowers = 1;
		this.decorator.dead_bushes = 1;
		
		this.decorator.mushrooms = 8;
		
		this.decorator.reeds = 10;
	}

	@Override
	public void genTerrainExt(
		World world,
		Random rand,
		Block[] ablock,
		short[] ameta,
		int x,
		int z,
		GenArrays arrays,
		GenVals vals
	)
	{
		if (this.generate_waterlilies) {
			double d1 = BiomeGenBase.plantNoise.func_151601_a((double) x * 0.25, (double) z * 0.25);
			if (d1 > 0.0) {
				int xc = x & 0xf;
				int zc = z & 0xf;
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

		super.genTerrainExt(world, rand, ablock, ameta, x, z, arrays, vals);
	}
}

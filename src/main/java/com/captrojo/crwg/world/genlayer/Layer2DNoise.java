package com.captrojo.crwg.world.genlayer;

import com.captrojo.crwg.main.CRWG;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.layer.IntCache;

import java.util.Random;

/**
 * Based on experiemental data (octaves: range):
 *
 * 1: -1 to +1 (it might be -1.25 to +1.25)
 * 2: -2.5 to +2.5
 * 3: -5 to +5
 * 4: -10 to +10
 * 5: -20 to +20 (maybe)
 */

public class Layer2DNoise extends LayerStraight
{
	public NoiseGeneratorOctaves noise_gen;
	public double[] noise_array;
	public int octaves;
	public double scale;
	public double m;

	public Layer2DNoise(long seed, int octaves, double scale, double m)
	{
		super(null);

		this.noise_gen = new NoiseGeneratorOctaves(new Random(seed), octaves);
		this.octaves = octaves;
		this.scale = 1.0 / scale;
		this.m = m;
	}

	@Override
	public double[] generate(int ax, int az, int aw, int ah, int asize)
	{
		if (this.noise_array == null || this.noise_array.length < asize) {
			this.noise_array = new double[asize];
		}

		this.noise_gen.generateNoiseOctaves(
			this.noise_array,
			ax, 1, az,
			aw, 1, ah,
			this.scale, 1, this.scale
		);

		if (this.m != 1.0) {
			for (int i = 0; i < asize; i++) {
				this.noise_array[i] *= this.m;
			}
		}

		return this.noise_array;
	}
}

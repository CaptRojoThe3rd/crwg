package com.captrojo.crwg.world.gen.noise.layer;

import com.captrojo.crwg.world.gen.noise.NoisePerlinFractal;

import java.util.Random;

public class NLayer2DNoise extends NLayer
{
	final boolean apply_multiplier;
	final double multiplier;

	final NoisePerlinFractal noise_gen;
	final double scale_x;
	final double scale_y;

	public double[] noise_array;

	public NLayer2DNoise(Random rand, int octaves, boolean normalize, double scale_x, double scale_y)
	{
		this(rand, octaves, normalize, scale_x, scale_y, 1.0);
	}

	public NLayer2DNoise(Random rand, int octaves, boolean normalize, double scale_x, double scale_y, double multiplier)
	{
		this.multiplier = multiplier;
		this.apply_multiplier = (multiplier != 1.0);

		this.noise_gen = (new NoisePerlinFractal(octaves, normalize)).initNoiseGens(rand);
		this.scale_x = scale_x;
		this.scale_y = scale_y;
	}
	
	protected NoisePerlinFractal createNoiseGen(int octaves, boolean normalize, Random rand)
	{
		return (new NoisePerlinFractal(octaves, normalize)).initNoiseGens(rand);
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		this.noise_array = this.noise_gen.calcArray(this.noise_array, ax, ay, aw, ah, this.scale_x, this.scale_y);

		if (this.apply_multiplier) {
			for (int i = 0; i < asize; i++) {
				this.noise_array[i] *= this.multiplier;
			}
		}

		return this.noise_array;
	}
}

package com.captrojo.crwg.world.gen.noise;

import java.util.Random;

public class NoisePerlinFractal
{
	private final int octaves;
	private final boolean normalize;

	private final NoisePerlin[] noise_gens;
	private final double[][] noise_arrays;

	public NoisePerlinFractal(int octaves, boolean normalize)
	{
		this.octaves = octaves;
		this.normalize = normalize;

		this.noise_gens = new NoisePerlin[octaves];
		this.noise_arrays = new double[octaves][];
	}

	public NoisePerlinFractal initNoiseGens(Random rand)
	{
		for (int i = 0; i < octaves; i++) {
			this.noise_gens[i] = new NoisePerlin(rand);
		}
		return this;
	}

	public double[] calcArray(double[] array, int pos_x, int pos_y, int width, int height, double scale_x, double scale_y)
	{
		int size = width * height;
		if (array == null || array.length < size) {
			array = new double[size];
		}
		for (int i = 0; i < size; i++) {
			array[i] = 0.0;
		}

		double amp = 1.0;
		double freq = 1.0 / (double) this.octaves;

		double atotal = 0.0;

		for (int octave = 0; octave < this.octaves; octave++) {
			this.noise_arrays[octave] = this.noise_gens[octave].calcArray(
				this.noise_arrays[octave],
				pos_x,
				pos_y,
				width,
				height,
				scale_x * freq,
				scale_y * freq
			);

			for (int i = 0; i < size; i++) {
				array[i] += amp * this.noise_arrays[octave][i];
			}

			atotal += amp;

			amp *= 0.5;
			freq *= 2.0;
		}

		atotal = (1.0 / atotal) * 0.5;

		if (this.normalize) {
			for (int i = 0; i < size; i++) {
				array[i] = array[i] * atotal + 0.5;
			}
		}

		return array;
	}
}

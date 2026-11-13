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
	
	public double calcPoint(int pos_x, int pos_y, double scale_x, double scale_y)
	{
		double amp = 1.0;
		double freq = 1.0 / (double) this.octaves;
		double atotal = 0.0;
		
		double n = 0.0;
		
		double x0 = (double) pos_x;
		double y0 = (double) pos_y;

		for (int octave = 0; octave < this.octaves; octave++) {
			double x = x0 * (scale_x * freq);
			double y = y0 * (scale_y * freq);
			
			n += this.noise_gens[octave].calcPoint(x, y);

			atotal += amp;
			amp *= 0.5;
			freq *= 2.0;
		}
		
		atotal = (1.0 / atotal) * 0.5;
		if (this.normalize) {
			n = n * atotal + 0.5;
		}
		
		return n;
	}

	public double[] calcArray(double[] array, int pos_x, int pos_y, int width, int height, double scale_x, double scale_y)
	{
		return this.calcArray(array, pos_x, pos_y, width, height, scale_x, scale_y, null);
	}
	
	public double[] calcArray(double[] array, int pos_x, int pos_y, int width, int height, double scale_x, double scale_y, double[] selector)
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
			if (selector == null) {
				this.noise_arrays[octave] = this.noise_gens[octave].calcArray(
					this.noise_arrays[octave],
					pos_x,
					pos_y,
					width,
					height,
					scale_x * freq,
					scale_y * freq
				);
			} else {
				this.noise_arrays[octave] = this.noise_gens[octave].calcArray(
					this.noise_arrays[octave],
					pos_x,
					pos_y,
					width,
					height,
					scale_x * freq,
					scale_y * freq,
					selector
				);
			}

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

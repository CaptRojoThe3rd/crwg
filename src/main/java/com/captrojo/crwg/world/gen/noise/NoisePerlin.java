package com.captrojo.crwg.world.gen.noise;

import net.minecraft.util.MathHelper;

import java.util.Random;

/**
 * https://rtouti.github.io/graphics/perlin-noise-algorithm
 */
public class NoisePerlin
{
	private static final double[] const_vec_x = {1.0, -1.0, -1.0, 1.0};
	private static final double[] const_vec_y = {1.0, 1.0, -1.0, -1.0};

	private final int[] permutations;

	private final double x_offset;
	private final double y_offset;

	public NoisePerlin(Random rand)
	{
		this.permutations = new int[2048];
		for (int i = 0; i < 1024; i++) {
			this.permutations[i] = i;
		}
		for (int i = 0; i < 1024; i++) {
			int j = rand.nextInt(1024);
			int tmp = this.permutations[j];
			this.permutations[i] = this.permutations[j];
			this.permutations[j] = tmp;
		}
		for (int i = 0; i < 1024; i++) {
			this.permutations[i + 1024] = this.permutations[i];
		}

		this.x_offset = rand.nextDouble() * 1024.0;
		this.y_offset = rand.nextDouble() * 1024.0;
	}

	private final double lerp(double t, double a, double b)
	{
		return a + t * (b - a);
	}

	private final double fade(double t)
	{
		return ((6 * t - 15) * t + 10) * t * t * t;
	}

	public final double calcPoint(double x, double y)
	{
		final int xi = MathHelper.floor_double(x);
		final int yi = MathHelper.floor_double(y);
		final int xp = xi & 1023;
		final int yp = yi & 1023;
		final double xf = x - (double) xi;
		final double yf = y - (double) yi;

		final double vec_tr_x = xf - 1.0;
		final double vec_tr_y = yf - 1.0;
		final double vec_tl_x = xf;
		final double vec_tl_y = yf - 1.0;
		final double vec_br_x = xf - 1.0;
		final double vec_br_y = yf;
		final double vec_bl_x = xf;
		final double vec_bl_y = yf;

		final int val_tr = this.permutations[this.permutations[xp + 1] + yp + 1] & 3;
		final int val_tl = this.permutations[this.permutations[xp] + yp + 1] & 3;
		final int val_br = this.permutations[this.permutations[xp + 1] + yp] & 3;
		final int val_bl = this.permutations[this.permutations[xp] + yp] & 3;

		final double dot_tr = vec_tr_x * NoisePerlin.const_vec_x[val_tr] + vec_tr_y * NoisePerlin.const_vec_y[val_tr];
		final double dot_tl = vec_tl_x * NoisePerlin.const_vec_x[val_tl] + vec_tl_y * NoisePerlin.const_vec_y[val_tl];
		final double dot_br = vec_br_x * NoisePerlin.const_vec_x[val_br] + vec_br_y * NoisePerlin.const_vec_y[val_br];
		final double dot_bl = vec_bl_x * NoisePerlin.const_vec_x[val_bl] + vec_bl_y * NoisePerlin.const_vec_y[val_bl];

		final double u = this.fade(xf);
		final double v = this.fade(yf);
		return this.lerp(u, this.lerp(v, dot_bl, dot_tl), this.lerp(v, dot_br, dot_tr));
	}

	public final double[] calcArray(double[] array, double pos_x, double pos_y, int width, int height, double scale_x, double scale_y)
	{
		int size = width * height;
		if (array == null || array.length < size) {
			array = new double[size];
		}

		double x0 = pos_x * scale_x;
		double y0 = pos_y * scale_y;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int idx = x + (y * height);

				final double xf = x0 + (double) x * scale_x + this.x_offset;
				final double yf = y0 + (double) y * scale_y + this.y_offset;

				array[idx] = this.calcPoint(xf, yf);
			}
		}

		return array;
	}
}

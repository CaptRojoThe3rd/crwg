package com.captrojo.crwg.world.gen.cave;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.data.GenArrays;

import net.minecraft.world.World;

public class CaveBubble extends CaveBase
{
	static boolean[][][] generateCircleLUT(int radius)
	{
		int diameter = radius * 2 + 1;

		boolean[][][] lut = new boolean[diameter][][];
		for (int x = -radius; x <= radius; x++) {
			int x1 = x + radius;
			lut[x1] = new boolean[diameter][];
			for (int y = -radius; y <= radius; y++) {
				int y1 = y + radius;
				lut[x1][y1] = new boolean[diameter];
				for (int z = -radius; z <= radius; z++) {
					int z1 = z + radius;
					int dist = (int) Math.sqrt(x * x + y * y + z * z);
					lut[x1][y1][z1] = (dist < radius);
				}
			}
		}

		return lut;
	}

	private final int[] radii = {8, 10, 12};
	private final int[] diameters = {17, 21, 25};
	private final boolean[][][][] luts;

	{
		this.range = 5;

		boolean[][][] circle_lut_8 = generateCircleLUT(8);
		boolean[][][] circle_lut_10 = generateCircleLUT(10);
		boolean[][][] circle_lut_12 = generateCircleLUT(12);
		this.luts = new boolean[][][][] {circle_lut_8, circle_lut_10, circle_lut_12};
	}

	void generateBubble(World world, int r_chunk_x, int r_chunk_z, int chunk_x, int chunk_z, GenArrays arrays)
	{
		Random rand = this.rand;

		int rcx0 = r_chunk_x << 4;
		int rcz0 = r_chunk_z << 4;

		int min_x = Integer.MAX_VALUE;
		int max_x = Integer.MIN_VALUE;
		int min_y = Integer.MAX_VALUE;
		int max_y = Integer.MIN_VALUE;
		int min_z = Integer.MAX_VALUE;
		int max_z = Integer.MIN_VALUE;

		int[] x_points = new int[5];
		int[] y_points = new int[5];
		int[] z_points = new int[5];
		int[] pt_sizes = new int[5];

		int hx = rcx0 + rand.nextInt(16);
		int hy = 20 + YConst.MIN_CAVE_H_LVL + rand.nextInt((YConst.MAX_CAVE_H_LVL - YConst.MIN_CAVE_H_LVL) / 4);
		int hz = rcz0 + rand.nextInt(16);

		for (int p = 0; p < 5; p++) {
			x_points[p] = hx;
			y_points[p] = hy;
			z_points[p] = hz;
			pt_sizes[p] = rand.nextInt(3);
			hx += rand.nextInt(7) - rand.nextInt(7);
			hy += rand.nextInt(7) - rand.nextInt(7);
			hz += rand.nextInt(7) - rand.nextInt(7);
			int r = this.radii[pt_sizes[p]];
			int l_min_x = hx - r;
			int l_max_x = hx + r;
			int l_min_y = hy - r;
			int l_max_y = hy + r;
			int l_min_z = hz - r;
			int l_max_z = hz + r;
			if (l_min_x < min_x) {
				min_x = l_min_x;
			}
			if (l_max_x > max_x) {
				max_x = l_max_x;
			}
			if (l_min_y < min_y) {
				min_y = l_min_y;
			}
			if (l_max_y > max_y) {
				max_y = l_max_y;
			}
			if (l_min_z < min_z) {
				min_z = l_min_z;
			}
			if (l_max_z > max_z) {
				max_z = l_max_z;
			}
		}

		int cx0 = chunk_x << 4;
		int cz0 = chunk_z << 4;
		int cx16 = cx0 + 16;
		int cz16 = cz0 + 16;
		if (min_x > cx16) {
			return;
		}
		if (max_x < cx0) {
			return;
		}
		if (min_z > cz16) {
			return;
		}
		if (max_z < cz0) {
			return;
		}
		if (min_x < cx0) {
			min_x = cx0;
		}
		if (max_x > cx16) {
			max_x = cx16;
		}
		if (min_z < cz0) {
			min_z = cz0;
		}
		if (max_z < cz16) {
			min_z = cz16;
		}

		for (int x = min_x; x < max_x; x++) {
			for (int y = min_y; y < max_y; y++) {
				for (int z = min_z; z < max_z; z++) {
				p_loop:
					for (int p = 0; p < 5; p++) {
						int s = 0;
						int r = this.radii[s];
						int d = this.diameters[s];

						int xr = x - x_points[p] + r;
						if (xr < 0 || xr >= d) {
							continue;
						}
						int yr = y - y_points[p] + r;
						if (yr < 0 || yr >= d) {
							continue;
						}
						int zr = z - z_points[p] + r;
						if (zr < 0 || zr >= d) {
							continue;
						}

						boolean b = this.luts[s][xr][yr][zr];
						if (b) {
							int xa = x - cx0;
							int za = z - cz0;
							int idx = xa | (za << 4) | (y << 8);
							this.dig(arrays, idx, y);
							break p_loop;
						}
					}
				}
			}
		}
	}

	@Override
	public void generate(World world, int r_chunk_x, int r_chunk_z, int chunk_x, int chunk_z, GenArrays arrays)
	{
		if (this.rand.nextInt(30) != 0) {
			return;
		}
		this.generateBubble(world, r_chunk_x, r_chunk_z, chunk_x, chunk_z, arrays);
	}
}

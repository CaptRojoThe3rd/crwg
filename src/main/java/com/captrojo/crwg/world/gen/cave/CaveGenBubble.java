package com.captrojo.crwg.world.gen.cave;

import java.util.Random;

import com.captrojo.crwg.util.MathUtil;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.data.GenArrays;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CaveGenBubble extends CaveGenBase
{
	final int add_h = (YConst.MAX_CAVE_H_LVL - YConst.MIN_CAVE_H_LVL) / 2;

	double[] x_points = new double[32];
	double[] y_points = new double[32];
	double[] z_points = new double[32];

	void generateBubble(World world, int r_chunk_x, int r_chunk_z, int chunk_x, int chunk_z, GenArrays arrays)
	{
		int x0 = chunk_x << 4;
		int z0 = chunk_z << 4;
		int x15 = x0 + 15;
		int z15 = z0 + 15;
		
		double dx0 = (double) x0;
		double dz0 = (double) z0;
		double dx15 = (double) x15;
		double dz15 = (double) z15;

		Random rand = this.rand;
		double[] x_points = this.x_points;
		double[] y_points = this.y_points;
		double[] z_points = this.z_points;

		double horz_size = rand.nextDouble() * 20.0 + 15.0;
		double vert_size = horz_size * 0.25 + 0.75;

		double hx = (double) (r_chunk_x << 4) + (rand.nextDouble() * 16.0);
		double hy = YConst.MIN_CAVE_H_LVL + (rand.nextDouble() * this.add_h);
		double hz = (double) (r_chunk_z << 4) + (rand.nextDouble() * 16.0);

		double min_hy = hy;
		double max_hy = hy;
		boolean affects_this_chunk = false;

		int points = 16 + rand.nextInt(17);
		for (int p = 0; p < points; p++) {
			if (hy < min_hy) {
				min_hy = hy;
			} else if (hy > max_hy) {
				max_hy = hy;
			}
			x_points[p] = hx;
			y_points[p] = hy;
			z_points[p] = hz;
			hx += (rand.nextDouble() - rand.nextDouble()) * 6.0;
			hy += (rand.nextDouble() - rand.nextDouble()) * 3.0;
			hz += (rand.nextDouble() - rand.nextDouble()) * 6.0;
			
			if (!affects_this_chunk) {
				double horz_size_sq = horz_size * horz_size;
				double Δx, Δz;
				
				Δx = dx0 - hx;
				Δz = dz0 - hz;
				if ((Δx * Δx + Δz * Δz) < horz_size_sq) {
					affects_this_chunk = true;
					continue;
				}
				Δx = dx15 - hx;
				Δz = dz0 - hz;
				if ((Δx * Δx + Δz * Δz) < horz_size_sq) {
					affects_this_chunk = true;
					continue;
				}
				Δx = dx0 - hx;
				Δz = dz15 - hz;
				if ((Δx * Δx + Δz * Δz) < horz_size_sq) {
					affects_this_chunk = true;
					continue;
				}
				Δx = dx15 - hx;
				Δz = dz15 - hz;
				if ((Δx * Δx + Δz * Δz) < horz_size_sq) {
					affects_this_chunk = true;
					continue;
				}
			}
		}
		
		if (!affects_this_chunk) {
			return;
		}

		int lo_h = MathHelper.ceiling_double_int(min_hy - vert_size);
		int hi_h = MathHelper.ceiling_double_int(max_hy + vert_size);
		lo_h = MathHelper.clamp_int(lo_h, YConst.MIN_H_LVL, YConst.MAX_H_LVL);
		hi_h = MathHelper.clamp_int(hi_h, YConst.MIN_H_LVL, YConst.MAX_H_LVL);

		for (int xa = 0; xa < 16; xa++) {
			for (int ya = lo_h; ya < hi_h; ya++) {
				for (int za = 0; za < 16; za++) {
					double dx = (double) (xa + x0);
					double dy = (double) ya;
					double dz = (double) (za + z0);
					for (int p = 0; p < points; p++) {
						double Δx = dx - x_points[p];
						double Δy = dy - y_points[p];
						double Δz = dz - z_points[p];
						if (Δx < 1E-7 && Δz < 1E-7 && Δy > vert_size) {
							continue;
						}
						double Δx_sq = Δx * Δx;
						double Δz_sq = Δz * Δz;
						double Δhorz = Math.sqrt(Δx_sq + Δz_sq);
						double t = Math.abs(Math.atan(Δy / Δhorz)) / MathUtil.PId2;
						double local_size = (vert_size * t) + (horz_size * (1.0 - t));
						double dist_sq = Δx_sq + Δz_sq + Δy * Δy;
						if (dist_sq > (local_size * local_size)) {
							continue;
						}
						int idx = xa | (za << 4) | (ya << 8);
						this.dig(arrays, idx, ya);
						break;
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

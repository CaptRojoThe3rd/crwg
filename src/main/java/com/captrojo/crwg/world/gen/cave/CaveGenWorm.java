package com.captrojo.crwg.world.gen.cave;

import java.util.Random;

import com.captrojo.crwg.util.MathUtil;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.data.GenArrays;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/* 80-160 block long "worms" carved into the underground.
 */
public class CaveGenWorm extends CaveGenBase
{
	void generateWorm(
		World world,
		int head_α,
		int head_β,
		double head_x,
		double head_y,
		double head_z,
		int chunk_x,
		int chunk_z,
		GenArrays arrays,
		boolean is_root
	)
	{
		Random rand = this.rand;

		int max_height = arrays.gnd_height_avg - YConst.MIN_Y_LVL;
		if (rand.nextInt(3) == 0) {
			max_height += 15;
		} else {
			max_height -= 15;
		}

		final double MAX_H_LVL = (double) YConst.MAX_H_LVL;

		int cx0 = chunk_x << 4;
		int cz0 = chunk_z << 4;

		int cx8 = cx0 + 8;
		int cz8 = cz0 + 8;
		double chk_x_n = (double) cx8 - 16.0;
		double chk_x_p = (double) cx8 + 16.0;
		double chk_z_n = (double) cz8 - 16.0;
		double chk_z_p = (double) cz8 + 16.0;

		final int min_β = -30000;
		final int max_β = 30000;

		int head_β_actual;

		double cur_radius_sq = 1.0 + (rand.nextDouble() * 2.0);
		cur_radius_sq = cur_radius_sq * cur_radius_sq;
		double actual_cur_radius_sq;

		int worm_length = rand.nextInt(80) + 80;
//		int remaining_forks = 3;

	move_loop:
		for (int head_pos = 0; head_pos < worm_length; head_pos++) {
			head_α += rand.nextInt(5000) - rand.nextInt(5000);
			if (head_β > max_β) {
				head_β -= rand.nextInt(5000);
			} else if (head_β < min_β) {
				head_β += rand.nextInt(5000);
			} else {
				head_β += rand.nextInt(5000) - rand.nextInt(5000);
			}
			head_β_actual = head_β * 5 / 4;

			double sin_head_α = (double) MathUtil.sin_65536(head_α);
			double cos_head_α = (double) MathUtil.cos_65536(head_α);
			double sin_head_β = (double) MathUtil.sin_65536(head_β_actual);
			double cos_head_β = (double) MathUtil.cos_65536(head_β_actual);

			head_x += sin_head_α * cos_head_β;
			head_y += sin_head_β;
			head_z += cos_head_α * cos_head_β;

			cur_radius_sq += (rand.nextDouble() - rand.nextDouble()) * 0.2;
			cur_radius_sq = MathHelper.clamp_double(cur_radius_sq, 0.0, 4.25);
			actual_cur_radius_sq = MathHelper.clamp_double(cur_radius_sq, 1.0, 4.0);

			int hyi = MathHelper.floor_double(head_y);
			if (hyi > (max_height)) {
				continue;
			}

			if (head_x < chk_x_n || head_x > chk_x_p) {
				continue;
			}
			if (head_y < 0.0 || head_y > MAX_H_LVL) {
				continue;
			}
			if (head_z < chk_z_n || head_z > chk_z_p) {
				continue;
			}

			int hxi = MathHelper.floor_double(head_x);
			int hzi = MathHelper.floor_double(head_z);
			int hxi0 = hxi - cx0;
			int hzi0 = hzi - cz0;

			int xn = hxi0 - 8;
			int xp = hxi0 + 8;
			int yn = hyi - 8;
			int yp = hyi + 8;
			int zn = hzi0 - 8;
			int zp = hzi0 + 8;
			if (xn < 0) {
				xn = 0;
			}
			if (xp > 16) {
				xp = 16;
			}
			if (yn < 0) {
				yn = 0;
			}
			if (yp > YConst.MAX_H_LVL) {
				yp = YConst.MAX_H_LVL;
			}
			if (zn < 0) {
				zn = 0;
			}
			if (zp > 16) {
				zp = 16;
			}
			for (int x1 = xn; x1 < xp; x1 += 2) {
				for (int y1 = yn; y1 < yp; y1 += 2) {
					for (int z1 = zn; z1 < zp; z1 += 2) {
						int idx = x1 | (z1 << 4) | (y1 << 8);
						if (this.isWater(arrays, idx)) {
							continue move_loop;
						}
					}
				}
			}

			for (double u = -4.0; u <= 4.1; u += 0.5) {
				for (double v = -4.0; v <= 4.1; v += 0.5) {
					if ((u * u + v * v) > actual_cur_radius_sq) {
						continue;
					}

					for (double w = -0.5; w <= 0.1; w += 0.5) {
						double dx = head_x
							+ (u * cos_head_α)
							- (v * sin_head_α * sin_head_β)
							+ (w * sin_head_α * cos_head_β);
						double dy = head_y
							+ (v * cos_head_β)
							+ (w * sin_head_β);
						double dz = head_z
							- (u * sin_head_α)
							- (v * cos_head_α * sin_head_β)
							+ (w * cos_head_α * cos_head_β);

						int ix = MathHelper.floor_double(dx);
						int iy = MathHelper.floor_double(dy);
						int iz = MathHelper.floor_double(dz);

						int xa = ix - cx0;
						int ya = iy;
						int za = iz - cz0;

						if (xa < 0 || xa >= 16) {
							continue;
						}
						if (ya < 0 || ya >= YConst.MAX_H_LVL) {
							continue;
						}
						if (za < 0 || za >= 16) {
							continue;
						}

						int idx = xa | (za << 4) | (ya << 8);
						this.dig(arrays, idx, ya);
					}
				}
			}
		}
	}

	@Override
	public void generate(World world, int r_chunk_x, int r_chunk_z, int chunk_x, int chunk_z, GenArrays arrays)
	{
		Random rand = this.rand;
		int rcx0 = r_chunk_x << 4;
		int rcz0 = r_chunk_z << 4;

		int head_α = rand.nextInt(65536);
		double head_x = (double) rcx0 + (rand.nextDouble() * 16.0);
		double head_y = YConst.MIN_CAVE_H_LVL + (rand.nextDouble() * (YConst.MAX_CAVE_H_LVL - YConst.MIN_CAVE_H_LVL));
		double head_z = (double) rcz0 + (rand.nextDouble() * 16.0);
		this.generateWorm(world, head_α, 0, head_x, head_y, head_z, chunk_x, chunk_z, arrays, true);

		head_α = rand.nextInt(65536);
		head_x = (double) rcx0 + (rand.nextDouble() * 16.0);
		head_y = YConst.MIN_CAVE_H_LVL + (rand.nextDouble() * (YConst.SEA_LVL_H - YConst.MIN_CAVE_H_LVL));
		head_z = (double) rcz0 + (rand.nextDouble() * 16.0);
		this.generateWorm(world, head_α, 0, head_x, head_y, head_z, chunk_x, chunk_z, arrays, true);
	}
}

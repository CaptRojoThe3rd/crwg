package com.captrojo.crwg.world.gen.cave;

import java.util.Random;

import com.captrojo.crwg.util.MathUtil;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.gen.data.GenArrays;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CaveGenWorm extends CaveGenBase
{
	void generateWorm(World world, int r_chunk_x, int r_chunk_z, int chunk_x, int chunk_z, GenArrays arrays)
	{
		Random rand = this.rand;
		
		final double MAX_H_LVL = (double) YConst.MAX_H_LVL;

		int x0 = chunk_x << 4;
		int z0 = chunk_z << 4;

		int x8 = x0 + 8;
		int z8 = z0 + 8;
		double chk_x_n = (double) x8 - 16.0;
		double chk_x_p = (double) x8 + 16.0;
		double chk_z_n = (double) z8 - 16.0;
		double chk_z_p = (double) z8 + 16.0;

		int rx0 = r_chunk_x << 4;
		int rz0 = r_chunk_z << 4;

		final float min_β = MathUtil.PI * -0.8F;
		final float max_β = MathUtil.PI * 0.8F;

		float head_α = MathUtil.PIx2 * rand.nextFloat();
		float head_β_c = 0.0F;
		float head_β;
		double head_x = (double) rx0 + (rand.nextDouble() * 16.0);
		double head_y = YConst.MIN_CAVE_H_LVL + (rand.nextDouble() * (YConst.MAX_CAVE_H_LVL - YConst.MIN_CAVE_H_LVL));
		double head_z = (double) rz0 + (rand.nextDouble() * 16.0);

		double cur_radius_sq = 1.0 + (rand.nextDouble() * 2.0);
		cur_radius_sq = cur_radius_sq * cur_radius_sq;
		double actual_cur_radius_sq;

		int worm_length = rand.nextInt(80) + 80;

	move_loop:
		for (int head_pos = 0; head_pos < worm_length; head_pos++) {
			head_α += (rand.nextFloat() - rand.nextFloat()) * 0.5F;
			if (head_β_c > max_β) {
				head_β_c -= rand.nextFloat() * 0.5F;
			} else if (head_β_c < min_β) {
				head_β_c += rand.nextFloat() * 0.5F;
			} else {
				head_β_c += (rand.nextFloat() - rand.nextFloat()) * 0.33F;
			}
			head_β = head_β_c * 0.8F;

			double sin_head_α = (double) MathHelper.sin(head_α);
			double cos_head_α = (double) MathHelper.cos(head_α);
			double sin_head_β = (double) MathHelper.sin(head_β);
			double cos_head_β = (double) MathHelper.cos(head_β);

			head_x += sin_head_α * cos_head_β;
			head_y += sin_head_β;
			head_z += cos_head_α * cos_head_β;

			cur_radius_sq += (rand.nextDouble() - rand.nextDouble()) * 0.2;
			cur_radius_sq = MathHelper.clamp_double(cur_radius_sq, 0.0, 4.25);
			actual_cur_radius_sq = MathHelper.clamp_double(cur_radius_sq, 1.0, 4.0);

			if (head_x < chk_x_n || head_x > chk_x_p) {
				continue;
			}
			if (head_y < 0.0 || head_y > MAX_H_LVL) {
				continue;
			}
			if (head_z < chk_z_n || head_z > chk_z_p) {
				continue;
			}

			int xn = MathHelper.floor_double(head_x) - 8 - x0;
			int xp = MathHelper.floor_double(head_x) + 8 - x0;
			int yn = MathHelper.floor_double(head_y) - 8;
			int yp = MathHelper.floor_double(head_y) + 8;
			int zn = MathHelper.floor_double(head_z) - 8;
			int zp = MathHelper.floor_double(head_z) + 8;
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
			for (int x1 = xn; x1 < xp; x1++) {
				for (int y1 = yn; y1 < yp; y1++) {
					for (int z1 = zn; z1 < zp; z1++) {
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

						int xa = ix - x0;
						int ya = iy;
						int za = iz - z0;

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
		this.generateWorm(world, r_chunk_x, r_chunk_z, chunk_x, chunk_z, arrays);
	}
}

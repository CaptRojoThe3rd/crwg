package com.captrojo.crwg.world.gen;

import java.util.Random;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenCaves;

public class MapGenCavesCRWG extends MapGenCaves
{
	private Random rand2 = new Random();

	protected void func_151542_a(long seed, int chunk_x, int chunk_z, Block[] ablock, double dw_x, double dw_y, double dw_z)
	{
		this.func_151541_a(seed, chunk_x, chunk_z, ablock, dw_x, dw_y, dw_z, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
	}

	@Override
	protected void func_151541_a(long seed, int chunk_x, int chunk_z, Block[] ablock, double dw_x, double dw_y, double dw_z, float fa, float a_horz, float a_vert, int ia, int ib, double d_vert_mult)
	{
		double d_x8 = (double) (chunk_x * 16 + 8);
		double d_z8 = (double) (chunk_z * 16 + 8);
		float a_horz_d = 0.0F;
		float a_vert_d = 0.0F;

		Random rand = this.rand2;
		rand.setSeed(seed);

		if (ib <= 0) {
			int j1 = this.range * 16 - 16;
			ib = j1 - rand.nextInt(j1 / 4);
		}

		boolean ia_init_flag = false;
		if (ia == -1) {
			ia = ib / 2;
			ia_init_flag = true;
		}

		int k1 = rand.nextInt(ib / 2) + ib / 4;

		for (boolean r6_flag = (rand.nextInt(6) == 0); ia < ib; ia++) {
			double d_horz = 1.5D + (double) (MathHelper.sin((float) ia * (float) Math.PI / (float) ib) * fa * 1.0F);
			double d_vert = d_horz * d_vert_mult;
			float ch_horz = MathHelper.cos(a_vert);
			float ch_vert = MathHelper.sin(a_vert);
			/* xyz of the worm */
			dw_x += (double) (MathHelper.cos(a_horz) * ch_horz);
			dw_y += (double) ch_vert;
			dw_z += (double) (MathHelper.sin(a_horz) * ch_horz);

			if (r6_flag) {
				a_vert *= 0.92F;
			} else {
				a_vert *= 0.7F;
			}

			a_vert += a_vert_d * 0.1F;
			a_horz += a_horz_d * 0.1F;
			a_vert_d *= 0.9F;
			a_horz_d *= 0.75F;
			a_vert_d += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 2.0F;
			a_horz_d += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 4.0F;

			if (!ia_init_flag && ia == k1 && fa > 1.0F && ib > 0) {
				this.func_151541_a(rand.nextLong(), chunk_x, chunk_z, ablock, dw_x, dw_y, dw_z, rand.nextFloat() * 0.5F + 0.5F, a_horz - ((float) Math.PI / 2F), a_vert / 3.0F, ia, ib, 1.0D);
				this.func_151541_a(rand.nextLong(), chunk_x, chunk_z, ablock, dw_x, dw_y, dw_z, rand.nextFloat() * 0.5F + 0.5F, a_horz + ((float) Math.PI / 2F), a_vert / 3.0F, ia, ib, 1.0D);
				return;
			}

			if (ia_init_flag || rand.nextInt(4) != 0) {
				double dc_x = dw_x - d_x8;
				double dc_z = dw_z - d_z8;
				double d10 = (double) (ib - ia);
				double d11 = (double) (fa + 2.0F + 16.0F);

				if ((dc_x * dc_x + dc_z * dc_z - d10 * d10) > (d11 * d11)) {
					return;
				}

				if (
					(dw_x >= (d_x8 - 16.0D - d_horz * 2.0D)) &&
						(dw_x <= (d_x8 + 16.0D + d_horz * 2.0D)) &&
						(dw_z >= (d_z8 - 16.0D - d_horz * 2.0D)) &&
						(dw_z <= (d_z8 + 16.0D + d_horz * 2.0D))
				) {
					int xl = MathHelper.floor_double(dw_x - d_horz) - chunk_x * 16 - 1;
					int xh = MathHelper.floor_double(dw_x + d_horz) - chunk_x * 16 + 1;
					int yl = MathHelper.floor_double(dw_y - d_vert) - 1;
					int yh = MathHelper.floor_double(dw_y + d_vert) + 1;
					int zl = MathHelper.floor_double(dw_z - d_horz) - chunk_z * 16 - 1;
					int zh = MathHelper.floor_double(dw_z + d_horz) - chunk_z * 16 + 1;

					if (xl < 0) {
						xl = 0;
					}
					if (xh > 16) {
						xh = 16;
					}

					if (yl < (YConst.MIN_Y_LVL + 1)) {
						yl = (YConst.MIN_Y_LVL + 1);
					}
					if (yh > (YConst.MAX_Y_LVL - 8)) {
						yh = (YConst.MAX_Y_LVL - 8);
					}

					if (zl < 0) {
						zl = 0;
					}
					if (zh > 16) {
						zh = 16;
					}

					boolean ocean_flag = false;

					for (int x1 = xl; !ocean_flag && x1 < xh; x1++) {
						for (int z1 = zl; !ocean_flag && z1 < zh; z1++) {
							for (int y1 = yh + 1; !ocean_flag && y1 >= yl - 1; y1--) {
								int idx = x1 | (z1 << 4) | (y1 << 8);

								if (y1 >= 0 && y1 < 512) {
									if (this.isOceanBlock(ablock, idx, x1, y1, z1, chunk_x, chunk_z)) {
										ocean_flag = true;
									}

									if (
										y1 != (yl - 1)
											&& x1 != xl
											&& x1 != (xh - 1)
											&& z1 != zl
											&& z1 != (zh - 1)
									) {
										y1 = yl;
									}
								}
							}
						}
					}

					if (!ocean_flag) {
						for (int x1 = xl; x1 < xh; x1++) {
							double xdist = ((double) (x1 + chunk_x * 16) + 0.5D - dw_x) / d_horz;

							for (int z1 = zl; z1 < zh; z1++) {
								double zdist = ((double) (z1 + chunk_z * 16) + 0.5D - dw_z) / d_horz;
								int idx = x1 | (z1 << 4) | (yh << 8);
								boolean top_flag = false;

								if ((xdist * xdist + zdist * zdist) < 1.0) {
									for (int y1 = yh - 1; y1 >= yl; y1--) {
										double ydist = ((double) y1 + 0.5D - dw_y) / d_vert;

										if (ydist > -0.7D && xdist * xdist + ydist * ydist + zdist * zdist < 1.0D) {
											if (this.isTopBlock(ablock, idx, x1, y1, z1, chunk_x, chunk_z)) {
												top_flag = true;
											}
											this.digBlock(ablock, idx, x1, y1, z1, chunk_x, chunk_z, top_flag);
										}

										idx -= 256;
									}
								}
							}
						}

						if (ia_init_flag) {
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected void func_151538_a(World world, int r_chunk_x, int r_chunk_z, int chunk_x, int chunk_z, Block[] ablock)
	{
		int im = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(31) + 1) + 1);
		if (this.rand.nextInt(7) != 0) {
			im = 0;
		}

		final int min = CRWG.funcs.getMinCaveHeight() + 8;
		final int max = CRWG.funcs.getMaxCaveHeight();

		for (int i = 0; i < im; i++) {
			double d_xa = (double) (r_chunk_x * 16 + this.rand.nextInt(16));
			double d_ya = (double) this.rand.nextInt(this.rand.nextInt(max - min) + min);
			double d_za = (double) (r_chunk_z * 16 + this.rand.nextInt(16));

			int jm = 1;
			if (this.rand.nextInt(4) == 0) {
				this.func_151542_a(this.rand.nextLong(), chunk_x, chunk_z, ablock, d_xa, d_ya, d_za);
				jm += this.rand.nextInt(4);
			}

			for (int j = 0; j < jm; j++) {
				float a_horz = this.rand.nextFloat() * (float) Math.PI * 2.0F;
				float a_vert = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float fa = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0) {
					fa *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
				}

				this.func_151541_a(this.rand.nextLong(), chunk_x, chunk_z, ablock, d_xa, d_ya, d_za, fa, a_horz, a_vert, 0, 0, 1.0D);
			}
		}
	}

	@Override
	public boolean isTopBlock(Block[] ablock, int idx, int x, int y, int z, int chunk_x, int chunk_z)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunk_x * 16, z + chunk_z * 16);
		if (biome instanceof BiomeCRWGBase biome_crwg) {
			BlockState b = biome_crwg.getTopBlock(this.worldObj, x, y, z, this.rand2, GenVals.DUMMY);
			return (ablock[idx] == b.block);
		}
		return super.isTopBlock(ablock, idx, x, y, z, chunk_x, chunk_z);
	}

	@Override
	protected void digBlock(Block[] ablock, int idx, int x, int y, int z, int chunk_x, int chunk_z, boolean found_top)
	{
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunk_x * 16, z + chunk_z * 16);
		Block top, filler;
		if (biome instanceof BiomeCRWGBase biome_crwg) {
			top = biome_crwg.getTopBlock(this.worldObj, x, y, z, this.rand2, GenVals.DUMMY).block;
			filler = biome_crwg.getFillerBlock(this.worldObj, x, y, z, this.rand2, GenVals.DUMMY, false).block;
		} else {
			top = (this.isExceptionBiome(biome) ? Blocks.grass : biome.topBlock);
			filler = (this.isExceptionBiome(biome) ? Blocks.dirt : biome.fillerBlock);
		}

		Block existing = ablock[idx];

		if (existing == Blocks.stone || existing == filler || existing == top) {
			if (y < (YConst.MIN_Y_LVL + 20)) {
				ablock[idx] = Blocks.lava;
			} else {
				ablock[idx] = Blocks.air;

				if (found_top && ablock[idx - 256] == filler) {
					ablock[idx - 256] = top;
				}
			}
		}
	}
}

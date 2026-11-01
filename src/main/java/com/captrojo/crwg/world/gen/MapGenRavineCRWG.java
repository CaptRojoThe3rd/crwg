package com.captrojo.crwg.world.gen;

import java.util.Random;

import com.captrojo.crwg.main.CRWG;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenRavine;

public class MapGenRavineCRWG extends MapGenRavine
{
	@Override
	protected void func_151540_a(long seed, int chunk_x, int chunk_z, Block[] ablock, double d0x, double d0y, double d0z, float fa, float fb, float fc, int ia, int ib, double dhv)
	{
		Random random = new Random(seed);
		double d4 = (double) (chunk_x * 16 + 8);
		double d5 = (double) (chunk_z * 16 + 8);
		float f3 = 0.0F;
		float f4 = 0.0F;

		if (ib <= 0) {
			int j1 = this.range * 16 - 16;
			ib = j1 - random.nextInt(j1 / 4);
		}

		boolean flag1 = false;

		if (ia == -1) {
			ia = ib / 2;
			flag1 = true;
		}

		float f5 = 1.0F;

		for (int k1 = 0; k1 < 256; ++k1) {
			if (k1 == 0 || random.nextInt(3) == 0) {
				f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
			}

			this.field_75046_d[k1] = f5 * f5;
		}

		for (; ia < ib; ++ia) {
			double dhorz = 1.5D + (double) (MathHelper.sin((float) ia * (float) Math.PI / (float) ib) * fa * 1.0F);
			double dvert = dhorz * dhv;
			dhorz *= (double) random.nextFloat() * 0.25D + 0.75D;
			dvert *= (double) random.nextFloat() * 0.25D + 0.75D;

			float fc_cos = MathHelper.cos(fc);
			float fc_sin = MathHelper.sin(fc);
			d0x += (double) (MathHelper.cos(fb) * fc_cos);
			d0y += (double) fc_sin;
			d0z += (double) (MathHelper.sin(fb) * fc_cos);

			fc *= 0.7F;
			fc += f4 * 0.05F;
			fb += f3 * 0.05F;
			f4 *= 0.8F;
			f3 *= 0.5F;
			f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (flag1 || random.nextInt(4) != 0) {
				double d7 = d0x - d4;
				double d8 = d0z - d5;
				double d9 = (double) (ib - ia);
				double d10 = (double) (fa + 2.0F + 16.0F);

				if (d7 * d7 + d8 * d8 - d9 * d9 > d10 * d10) {
					return;
				}

				if (d0x >= d4 - 16.0D - dhorz * 2.0D && d0z >= d5 - 16.0D - dhorz * 2.0D && d0x <= d4 + 16.0D + dhorz * 2.0D && d0z <= d5 + 16.0D + dhorz * 2.0D) {
					int ax_n = MathHelper.floor_double(d0x - dhorz) - chunk_x * 16 - 1;
					int ax_p = MathHelper.floor_double(d0x + dhorz) - chunk_x * 16 + 1;
					int ay_n = MathHelper.floor_double(d0y - dvert) - 1;
					int ay_p = MathHelper.floor_double(d0y + dvert) + 1;
					int az_n = MathHelper.floor_double(d0z - dhorz) - chunk_z * 16 - 1;
					int az_p = MathHelper.floor_double(d0z + dhorz) - chunk_z * 16 + 1;

					if (ax_n < 0) {
						ax_n = 0;
					}
					if (ax_p > 16) {
						ax_p = 16;
					}

					if (ay_n < 1) {
						ay_n = 1;
					}
					if (ay_p > 504) {
						ay_p = 504;
					}

					if (az_n < 0) {
						az_n = 0;
					}
					if (az_p > 16) {
						az_p = 16;
					}

					boolean flag2 = false;
					int x1;
					int z1;

					for (x1 = ax_n; !flag2 && x1 < ax_p; ++x1) {
						for (z1 = az_n; !flag2 && z1 < az_p; ++z1) {
							for (int y1 = ay_p + 1; !flag2 && y1 >= ay_n - 1; --y1) {
								int idx = x1 | (z1 << 4) | (y1 << 8);

								if (y1 >= 0 && y1 < 512) {
									if (this.isOceanBlock(ablock, idx, x1, y1, z1, chunk_x, chunk_z)) {
										flag2 = true;
									}

									if (y1 != ay_n - 1 && x1 != ax_n && x1 != ax_p - 1 && z1 != az_n && z1 != az_p - 1) {
										y1 = ay_n;
									}
								}
							}
						}
					}

					if (!flag2) {
						for (x1 = ax_n; x1 < ax_p; ++x1) {
							double d13 = ((double) (x1 + chunk_x * 16) + 0.5D - d0x) / dhorz;

							for (z1 = az_n; z1 < az_p; ++z1) {
								double d14 = ((double) (z1 + chunk_z * 16) + 0.5D - d0z) / dhorz;
								int idx = x1 | (z1 << 4) | (ay_p << 8);
								boolean flag = false;

								if (d13 * d13 + d14 * d14 < 1.0D) {
									for (int y1 = ay_p - 1; y1 >= ay_n; --y1) {
										double d11 = ((double) y1 + 0.5D - d0y) / dvert;

										if ((d13 * d13 + d14 * d14) * (double) this.field_75046_d[y1] + d11 * d11 / 6.0D < 1.0D) {
											if (this.isTopBlock(ablock, idx, x1, y1, z1, chunk_x, chunk_z)) {
												flag = true;
											}

											this.digBlock(ablock, idx, x1, y1, z1, chunk_x, chunk_z, flag);
										}

										idx -= 256;
									}
								}
							}
						}

						if (flag1) {
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected void func_151538_a(World world, int start_chunk_x, int start_chunk_z, int chunk_x, int chunk_z, Block[] ablock)
	{
		if (this.rand.nextInt(50) == 0) {
			double d0 = (double) (start_chunk_x * 16 + this.rand.nextInt(16));
			double d2 = (double) (start_chunk_z * 16 + this.rand.nextInt(16));
			
			final int h1 = CRWG.funcs.getRavineHeight1();
			final int h2 = CRWG.funcs.getRavineHeight2();
			final int h3 = CRWG.funcs.getRavineHeight3();
			double d1 = (double) (this.rand.nextInt(this.rand.nextInt(h1) + h2) + h3);
			
			byte b0 = 1;

			for (int i1 = 0; i1 < b0; ++i1) {
				float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f2 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
				this.func_151540_a(this.rand.nextLong(), chunk_x, chunk_z, ablock, d0, d1, d2, f2, f, f1, 0, 0, 3.0D);
			}
		}
	}

	@Override
	protected void digBlock(Block[] ablock, int index, int x, int y, int z, int chunk_x, int chunk_z, boolean found_top)
	{
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(x + chunk_x * 16, z + chunk_z * 16);
		Block top = (this.isExceptionBiome(biome) ? Blocks.grass : biome.topBlock);
		Block filler = (this.isExceptionBiome(biome) ? Blocks.dirt : biome.fillerBlock);
		Block block = ablock[index];

		if (block == Blocks.stone || block == filler || block == top) {
			if (y < 10) {
				ablock[index] = Blocks.flowing_lava;
			} else {
				ablock[index] = null;

				if (found_top && ablock[index - 256] == filler) {
					ablock[index - 256] = top;
				}
			}
		}
	}
}

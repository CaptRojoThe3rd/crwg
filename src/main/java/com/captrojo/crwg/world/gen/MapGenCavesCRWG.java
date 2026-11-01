package com.captrojo.crwg.world.gen;

import java.util.Random;

import com.captrojo.crwg.main.CRWG;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenCaves;

public class MapGenCavesCRWG extends MapGenCaves
{
	private Random rand2 = new Random();
	
	@Override
	protected void func_151541_a(long seed, int chunk_x, int chunk_z, Block[] ablock, double d0x, double d0y, double d0z, float fa, float fb, float fc, int ia, int ib, double dhv)
	{
		double d4 = (double) (chunk_x * 16 + 8);
		double d5 = (double) (chunk_z * 16 + 8);
		float f3 = 0.0F;
		float f4 = 0.0F;
		Random random = this.rand2;
		random.setSeed(seed);

		if (ib <= 0) {
			int j1 = this.range * 16 - 16;
			ib = j1 - random.nextInt(j1 / 4);
		}

		boolean flag2 = false;

		if (ia == -1) {
			ia = ib / 2;
			flag2 = true;
		}

		int k1 = random.nextInt(ib / 2) + ib / 4;

		for (boolean flag = random.nextInt(6) == 0; ia < ib; ++ia) {
			double dhorz = 1.5D + (double) (MathHelper.sin((float) ia * (float) Math.PI / (float) ib) * fa * 1.0F);
			double dvert = dhorz * dhv;
			float f5 = MathHelper.cos(fc);
			float f6 = MathHelper.sin(fc);
			d0x += (double) (MathHelper.cos(fb) * f5);
			d0y += (double) f6;
			d0z += (double) (MathHelper.sin(fb) * f5);

			if (flag) {
				fc *= 0.92F;
			} else {
				fc *= 0.7F;
			}

			fc += f4 * 0.1F;
			fb += f3 * 0.1F;
			f4 *= 0.9F;
			f3 *= 0.75F;
			f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

			if (!flag2 && ia == k1 && fa > 1.0F && ib > 0) {
				this.func_151541_a(random.nextLong(), chunk_x, chunk_z, ablock, d0x, d0y, d0z, random.nextFloat() * 0.5F + 0.5F, fb - ((float) Math.PI / 2F), fc / 3.0F, ia, ib, 1.0D);
				this.func_151541_a(random.nextLong(), chunk_x, chunk_z, ablock, d0x, d0y, d0z, random.nextFloat() * 0.5F + 0.5F, fb + ((float) Math.PI / 2F), fc / 3.0F, ia, ib, 1.0D);
				return;
			}

			if (flag2 || random.nextInt(4) != 0) {
				double d8 = d0x - d4;
				double d9 = d0z - d5;
				double d10 = (double) (ib - ia);
				double d11 = (double) (fa + 2.0F + 16.0F);

				if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11) {
					return;
				}

				if (
					(d0x >= (d4 - 16.0D - dhorz * 2.0D)) &&
					(d0z >= (d5 - 16.0D - dhorz * 2.0D)) &&
					(d0x <= (d4 + 16.0D + dhorz * 2.0D)) &&
					(d0z <= (d5 + 16.0D + dhorz * 2.0D))
				) {
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

					boolean ocean_flag = false;
					int x1;
					int z1;

					for (x1 = ax_n; !ocean_flag && x1 < ax_p; ++x1) {
						for (z1 = az_n; !ocean_flag && z1 < az_p; ++z1) {
							for (int y1 = ay_p + 1; !ocean_flag && y1 >= ay_n - 1; --y1) {
								int idx = x1 | (z1 << 4) | (y1 << 8);

								if (y1 >= 0 && y1 < 512) {
									if (this.isOceanBlock(ablock, idx, x1, y1, z1, chunk_x, chunk_z)) {
										ocean_flag = true;
									}

									if (y1 != ay_n - 1 && x1 != ax_n && x1 != ax_p - 1 && z1 != az_n && z1 != az_p - 1) {
										y1 = ay_n;
									}
								}
							}
						}
					}

					if (!ocean_flag) {
						for (x1 = ax_n; x1 < ax_p; ++x1) {
							double d13 = ((double) (x1 + chunk_x * 16) + 0.5D - d0x) / dhorz;

							for (z1 = az_n; z1 < az_p; ++z1) {
								double d14 = ((double) (z1 + chunk_z * 16) + 0.5D - d0z) / dhorz;
								int idx = x1 | (z1 << 4) | (ay_p << 8);
								boolean top_flag = false;

								if (d13 * d13 + d14 * d14 < 1.0D) {
									for (int y1 = ay_p - 1; y1 >= ay_n; --y1) {
										double d12 = ((double) y1 + 0.5D - d0y) / dvert;

										if (d12 > -0.7D && d13 * d13 + d12 * d12 + d14 * d14 < 1.0D) {
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

						if (flag2) {
							break;
						}
					}
				}
			}
		}
	}

	@Override
	protected void func_151538_a(World world, int p_151538_2_, int p_151538_3_, int chunk_x, int chunk_z, Block[] ablock)
	{
		int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(31) + 1) + 1);

		if (this.rand.nextInt(7) != 0) {
			i1 = 0;
		}
		
		final int min = CRWG.funcs.getMinCaveHeight() + 8;
		final int max = CRWG.funcs.getMaxCaveHeight();

		for (int j1 = 0; j1 < i1; ++j1) {
			double d0 = (double) (p_151538_2_ * 16 + this.rand.nextInt(16));
			double d1 = (double) this.rand.nextInt(this.rand.nextInt(max - min) + min);
			double d2 = (double) (p_151538_3_ * 16 + this.rand.nextInt(16));
			int k1 = 1;

			if (this.rand.nextInt(4) == 0) {
				this.func_151542_a(this.rand.nextLong(), chunk_x, chunk_z, ablock, d0, d1, d2);
				k1 += this.rand.nextInt(4);
			}

			for (int l1 = 0; l1 < k1; ++l1) {
				float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
				float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

				if (this.rand.nextInt(10) == 0) {
					f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
				}

				this.func_151541_a(this.rand.nextLong(), chunk_x, chunk_z, ablock, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
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
				ablock[index] = Blocks.lava;
			} else {
				ablock[index] = null;

				if (found_top && ablock[index - 256] == filler) {
					ablock[index - 256] = top;
				}
			}
		}
	}
}

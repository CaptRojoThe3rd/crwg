package com.captrojo.crwg.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.MapGenCaves;

import java.util.Random;

public class MapGenCavesCRWG extends MapGenCaves
{
	/* We have to override this entire function because it is what calculates XYZ → index */
	/* I will NOT be attempting to mixin this */
	@Override
	protected void func_151541_a(long seed, int chunk_x, int chunk_z, Block[] ablock, double p_151541_6_, double p_151541_8_, double p_151541_10_, float p_151541_12_, float p_151541_13_, float p_151541_14_, int p_151541_15_, int p_151541_16_, double p_151541_17_)
	{
		double d4 = (double) (chunk_x * 16 + 8);
		double d5 = (double) (chunk_z * 16 + 8);
		float f3 = 0.0F;
		float f4 = 0.0F;
		Random rand = new Random(seed);

		if (p_151541_16_ <= 0) {
			int j1 = this.range * 16 - 16;
			p_151541_16_ = j1 - rand.nextInt(j1 / 4);
		}

		boolean flag2 = false;

		if (p_151541_15_ == -1) {
			p_151541_15_ = p_151541_16_ / 2;
			flag2 = true;
		}

		int k1 = rand.nextInt(p_151541_16_ / 2) + p_151541_16_ / 4;

		for (boolean flag = rand.nextInt(6) == 0; p_151541_15_ < p_151541_16_; ++p_151541_15_) {
			double d6 = 1.5D + (double) (MathHelper.sin((float) p_151541_15_ * (float) Math.PI / (float) p_151541_16_) * p_151541_12_ * 1.0F);
			double d7 = d6 * p_151541_17_;
			float f5 = MathHelper.cos(p_151541_14_);
			float f6 = MathHelper.sin(p_151541_14_);
			p_151541_6_ += (double) (MathHelper.cos(p_151541_13_) * f5);
			p_151541_8_ += (double) f6;
			p_151541_10_ += (double) (MathHelper.sin(p_151541_13_) * f5);

			if (flag) {
				p_151541_14_ *= 0.92F;
			} else {
				p_151541_14_ *= 0.7F;
			}

			p_151541_14_ += f4 * 0.1F;
			p_151541_13_ += f3 * 0.1F;
			f4 *= 0.9F;
			f3 *= 0.75F;
			f4 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 2.0F;
			f3 += (rand.nextFloat() - rand.nextFloat()) * rand.nextFloat() * 4.0F;

			if (!flag2 && p_151541_15_ == k1 && p_151541_12_ > 1.0F && p_151541_16_ > 0) {
				this.func_151541_a(rand.nextLong(), chunk_x, chunk_z, ablock, p_151541_6_, p_151541_8_, p_151541_10_, rand.nextFloat() * 0.5F + 0.5F, p_151541_13_ - ((float) Math.PI / 2F), p_151541_14_ / 3.0F, p_151541_15_, p_151541_16_, 1.0);
				this.func_151541_a(rand.nextLong(), chunk_x, chunk_z, ablock, p_151541_6_, p_151541_8_, p_151541_10_, rand.nextFloat() * 0.5F + 0.5F, p_151541_13_ + ((float) Math.PI / 2F), p_151541_14_ / 3.0F, p_151541_15_, p_151541_16_, 1.0);
				return;
			}

			if (flag2 || rand.nextInt(4) != 0) {
				double d8 = p_151541_6_ - d4;
				double d9 = p_151541_10_ - d5;
				double d10 = (double) (p_151541_16_ - p_151541_15_);
				double d11 = (double) (p_151541_12_ + 2.0F + 16.0F);

				if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11) {
					return;
				}

				if (p_151541_6_ >= d4 - 16.0 - d6 * 2.0 && p_151541_10_ >= d5 - 16.0 - d6 * 2.0 && p_151541_6_ <= d4 + 16.0 + d6 * 2.0 && p_151541_10_ <= d5 + 16.0 + d6 * 2.0) {
					int i4 = MathHelper.floor_double(p_151541_6_ - d6) - chunk_x * 16 - 1;
					int l1 = MathHelper.floor_double(p_151541_6_ + d6) - chunk_x * 16 + 1;
					int j4 = MathHelper.floor_double(p_151541_8_ - d7) - 1;
					int i2 = MathHelper.floor_double(p_151541_8_ + d7) + 1;
					int k4 = MathHelper.floor_double(p_151541_10_ - d6) - chunk_z * 16 - 1;
					int j2 = MathHelper.floor_double(p_151541_10_ + d6) - chunk_z * 16 + 1;

					if (i4 < 0) {
						i4 = 0;
					}

					if (l1 > 16) {
						l1 = 16;
					}

					if (j4 < 1) {
						j4 = 1;
					}

					if (i2 > 248) {
						i2 = 248;
					}

					if (k4 < 0) {
						k4 = 0;
					}

					if (j2 > 16) {
						j2 = 16;
					}

					boolean flag3 = false;
					int k2;
					int j3;

					for (k2 = i4; !flag3 && k2 < l1; ++k2) {
						for (int l2 = k4; !flag3 && l2 < j2; ++l2) {
							for (int i3 = i2 + 1; !flag3 && i3 >= j4 - 1; --i3) {
								j3 = (k2 * 16 + l2) * 256 + i3;

								if (i3 >= 0 && i3 < 256) {
									Block block = ablock[j3];

									if (this.isOceanBlock(ablock, j3, k2, i3, l2, chunk_x, chunk_z)) {
										flag3 = true;
									}

									if (i3 != j4 - 1 && k2 != i4 && k2 != l1 - 1 && l2 != k4 && l2 != j2 - 1) {
										i3 = j4;
									}
								}
							}
						}
					}

					if (!flag3) {
						for (k2 = i4; k2 < l1; ++k2) {
							double d13 = ((double) (k2 + chunk_x * 16) + 0.5D - p_151541_6_) / d6;

							for (j3 = k4; j3 < j2; ++j3) {
								double d14 = ((double) (j3 + chunk_z * 16) + 0.5D - p_151541_10_) / d6;
								int k3 = (k2 * 16 + j3) * 256 + i2;
								boolean flag1 = false;

								if (d13 * d13 + d14 * d14 < 1.0) {
									for (int l3 = i2 - 1; l3 >= j4; --l3) {
										double d12 = ((double) l3 + 0.5D - p_151541_8_) / d7;

										if (d12 > -0.7D && d13 * d13 + d12 * d12 + d14 * d14 < 1.0) {
											Block block1 = ablock[k3];

											if (this.isTopBlock(ablock, k3, k2, l3, j3, chunk_x, chunk_z)) {
												flag1 = true;
											}
											this.digBlock(ablock, k3, k2, l3, j3, chunk_x, chunk_z, flag1);
										}

										--k3;
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
}

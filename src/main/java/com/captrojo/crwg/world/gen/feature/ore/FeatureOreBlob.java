package com.captrojo.crwg.world.gen.feature.ore;

import java.util.Random;

import com.captrojo.crwg.util.MathUtil;
import com.captrojo.crwg.util.block.BlockReplacer;
import com.captrojo.crwg.world.gen.placement.HeightProvider;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class FeatureOreBlob extends FeatureAbstractOre
{
	protected FeatureOreBlob(
		final HeightProvider height_prov,
		final BlockReplacer block_replacer,
		final int size,
		final float discard_on_exposure_chance
	)
	{
		super(height_prov, block_replacer, size, discard_on_exposure_chance);
	}

	@Override
	protected void doGeneration(final World world, final Random rand, final int ox, final int oy, final int oz)
	{
		float α = rand.nextFloat() * (float) Math.PI;
		double x_max = (double) ((float) ox + MathHelper.sin(α) * (float) this.size / 8.0F);
		double x_min = (double) ((float) ox - MathHelper.sin(α) * (float) this.size / 8.0F);
		double z_max = (double) ((float) oz + MathHelper.cos(α) * (float) this.size / 8.0F);
		double z_min = (double) ((float) oz - MathHelper.cos(α) * (float) this.size / 8.0F);
		double y_r1 = (double) (oy + rand.nextInt(3) - 2);
		double y_r2 = (double) (oy + rand.nextInt(3) - 2);

		for (int l = 0; l <= this.size; ++l) {
			double dx = x_max + (x_min - x_max) * (double) l / (double) this.size;
			double dy = y_r1 + (y_r2 - y_r1) * (double) l / (double) this.size;
			double dz = z_max + (z_min - z_max) * (double) l / (double) this.size;
			double dr = rand.nextDouble() * (double) this.size / 16.0D;
			double hwave = (double) (MathHelper.sin((float) l * MathUtil.PI / (float) this.size) + 1.0F) * dr + 1.0D;
			double vwave = (double) (MathHelper.sin((float) l * MathUtil.PI / (float) this.size) + 1.0F) * dr + 1.0D;
			int xn_min = MathHelper.floor_double(dx - hwave / 2.0D);
			int yn_min = MathHelper.floor_double(dy - vwave / 2.0D);
			int zn_min = MathHelper.floor_double(dz - hwave / 2.0D);
			int xn_max = MathHelper.floor_double(dx + hwave / 2.0D);
			int yn_max = MathHelper.floor_double(dy + vwave / 2.0D);
			int zn_max = MathHelper.floor_double(dz + hwave / 2.0D);

			for (int x = xn_min; x <= xn_max; x++) {
				double d12 = ((double) x + 0.5D - dx) / (hwave / 2.0D);
				if (d12 * d12 >= 1.0D) {
					continue;
				}

				for (int y = yn_min; y <= yn_max; y++) {
					double d13 = ((double) y + 0.5D - dy) / (vwave / 2.0D);
					if (d12 * d12 + d13 * d13 >= 1.0D) {
						continue;
					}

					for (int z = zn_min; z <= zn_max; z++) {
						double d14 = ((double) z + 0.5D - dz) / (hwave / 2.0D);
						if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0) {
							continue;
						}

						BlockState replacement = this.block_replacer.getFor(world, x, y, z);
						if (replacement != null) {
							replacement.place(world, x, y, z, 2);
						}
					}
				}
			}
		}
	}
}

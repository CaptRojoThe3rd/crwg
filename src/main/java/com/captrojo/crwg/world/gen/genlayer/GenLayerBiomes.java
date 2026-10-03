package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.WorldChunkManagerCRWG;
import com.captrojo.crwg.world.biome.BiomeAndClimate;
import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.biome.MutatedBiomes;
import com.captrojo.crwg.world.gen.CircleR16;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomes extends GenLayer
{
	/**
	 * Creates a 3D array of biomes indexed by temperature, rainfall, and selector noise.
	 */
	static short[][][] createBiomeLUT(BiomeAndClimate[] biomes)
	{
		short[][][] lut = new short[200][][];
		for (int i = 0; i < 200; i++) {
			lut[i] = new short[200][];
		}

		double[] dists = new double[biomes.length];

		for (int ti = -100; ti < 100; ti++) {
			float t = (float) ti / 50.0f;
			int tidx = ti + 100;
			for (int ri = -100; ri < 100; ri++) {
				float r = (float) ri / 50.0f;
				int ridx = ri + 100;

				double lowest_d = 100.0;
				for (int i = 0; i < biomes.length; i++) {
					double dt = (double) (t - biomes[i].temperature);
					double dr = (double) (r - biomes[i].rainfall);
					double d = Math.sqrt(dt * dt + dr * dr);
					if (d < lowest_d) {
						lowest_d = d;
					}
					dists[i] = d;
				}

				lut[tidx][ridx] = new short[1];
				int sel = 0;
				for (int i = 0; i < biomes.length; i++) {
					if (dists[i] == lowest_d) {
						if (sel >= lut[tidx][ridx].length) {
							short[] aold = lut[tidx][ridx];
							short[] anew = new short[aold.length + 1];
							System.arraycopy(aold, 0, anew, 0, aold.length);
							lut[tidx][ridx] = anew;
						}
						lut[tidx][ridx][sel] = (short) biomes[i].biome.biomeID;
						sel++;
					}
				}
			}
		}

//		for (int t = 0; t < 200; t++) {
//			for (int r = 0; r < 200; r++) {
//				for (int s = 0; s < array[t][r].length; s++) {
//					if (array[t][r][s] != 0) {
//						continue;
//					}
//					CRWG.LOG.error(String.format("Biome in array was invalid! t=%d, r=%d, s=%d", t, r, s));
//					array[t][r][s] = (short) BiomeGenBase.jungle.biomeID;
//				}
//			}
//		}

		return lut;
	}

	/**
	 * Converts a 15x10 array (1-dimensional; it's 15x10 visually) of biomes to an array
	 * of BiomeAndClimate objects to be a simple and dumb argument the createBiomeArray function
	 * 
	 * Temperature is on the horizontal axis, with lower temperatures on the left.
	 * Rainfall is on the vertical axis, with lower rainfall amounts at the top.
	 */
	static BiomeAndClimate[] convert15x10Array(BiomeGenBase[] biomes)
	{
		BiomeAndClimate[] array = new BiomeAndClimate[15 * 10];

		for (int ti = -5; ti < 10; ti++) {
			int tx = ti + 5;
			float tf = ((float) ti) * 0.2f + 0.1f;
			for (int rix = 0; rix < 10; rix++) {
				float rf = ((float) rix) * 0.1f + 0.05f;

				int idx = tx + (rix * 15);
				
				BiomeGenBase biome = biomes[idx];

				BiomeAndClimate bac = new BiomeAndClimate(biome, tf, rf);
				array[idx] = bac;
			}
		}

		return array;
	}
	
	/**
	 * Combine multiple BiomeAndClimate[] arrays
	 */
	static BiomeAndClimate[] combineArrays(BiomeAndClimate[]...arrays)
	{
		int size = 0;
		for (BiomeAndClimate[] arr : arrays) {
			size += arr.length;
		}
		BiomeAndClimate[] big_arr = new BiomeAndClimate[size];
		
		int j = 0;
		for (BiomeAndClimate[] arr : arrays) {
			for (int i = 0; i < arr.length; i++, j++) {
				big_arr[j] = arr[i];
			}
		}
		
		return big_arr;
	}

	WorldChunkManagerCRWG wcm;

	boolean[] a_beach;

	/* a[temperature][rainfall][selector] */
	short[][][] lowland_biomes;

	public GenLayerBiomes(long seed, WorldChunkManagerCRWG wcm)
	{
		super(seed);
		this.wcm = wcm;

		/* Shorthand names to make the giant array look a bit nicer */
		BiomeGenBase Mesa = BiomeGenBase.mesa;
		BiomeGenBase Dsrt = BiomeGenBase.desert;
		
		BiomeGenBase PlNm = BiomeGenBase.plains;
		BiomeGenBase PlSf = MutatedBiomes.Sunflower_Plains;
		BiomeGenBase PlDr = ModBiomes.dry_plains;
		BiomeGenBase PDrC = ModBiomes.dry_plains_cold;
		BiomeGenBase PlIc = BiomeGenBase.icePlains;
		
		BiomeGenBase Svnh = BiomeGenBase.savanna;
		
		BiomeGenBase Jngl = BiomeGenBase.jungle;
		
		BiomeGenBase IcSk = MutatedBiomes.Ice_Spikes;
		
		BiomeGenBase FrOk = BiomeGenBase.forest;
		BiomeGenBase FrBh = BiomeGenBase.birchForest;
		
		BiomeGenBase FrDk = BiomeGenBase.roofedForest;
		
		BiomeGenBase TgNm = BiomeGenBase.taiga;
		BiomeGenBase TgMg = BiomeGenBase.megaTaiga;
		BiomeGenBase TgCd = BiomeGenBase.coldTaiga;
		
/* @formatter:off */
		
		BiomeAndClimate[] lowlands_1 = GenLayerBiomes.convert15x10Array(new BiomeGenBase[] {
		/*                                        T E M P E R A T U R E                                  */
		/*      -0.9  -0.7  -0.5  -0.3  -0.1  +0.1  +0.3  +0.5  +0.7  +0.9  +1.1  +1.3  +1.5  +1.7  +1.9 */
			PlIc, PlIc, PlIc, PlIc, PlIc, PDrC, PlDr, PlDr, PlDr, PlDr, PlDr, Dsrt, Dsrt, Dsrt, Mesa, /* 0.05   */
			PlIc, PlIc, PlIc, PlIc, PlIc, PDrC, PlDr, PlDr, PlDr, PlDr, PlDr, PlDr, Dsrt, Dsrt, Dsrt, /* 0.15 R */
			PlIc, PlIc, PlIc, PlIc, PlIc, PDrC, PlDr, PlDr, PlDr, PlDr, PlDr, Svnh, Svnh, Svnh, Dsrt, /* 0.25 A */
			IcSk, PlIc, PlIc, PlIc, PlIc, PDrC, PlNm, PlNm, PlNm, PlNm, PlNm, Svnh, Svnh, Svnh, Svnh, /* 0.35 I */
			IcSk, PlIc, PlIc, PlIc, PlIc, PDrC, PlNm, PlNm, PlNm, PlNm, PlNm, PlNm, Svnh, Svnh, Svnh, /* 0.45 N */
			IcSk, PlIc, TgCd, TgCd, TgCd, TgCd, TgNm, PlNm, PlNm, PlNm, FrOk, FrDk, FrDk, FrDk, FrDk, /* 0.55 F */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgCd, TgNm, FrOk, FrOk, FrOk, FrOk, FrDk, FrDk, FrDk, FrDk, /* 0.65 A */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgCd, TgNm, FrOk, FrOk, FrOk, FrOk, FrDk, FrDk, Jngl, Jngl, /* 0.75 L */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgMg, TgMg, FrOk, FrOk, Jngl, Jngl, Jngl, Jngl, Jngl, Jngl, /* 0.85 L */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgMg, TgMg, FrOk, Jngl, Jngl, Jngl, Jngl, Jngl, Jngl, Jngl  /* 0.95   */
		});
		
		BiomeAndClimate[] lowlands_2 = GenLayerBiomes.convert15x10Array(new BiomeGenBase[] {
		/*                                        T E M P E R A T U R E                                  */
		/*      -0.9  -0.7  -0.5  -0.3  -0.1  +0.1  +0.3  +0.5  +0.7  +0.9  +1.1  +1.3  +1.5  +1.7  +1.9 */
			PlIc, PlIc, PlIc, PlIc, PlIc, PDrC, PlDr, PlDr, PlDr, PlDr, PlDr, Dsrt, Mesa, Mesa, Mesa, /* 0.05   */
			PlIc, PlIc, PlIc, PlIc, PlIc, PDrC, PlDr, PlDr, PlDr, PlDr, PlDr, PlDr, Dsrt, Dsrt, Mesa, /* 0.15 R */
			PlIc, PlIc, PlIc, PlIc, PlIc, PDrC, PlDr, PlDr, PlDr, PlDr, PlDr, Svnh, Svnh, Svnh, Mesa, /* 0.25 A */
			IcSk, PlIc, PlIc, PlIc, PlIc, PDrC, PlSf, PlSf, PlSf, PlSf, PlSf, Svnh, Svnh, Svnh, Svnh, /* 0.35 I */
			IcSk, PlIc, PlIc, PlIc, PlIc, PDrC, PlSf, PlSf, PlSf, PlSf, PlSf, PlSf, Svnh, Svnh, Svnh, /* 0.45 N */
			IcSk, PlIc, TgCd, TgCd, TgCd, TgCd, TgNm, PlSf, PlSf, PlSf, FrBh, FrDk, FrDk, FrDk, FrDk, /* 0.55 F */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgCd, TgNm, FrBh, FrBh, FrBh, FrBh, FrDk, FrDk, FrDk, FrDk, /* 0.65 A */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgCd, TgNm, FrBh, FrBh, FrBh, FrBh, FrDk, FrDk, Jngl, Jngl, /* 0.75 L */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgMg, TgMg, FrBh, FrBh, Jngl, Jngl, Jngl, Jngl, Jngl, Jngl, /* 0.85 L */
			IcSk, IcSk, TgCd, TgCd, TgCd, TgMg, TgMg, FrBh, Jngl, Jngl, Jngl, Jngl, Jngl, Jngl, Jngl  /* 0.95   */
		});

/* @formatter:on */

		this.lowland_biomes = GenLayerBiomes.createBiomeLUT(GenLayerBiomes.combineArrays(lowlands_1, lowlands_2));
	}

	@Override
	public int[] getInts(int ax, int az, int aw, int ah)
	{
		int size = aw * ah;
		int[] array = IntCache.getIntCache(size);

		this.initChunkSeed(ax, az);

		int ax_big = ax - 16;
		int az_big = az - 16;
		int aw_big = aw + 32;
		int ah_big = ah + 32;
		int size_big = aw_big * ah_big;

		double[] na_temperature = this.wcm.nl_temperature.generate(ax, az, aw, ah, size);
		double[] na_rainfall = this.wcm.nl_rainfall.generate(ax, az, aw, ah, size);

		double[] na_continentalness_big = this.wcm.nl_continentalness.generate(ax_big, az_big, aw_big, ah_big, size_big);

		double[] na_height = this.wcm.nl_height.generate(ax, az, aw, ah, size);

		/* Generated by the call to generate the height map */
		double[] na_continentalness = this.wcm.nl_continentalness.saved;
		double[] na_hill_sel = this.wcm.nl_hill_selector.saved;

		if (this.a_beach == null || this.a_beach.length < size) {
			this.a_beach = new boolean[size];
		}
		for (int i = 0; i < size; i++) {
			this.a_beach[i] = false;
		}

		for (int x = 0; x < aw; x++) {
			for (int z = 0; z < ah; z++) {
				int i = z + (x * ah);
				int i_big = (z + 16) + ((x + 16) * ah_big);
				double n0 = na_continentalness_big[i_big];

				if (Math.abs(n0) < 0.02 && na_height[i] < 0.0234375) {
					this.a_beach[i] = true;
					continue;
				}

				for (int c = 0; c < CircleR16.arr_x.length; c++) {
					int x1 = x + CircleR16.arr_x[c];
					int z1 = z + CircleR16.arr_y[c];
					int i_big_1 = (z1 + 16) + ((x1 + 16) * ah_big);
					double n1 = na_continentalness_big[i_big_1];
					if (n0 * n1 < 0.0) {
						this.a_beach[i] = true;
						break;
					}
				}
			}
		}

		for (int xz = 0; xz < size; xz++) {
			double h = na_height[xz];

			double tf = na_temperature[xz] * 3.5f - 1.0f;
			tf = Math.min(Math.max(tf, -1.99f), 1.99f);

			int t = (int) (tf * 50.0f) + 100;
			int r = (int) (na_rainfall[xz] * 50.0f) + 100;

			if (this.a_beach[xz]) {
				array[xz] = BiomeGenBase.beach.biomeID;
				continue;
			}

			if (na_continentalness[xz] < 0.0 && h < 0.0) {
				array[xz] = BiomeGenBase.ocean.biomeID;
			} else {
				double ht = na_hill_sel[xz];

				short biome_id = this.lowland_biomes[t][r][0];

				if (ht > 0.65) {
					array[xz] = biome_id;
				} else if (ht > 0.3) {
					array[xz] = biome_id;
				} else {
					array[xz] = biome_id;
				}
			}
		}

		return array;
	}
}

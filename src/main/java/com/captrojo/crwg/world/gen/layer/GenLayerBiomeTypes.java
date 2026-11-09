package com.captrojo.crwg.world.gen.layer;

import com.captrojo.crwg.world.WorldChunkManagerCRWG;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.gen.noise.NoisePerlinFractal;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeTypes extends GenLayer
{
	private static final BiomeTypeCRWG[][] BIOME_TYPE_MAP;

	static {
		/* BASIC */

		BiomeTypeCRWG Mesa = BiomeTypeCRWG.MESA;

		BiomeTypeCRWG Dsrt = BiomeTypeCRWG.DESERT;
		BiomeTypeCRWG Svnh = BiomeTypeCRWG.SAVANNA;
		BiomeTypeCRWG Scrb = BiomeTypeCRWG.SCRUBLAND;
		BiomeTypeCRWG TrFr = BiomeTypeCRWG.TROPICAL_FOREST;
		BiomeTypeCRWG RnFr = BiomeTypeCRWG.RAINFOREST;

		BiomeTypeCRWG ClDs = BiomeTypeCRWG.COOLER_DESERT;
		BiomeTypeCRWG DrGr = BiomeTypeCRWG.DRY_GRASSLAND;
		BiomeTypeCRWG GrsL = BiomeTypeCRWG.GRASSLAND;
		BiomeTypeCRWG Chpl = BiomeTypeCRWG.CHAPARRAL;
		BiomeTypeCRWG DcFr = BiomeTypeCRWG.DECIDUOUS_FOREST;
		BiomeTypeCRWG DDFr = BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST;

		BiomeTypeCRWG CdGs = BiomeTypeCRWG.COLD_GRASSLAND;
		BiomeTypeCRWG BrFr = BiomeTypeCRWG.BOREAL_FOREST;
		BiomeTypeCRWG DBFr = BiomeTypeCRWG.DENSE_BOREAL_FOREST;

		BiomeTypeCRWG SnBr = BiomeTypeCRWG.SNOWY_BOREAL_FOREST;
		BiomeTypeCRWG SnDB = BiomeTypeCRWG.SNOWY_DENSE_BOREAL_FOREST;

		BiomeTypeCRWG FzWs = BiomeTypeCRWG.FROZEN_WASTELAND;
		BiomeTypeCRWG Tndr = BiomeTypeCRWG.TUNDRA;

		/* MAGICAL */

		BiomeTypeCRWG Lnyu = BiomeTypeCRWG.LANAYRU;
		BiomeTypeCRWG CRFr = BiomeTypeCRWG.COLORFUL_RAINFOREST;

		BiomeTypeCRWG VbFr = BiomeTypeCRWG.VIBRANT_FOREST;

		BiomeTypeCRWG CBFr = BiomeTypeCRWG.COLORFUL_BOREAL_FOREST;
		BiomeTypeCRWG CDBF = BiomeTypeCRWG.COLORFUL_DENSE_BOREAL_FOREST;
		BiomeTypeCRWG CSBF = BiomeTypeCRWG.COLORFUL_SNOWY_BOREAL_FOREST;
		BiomeTypeCRWG CSDB = BiomeTypeCRWG.COLORFUL_SNOWY_DENSE_BOREAL_FOREST;

		BiomeTypeCRWG SpTn = BiomeTypeCRWG.SPECIAL_TUNDRA;

		/* @formatter:off */

		final BiomeTypeCRWG[] Basic = new BiomeTypeCRWG[] {
		/*                              R A I N F A L L                    */
		/*      0.05  0.15  0.25  0.35  0.45  0.55  0.65  0.75  0.85  0.95 */
			FzWs, FzWs, FzWs, FzWs, FzWs, Tndr, Tndr, Tndr, SpTn, SpTn, /* -0.9   */
			FzWs, FzWs, FzWs, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, /* -0.7   */
			FzWs, Tndr, Tndr, Tndr, Tndr, Tndr, SnBr, SnBr, SnBr, SnBr, /* -0.5 T */
			Tndr, Tndr, Tndr, Tndr, CdGs, SnBr, SnBr, SnBr, SnBr, SnDB, /* -0.3 E */
			Tndr, Tndr, Tndr, CdGs, CdGs, SnBr, SnBr, SnBr, SnDB, SnDB, /* -0.1 M */
			Tndr, Tndr, CdGs, CdGs, CdGs, BrFr, BrFr, BrFr, DBFr, DBFr, /* +0.1 P */
			ClDs, DrGr, GrsL, GrsL, Chpl, BrFr, BrFr, BrFr, DBFr, DBFr, /* +0.3 E */
			ClDs, DrGr, GrsL, GrsL, Chpl, DcFr, DcFr, DcFr, DDFr, DDFr, /* +0.5 R */
			ClDs, DrGr, GrsL, GrsL, Chpl, DcFr, DcFr, DcFr, DDFr, DDFr, /* +0.7 A */
			ClDs, ClDs, Scrb, GrsL, Chpl, Chpl, DcFr, DcFr, DDFr, DDFr, /* +0.9 T */
			Dsrt, ClDs, Svnh, Scrb, Scrb, TrFr, TrFr, DcFr, DDFr, RnFr, /* +1.1 U */
			Dsrt, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, RnFr, RnFr, /* +1.3 R */
			Dsrt, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, TrFr, RnFr, RnFr, /* +1.5 E */
			Mesa, Dsrt, Svnh, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, RnFr, /* +1.7   */
			Mesa, Dsrt, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, RnFr, /* +1.9   */
		};

		final BiomeTypeCRWG[] SMagc = new BiomeTypeCRWG[] {
		/*                              R A I N F A L L                    */
		/*      0.05  0.15  0.25  0.35  0.45  0.55  0.65  0.75  0.85  0.95 */
			FzWs, FzWs, FzWs, FzWs, FzWs, Tndr, SpTn, SpTn, SpTn, SpTn, /* -0.9   */
			FzWs, FzWs, FzWs, Tndr, Tndr, Tndr, Tndr, Tndr, SpTn, SpTn, /* -0.7   */
			FzWs, Tndr, Tndr, Tndr, Tndr, Tndr, SnBr, SnBr, SnBr, SnBr, /* -0.5 T */
			Tndr, Tndr, Tndr, Tndr, CdGs, SnBr, SnBr, SnBr, SnBr, SnDB, /* -0.3 E */
			Tndr, Tndr, Tndr, CdGs, CdGs, SnBr, SnBr, SnBr, SnDB, SnDB, /* -0.1 M */
			Tndr, Tndr, CdGs, CdGs, CdGs, BrFr, BrFr, BrFr, DBFr, DBFr, /* +0.1 P */
			ClDs, DrGr, GrsL, GrsL, Chpl, BrFr, BrFr, BrFr, DBFr, DBFr, /* +0.3 E */
			ClDs, DrGr, GrsL, GrsL, Chpl, DcFr, DcFr, DcFr, DDFr, DDFr, /* +0.5 R */
			ClDs, DrGr, GrsL, GrsL, Chpl, VbFr, VbFr, VbFr, DDFr, DDFr, /* +0.7 A */
			ClDs, ClDs, Scrb, GrsL, Chpl, Chpl, VbFr, VbFr, DDFr, DDFr, /* +0.9 T */
			Dsrt, ClDs, Svnh, Scrb, Scrb, TrFr, TrFr, VbFr, DDFr, RnFr, /* +1.1 U */
			Mesa, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, RnFr, RnFr, /* +1.3 R */
			Mesa, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, TrFr, RnFr, RnFr, /* +1.5 E */
			Mesa, Dsrt, Svnh, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, CRFr, /* +1.7   */
			Mesa, Mesa, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, CRFr, /* +1.9   */
		};

		final BiomeTypeCRWG[] Magic = new BiomeTypeCRWG[] {
		/*                              R A I N F A L L                    */
		/*      0.05  0.15  0.25  0.35  0.45  0.55  0.65  0.75  0.85  0.95 */
			FzWs, FzWs, FzWs, FzWs, FzWs, SpTn, SpTn, SpTn, SpTn, SpTn, /* -0.9   */
			FzWs, FzWs, FzWs, Tndr, Tndr, Tndr, Tndr, SpTn, SpTn, SpTn, /* -0.7   */
			FzWs, Tndr, Tndr, Tndr, Tndr, Tndr, CSBF, CSBF, CSBF, CSBF, /* -0.5 T */
			Tndr, Tndr, Tndr, Tndr, CdGs, CSBF, CSBF, CSBF, CSBF, CSDB, /* -0.3 E */
			Tndr, Tndr, Tndr, CdGs, CdGs, CSBF, CSBF, CSBF, CSDB, CSDB, /* -0.1 M */
			Tndr, Tndr, CdGs, CdGs, CdGs, CBFr, CBFr, CBFr, CDBF, CDBF, /* +0.1 P */
			ClDs, DrGr, GrsL, GrsL, Chpl, CBFr, CBFr, CBFr, CDBF, CDBF, /* +0.3 E */
			ClDs, DrGr, GrsL, GrsL, Chpl, DcFr, DcFr, DcFr, DDFr, DDFr, /* +0.5 R */
			ClDs, DrGr, GrsL, GrsL, Chpl, VbFr, VbFr, VbFr, DDFr, DDFr, /* +0.7 A */
			ClDs, ClDs, Scrb, GrsL, Chpl, Chpl, VbFr, VbFr, DDFr, DDFr, /* +0.9 T */
			Dsrt, ClDs, Svnh, Scrb, Scrb, Lnyu, Lnyu, VbFr, DDFr, CRFr, /* +1.1 U */
			Mesa, Dsrt, Svnh, Svnh, Scrb, Scrb, Lnyu, CRFr, CRFr, CRFr, /* +1.3 R */
			Mesa, Mesa, Svnh, Svnh, Scrb, Scrb, Lnyu, CRFr, CRFr, CRFr, /* +1.5 E */
			Mesa, Mesa, Svnh, Svnh, Svnh, Scrb, Scrb, CRFr, CRFr, CRFr, /* +1.7   */
			Mesa, Mesa, Mesa, Svnh, Svnh, Scrb, Scrb, CRFr, CRFr, CRFr, /* +1.9   */
		};

		BIOME_TYPE_MAP = new BiomeTypeCRWG[][] {
		/*                             A W E S O M E N E S S                         */
		/*       0.05   0.15   0.25   0.35   0.45   0.55   0.65   0.75   0.85   0.95 */
			Basic, Basic, Basic, Basic, Basic, Basic, SMagc, SMagc, Magic, Magic
		};

		/* @formatter:on */
	}

	static int getType(double temperature, double rainfall, double awesomeness)
	{
		double td = temperature * 15.0;
		int t = (int) td;
		double rd = rainfall * 10.0;
		int r = (int) rd;
		double ad = awesomeness * 10.0;
		int a = (int) ad;

		int idx = r + (t * 10);
		return BIOME_TYPE_MAP[a][idx].ordinal();
	}

	final NoisePerlinFractal temp_noise;
	final NoisePerlinFractal rain_noise;
	final NoisePerlinFractal awesome_noise;

	double[] temperature;
	double[] rainfall;
	double[] awesomeness;

	public GenLayerBiomeTypes(
		long base_seed,
		NoisePerlinFractal temp_noise,
		NoisePerlinFractal rain_noise,
		NoisePerlinFractal awesome_noise
	)
	{
		super(base_seed);
		this.temp_noise = temp_noise;
		this.rain_noise = rain_noise;
		this.awesome_noise = awesome_noise;
	}

	private double fade(double t)
	{
		return ((6.0 * t - 15.0) * t + 10.0) * t * t * t;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		final double temp_scale = WorldChunkManagerCRWG.temp_scale_64;
		final double rain_scale = WorldChunkManagerCRWG.rain_scale_64;
		final double awesome_scale = WorldChunkManagerCRWG.awesome_scale_64;

		this.temperature = this.temp_noise.calcArray(this.temperature, ax, ay, aw, ah, temp_scale, temp_scale);
		this.rainfall = this.rain_noise.calcArray(this.rainfall, ax, ay, aw, ah, rain_scale, rain_scale);
		this.awesomeness = this.awesome_noise.calcArray(this.awesomeness, ax, ay, aw, ah, awesome_scale, awesome_scale);

		int[] array = IntCache.getIntCache(aw * ah);

		for (int x = 0; x < aw; x++) {
			for (int y = 0; y < ah; y++) {
				int idx = x + (y * aw);
				double t = this.temperature[idx];
				double r = this.rainfall[idx];
				double a = this.awesomeness[idx];

				t = this.fade(t);
				r = this.fade(r);
				a = this.fade(a);

				array[idx] = GenLayerBiomeTypes.getType(t, r, a);
			}
		}

		return array;
	}
}

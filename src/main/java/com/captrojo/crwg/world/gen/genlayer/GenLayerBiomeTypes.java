package com.captrojo.crwg.world.gen.genlayer;

import com.captrojo.crwg.world.WorldChunkManagerCRWG;
import com.captrojo.crwg.world.biome.BiomeTypeCRWG;
import com.captrojo.crwg.world.gen.noise.NoisePerlinFractal;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeTypes extends GenLayer
{
	private static final BiomeTypeCRWG[] type_map;
	
	static
	{
		/* @formatter off */
		
		BiomeTypeCRWG Dsrt = BiomeTypeCRWG.DESERT;
		BiomeTypeCRWG Svnh = BiomeTypeCRWG.SAVANNA;
		BiomeTypeCRWG Scrb = BiomeTypeCRWG.SCRUBLAND;
		BiomeTypeCRWG TrFr = BiomeTypeCRWG.TROPICAL_FOREST;
		BiomeTypeCRWG RnFr = BiomeTypeCRWG.RAINFOREST;
		
		BiomeTypeCRWG ClDs = BiomeTypeCRWG.COOLER_DESERT;
		BiomeTypeCRWG GrsL = BiomeTypeCRWG.GRASSLAND;
		BiomeTypeCRWG Chpl = BiomeTypeCRWG.CHAPARRAL;
		BiomeTypeCRWG DcFr = BiomeTypeCRWG.DECIDUOUS_FOREST;
		BiomeTypeCRWG DDFr = BiomeTypeCRWG.DENSE_DECIDUOUS_FOREST;
		
		BiomeTypeCRWG CdGs = BiomeTypeCRWG.COLD_GRASSLAND;
		BiomeTypeCRWG BrFr = BiomeTypeCRWG.BOREAL_FOREST;
		BiomeTypeCRWG DBFr = BiomeTypeCRWG.DENSE_BOREAL_FOREST;
		
		BiomeTypeCRWG Tndr = BiomeTypeCRWG.TUNDRA;
		
		type_map = new BiomeTypeCRWG[] {
		/*                             R A I N F A L L                    */
		/*     0.05  0.15  0.25  0.35  0.45  0.55  0.65  0.75  0.85  0.95 */
		       Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, /* -0.9   */
		       Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, /* -0.7   */
		       Tndr, Tndr, Tndr, Tndr, Tndr, Tndr, BrFr, BrFr, BrFr, BrFr, /* -0.5 T */
		       Tndr, Tndr, Tndr, Tndr, CdGs, BrFr, BrFr, BrFr, BrFr, DBFr, /* -0.3 E */
		       Tndr, Tndr, Tndr, CdGs, CdGs, BrFr, BrFr, BrFr, DBFr, DBFr, /* -0.1 M */
		       Tndr, Tndr, CdGs, CdGs, CdGs, BrFr, BrFr, BrFr, DBFr, DBFr, /* +0.1 P */
		       ClDs, ClDs, GrsL, GrsL, Chpl, BrFr, BrFr, BrFr, DBFr, DBFr, /* +0.3 E */
		       ClDs, ClDs, GrsL, GrsL, Chpl, DcFr, DcFr, DcFr, DDFr, DDFr, /* +0.5 R */
		       ClDs, GrsL, GrsL, GrsL, Chpl, DcFr, DcFr, DcFr, DDFr, DDFr, /* +0.7 A */
		       ClDs, GrsL, Scrb, GrsL, Chpl, Chpl, DcFr, DcFr, DDFr, DDFr, /* +0.9 T */
		       Dsrt, ClDs, Svnh, Scrb, Scrb, TrFr, TrFr, DcFr, DDFr, RnFr, /* +1.1 U */
		       Dsrt, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, RnFr, RnFr, /* +1.3 R */
		       Dsrt, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, TrFr, RnFr, RnFr, /* +1.5 E */
		       Dsrt, Dsrt, Svnh, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, RnFr, /* +1.7   */
		       Dsrt, Dsrt, Dsrt, Svnh, Svnh, Scrb, Scrb, TrFr, RnFr, RnFr, /* +1.9   */
		};
		
		/* @formatter on */
	}
	
	static int getType(double temperature, double rainfall)
	{
		double td = (temperature) * 15.0;
		int t = (int) td;
		double rd = (rainfall) * 10.0;
		int r = (int) rd;
		
		int idx = r + (t * 10);
		return type_map[idx].ordinal();
	}
	
	final NoisePerlinFractal temp_noise;
	final NoisePerlinFractal rain_noise;
	
	double[] temperature;
	double[] rainfall;
	
	public GenLayerBiomeTypes(long base_seed, NoisePerlinFractal temp_noise, NoisePerlinFractal rain_noise)
	{
		super(base_seed);
		this.temp_noise = temp_noise;
		this.rain_noise = rain_noise;
	}

	private double fade(double t)
	{
		return ((6 * t - 15) * t + 10) * t * t * t;
	}
	
	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		final double temp_scale = WorldChunkManagerCRWG.temp_scale_pz;
		final double rain_scale = WorldChunkManagerCRWG.rain_scale_pz;
		
		this.temperature = this.temp_noise.calcArray(this.temperature, ax, ay, aw, ah, temp_scale, temp_scale);
		this.rainfall = this.rain_noise.calcArray(this.rainfall, ax, ay, aw, ah, rain_scale, rain_scale);
		
		int[] array = IntCache.getIntCache(aw * ah);
		
		for (int x = 0; x < aw; x++) {
			for (int y = 0; y < ah; y++) {
				int idx = x + (y * aw);
				double t = this.temperature[idx];
				double r = this.rainfall[idx];
				
				t = this.fade(t);
				r = this.fade(r);
				
				array[idx] = GenLayerBiomeTypes.getType(t, r);
			}
		}
		
		return array;
	}
}

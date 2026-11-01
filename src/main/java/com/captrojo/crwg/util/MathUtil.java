package com.captrojo.crwg.util;

public abstract class MathUtil
{
	public static int averageColors(int color_1, int color_2, float weight_of_color_1)
	{
		float weight_of_color_2 = 1.0F - weight_of_color_1;
		
		int r1 = (color_1 >> 16) & 0xff;
		int g1 = (color_1 >> 8) & 0xff;
		int b1 = (color_1) & 0xff;
		
		int r2 = (color_2 >> 16) & 0xff;
		int g2 = (color_2 >> 8) & 0xff;
		int b2 = (color_2) & 0xff;
		
		int rf = (int) (((float) r1 * weight_of_color_1) + ((float) r2 * weight_of_color_2));
		int gf = (int) (((float) g1 * weight_of_color_1) + ((float) g2 * weight_of_color_2));
		int bf = (int) (((float) b1 * weight_of_color_1) + ((float) b2 * weight_of_color_2));
		
		return (rf << 16) | (gf << 8) | (bf);
	}
}

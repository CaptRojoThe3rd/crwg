package com.captrojo.crwg.block.data;

import com.captrojo.crwg.util.MathUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;

public class Colorizer
{
	public static final Colorizer NONE = Colorizer.fixed(0xffffff);
	public static final Colorizer BIOME_FOLIAGE = new Colorizer(ColorType.BIOME_FOLIAGE);
	public static final Colorizer BIOME_GRASS = new Colorizer(ColorType.BIOME_GRASS);

	public static Colorizer biomeFoliageAvg(int color, float biome_color_weight)
	{
		Colorizer c = new Colorizer(ColorType.BIOME_FOLIAGE_AVERAGED);
		c.fixed_color = color;
		c.biome_color_weight = biome_color_weight;
		return c;
	}

	public static Colorizer fixed(int color)
	{
		Colorizer c = new Colorizer(ColorType.FIXED);
		c.fixed_color = color;
		return c;
	}

	final ColorType type;
	boolean for_item = true;

	int fixed_color;
	float biome_color_weight;

	private Colorizer(ColorType type)
	{
		this.type = type;
	}

	public Colorizer notForItem()
	{
		this.for_item = false;
		return this;
	}

	@SideOnly(Side.CLIENT)
	private int getBiomeFoliageColor(IBlockAccess world, int x, int y, int z)
	{
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int za = -1; za <= 1; za++) {
			for (int xa = -1; xa <= 1; xa++) {
				int color = world.getBiomeGenForCoords(x + xa, z + za).getBiomeFoliageColor(x + xa, y, z + za);
				red += (color & 0xff0000) >> 16;
				green += (color & 0x00ff00) >> 8;
				blue += color & 0x0000ff;
			}
		}

		red /= 9;
		green /= 9;
		blue /= 9;

		return ((red & 0xff) << 16) | ((green & 0xff) << 8) | (blue & 0xff);
	}

	@SideOnly(Side.CLIENT)
	private int getBiomeGrassColor(IBlockAccess world, int x, int y, int z)
	{
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int za = -1; za <= 1; za++) {
			for (int xa = -1; xa <= 1; xa++) {
				int color = world.getBiomeGenForCoords(x + xa, z + za).getBiomeGrassColor(x + xa, y, z + za);
				red += (color & 0xff0000) >> 16;
				green += (color & 0x00ff00) >> 8;
				blue += color & 0x0000ff;
			}
		}

		red /= 9;
		green /= 9;
		blue /= 9;

		return ((red & 0xff) << 16) | ((green & 0xff) << 8) | (blue & 0xff);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor()
	{
		if (!this.for_item) {
			return 0xffffff;
		}

		switch (this.type) {
		default:
		case BIOME_FOLIAGE:
			return ColorizerFoliage.getFoliageColorBasic();
		case BIOME_FOLIAGE_AVERAGED:
			int biome_color = ColorizerFoliage.getFoliageColorBasic();
			return MathUtil.averageColors(biome_color, this.fixed_color, this.biome_color_weight);
		case BIOME_GRASS:
			return ColorizerGrass.getGrassColor(0.5, 1.0);
		case FIXED:
			return this.fixed_color;
		}
	}

	@SideOnly(Side.CLIENT)
	public int colorMultipler(IBlockAccess world, int x, int y, int z)
	{
		switch (this.type) {
		default:
		case BIOME_FOLIAGE:
			return this.getBiomeFoliageColor(world, x, y, z);
		case BIOME_FOLIAGE_AVERAGED:
			int biome_color = this.getBiomeFoliageColor(world, x, y, z);
			return MathUtil.averageColors(biome_color, this.fixed_color, this.biome_color_weight);
		case BIOME_GRASS:
			return this.getBiomeGrassColor(world, x, y, z);
		case FIXED:
			return this.fixed_color;
		}
	}
}

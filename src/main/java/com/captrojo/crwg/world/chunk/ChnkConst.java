package com.captrojo.crwg.world.chunk;

/**
 * Because SpongePowered wouldn't let me but public static final ints inside a mixin...
 */
public abstract class ChnkConst
{
	/* ChunkMixin.crwg$surf_data_map_a */
	
	public static final int SHIFT_BIOMETYPE = 6;
	public static final int SHIFT_SOILTYPE = 12;
	public static final int SHIFT_SUBSPECIES = 16;

	public static final int MASK_LANDTYPE = 0x3f;
	public static final int MASK_BIOMETYPE = 0x3f << SHIFT_BIOMETYPE;
	public static final int MASK_SOILTYPE = 0xf << SHIFT_SOILTYPE;
	public static final int MASK_SPECIES = 0xff << SHIFT_SUBSPECIES;
}

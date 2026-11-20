package com.captrojo.crwg.util;

public abstract class YConst
{
	public static int MIN_Y_LVL = 0;
	public static int MAX_Y_LVL = 511;
	public static int SEA_LVL_Y = 127;
	
	public static int MIN_H_LVL = 0;
	public static int MAX_H_LVL = 511;
	public static int SEA_LVL_H = 127;
	
	public static int BOTTOM_H_LVL = 0;
	public static int MIN_CAVE_H_LVL = 0;
	public static int MAX_CAVE_H_LVL = 320;
	public static int EXT_CAVE_H = 0;
	
	public static int CHUNK_HEIGHT = 512;
	public static int SUBCHUNK_COUNT = 32;
	public static int MIN_CHUNK_Y = 0;
	public static int MAX_CHUNK_Y = 31;

	public static final int NEG_CHUNK_Y_OFFS = 4;
	public static final int NEG_Y_OFFS = 64;
}

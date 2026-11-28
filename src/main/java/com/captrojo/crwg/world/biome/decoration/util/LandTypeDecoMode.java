package com.captrojo.crwg.world.biome.decoration.util;

import java.util.function.Supplier;

import com.captrojo.crwg.world.biome.data.LandType;

public enum LandTypeDecoMode
{
	SINGLE_LAND_TYPE(1, () -> {
		return new int[LandType.values().length];
	}),
	LOW_HILL_HIGH(3, () -> {
		int[] arr = new int[LandType.values().length];
		for (LandType lt : LandType.values()) {
			switch (lt) {
			default:
			case LOWLANDS:
				arr[lt.ordinal()] = 0;
				break;
			case HILLS:
			case ISLAND_HILLS:
			case MUSHROOM_ISLAND_HILLS:
				arr[lt.ordinal()] = 1;
				break;
			case HIGHLANDS:
			case FOOTHILLS:
			case EXTREME_HILLS:
				arr[lt.ordinal()] = 2;
				break;
			}
		}
		return arr;
	});

	public final int arr_size;
	public final int[] lt_to_idx;

	LandTypeDecoMode(final int arr_size, Supplier<int[]> lt_to_idx_sup)
	{
		this.arr_size = arr_size;
		this.lt_to_idx = lt_to_idx_sup.get();
	}
}

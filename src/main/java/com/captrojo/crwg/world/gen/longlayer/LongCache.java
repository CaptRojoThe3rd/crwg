package com.captrojo.crwg.world.gen.longlayer;

import java.util.ArrayList;

/* It's the IntCache, but for longs! */
public class LongCache
{
	private static ArrayList<long[]> free_small_arrays = new ArrayList<long[]>();
	private static ArrayList<long[]> used_small_arrays = new ArrayList<long[]>();

	private static ArrayList<long[]> free_large_arrays = new ArrayList<long[]>();
	private static ArrayList<long[]> used_large_arrays = new ArrayList<long[]>();
	private static int large_array_size = 0;

	public static synchronized long[] getLongCache(int size)
	{
		if (size <= 256) {
			if (free_small_arrays.isEmpty()) {
				long[] arr = new long[256];
				used_small_arrays.add(arr);
				return arr;
			}
			long[] arr = free_small_arrays.remove(free_small_arrays.size() - 1);
			used_small_arrays.add(arr);
			return arr;
		}

		if (large_array_size < size) {
			large_array_size = size;
			free_large_arrays.clear();
			used_large_arrays.clear();
		}

		if (free_large_arrays.isEmpty()) {
			long[] arr = new long[large_array_size];
			used_large_arrays.add(arr);
			return arr;
		}
		long[] arr = free_large_arrays.remove(free_large_arrays.size() - 1);
		used_large_arrays.add(arr);
		return arr;
	}

	public static synchronized void resetLongCache()
	{
		if (!free_small_arrays.isEmpty()) {
			free_small_arrays.remove(free_small_arrays.size() - 1);
		}
		if (!free_large_arrays.isEmpty()) {
			free_large_arrays.remove(free_large_arrays.size() - 1);
		}
		free_small_arrays.addAll(used_small_arrays);
		free_large_arrays.addAll(used_large_arrays);
		used_small_arrays.clear();
		used_large_arrays.clear();
	}

	public static synchronized String getCacheSizes()
	{
		return "cache: " + free_large_arrays.size() + ", tcache: " + free_small_arrays.size() + ", allocated: " + used_large_arrays.size() + ", tallocated: " + used_small_arrays.size();
	}
}

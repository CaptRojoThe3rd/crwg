package com.captrojo.crwg.main;

public abstract class PerformanceInfo
{
	public static boolean available = false;
	
	public static long tick_time_start;
	public static long tick_time_end;
	public static long[] mspt_avg_arr = new long[20];
	public static int mspt_avg_arr_idx;
	public static long mspt_avg;
	public static long mspt_last;
	public static long mspt_worst;
	
	public static void resetInfo()
	{
		mspt_avg_arr = new long[20];
		mspt_worst = 0;
	}

	public static void onTickStart(long time_ms)
	{
		available = true;
		tick_time_start = time_ms;
	}

	public static void onTickEnd(long time_ms)
	{
		tick_time_end = time_ms;

		mspt_last = tick_time_end - tick_time_start;
		if (mspt_last > mspt_worst) {
			mspt_worst = mspt_last;
		}

		mspt_avg_arr[mspt_avg_arr_idx] = mspt_last;
		mspt_avg_arr_idx = (mspt_avg_arr_idx + 1) % 20;
		mspt_avg = 0;
		for (long l : mspt_avg_arr) {
			mspt_avg += l;
		}
		mspt_avg /= 20;
	}
}

package com.captrojo.crwg.config;

public abstract class ClientConfig
{
	public static boolean cmd_flight_speed = true;
	public static boolean cmd_reset_perf_info = true;

	public static void init()
	{
		ModConfig._category = "Commands";

		cmd_flight_speed = ModConfig.getBool("cmd_flight_speed", "/fs <flight speed>", cmd_flight_speed);
		cmd_reset_perf_info = ModConfig.getBool("cmd_reset_perf_info", "/resetperfinfo", cmd_reset_perf_info);
	}
}

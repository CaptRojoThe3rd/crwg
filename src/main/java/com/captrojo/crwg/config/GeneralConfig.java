package com.captrojo.crwg.config;

public abstract class GeneralConfig
{
	public static boolean enable_motd = true;
	public static boolean yeet_decorator_crash = true;

	public static void init()
	{
		ModConfig._category = "General";

		enable_motd = ModConfig.getBool("enable_motd", "Enable 'message of the day'", enable_motd);
		yeet_decorator_crash = ModConfig.getBool("yeet_decorator_crash", "Disable the \"Already decorating!!\" crash - only applies to CRWG worlds", yeet_decorator_crash);
	}
}

package com.captrojo.crwg.config;

public abstract class GeneralConfig
{
	public static boolean yeet_decorator_crash = true;

	public static void init()
	{
		ModConfig._category = "General";

		yeet_decorator_crash = ModConfig.getBool("yeet_decorator_crash", "Disable the \"Already decorating!!\" crash - only applies to CRWG worlds", yeet_decorator_crash);
	}
}

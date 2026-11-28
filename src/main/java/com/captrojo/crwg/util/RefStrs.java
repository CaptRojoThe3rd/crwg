package com.captrojo.crwg.util;

public abstract class RefStrs
{
	public static final String[] LETTERS = "abcdefghijklmnopqrstuvwxz".split("");

	public static boolean areStrsEqual(String a, String b)
	{
		if (a == null && b == null) {
			return true;
		}
		if (a != null && a.equals(b)) {
			return true;
		}
		return false;
	}
}

package com.captrojo.crwg.world.gen;

import java.util.ArrayList;
import java.util.List;

public abstract class CircleR16
{
	public static int[] arr_x;
	public static int[] arr_y;

	static {
		List<Pos> list = new ArrayList<>();

		for (int x = -16; x <= 16; x++) {
			for (int y = -16; y <= 16; y++) {
				if (Math.sqrt(x * x + y * y) > 16.0) {
					continue;
				}
				list.add(new Pos(x, y));
			}
		}

		list.sort(null);
		arr_x = new int[list.size()];
		arr_y = new int[list.size()];
		for (int j = 0; j < arr_x.length; j++) {
			arr_x[j] = list.get(j).x;
			arr_y[j] = list.get(j).y;
		}
	}

	private static class Pos implements Comparable<Pos>
	{
		private final int x, y;

		private Pos(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos that)
		{
			return (Math.abs(this.x) + Math.abs(this.y)) - (Math.abs(that.x) + Math.abs(that.y));
		}
	}
}

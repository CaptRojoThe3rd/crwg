package com.captrojo.crwg.world.gen.layer;

import com.captrojo.crwg.world.gen.data.GConst;

import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerForcedIsland extends GenLayer
{
	public GenLayerForcedIsland(GenLayer parent)
	{
		super(0L);
		this.parent = parent;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		int[] arr = this.parent.getInts(ax, ay, aw, ah);
		if (ax == 0 && ay == 0) {
			arr[0] = GConst.ISLAND;
		}
		return arr;
	}
}

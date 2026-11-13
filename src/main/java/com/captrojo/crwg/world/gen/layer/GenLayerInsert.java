package com.captrojo.crwg.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerInsert extends GenLayer
{
	private int[] array;
	
	public GenLayerInsert()
	{
		super(0L);
	}
	
	public void insert(int[] array)
	{
		this.array = array;
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		return this.array;
	}
}

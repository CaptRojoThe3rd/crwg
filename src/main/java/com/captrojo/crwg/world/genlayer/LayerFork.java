package com.captrojo.crwg.world.genlayer;

public abstract class LayerFork extends Layer
{
	public final Layer parent_1;
	public final Layer parent_2;

	public LayerFork(Layer parent_1, Layer parent_2)
	{
		this.parent_1 = parent_1;
		this.parent_2 = parent_2;
	}
}

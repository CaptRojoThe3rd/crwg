package com.captrojo.crwg.render.block;

import cpw.mods.fml.client.registry.RenderingRegistry;

public enum BlockRenderIDs
{
	DOUBLE_PLANT,
	FLOOR_PLANT,
	FLOOR_PLANT_2X2;
	
	public final int id;
	
	BlockRenderIDs()
	{
		this.id = RenderingRegistry.getNextAvailableRenderId();
	}
}

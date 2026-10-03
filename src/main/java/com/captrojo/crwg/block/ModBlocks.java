package com.captrojo.crwg.block;

public abstract class ModBlocks
{
	public static void initBlocks()
	{
		WoodBlocks.values();
	}
	
	public static void registerBlocks()
	{
		for (WoodBlocks type : WoodBlocks.values()) {
			type.registerBlocks();
		}
	}
}

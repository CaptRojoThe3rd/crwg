package com.captrojo.crwg.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ModTileEntities
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TESignCRWG.class, "crwg.sign");
	}
}

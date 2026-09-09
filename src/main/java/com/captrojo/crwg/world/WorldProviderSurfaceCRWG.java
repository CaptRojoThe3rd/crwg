package com.captrojo.crwg.world;

import net.minecraft.world.WorldProviderSurface;

public class WorldProviderSurfaceCRWG extends WorldProviderSurface
{
	@Override
	public boolean canCoordinateBeSpawn(int x, int z)
	{
		return true;
	}
}

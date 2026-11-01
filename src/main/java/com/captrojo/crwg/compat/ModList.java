package com.captrojo.crwg.compat;

import cpw.mods.fml.common.Loader;

public enum ModList
{
	NEI("NotEnoughItems");
	
	public final String id;
	
	private ModList(String id)
	{
		this.id = id;
	}
	
	public boolean isLoaded()
	{
		return Loader.isModLoaded(this.id);
	}
	
	public String getVersionString()
	{
		return Loader.instance().getIndexedModList().get(this.id).getVersion();
	}
}

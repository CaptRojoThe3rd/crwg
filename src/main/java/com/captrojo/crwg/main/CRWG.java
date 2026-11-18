/**
 * CaptRojo's World Gen Mod
 * Copyright (C) 2026 CaptRojoThe3rd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.captrojo.crwg.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.captrojo.crwg.GeneratedTags;
import com.captrojo.crwg.compat.ICRWGFuncs;
import com.captrojo.crwg.creativetab.TabBlocks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

@Mod(
	modid = CRWG.MOD_ID,
	name = CRWG.NAME,
	version = CRWG.VERSION,
	acceptableRemoteVersions = CRWG.VERSIONS_ACCEPTED,
	acceptedMinecraftVersions = "[1.7.10]"
)
public class CRWG
{
	public static final String MOD_ID = "crwg";
	public static final String NAME = "CaptRojo's World Gen";

//	public static final String VERSION = "v1.0.0.0";
//	public static final String VERSIONS_ACCEPTED = "v1.0.0.*";
//	public static final String VERSION_NAME = "v1.0.0.0 (Build #0001)";
//	public static final String VERSION = "X0001";
//	public static final String VERSIONS_ACCEPTED = "X0001";
//	public static final String VERSION_NAME = "Build #0001";
	public static final String VERSION = GeneratedTags.VERSION;
	public static final String VERSIONS_ACCEPTED = GeneratedTags.VERSION;
	public static final String VERSION_NAME = GeneratedTags.VERSION;

	/* Given to ChunkAPI */
	public static final String DATA_VERSION = "0.0";

	public static final Logger log = LogManager.getLogger(MOD_ID);

	public static ICRWGFuncs funcs;

	@SidedProxy(
		clientSide = "com.captrojo.crwg.main.ClientProxy",
		serverSide = "com.captrojo.crwg.main.CommonProxy"
	)
	public static CommonProxy proxy;

	public static String dir_config;

	public static CreativeTabs tab_blocks = new TabBlocks();

	public static String ident(String s)
	{
		if (s.contains(":")) {
			return s;
		}
		return MOD_ID + ":" + s;
	}

	public static ResourceLocation resource(String s)
	{
		return new ResourceLocation(ident(s));
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event);
	}
}

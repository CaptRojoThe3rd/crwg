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

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.compat.EHLFuncs;
import com.captrojo.crwg.compat.ICRWGFuncs;
import com.captrojo.crwg.creativetab.TabBlocks;
import com.captrojo.crwg.item.ModItems;
import com.captrojo.crwg.recipe.ModCraftingRecipes;
import com.captrojo.crwg.recipe.ModSmeltingRecipes;
import com.captrojo.crwg.tileentity.ModTileEntities;
import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.WorldEvents;
import com.captrojo.crwg.world.WorldTypeCRWG;
import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.chunk.ChunkDataMgr;
import com.falsepattern.chunk.api.DataRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

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

	public static final String VERSION = "0.0.0";
	public static final String VERSIONS_ACCEPTED = "0.0.0";

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
		funcs = new EHLFuncs();

		dir_config = event.getModConfigurationDirectory().getPath() + File.separator + "crwg";
		File f = new File(dir_config);
		if (!f.exists()) {
			f.mkdirs();
		}
		proxy.loadConfig();

		ModBlocks.initBlocks();
		ModBlocks.registerBlocks();
		ModItems.initItems();
		ModItems.registerItems();

		ModTileEntities.registerTileEntities();

		WorldEvents.instance = new WorldEvents();
		MinecraftForge.TERRAIN_GEN_BUS.register(WorldEvents.instance);

		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		BlockState.initPreMade();

		ModCraftingRecipes.registerRecipes();
		ModSmeltingRecipes.registerRecipes();

		ModBiomes.registerBiomes();
		ModBiomes.initBiomeTypes();

		WorldTypeCRWG.TYPE_CRWG = new WorldTypeCRWG();

		DataRegistry.registerDataManager(new ChunkDataMgr(), 0);

		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ModBiomes.initBiomeTypes();

		proxy.postInit(event);
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event);
	}
}

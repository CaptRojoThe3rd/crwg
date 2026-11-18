package com.captrojo.crwg.main;

import java.io.File;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.compat.EHLFuncs;
import com.captrojo.crwg.config.BiomeConfig;
import com.captrojo.crwg.config.GeneralConfig;
import com.captrojo.crwg.config.ModConfig;
import com.captrojo.crwg.item.ModItems;
import com.captrojo.crwg.recipe.ModCraftingRecipes;
import com.captrojo.crwg.recipe.ModSmeltingRecipes;
import com.captrojo.crwg.tileentity.ModTileEntities;
import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.WorldTypeCRWG;
import com.captrojo.crwg.world.biome.ModBiomes;
import com.captrojo.crwg.world.chunk.ChunkDataMgr;
import com.captrojo.crwg.world.gen.WorldGenEvents;
import com.falsepattern.chunk.api.DataRegistry;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		CRWG.log.info("CaptRojo's World Gen " + CRWG.VERSION_NAME);

		CRWG.funcs = new EHLFuncs();

		CRWG.dir_config = event.getModConfigurationDirectory().getPath() + File.separator + "crwg";
		File f = new File(CRWG.dir_config);
		if (!f.exists()) {
			f.mkdirs();
		}
		ModConfig.load(new File(CRWG.dir_config + File.separator + "common.cfg"));
		GeneralConfig.init();

		ModBlocks.initBlocks();
		ModBlocks.registerBlocks();
		ModItems.initItems();
		ModItems.registerItems();

		ModTileEntities.registerTileEntities();

		ModBiomes.initBiomes();
		BiomeConfig.init();

		WorldGenEvents.instance = new WorldGenEvents();
		MinecraftForge.TERRAIN_GEN_BUS.register(WorldGenEvents.instance);

		CommonEvents.instance = new CommonEvents();
		MinecraftForge.EVENT_BUS.register(CommonEvents.instance);
		FMLCommonHandler.instance().bus().register(CommonEvents.instance);

		ModConfig.save();
	}

	public void init(FMLInitializationEvent event)
	{
		BlockState.initPreMade();

		ModCraftingRecipes.registerRecipes();
		ModSmeltingRecipes.registerRecipes();

		ModBiomes.registerBiomes();
		ModBiomes.initBiomeTypes();

		WorldTypeCRWG.TYPE_CRWG = new WorldTypeCRWG("CRWG", WorldTypeCRWG.SUBTYPE_NORMAL);
		WorldTypeCRWG.TYPE_CRWG_ISLANDS = new WorldTypeCRWG("CRWGIslands", WorldTypeCRWG.SUBTYPE_ISLANDS);

		DataRegistry.registerDataManager(new ChunkDataMgr(), 0);
	}

	public void postInit(FMLPostInitializationEvent event)
	{
	}

	public void serverStarting(FMLServerStartingEvent event)
	{
	}
}

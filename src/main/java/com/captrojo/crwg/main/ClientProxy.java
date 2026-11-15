package com.captrojo.crwg.main;

import java.io.File;

import com.captrojo.crwg.config.ClientConfig;
import com.captrojo.crwg.config.ModConfig;
import com.captrojo.crwg.render.block.RenderBlock2x2FloorPlant;
import com.captrojo.crwg.render.block.RenderBlockDoublePlant;
import com.captrojo.crwg.render.block.RenderBlockFloorPlant;
import com.captrojo.crwg.render.tileentity.RenderTESignCRWG;
import com.captrojo.crwg.tileentity.TESignCRWG;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	@Override
	public void loadConfig()
	{
		super.loadConfig();
		ModConfig.load(new File(CRWG.dir_config + File.separator + "client.cfg"));

		ClientConfig.init();

		ModConfig.save();
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);

		ClientEvents.instance = new ClientEvents();
		MinecraftForge.EVENT_BUS.register(ClientEvents.instance);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);

		if (ClientConfig.cmd_flight_speed) {
			ClientCommandHandler.instance.registerCommand(new CommandFlightSpeed());
		}
		if (ClientConfig.cmd_reset_perf_info) {
			ClientCommandHandler.instance.registerCommand(new CommandResetPerformanceInfo());
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TESignCRWG.class, new RenderTESignCRWG());

		RenderingRegistry.registerBlockHandler(new RenderBlockDoublePlant());
		RenderingRegistry.registerBlockHandler(new RenderBlockFloorPlant());
		RenderingRegistry.registerBlockHandler(new RenderBlock2x2FloorPlant());
	}
}

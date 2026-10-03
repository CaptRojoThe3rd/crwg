package com.captrojo.crwg.main;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;

public class ClientProxy extends CommonProxy
{	
	@Override
	public void init(FMLInitializationEvent event)
	{
		ClientCommandHandler.instance.registerCommand(new CommandFlightSpeed());
	}
}

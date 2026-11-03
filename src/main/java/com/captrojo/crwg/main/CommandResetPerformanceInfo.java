package com.captrojo.crwg.main;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandResetPerformanceInfo extends CommandBase
{
	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	@Override
	public String getCommandName()
	{
		return "resetperfinfo";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		PerformanceInfo.resetInfo();
	}
}

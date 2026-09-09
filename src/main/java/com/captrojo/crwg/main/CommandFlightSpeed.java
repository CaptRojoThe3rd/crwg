package com.captrojo.crwg.main;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandFlightSpeed extends CommandBase
{
	@SideOnly(Side.CLIENT)
	public void setFS(float speed)
	{
		Minecraft.getMinecraft().thePlayer.capabilities.setFlySpeed(speed);
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	@Override
	public String getCommandName()
	{
		return "fs";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.fs.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if (args.length < 1) {
			throw new WrongUsageException(this.getCommandUsage(sender));
		}

		EntityPlayer player = (EntityPlayer) sender;
		float speed = 0.05f;
		try {
			speed = Float.parseFloat(args[args.length - 1]);
		} catch (NumberFormatException e) {}

		if (player.worldObj.isRemote) {
			this.setFS(speed);
		}
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args)
	{
		List<String> list = null;
		if (args.length == 1) {
			list = new ArrayList<String>();
			list.add("0.05");
		}
		return list;
	}
}

package com.captrojo.crwg.main;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.compat.EHLFuncs;
import com.captrojo.crwg.world.WorldChunkManagerCRWG;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class CommonEvents
{
	public static CommonEvents instance;

	private void makeFarmland(World world, int x, int y, int z, Block farmland, int meta)
	{
		world.playSoundEffect(
			(double) x + 0.5,
			(double) y + 0.5,
			(double) z + 0.5,
			farmland.stepSound.getStepResourcePath(),
			(farmland.stepSound.getVolume() + 1.0f) / 2.0f,
			farmland.stepSound.getPitch() * 0.8f
		);
		if (!world.isRemote) {
			world.setBlock(x, y, z, farmland, meta, 3);
		}
	}

	@SubscribeEvent
	public void onHoeUse(UseHoeEvent event)
	{
		final World world = event.world;
		final int x = event.x, y = event.y, z = event.z;

		final Block block = world.getBlock(event.x, event.y, event.z);

		if (block == ModBlocks.silty_dirt || block == ModBlocks.silty_grass || block == ModBlocks.silty_podzol) {
			this.makeFarmland(world, x, y, z, ModBlocks.silty_farmland, 0);
			event.setResult(Result.ALLOW);
			return;
		}
		if (block == ModBlocks.coarse_silty_dirt) {
			this.makeFarmland(world, x, y, z, ModBlocks.silty_dirt, 0);
			event.setResult(Result.ALLOW);
			return;
		}
		if (block == ModBlocks.loamy_dirt || block == ModBlocks.loamy_grass || block == ModBlocks.loamy_podzol) {
			this.makeFarmland(world, x, y, z, ModBlocks.loamy_farmland, 0);
			event.setResult(Result.ALLOW);
			return;
		}
		if (block == ModBlocks.coarse_loamy_dirt) {
			this.makeFarmland(world, x, y, z, ModBlocks.loamy_dirt, 0);
			event.setResult(Result.ALLOW);
			return;
		}
		if (block == ModBlocks.clay_dirt || block == ModBlocks.clay_grass || block == ModBlocks.clay_podzol) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta == 0) {
				this.makeFarmland(world, x, y, z, block, 1);
			} else {
				this.makeFarmland(world, x, y, z, ModBlocks.clay_farmland, 0);
			}
			event.setResult(Result.ALLOW);
			return;
		}
		if (block == ModBlocks.coarse_clay_dirt) {
			this.makeFarmland(world, x, y, z, ModBlocks.clay_dirt, 0);
			event.setResult(Result.ALLOW);
			return;
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (event.player.worldObj.isRemote) {
			return;
		}
		WorldChunkManager wcm = event.player.worldObj.getWorldChunkManager();
		if (wcm instanceof WorldChunkManagerCRWG) {
			event.player.addChatMessage(
				new ChatComponentText("§7Loaded world with CaptRojo's World Gen " + CRWG.VERSION_NAME)
			);
			if (CRWG.funcs instanceof EHLFuncs) {
				event.player.addChatMessage(
					new ChatComponentText("§7Please caution the poor extended height limit code.")
				);
			}
		}
	}

	@SubscribeEvent
	public void onServerTickEvent(TickEvent.ServerTickEvent event)
	{
		long time_ms = MinecraftServer.getSystemTimeMillis();
		if (event.phase == TickEvent.Phase.START) {
			PerformanceInfo.onTickStart(time_ms);
		} else if (event.phase == TickEvent.Phase.END) {
			PerformanceInfo.onTickEnd(time_ms);
		}
	}
}

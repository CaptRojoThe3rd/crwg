package com.captrojo.crwg.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.WorldChunkManagerCRWG;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;
import com.captrojo.crwg.world.gen.data.GConst;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ClientEvents
{
	public static ClientEvents instance;

	private static Pattern biome_str_pattern = Pattern.compile("(?<=b: )(.*)(?= bl: )");

	@SubscribeEvent(priority = EventPriority.LOW)
	public void renderOverlayText(RenderGameOverlayEvent.Text event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if (!mc.gameSettings.showDebugInfo) {
			return;
		}

		mc.mcProfiler.startSection("crwg_debug");
		
		if (PerformanceInfo.available) {
			int i = event.right.indexOf(null);
			if (i == -1) {
				event.right.add(null);
				i = event.right.size() - 1;
			} else {
				event.right.add(i, null);
				i++;
			}
			
			String mspt_str = String.format(
				"mspt: [avg: %d, last: %d, worst: %d]",
				PerformanceInfo.mspt_avg,
				PerformanceInfo.mspt_last,
				PerformanceInfo.mspt_worst
			);
			event.right.add(i, mspt_str);
			i++;
			
			long since_last_tick = Minecraft.getSystemTime() - PerformanceInfo.tick_time_end;
			
			String tps_str = String.format(
				"tps: %.2f, slt: %.1f",
				((PerformanceInfo.mspt_avg < 50) ? 20D : (1000D / (double) PerformanceInfo.mspt_avg)),
				((float) since_last_tick) / 1000f
			);
			event.right.add(i, tps_str);
		}

		World world = Minecraft.getMinecraft().theWorld;
		WorldChunkManager wcm0 = world.provider.worldChunkMgr;

		int x = MathHelper.floor_double(mc.thePlayer.posX);
		int y = MathHelper.floor_double(mc.thePlayer.posY);
		int z = MathHelper.floor_double(mc.thePlayer.posZ);

		if (world != null && world.blockExists(x, y, z)) {
			Chunk chunk = world.getChunkFromBlockCoords(x, z);
			WorldCRWG world_crwg = (WorldCRWG) (Object) world;

			String biome_str = null;
			for (int i = 0; i < event.left.size(); i++) {
				String old_str = event.left.get(i);
				if (old_str == null) {
					continue;
				}
				if (!old_str.startsWith("lc: ")) {
					continue;
				}

				Matcher matcher = ClientEvents.biome_str_pattern.matcher(old_str);
				if (matcher.find()) {
					biome_str = matcher.group();
				}

				String new_str = String.format(
					"lc: %d bl: %d sl: %d rl: %d",
					chunk.getTopFilledSegment() + 15,
					chunk.getSavedLightValue(EnumSkyBlock.Block, x & 0xf, y, z & 0xf),
					chunk.getSavedLightValue(EnumSkyBlock.Sky, x & 0xf, y, z & 0xf),
					chunk.getBlockLightValue(x & 0xf, y, z & 0xf, 0)
				);
				event.left.set(i, new_str);
			}

			event.left.add(null);

			BiomeGenBase biome = chunk.getBiomeGenForWorldCoords(x & 0xf, z & 0xf, world.getWorldChunkManager());
			if (biome_str == null) {
				biome_str = biome.biomeName;
			}

			event.left.add("biome: " + biome_str);

			if (wcm0 instanceof WorldChunkManagerCRWG) {
				BiomeTypeCRWG biome_type = world_crwg.crwg$getBiomeType(x, z);
				String str = String.format(
					"bt: %s (t: %s, r: %s, a: %s)",
					biome_type.name,
					GConst.TEMPERATURE_NAMES[biome_type.s_temp],
					GConst.RAINFALL_NAMES[biome_type.s_rain],
					GConst.AWESOMENESS_NAMES[biome_type.s_awesome]
				);
				event.left.add(str);

				LandType land_type = world_crwg.crwg$getLandType(x, z);
				Soil soil_type = world_crwg.crwg$getSoilType(x, z);
				int species = world_crwg.crwg$getSubspeciesVal(x, z);
				str = String.format(
					"lt: %s, st: %s, sp: %d",
					land_type.name,
					soil_type.name,
					species
				);
				event.left.add(str);
			}
		}

		mc.mcProfiler.endSection();
	}
}

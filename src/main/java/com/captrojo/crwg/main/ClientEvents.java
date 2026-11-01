package com.captrojo.crwg.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.WorldChunkManagerCRWG;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;

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
				LandType land_type = world_crwg.crwg$getLandType(x, z);
				BiomeTypeCRWG biome_type = world_crwg.crwg$getBiomeType(x, z);
				Soil soil_type = world_crwg.crwg$getSoilType(x, z);
				String str = String.format(
					"lt: %s, bt: %s, st: %s",
					land_type.name,
					biome_type.name,
					soil_type.name
				);
				event.left.add(str);
				
				int species = world_crwg.crwg$getSpeciesVal(x, z);
				str = String.format("sp: %d", species);
				event.left.add(str);
			}
		}

		mc.mcProfiler.endSection();
	}
}

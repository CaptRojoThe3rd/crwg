package com.captrojo.crwg.event.worldgen;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.util.DecoCoords;

import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class CRWGDecorateEvent extends DecorateBiomeEvent.Decorate
{
	public final String custom_type;

	public CRWGDecorateEvent(
		final World world,
		final Random rand,
		final DecoCoords dpos,
		final EventType event_type,
		final String event_name
	) {
		super(world, rand, dpos.get_x0(), dpos.get_z0(), event_type);
		this.custom_type = event_name;
	}
}

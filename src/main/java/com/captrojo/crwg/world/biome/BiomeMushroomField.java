package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.data.GenVals;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeMushroomField extends BiomeCRWGBase
{
	private final byte mushrooms;

	public BiomeMushroomField(final BiomeInfo info, final float temperature, final float rainfall, final int mushrooms)
	{
		super(info, temperature, rainfall);

		this.mushrooms = (byte) mushrooms;

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();

		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.big_mushrooms = this.mushrooms;
		optns.mushrooms = this.mushrooms;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public BlockState getTopBlock(final World world, final Random rand, final GenVals vals)
	{
		return this.getSoilType(world, rand, vals).mycelium;
	}
}

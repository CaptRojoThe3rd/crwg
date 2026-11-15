package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.BlockState;
import com.captrojo.crwg.world.gen.data.GenVals;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeMushroomField extends BiomeCRWGBase
{
	public BiomeMushroomField(int id, String name, int color, float temperature, float rainfall, int mushrooms)
	{
		super(id, name, color, temperature, rainfall);

		this.decorator.big_mushrooms = mushrooms;
		this.decorator.mushrooms = mushrooms;

		this.copyToHigherDecorators();

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();

		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
	}

	@Override
	public BlockState getTopBlock(World world, int x, int y, int z, Random rand, GenVals vals)
	{
		return this.getSoilType(world, x, y, z, rand, vals.s_temp, vals.s_rain, vals.species).mycelium;
	}
}

package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.ChunkProviderCRWG;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeJungle extends BiomeCRWGBase
{
	public BiomeJungle(int id, String name, int color, float temperature, float rainfall, int sparseness)
	{
		super(id, name, color, temperature, rainfall);

		if (sparseness == 0) {
			this.decorator_lowlands.trees = 30;
			this.decorator_lowlands.grass = 25;
			this.decorator_lowlands.flowers = 4;
		} else if (sparseness == 1) {
			this.decorator_lowlands.trees = 10;
			this.decorator_lowlands.grass = 17;
			this.decorator_lowlands.flowers = 3;
		} else {
			this.decorator_lowlands.trees = 5;
			this.decorator_lowlands.grass = 10;
			this.decorator_lowlands.flowers = 2;
		}
		this.decorator_lowlands.pumpkin_chance = 0.0625F;

		if (sparseness < 1) {
			this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
		}
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(10) == 0) {
			return TreeGens.Oak_Big;
		}
		if (rand.nextInt(2) == 0) {
			return TreeGens.Jungle_Shrub;
		}
		if (rand.nextInt(3) == 0) {
			return TreeGens.Jungle_Large;
		}
		return TreeGens.Jungle_Small;
	}

	@Override
	public WorldGenerator getPumpkinGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Melon;
	}

	@Override
	public void decorate(World world, Random rand, int x0, int z0)
	{
		super.decorate(world, rand, x0, z0);

		final int y = ChunkProviderCRWG.SEA_LVL_Y + 64;
		final int x1 = x0 + 8;
		final int z1 = z0 + 8;
		for (int i = 0; i < 50; i++) {
			int x = x1 + rand.nextInt(16);
			int z = z1 + rand.nextInt(16);
			BiomeCRWGBase.Gen_Vines.generate(world, rand, x, y, z);
		}
	}
}

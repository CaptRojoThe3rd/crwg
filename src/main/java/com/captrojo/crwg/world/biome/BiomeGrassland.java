package com.captrojo.crwg.world.biome;

import java.util.Random;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGrassland extends BiomeCRWGBase
{
	final boolean sunflower;

	public BiomeGrassland(int id, String name, int color, float temperature, float rainfall, boolean sunflower)
	{
		super(id, name, color, temperature, rainfall);
		this.sunflower = sunflower;

		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));

		this.decorator_lowlands.grass = 10;
		this.decorator_lowlands.flowers = 4;

		this.flowers.clear();
		this.addFlower(Blocks.red_flower, 4, 3);
		this.addFlower(Blocks.red_flower, 5, 3);
		this.addFlower(Blocks.red_flower, 6, 3);
		this.addFlower(Blocks.red_flower, 7, 3);
		this.addFlower(Blocks.red_flower, 0, 20);
		this.addFlower(Blocks.red_flower, 3, 20);
		this.addFlower(Blocks.red_flower, 8, 20);
		this.addFlower(Blocks.yellow_flower, 0, 30);
	}

	@Override
	public void decorate(World world, Random rand, int x0, int z0)
	{
		super.decorate(world, rand, x0, z0);

		int i;
		int x, y, z;

		for (i = 0; i < 2; i++) {
			x = x0 + rand.nextInt(16) + 8;
			z = z0 + rand.nextInt(16) + 8;
			y = world.getHeightValue(x, z);
			BiomeCRWGBase.Gen_DPlant_TallGrass.generate(world, rand, x, y, z);
		}

		if (this.sunflower) {
			for (i = 0; i < 3; i++) {
				x = x0 + rand.nextInt(16) + 8;
				z = z0 + rand.nextInt(16) + 8;
				y = world.getHeightValue(x, z);
				BiomeCRWGBase.Gen_DPlant_Sunflower.generate(world, rand, x, y, z);
			}
		}
	}
}

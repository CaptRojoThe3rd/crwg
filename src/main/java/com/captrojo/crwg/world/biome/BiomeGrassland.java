package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGrassland extends BiomeCRWGBase
{
	public static final int TYPE_PLAIN = 0;
	public static final int TYPE_SUNFLOWER = 1;
	public static final int TYPE_DRY = 2;
	public static final int TYPE_COLD = 3;

	final int type;

	public BiomeGrassland(int id, String name, int color, float temperature, float rainfall, int type)
	{
		super(id, name, color, temperature, rainfall);
		this.type = type;

		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));

		this.decorator.trees = 0;
		this.decorator.extra_tree_chance = 0.05F;

		this.decorator.flowers = (type <= TYPE_SUNFLOWER) ? 4 : 0;
		this.decorator.grass = 10;
		this.decorator.shrubs = 1;
		
		if (type == TYPE_DRY) {
			this.decorator.pumpkins = 0;
		}

		this.copyToHigherDecorators();

		if (type == TYPE_PLAIN || type == TYPE_SUNFLOWER) {
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
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (this.type == TYPE_DRY) {
			return TreeGens.Acacia_Shrub;
		}
		if (this.type == TYPE_COLD) {
			return TreeGens.Spruce_Shrub;
		}
		return TreeGens.Oak_Shrub;
	}

	@Override
	public WorldGenerator getGrassGen(World world, int x, int y, int z, Random rand)
	{
		if (this.type != TYPE_DRY && rand.nextInt(5) == 0) {
			return BiomeCRWGBase.Gen_Clover;
		}
		return BiomeCRWGBase.Gen_TallGrass;
	}

	@Override
	public void decorate(World world, Random rand, int cx0, int cz0)
	{
		super.decorate(world, rand, cx0, cz0);

		int i;
		int x, y, z;

		for (i = 0; i < 2; i++) {
			x = cx0 + rand.nextInt(16) + 8;
			z = cz0 + rand.nextInt(16) + 8;
			y = world.getHeightValue(x, z);
			BiomeCRWGBase.Gen_DPlant_TallGrass.generate(world, rand, x, y, z);
		}

		if (this.type == TYPE_SUNFLOWER) {
			for (i = 0; i < 3; i++) {
				x = cx0 + rand.nextInt(16) + 8;
				z = cz0 + rand.nextInt(16) + 8;
				y = world.getHeightValue(x, z);
				BiomeCRWGBase.Gen_DPlant_Sunflower.generate(world, rand, x, y, z);
			}
		}
	}
}

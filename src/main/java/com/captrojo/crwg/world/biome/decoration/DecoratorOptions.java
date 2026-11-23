package com.captrojo.crwg.world.biome.decoration;

import java.lang.reflect.Field;
import java.util.Random;

import com.captrojo.crwg.util.func.WorldCPosIntSupplier;

import net.minecraft.world.World;

public class DecoratorOptions
{
	public int underwater_sand = 3;
	public int underwater_gravel = 1;
	public int underwater_clay = 1;

	public int boulders = 0;
	public float boulder_chance = 0.0F;

	public int flowers = 1;
	public float flower_chance = 0.2F;
	public int grass = 2;
	public float grass_chance = 0.5F;
	public int tall_grass = 0;
	public float tall_grass_chance = 0.5F;
	public int shrubs = 0;
	public float shrub_chance = 0.2F;
	public int waterlilies = 0;
	public float waterlily_chance = 0.2F;

	public int mushrooms = 0;
	public float mushroom_chance = 1.0F;
	public float brown_mushroom_chance = 0.25F;
	public float red_mushroom_chance = 0.125F;

	public WorldCPosIntSupplier sup_trees = this::getTrees;
	public int trees = 0;
	public float extra_tree_chance = 0.0F;
	public int big_mushrooms = 0;
	public float extra_big_mushroom_chance = 0.0F;

	public int reeds = 10;
	public float reed_chance = 0.03125F;
	public int pumpkins = 1;
	public float pumpkin_chance = 0.03125F;
	public int cacti = 0;
	public float cacti_chance = 0.0625F;

	public int liquid_waters = 50;
	public int liquid_lavas = 20;

	private int getTrees(World world, int cx0, int cz0, Random rand)
	{
		return this.trees;
	}

	public void copyFrom(DecoratorOptions that)
	{
		for (Field field : DecoratorOptions.class.getFields()) {
			try {
				Object obj = field.get(this);
				field.set(that, obj);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}

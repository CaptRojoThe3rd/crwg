package com.captrojo.crwg.world.biome.decoration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.captrojo.crwg.util.func.TriArgFloatProvider;
import com.captrojo.crwg.util.func.TriArgIntProvider;
import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.biome.decoration.decors.Decoration;
import com.captrojo.crwg.world.biome.decoration.util.DecoCoords;

import net.minecraft.world.World;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;

public class DecoratorOptions
{
	public int underwater_sand = 3;
	public int underwater_gravel = 1;
	public int underwater_clay = 1;

	public int boulders = 0;
	public float boulder_chance = 0.0F;
	public TriArgIntProvider<World, Random, DecoCoords> p_boulders;
	public TriArgFloatProvider<World, Random, DecoCoords> p_boulder_chance;

	public int trees = 0;
	public float tree_chance = 1.0F;
	public TriArgIntProvider<World, Random, DecoCoords> p_trees;
	public TriArgFloatProvider<World, Random, DecoCoords> p_tree_chance;

	public int big_mushrooms = 0;
	public float big_mushroom_chance = 0.0F;
	public TriArgIntProvider<World, Random, DecoCoords> p_big_mushrooms;
	public TriArgFloatProvider<World, Random, DecoCoords> p_big_mushroom_chance;

	public int flowers = 1;
	public float flower_chance = 0.2F;
	public TriArgIntProvider<World, Random, DecoCoords> p_flowers;
	public TriArgFloatProvider<World, Random, DecoCoords> p_flower_chance;

	public int grass = 2;
	public float grass_chance = 0.5F;
	public TriArgIntProvider<World, Random, DecoCoords> p_grass;
	public TriArgFloatProvider<World, Random, DecoCoords> p_grass_chance;

	public int tall_grass = 0;
	public float tall_grass_chance = 0.5F;
	public TriArgIntProvider<World, Random, DecoCoords> p_tall_grass;
	public TriArgFloatProvider<World, Random, DecoCoords> p_tall_grass_chance;

	public int cattails = 0;
	public float cattail_chance = 0.5F;
	public TriArgIntProvider<World, Random, DecoCoords> p_cattails;
	public TriArgFloatProvider<World, Random, DecoCoords> p_cattail_chance;

	public int shrubs = 0;
	public float shrub_chance = 0.2F;
	public TriArgIntProvider<World, Random, DecoCoords> p_shrubs;
	public TriArgFloatProvider<World, Random, DecoCoords> p_shrub_chance;

	public int waterlilies = 0;
	public float waterlily_chance = 0.2F;
	public TriArgIntProvider<World, Random, DecoCoords> p_waterlilies;
	public TriArgFloatProvider<World, Random, DecoCoords> p_waterlily_chance;

	public int mushrooms = 0;
	public float mushroom_chance = 1.0F;
	public TriArgIntProvider<World, Random, DecoCoords> p_mushrooms;
	public TriArgFloatProvider<World, Random, DecoCoords> p_mushroom_chance;

	public int reeds = 10;
	public float reed_chance = 0.03125F;
	public TriArgIntProvider<World, Random, DecoCoords> p_reeds;
	public TriArgFloatProvider<World, Random, DecoCoords> p_reed_chance;

	public int pumpkins = 1;
	public float pumpkin_chance = 0.03125F;
	public TriArgIntProvider<World, Random, DecoCoords> p_pumpkins;
	public TriArgFloatProvider<World, Random, DecoCoords> p_pumpkin_chance;

	public int cacti = 0;
	public float cactus_chance = 0.0625F;
	public TriArgIntProvider<World, Random, DecoCoords> p_cacti;
	public TriArgFloatProvider<World, Random, DecoCoords> p_cactus_chance;

	public int liquid_waters = 50;
	public int liquid_lavas = 20;

	public void copyFrom(final DecoratorOptions that)
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

	public List<Decoration> compileDecorations(final BiomeCRWGBase biome)
	{
		List<Decoration> decorations = new ArrayList<>();
		Decoration d;

		if (this.underwater_sand > 0) {
			d = Decoration.newRandXZGnd(
				EventType.SAND,
				null,
				biome::getUnderwaterSandGen,
				this.underwater_sand,
				1.0F,
				null,
				null
			);
			decorations.add(d);
		}

		if (this.underwater_clay > 0) {
			d = Decoration.newRandXZGnd(
				EventType.CLAY,
				null,
				biome::getUnderwaterClayGen,
				this.underwater_clay,
				1.0F,
				null,
				null
			);
			decorations.add(d);
		}

		if (this.underwater_gravel > 0) {
			d = Decoration.newRandXZGnd(
				EventType.SAND_PASS2,
				null,
				biome::getUnderwaterGravelGen,
				this.underwater_gravel,
				1.0F,
				null,
				null
			);
			decorations.add(d);
		}

		if (this.boulders > 0 || this.p_boulders != null) {
			d = Decoration.newRandXZGnd(
				EventType.CUSTOM,
				"Boulder",
				biome::getBoulderGen,
				this.boulders,
				this.boulder_chance,
				this.p_boulders,
				this.p_boulder_chance
			);
			decorations.add(d);
		}

		if (this.trees > 0 || this.p_trees != null) {
			d = Decoration.newRandXZGnd(
				EventType.TREE,
				null,
				biome::getTreeGen,
				this.trees,
				this.tree_chance,
				this.p_trees,
				this.p_tree_chance
			);
			decorations.add(d);
		}

		if (this.big_mushrooms > 0 || this.p_big_mushrooms != null) {
			d = Decoration.newRandXZGnd(
				EventType.BIG_SHROOM,
				null,
				biome::getBigMushroomGen,
				this.big_mushrooms,
				this.big_mushroom_chance,
				this.p_big_mushrooms,
				this.p_big_mushroom_chance
			);
			decorations.add(d);
		}

		if (this.flowers > 0 || this.p_flowers != null) {
			d = Decoration.newRandXZ(
				EventType.FLOWERS,
				null,
				biome::getFlowerGen,
				this.flowers,
				this.flower_chance,
				this.p_flowers,
				this.p_flower_chance
			);
			decorations.add(d);
		}

		if (this.grass > 0 || this.p_grass != null) {
			d = Decoration.newRandXZ(
				EventType.GRASS,
				null,
				biome::getGrassGen,
				this.grass,
				this.grass_chance,
				this.p_grass,
				this.p_grass_chance
			);
			decorations.add(d);
		}

		if (this.tall_grass > 0 || this.p_tall_grass != null) {
			d = Decoration.newRandXZ(
				EventType.GRASS,
				"Tall",
				biome::getTallGrassGen,
				this.tall_grass,
				this.tall_grass_chance,
				this.p_tall_grass,
				this.p_tall_grass_chance
			);
			decorations.add(d);
		}

		if (this.cattails > 0 || this.p_cattails != null) {
			d = Decoration.newRandXZ(
				EventType.CUSTOM,
				"Cattail",
				biome::getCattailGen,
				this.cattails,
				this.cattail_chance,
				this.p_cattails,
				this.p_cattail_chance
			);
			decorations.add(d);
		}

		if (this.shrubs > 0 || this.p_shrubs != null) {
			d = Decoration.newRandXZ(
				EventType.GRASS,
				null,
				biome::getBushGen,
				this.shrubs,
				this.shrub_chance,
				this.p_shrubs,
				this.p_shrub_chance
			);
			decorations.add(d);
		}

		if (this.waterlilies > 0 || this.p_waterlilies != null) {
			d = Decoration.newRandXZGnd(
				EventType.LILYPAD,
				null,
				biome::getWaterlilyGen,
				this.waterlilies,
				this.waterlily_chance,
				this.p_waterlilies,
				this.p_waterlily_chance
			);
			decorations.add(d);
		}

		if (this.mushrooms > 0 || this.p_mushrooms != null) {
			d = Decoration.newRandXZ(
				EventType.SHROOM,
				null,
				biome::getMushroomGen,
				this.mushrooms,
				this.mushroom_chance,
				this.p_mushrooms,
				this.p_mushroom_chance
			);
			decorations.add(d);
		}

		if (this.reeds > 0 || this.p_reeds != null) {
			d = Decoration.newRandXZGnd(
				EventType.REED,
				null,
				biome::getReedGen,
				this.reeds,
				this.reed_chance,
				this.p_reeds,
				this.p_reed_chance
			);
			decorations.add(d);
		}

		if (this.pumpkins > 0 || this.p_pumpkins != null) {
			d = Decoration.newRandXZ(
				EventType.PUMPKIN,
				null,
				biome::getPumpkinGen,
				this.pumpkins,
				this.pumpkin_chance,
				this.p_pumpkins,
				this.p_pumpkin_chance
			);
			decorations.add(d);
		}

		if (this.cacti > 0 || this.p_cacti != null) {
			d = Decoration.newRandXZGnd(
				EventType.GRASS,
				null,
				biome::getCactusGen,
				this.cacti,
				this.cactus_chance,
				this.p_cacti,
				this.p_cactus_chance
			);
			decorations.add(d);
		}

		if (this.liquid_waters > 0) {
			d = Decoration.newRandXYZ(
				EventType.LAKE,
				"Water",
				biome::getLiquidWaterGen,
				this.liquid_waters,
				1.0F,
				null,
				null
			);
			decorations.add(d);
		}

		if (this.liquid_lavas > 0) {
			d = Decoration.newRandXYZ(
				EventType.LAKE,
				"Lava",
				biome::getLiquidLavaGen,
				this.liquid_lavas,
				1.0F,
				null,
				null
			);
			decorations.add(d);
		}

		return decorations;
	}
}

package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGrassland extends BiomeCRWGBase
{
	static final byte TYPE_PLAIN = 0;
	static final byte TYPE_SUNFLOWER = 1;
	static final byte TYPE_DRY = 2;
	static final byte TYPE_COLD = 3;

	private final byte type;

	public BiomeGrassland(final BiomeInfo info, final float temperature, final float rainfall, final byte type)
	{
		super(info, temperature, rainfall);
		this.type = type;

		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));

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
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.flowers = (this.type <= TYPE_SUNFLOWER) ? 4 : 0;
		optns.grass = 10;
		optns.shrubs = 1;

		optns.trees = 1;
		optns.tree_chance = 0.05F;

		if (this.type == TYPE_DRY) {
			optns.pumpkins = 0;
		}

		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
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
	public WorldGenerator getGrassGen(final World world, final Random rand, final BlockPos origin)
	{
		if (this.type != TYPE_DRY && rand.nextInt(5) == 0) {
			return BiomeCRWGBase.Gen_Clover;
		}
		return BiomeCRWGBase.Gen_TallGrass;
	}

	@Override
	public WorldGenerator getTallGrassGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_DPlant_TallGrass;
	}
}

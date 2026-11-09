package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeRainbowForest extends BiomeCRWGBase
{
	static final WorldGenerator[] FLOWERS = {
		BiomeCRWGBase.Gen_Flower_Allium,
		BiomeCRWGBase.Gen_Flower_Houstonia,
		BiomeCRWGBase.Gen_Flower_TulipRed,
		BiomeCRWGBase.Gen_Flower_TulipOrange,
		BiomeCRWGBase.Gen_Flower_TulipWhite,
		BiomeCRWGBase.Gen_Flower_TulipPink,
		BiomeCRWGBase.Gen_Flower_OxeyeDaisy,

		BiomeCRWGBase.Gen_Flower_PurpleOwlsClover,

		BiomeCRWGBase.Gen_Flower_LongspurLupine,
		BiomeCRWGBase.Gen_Flower_SilverLupine,
		BiomeCRWGBase.Gen_Flower_SkyBlueLupine
	};

	final boolean jungle;

	public BiomeRainbowForest(int id, String name, int color, float temperature, float rainfall, boolean jungle)
	{
		super(id, name, color, temperature, rainfall);
		this.jungle = jungle;

		this.decorator.flowers = 20;
		this.decorator.grass = 10;

		this.decorator.trees = 25;

		this.decorator.pumpkins = 2;

		this.decorator.coal_amount *= 1.5;

		this.copyToHigherDecorators();

		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 7, 14));
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextBoolean()) {
			switch (rand.nextInt(5)) {
			case 0:
				return TreeGens.Chromaticus_Red_Shrub;
			case 1:
				return TreeGens.Chromaticus_Green_Shrub;
			case 2:
				return TreeGens.Chromaticus_Blue_Shrub;
			case 3:
				return TreeGens.Chromaticus_Violet_Shrub;
			case 4:
				return TreeGens.Jungle_Shrub;
			}
		}

		switch (rand.nextInt(this.jungle ? 10 : 9)) {
		case 0:
			return TreeGens.Chromaticus_Red;
		case 1:
			return TreeGens.Chromaticus_Green;
		case 2:
			return TreeGens.Chromaticus_Blue;
		case 3:
			return TreeGens.Chromaticus_Violet;
		case 4:
			return TreeGens.Chromaticus_Red_Big;
		case 5:
			return TreeGens.Chromaticus_Green_Big;
		case 6:
			return TreeGens.Chromaticus_Blue_Big;
		case 7:
			return TreeGens.Chromaticus_Violet_Big;
		case 8:
			return TreeGens.Jungle_Small;
		case 9:
			return TreeGens.Jungle_Large;
		}

		return null; /* unreachable - the java compiler can be annoying sometimes */
	}

	@Override
	public WorldGenerator getFlowerGen(World world, int x, int y, int z, Random rand)
	{
		double d0 = (1.0 + BiomeGenBase.plantNoise.func_151601_a((double) x / 96.0, (double) z / 96.0)) / 2.0;
		d0 = MathHelper.clamp_double(d0, 0.0, 0.9999);
		int i0 = (int) (d0 * (double) FLOWERS.length);
		return FLOWERS[i0];
	}

	@Override
	public WorldGenerator getPumpkinGen(World world, int x, int y, int z, Random rand)
	{
		return BiomeCRWGBase.Gen_Melon;
	}
}

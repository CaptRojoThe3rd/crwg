package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeRainbowForest extends BiomeCRWGBase
{
	private static final WorldGenerator[] FLOWERS = {
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

	private final boolean jungle;

	public BiomeRainbowForest(final BiomeInfo info, final float temperature, final float rainfall, final boolean jungle)
	{
		super(info, temperature, rainfall);
		this.jungle = jungle;
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 7, 14));
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.flowers = 20;
		optns.grass = 10;

		optns.trees = 25;

		optns.pumpkins = 2;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
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
	public WorldGenerator getFlowerGen(final World world, final Random rand, final BlockPos origin)
	{
		double d0 = (1.0 + BiomeGenBase.plantNoise.func_151601_a((double) origin.x / 96.0, (double) origin.z / 96.0)) / 2.0;
		d0 = MathHelper.clamp_double(d0, 0.0, 0.9999);
		int i0 = (int) (d0 * (double) FLOWERS.length);
		return FLOWERS[i0];
	}

	@Override
	public WorldGenerator getPumpkinGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_Melon;
	}
}

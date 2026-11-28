package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeEnchantedAshForest extends BiomeCRWGBase
{
	private static final WorldGenerator[] FLOWERS = {
		BiomeCRWGBase.Gen_Flower_Allium,

		BiomeCRWGBase.Gen_Flower_Sage,
		BiomeCRWGBase.Gen_Flower_WoodlandSage,

		BiomeCRWGBase.Gen_Flower_AquaHyacinth,
		BiomeCRWGBase.Gen_Flower_DeepBlueHyacinth,
		BiomeCRWGBase.Gen_Flower_PinkHyacinth
	};

	public BiomeEnchantedAshForest(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);

		this.addFlower(Blocks.red_flower, 1, 3);
		this.addFlower(Blocks.red_flower, 2, 10);

		this.addFlower(ModBlocks.flower_sage, 0, 10);
		this.addFlower(ModBlocks.flower_woodland_sage, 0, 10);

		this.addFlower(ModBlocks.flower_hyacinth_aqua, 0, 10);
		this.addFlower(ModBlocks.flower_hyacinth_deepblue, 0, 10);
		this.addFlower(ModBlocks.flower_hyacinth_pink, 0, 10);
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		optns.flowers = 3;
		optns.grass = 5;
		optns.waterlilies = 2;

		optns.trees = 5;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (rand.nextInt(10) == 0) {
			return TreeGens.Spruce_Tiny;
		}
		return TreeGens.Enchanted_Ash;
	}

	@Override
	public WorldGenerator getFlowerGen(final World world, final Random rand, final BlockPos origin)
	{
		if (rand.nextInt(15) == 0) {
			return BiomeCRWGBase.Gen_Flower_BlueOrchid;
		}
		return FLOWERS[rand.nextInt(FLOWERS.length)];
	}

	@Override
	public WorldGenerator getGrassGen(final World world, final Random rand, final BlockPos origin)
	{
		if (rand.nextBoolean()) {
			return BiomeCRWGBase.Gen_TallGrass;
		}
		if (rand.nextBoolean()) {
			return BiomeCRWGBase.Gen_Clover_Large;
		}
		return BiomeCRWGBase.Gen_Clover;
	}

	@Override
	public void decorate(final World world, final Random rand, final int cx0, final int cz0)
	{
		final WorldCRWG world_crwg = (WorldCRWG) world;
		int x, y, z;
		for (int i = 0; i < 2; i++) {
			x = cx0 + 8 + rand.nextInt(16);
			z = cz0 + 8 + rand.nextInt(16);
			y = world_crwg.crwg$getGndHeight(x, z);
			BiomeCRWGBase.Gen_ShallowPond.generate(world, rand, x, y, z);
		}
		super.decorate(world, rand, cx0, cz0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(final int x, final int y, final int z)
	{
		double d0 = BiomeGenBase.plantNoise.func_151601_a((double) x * 0.0225, (double) z * 0.0225);
		if (d0 < -0.3) {
			return 0x69bc8c;
		}
		if (d0 < 0.3) {
			return 0x69bca7;
		}
		return 0x69a6bc;
	}
}

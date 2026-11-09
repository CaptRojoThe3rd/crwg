package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeEnchantedAshForest extends BiomeCRWGBase
{
	static final WorldGenerator[] FLOWERS = {
		BiomeCRWGBase.Gen_Flower_Allium,

		BiomeCRWGBase.Gen_Flower_Sage,
		BiomeCRWGBase.Gen_Flower_WoodlandSage,

		BiomeCRWGBase.Gen_Flower_AquaHyacinth,
		BiomeCRWGBase.Gen_Flower_DeepBlueHyacinth,
		BiomeCRWGBase.Gen_Flower_PinkHyacinth
	};

	public BiomeEnchantedAshForest(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.decorator.flowers = 3;
		this.decorator.grass = 5;
		this.decorator.waterlilies = 2;

		this.decorator.trees = 5;

		this.copyToHigherDecorators();

		this.addFlower(Blocks.red_flower, 1, 3);
		this.addFlower(Blocks.red_flower, 2, 10);

		this.addFlower(ModBlocks.flower_sage, 0, 10);
		this.addFlower(ModBlocks.flower_woodland_sage, 0, 10);

		this.addFlower(ModBlocks.flower_hyacinth_aqua, 0, 10);
		this.addFlower(ModBlocks.flower_hyacinth_deepblue, 0, 10);
		this.addFlower(ModBlocks.flower_hyacinth_pink, 0, 10);
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(10) == 0) {
			return TreeGens.Spruce_Tiny;
		}
		return TreeGens.Enchanted_Ash;
	}

	@Override
	public WorldGenerator getFlowerGen(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(15) == 0) {
			return BiomeCRWGBase.Gen_Flower_BlueOrchid;
		}
		return FLOWERS[rand.nextInt(FLOWERS.length)];
	}

	@Override
	public WorldGenerator getGrassGen(World world, int x, int y, int z, Random rand)
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
	public void decorate(World world, WorldCRWG world_crwg, Random rand, int x0, int z0)
	{
		for (int i = 0; i < 2; i++) {
			int x = x0 + 8 + rand.nextInt(16);
			int z = z0 + 8 + rand.nextInt(16);
			int y = world_crwg.crwg$getGndHeight(x, z);
			BiomeCRWGBase.Gen_ShallowPond.generate(world, rand, x, y, z);
		}

		super.decorate(world, world_crwg, rand, x0, z0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int x, int y, int z)
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

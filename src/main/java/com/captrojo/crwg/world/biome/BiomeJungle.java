package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.util.YConst;
import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeJungle extends BiomeCRWGBase
{
	private final byte sparseness;

	public BiomeJungle(final BiomeInfo info, final float temperature, final float rainfall, final int sparseness)
	{
		super(info, temperature, rainfall);
		this.sparseness = (byte) sparseness;

		if (sparseness < 1) {
			this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
		}
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();

		if (this.sparseness == 0) {
			optns.trees = 35;
			optns.grass = 25;
			optns.flowers = 4;
		} else if (this.sparseness == 1) {
			optns.trees = 12;
			optns.grass = 17;
			optns.flowers = 3;
		} else {
			optns.trees = 5;
			optns.grass = 10;
			optns.flowers = 2;
		}
		optns.pumpkin_chance = 0.0625F;

		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (rand.nextInt(10) == 0) {
			return TreeGens.Oak_Big;
		}
		if (rand.nextInt(3) == 0) {
			return TreeGens.Jungle_Shrub;
		}
		if (rand.nextInt(2) == 0) {
			return TreeGens.Jungle_Large;
		}
		return TreeGens.Jungle_Small;
	}

	@Override
	public WorldGenerator getPumpkinGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_Melon;
	}

	@Override
	public void decorate(final World world, final Random rand, final int cx0, final int cz0)
	{
		super.decorate(world, rand, cx0, cz0);

		final int y = YConst.SEA_LVL_Y + 64;
		final int cx8 = cx0 + 8;
		final int cz8 = cz0 + 8;
		for (int i = 0; i < 50; i++) {
			int x = cx8 + rand.nextInt(16);
			int z = cz8 + rand.nextInt(16);
			BiomeCRWGBase.Gen_Vines.generate(world, rand, x, y, z);
		}
	}
}

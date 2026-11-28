package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDarkSwamp extends BiomeAbstractSwamp
{
	public BiomeDarkSwamp(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);

		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));

		this.flowers.clear();
		this.addFlower(Blocks.red_flower, 1, 10);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		return TreeGens.Oak_Swamp;
	}

	@Override
	public WorldGenerator getFlowerGen(final World world, final Random rand, final BlockPos origin)
	{
		return BiomeCRWGBase.Gen_Flower_BlueOrchid;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(final int x, final int y, final int z)
	{
		double d0 = BiomeGenBase.plantNoise.func_151601_a((double) x * 0.0225, (double) z * 0.0225);
		return (d0 < -0.1) ? 0x4c763c : 0x6a7039;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(final int x, final int y, final int z)
	{
		return 0x6a7039;
	}
}

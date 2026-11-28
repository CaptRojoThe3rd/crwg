package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.biome.decoration.DecoratorOptions;
import com.captrojo.crwg.world.biome.info.BiomeInfo;
import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDarkOakForest extends BiomeCRWGBase
{
	public BiomeDarkOakForest(final BiomeInfo info, final float temperature, final float rainfall)
	{
		super(info, temperature, rainfall);
	}

	@Override
	public void initDecorators(final long world_seed)
	{
		DecoratorOptions optns = new DecoratorOptions();
		optns.trees = 0; /* we will generate them ourselves */
		optns.grass = 2;
		this.decorator.setAllOptions(optns);
	}

	@Override
	public WorldGenerator getTreeGen(final World world, final Random rand, final BlockPos origin)
	{
		if (rand.nextInt(3) == 0) {
			return TreeGens.Birch;
		}
		return TreeGens.Dark_Oak;
	}

	@Override
	public void decorate(final World world, final Random rand, final int cx0, final int cz0)
	{
		/* We generate trees here to ensure they are spaced nicely and create a canopy */
		final BlockPos pos = new BlockPos();
		for (int xt = 0; xt < 4; xt++) {
			for (int zt = 0; zt < 4; zt++) {
				pos.x = cx0 + (xt * 4) + 9 + rand.nextInt(3);
				pos.z = cz0 + (zt * 4) + 9 + rand.nextInt(3);
				pos.y = world.getHeightValue(pos.x, pos.z);

				if (rand.nextInt(20) == 0) {
					BiomeCRWGBase.Gen_BigMushroom.generate(world, rand, pos.x, pos.y, pos.z);
				} else {
					WorldGenerator tree = this.getTreeGen(world, rand, pos);
					tree.generate(world, rand, pos.x, pos.y, pos.z);
				}
			}
		}
		super.decorate(world, rand, cx0, cz0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(final int x, final int y, final int z)
	{
		/* Don't really understand it; this is just what Minecraft does for dark oak forests. */
		int color = super.getBiomeGrassColor(x, y, z);
		return (color & 0xfefefe) + 0x28340a >> 1;
	}
}

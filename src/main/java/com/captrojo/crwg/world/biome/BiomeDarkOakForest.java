package com.captrojo.crwg.world.biome;

import java.util.Random;

import com.captrojo.crwg.world.gen.feature.tree.TreeGens;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeDarkOakForest extends BiomeCRWGBase
{
	public BiomeDarkOakForest(int id, String name, int color, float temperature, float rainfall)
	{
		super(id, name, color, temperature, rainfall);

		this.decorator.trees = 0; /* we will generate them ourselves */
		this.decorator.grass = 2;
		
		this.decorator_hills.copyFrom(this.decorator);
		this.decorator_highlands.copyFrom(this.decorator);
	}

	@Override
	public WorldGenAbstractTree getTreeGen(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(3) == 0) {
			return TreeGens.Birch;
		}
		return TreeGens.Dark_Oak;
	}

	@Override
	public void decorate(World world, Random rand, int x0, int z0)
	{
		/* We generate trees here to ensure they are spaced nicely and create a canopy */
		for (int xt = 0; xt < 4; xt++) {
			for (int zt = 0; zt < 4; zt++) {
				int x = x0 + (xt * 4) + 9 + rand.nextInt(3);
				int z = z0 + (zt * 4) + 9 + rand.nextInt(3);
				int y = world.getHeightValue(x, z);

				if (rand.nextInt(20) == 0) {
					BiomeCRWGBase.Gen_BigMushroom.generate(world, rand, x, y, z);
				} else {
					WorldGenAbstractTree tree = this.getTreeGen(world, x, y, z, rand);
					if (tree.generate(world, rand, x, y, z)) {
						tree.func_150524_b(world, rand, x, y, z);
					}
				}
			}
		}

		super.decorate(world, rand, x0, z0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int x, int y, int z)
	{
		/* Don't really understand it; this is just what Minecraft does for dark oak forests. */
		int color = super.getBiomeGrassColor(x, y, z);
		return (color & 0xfefefe) + 0x28340a >> 1;
	}
}

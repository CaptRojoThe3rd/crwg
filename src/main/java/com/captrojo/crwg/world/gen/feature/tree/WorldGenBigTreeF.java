package com.captrojo.crwg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class WorldGenBigTreeF extends WorldGenBigTree
{
	private int min_height;
	
	public WorldGenBigTreeF(boolean notify)
	{
		this(notify, 5, 17);
	}
	
	public WorldGenBigTreeF(boolean notify, int min_height, int max_height)
	{
		super(notify);
		this.min_height = min_height;
		this.heightLimitLimit = max_height - min_height + 1;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		this.heightLimit = this.min_height + rand.nextInt(this.heightLimitLimit);		
		return super.generate(world, rand, x, y, z);
	}
}

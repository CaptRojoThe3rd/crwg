package com.captrojo.crwg.world.biome.decoration.util;

import java.util.Random;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

public final class DecoCoords
{
	private Random rand;
	private int x0, z0;
	private int x8, z8;
	private int x15, z15;

	public DecoCoords(final Random rand, final int x0, final int z0)
	{
		this.rand = rand;
		this.x0 = x0;
		this.z0 = z0;
		this.x8 = x0 + 8;
		this.z8 = z0 + 8;
		this.x15 = x0 + 15;
		this.z15 = z0 + 15;
	}

	public int get_x0()
	{
		return this.x0;
	}

	public int get_z0()
	{
		return this.z0;
	}

	public int get_x8()
	{
		return this.x8;
	}

	public int get_z8()
	{
		return this.z8;
	}

	public int get_x15()
	{
		return this.x15;
	}

	public int get_z15()
	{
		return this.z15;
	}

	public void applyRandPos(final BlockPos pos)
	{
		pos.x = this.rand.nextInt(16) + this.x8;
		pos.z = this.rand.nextInt(16) + this.z8;
	}
}

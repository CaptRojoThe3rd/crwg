package com.captrojo.crwg.world.gen.cave;

import java.util.Random;

import com.captrojo.crwg.world.gen.data.GenArrays;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class CaveGenBase
{
	public int range = 8;
	protected Random rand = new Random();
	protected World world;

	public abstract void generate(World world, int r_chunk_x, int r_chunk_z, int chunk_x, int chunk_z, GenArrays arrays);

	public void generateAll(World world, int chunk_x, int chunk_z, GenArrays arrays)
	{
		int r = this.range;
		this.world = world;
		this.rand.setSeed(world.getSeed());
		long la = this.rand.nextLong();
		long lb = this.rand.nextLong();

		for (int r_chunk_x = (chunk_x - r); r_chunk_x <= (chunk_x + r); r_chunk_x++) {
			for (int r_chunk_z = (chunk_z - r); r_chunk_z <= (chunk_z + r); r_chunk_z++) {
				long lx = (long) r_chunk_x * la;
				long lz = (long) r_chunk_z * lb;
				this.rand.setSeed(lx ^ lz ^ world.getSeed());
				this.generate(world, r_chunk_x, r_chunk_z, chunk_x, chunk_z, arrays);
			}
		}
	}

	void dig(GenArrays arrays, int idx, int ya)
	{
		if (ya < 20) {
			arrays.ablock[idx] = Blocks.lava;
		} else {
			arrays.ablock[idx] = Blocks.air;
		}
	}

	boolean isWater(GenArrays arrays, int idx)
	{
		Block block = arrays.ablock[idx];
		return (block == Blocks.flowing_water || block == Blocks.water);
	}
}

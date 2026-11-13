package com.captrojo.crwg.world.gen.longlayer;

import net.minecraft.world.gen.layer.GenLayer;

public abstract class GenLayerLong extends GenLayer
{
	protected GenLayerLong parent;
	
	public GenLayerLong(long base_seed)
	{
		super(base_seed);
	}

	public abstract long[] getLongs(int ax, int ay, int aw, int ah);

	protected long selectRandom(long... p_151619_1_)
	{
		return p_151619_1_[this.nextInt(p_151619_1_.length)];
	}

	protected long selectModeOrRandom(long p1, long p2, long p3, long p4)
	{
		/* I hope this is just a result of the decompiler and not something
		 * Mojang actually wrote...
		 */
		return p2 == p3 && p3 == p4 ? p2 : (p1 == p2 && p1 == p3 ? p1 : (p1 == p2 && p1 == p4 ? p1 : (p1 == p3 && p1 == p4 ? p1 : (p1 == p2 && p3 != p4 ? p1 : (p1 == p3 && p2 != p4 ? p1 : (p1 == p4 && p2 != p3 ? p1 : (p2 == p3 && p1 != p4 ? p2 : (p2 == p4 && p1 != p3 ? p2 : (p3 == p4 && p1 != p2 ? p3 : this.selectRandom(new long[] {p1, p2, p3, p4}))))))))));
	}

	@Override
	public int[] getInts(int ax, int ay, int aw, int ah)
	{
		throw new IllegalStateException();
	}
}

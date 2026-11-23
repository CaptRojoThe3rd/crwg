package com.captrojo.crwg.util.block;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;

public class BlockReplacement
{
	public final BlockState from;
	public final BlockState to;

	public BlockReplacement(BlockState from, BlockState to)
	{
		this.from = from;
		this.to = to;
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof BlockReplacement that)) {
			return false;
		}
		return this.from.equals(that.from) && this.to.equals(that.to);
	}
}

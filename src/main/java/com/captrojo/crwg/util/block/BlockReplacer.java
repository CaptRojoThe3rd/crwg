package com.captrojo.crwg.util.block;

import javax.annotation.Nullable;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockStateImpl;

import net.minecraft.world.World;

public class BlockReplacer
{
	private final int length;
	private final BlockState[] to_replace;
	private final BlockState[] replacements;

	private final ThreadLocal<BlockStateImpl> blockstate_obj = ThreadLocal.withInitial(() -> new BlockStateImpl());

	public BlockReplacer(BlockState[]... groups)
	{
		this.length = groups.length;
		this.to_replace = new BlockState[groups.length];
		this.replacements = new BlockState[groups.length];
		for (int i = 0; i < groups.length; i++) {
			this.to_replace[i] = groups[i][0];
			this.replacements[i] = groups[i][1];
		}
	}

	public boolean replaceAt(final World world, final BlockPos pos, final int flags)
	{
		return this.replaceAt(world, pos.x, pos.y, pos.z, flags);
	}

	public boolean replaceAt(final World world, final int x, final int y, final int z, final int flags)
	{
		BlockState replacement = this.getFor(world, x, y, z);
		if (replacement == null) {
			return false;
		}
		replacement.place(world, x, y, z, flags);
		return true;
	}

	public @Nullable BlockState getFor(final World world, final BlockPos pos)
	{
		return this.getFor(world, pos.x, pos.y, pos.z);
	}

	public @Nullable BlockState getFor(final World world, final int x, final int y, final int z)
	{
		BlockStateImpl state = this.blockstate_obj.get();
		state.fromWorld(world, x, y, z);
		for (int i = 0; i < this.length; i++) {
			if (state.equals(this.to_replace[i])) {
				return this.replacements[i];
			}
		}
		return null;
	}
}

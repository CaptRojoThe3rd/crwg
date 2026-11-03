package com.captrojo.crwg.tileentity;

import com.captrojo.crwg.block.BlockSignCRWG.Sign;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSign;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySign;

public class TESignCRWG extends TileEntitySign
{
	public Sign sign;

	public TESignCRWG()
	{
	}

	public TESignCRWG(Sign sign)
	{
		this.sign = sign;
	}

	@Override
	public Block getBlockType()
	{
		if (this.blockType == null) {
			this.blockType = super.getBlockType();
			if (this.blockType instanceof BlockSign) {
				boolean standing = ((BlockSign) this.blockType).field_149967_b;
				this.blockType = standing ? Blocks.standing_sign : Blocks.wall_sign;
			}
		}
		return this.blockType;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setByte("S", (byte) this.sign.ordinal());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		byte b = nbt.getByte("S");
		if (b >= 0 && b < Sign.values().length) {
			this.sign = Sign.values()[b];
		} else {
			this.sign = Sign.PALM;
		}
	}
}

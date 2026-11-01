package com.captrojo.crwg.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;

public class BlockPressurePlateCRWG extends BlockPressurePlate
{
	public BlockPressurePlateCRWG(String name, Block parent, Sensitivity sensitivity)
	{
		super(parent.textureName, parent.getMaterial(), sensitivity);
		this.setBlockName(name);
		this.setCreativeTab(null);

		this.textureName = parent.textureName;
		this.blockHardness = parent.blockHardness;
		this.blockResistance = parent.blockResistance;
		this.stepSound = parent.stepSound;
	}
}

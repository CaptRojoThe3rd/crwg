package com.captrojo.crwg.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockButtonCRWG extends BlockButton
{	
	public BlockButtonCRWG(String name, Block parent, boolean wooden)
	{
		super(wooden);
		this.setBlockName(name);
		this.setCreativeTab(null);
		
		this.textureName = parent.textureName;
		this.blockHardness = parent.blockHardness;
		this.blockResistance = parent.blockResistance;
		this.stepSound = parent.stepSound;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = reg.registerIcon(this.textureName);
	}
}

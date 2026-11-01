package com.captrojo.crwg.block;

import java.util.Random;

import com.captrojo.crwg.render.block.BlockRenderIDs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class Block2x2FloorPlant extends BlockBushCRWG
{
	@SideOnly(Side.CLIENT)
	public IIcon[] icons;
	
	public Block2x2FloorPlant(String name)
	{
		this(name, Material.plants, EnumPlantType.Plains);
	}

	public Block2x2FloorPlant(String name, Material material, EnumPlantType type)
	{
		super(name, material, type, 1);
	        this.setSize(1.0F, 0.03125F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return this.textureName;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.icons = new IIcon[4];
		this.icons[0] = reg.registerIcon(this.textureName + "_a");
		this.icons[1] = reg.registerIcon(this.textureName + "_b");
		this.icons[2] = reg.registerIcon(this.textureName + "_c");
		this.icons[3] = reg.registerIcon(this.textureName + "_d");
		this.blockIcon = this.icons[0];
	}
	
	@Override
	public int getRenderType()
	{
		return BlockRenderIDs.FLOOR_PLANT_2X2.id;
	}
	
	@Override
	public int quantityDropped(int fortune, int meta, Random rand)
	{
		int q = 0;
		for (int m = meta; m != 0; m >>>= 1) {
			if ((m & 1) != 0) {
				q++;
			}
		}
		return q;
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block ground)
	{
		return ground == this;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return this.type;
	}
}

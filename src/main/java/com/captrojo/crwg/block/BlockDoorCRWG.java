package com.captrojo.crwg.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDoorCRWG extends BlockDoor
{
	public int flammability = 0;
	public int fire_spread_speed = 0;
	
	public BlockDoorCRWG(String name, Material material)
	{
		super(material);
		this.setBlockName(name);
		
		if (material == Material.wood) {
			this.flammability = 5;
			this.fire_spread_speed = 20;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, int x, int y, int z)
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return this.textureName;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return this.flammability;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return this.fire_spread_speed;
	}
}

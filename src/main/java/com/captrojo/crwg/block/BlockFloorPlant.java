package com.captrojo.crwg.block;

import com.captrojo.crwg.render.block.BlockRenderIDs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.EnumPlantType;

public class BlockFloorPlant extends BlockBushCRWG
{
	public BlockFloorPlant(String name)
	{
		this(name, Material.plants, EnumPlantType.Plains);
	}
	
	public BlockFloorPlant(String name, Material material, EnumPlantType type)
	{
		super(name, material, type);
	        this.setSize(1.0F, 0.03125F);
	}
	
	@Override
	public int getRenderType()
	{
		return BlockRenderIDs.FLOOR_PLANT.id;
	}
}

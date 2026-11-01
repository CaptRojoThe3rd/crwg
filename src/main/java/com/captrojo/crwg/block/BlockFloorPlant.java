package com.captrojo.crwg.block;

import com.captrojo.crwg.render.block.BlockRenderIDs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.EnumPlantType;

public class BlockFloorPlant extends BlockBushCRWG
{
	public BlockFloorPlant(String name, int variants)
	{
		this(name, Material.plants, EnumPlantType.Plains, variants);
	}
	
	public BlockFloorPlant(String name, Material material, EnumPlantType type, int variants)
	{
		super(name, material, type, variants);
	        this.setSize(1.0F, 0.03125F);
	}
	
	@Override
	public int getRenderType()
	{
		return BlockRenderIDs.FLOOR_PLANT.id;
	}
}

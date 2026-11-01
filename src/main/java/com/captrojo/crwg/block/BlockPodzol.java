package com.captrojo.crwg.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockPodzol extends BlockCRWG
{
	@SideOnly(Side.CLIENT)
	private IIcon side_icon;
	@SideOnly(Side.CLIENT)
	private IIcon top_icon;

	private final Block grass_block;

	public BlockPodzol(String name, Block grass_block)
	{
		super(name, Material.ground);
		this.setHarvestLevel("shovel", 0);
		this.grass_block = grass_block;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = reg.registerIcon(this.textureName + "_dirt");
		this.side_icon = reg.registerIcon(this.textureName + "_podzol_side");
		this.top_icon = reg.registerIcon(this.textureName + "_podzol_top");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 1) {
			return this.top_icon;
		}
		if (side != 0) {
			return this.side_icon;
		}
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if (side == 1) {
			return this.top_icon;
		}
		if (side != 0) {
			Material mat = world.getBlock(x, y, z).getMaterial();
			if (mat == Material.snow || mat == Material.craftedSnow) {
				return this.grass_block.getIcon(world, x, y, z, side);
			}
			return this.side_icon;
		}
		return this.blockIcon;
	}
}

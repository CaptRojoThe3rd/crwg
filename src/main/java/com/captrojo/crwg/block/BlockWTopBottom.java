package com.captrojo.crwg.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockWTopBottom extends BlockCRWG
{
	protected IIcon top_icon;
	protected IIcon bottom_icon;

	private String top_icon_name;
	private String bottom_icon_name;

	public BlockWTopBottom(String name, Material material)
	{
		super(name, material);
	}

	public BlockWTopBottom setTopIconName(String name)
	{
		this.top_icon_name = name;
		return this;
	}

	public BlockWTopBottom setBottomIconName(String name)
	{
		this.bottom_icon_name = name;
		return this;
	}

	@Override
	public BlockWTopBottom setHarvestTool(String tool, int level)
	{
		super.setHarvestTool(tool, level);
		return this;
	}

	@Override
	public BlockWTopBottom setHardness(float hardness)
	{
		super.setHardness(hardness);
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0) {
			return this.bottom_icon;
		}
		if (side == 1) {
			return this.top_icon;
		}
		return super.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		super.registerBlockIcons(reg);
		if (this.top_icon_name == null || this.bottom_icon_name == null) {
			this.top_icon = reg.registerIcon(this.textureName + "_top");
			this.bottom_icon = reg.registerIcon(this.textureName + "_bottom");
		} else {
			this.top_icon = reg.registerIcon(this.top_icon_name);
			this.bottom_icon = reg.registerIcon(this.bottom_icon_name);
		}
	}
}

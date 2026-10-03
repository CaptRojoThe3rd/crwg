package com.captrojo.crwg.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockModLog extends BlockLog
{
	private int flammability = 5;
	private int fire_spread_speed = 5;
	
	public BlockModLog(String name)
	{
		super();
		this.setBlockName(name);
		this.setCreativeTab(null);
	}
	
	public final BlockModLog setFlammable(int fire_spread_speed, int flammability)
	{
		this.flammability = flammability;
		this.fire_spread_speed = fire_spread_speed;
		return this;
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
	
	@Override
	public int damageDropped(int meta)
	{
		if ((meta & 0xc) == 0xc) {
			return meta;
		}
		return meta & 0x1;
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float sx, float sy, float sz, int meta)
	{
		if ((meta & 0xc) == 0xc) {
			return meta;
		}
		return super.onBlockPlaced(world, x, y, z, side, sx, sy, sz, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.field_150167_a = new IIcon[] {
			reg.registerIcon(this.textureName + "_side"),
			reg.registerIcon(this.textureName + "_side_stripped")
		};
		this.field_150166_b = new IIcon[] {
			reg.registerIcon(this.textureName + "_end"),
			reg.registerIcon(this.textureName + "_end_stripped")
		};
	}
}

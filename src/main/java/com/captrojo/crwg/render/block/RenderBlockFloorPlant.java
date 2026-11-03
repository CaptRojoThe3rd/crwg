package com.captrojo.crwg.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderBlockFloorPlant implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int model_id, RenderBlocks rb)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int model_id, RenderBlocks rb)
	{
		long rng = ((long) x * 3129871L) ^ ((long) z * 116129781L) ^ (long) y;
		rng = (rng * rng * 42317861L) + (rng * 11L);
		int rng0 = (int) rng;

		IIcon icon = block.getIcon(world, x, y, z, 1);
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();

		double ux0z0, vx0z0;
		double ux1z0, vx1z0;
		double ux0z1, vx0z1;
		double ux1z1, vx1z1;

		int rot = rng0 & 3;
		if (rot == 0) {
			ux0z0 = u0;
			vx0z0 = v0;
			ux1z0 = u1;
			vx1z0 = v0;
			ux0z1 = u0;
			vx0z1 = v1;
			ux1z1 = u1;
			vx1z1 = v1;
		} else if (rot == 1) {
			ux0z0 = u0;
			vx0z0 = v1;
			ux1z0 = u0;
			vx1z0 = v0;
			ux0z1 = u1;
			vx0z1 = v1;
			ux1z1 = u1;
			vx1z1 = v0;
		} else if (rot == 2) {
			ux0z0 = u1;
			vx0z0 = v1;
			ux1z0 = u0;
			vx1z0 = v1;
			ux0z1 = u1;
			vx0z1 = v0;
			ux1z1 = u0;
			vx1z1 = v0;
		} else {
			ux0z0 = u1;
			vx0z0 = v0;
			ux1z0 = u1;
			vx1z0 = v1;
			ux0z1 = u0;
			vx0z1 = v0;
			ux1z1 = u0;
			vx1z1 = v1;
		}

		int color = block.colorMultiplier(world, x, y, z);

		double xd = (double) x;
		double yd = (double) y + 0.0625;
		double zd = (double) z;

		Tessellator ts = Tessellator.instance;
		ts.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

		ts.setColorOpaque_I(color);
		ts.addVertexWithUV(xd + 1.0, yd, zd + 1.0, ux1z1, vx1z1);
		ts.addVertexWithUV(xd + 1.0, yd, zd, ux1z0, vx1z0);
		ts.addVertexWithUV(xd, yd, zd, ux0z0, vx0z0);
		ts.addVertexWithUV(xd, yd, zd + 1.0, ux0z1, vx0z1);

		ts.setColorOpaque_I((color & 0xfefefe) >> 1);
		ts.addVertexWithUV(xd, yd, zd + 1.0, ux0z1, vx0z1);
		ts.addVertexWithUV(xd, yd, zd, ux0z0, vx0z0);
		ts.addVertexWithUV(xd + 1.0, yd, zd, ux1z0, vx1z1);
		ts.addVertexWithUV(xd + 1.0, yd, zd + 1.0, ux1z1, vx1z1);

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int model_id)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return BlockRenderIDs.FLOOR_PLANT.id;
	}
}

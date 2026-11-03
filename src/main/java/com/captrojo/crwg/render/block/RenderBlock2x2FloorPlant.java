package com.captrojo.crwg.render.block;

import com.captrojo.crwg.block.Block2x2FloorPlant;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class RenderBlock2x2FloorPlant implements ISimpleBlockRenderingHandler
{
	private void renderPiece(double x, double y, double z, IIcon icon, int color, int rot)
	{
		Tessellator ts = Tessellator.instance;

		rot = 1;

		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();

		double ux0z0, vx0z0;
		double ux1z0, vx1z0;
		double ux0z1, vx0z1;
		double ux1z1, vx1z1;

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

		ts.setColorOpaque_I(color);
		ts.addVertexWithUV(x + 0.5, y, z + 0.5, ux1z1, vx1z1);
		ts.addVertexWithUV(x + 0.5, y, z, ux1z0, vx1z0);
		ts.addVertexWithUV(x, y, z, ux0z0, vx0z0);
		ts.addVertexWithUV(x, y, z + 0.5, ux0z1, vx0z1);

		ts.setColorOpaque_I((color & 0xfefefe) >> 1);
		ts.addVertexWithUV(x, y, z + 0.5, ux0z1, vx0z1);
		ts.addVertexWithUV(x, y, z, ux0z0, vx0z0);
		ts.addVertexWithUV(x + 0.5, y, z, ux1z0, vx1z1);
		ts.addVertexWithUV(x + 0.5, y, z + 0.5, ux1z1, vx1z1);
	}

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks rb)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int model_id, RenderBlocks rb)
	{
		if (!(block instanceof Block2x2FloorPlant blockplant)) {
			return false;
		}

		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0) {
			meta = 0xf;
		}
		boolean tl = (meta & 0x1) != 0;
		boolean tr = (meta & 0x2) != 0;
		boolean bl = (meta & 0x4) != 0;
		boolean br = (meta & 0x8) != 0;

		long rng = ((long) x * 3129871L) ^ ((long) z * 116129781L) ^ (long) y;
		rng = (rng * rng * 42317861L) + (rng * 11L);
		int rng0 = (int) rng;
		int rng1 = (int) (rng >> 32);

		Tessellator ts = Tessellator.instance;

		int color = block.colorMultiplier(world, x, y, z);

		ts.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

		double xd = (double) x;
		double yd = (double) y;
		double zd = (double) z;

		if (blockplant.getPlantType(world, x, y, z) == EnumPlantType.Water) {
			yd += 0.015625;
		} else {
			yd += 0.0625;
		}

		IIcon icon;

		if (tl) {
			icon = blockplant.getIconFromRNG(0, rng1);
			this.renderPiece(xd, yd, zd, icon, color, rng0 & 3);
		}
		if (tr) {
			icon = blockplant.getIconFromRNG(1, rng1);
			this.renderPiece(xd + 0.5, yd, zd, icon, color, (rng0 >> 2) & 3);
		}
		if (bl) {
			icon = blockplant.getIconFromRNG(2, rng1);
			this.renderPiece(xd, yd, zd + 0.5, icon, color, (rng0 >> 4) & 3);
		}
		if (br) {
			icon = blockplant.getIconFromRNG(3, rng1);
			this.renderPiece(xd + 0.5, yd, zd + 0.5, icon, color, (rng0 >> 6) & 3);
		}

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
		return BlockRenderIDs.FLOOR_PLANT_2X2.id;
	}
}

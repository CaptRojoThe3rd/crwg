package com.captrojo.crwg.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderBlockDoublePlant implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks rb)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int model_id, RenderBlocks rb)
	{
		BlockDoublePlant blockplant = (BlockDoublePlant) block;

		Tessellator ts = Tessellator.instance;
		ts.setBrightness(blockplant.getMixedBrightnessForBlock(world, x, y, z));

		int color = blockplant.colorMultiplier(world, x, y, z);
		float red = (float) (color >> 16 & 255) / 255.0F;
		float green = (float) (color >> 8 & 255) / 255.0F;
		float blue = (float) (color & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable) {
			float red2 = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
			float green2 = (red * 30.0F + green * 70.0F) / 100.0F;
			float blue2 = (red * 30.0F + blue * 70.0F) / 100.0F;
			red = red2;
			green = green2;
			blue = blue2;
		}

		ts.setColorOpaque_F(red, green, blue);

		long rng = (long) (x * 3129871) ^ (long) z * 116129781L;
		rng = rng * rng * 42317861L + rng * 11L;

		double xd = (double) x;
		double yd = (double) y;
		double zd = (double) z;
		xd += ((double) ((float) (rng >> 16 & 15L) / 15.0F) - 0.5D) * 0.3D;
		zd += ((double) ((float) (rng >> 24 & 15L) / 15.0F) - 0.5D) * 0.3D;

		int meta = world.getBlockMetadata(x, y, z);

		boolean is_upper = BlockDoublePlant.func_149887_c(meta);
		int meta_lo;

		if (is_upper) {
			if (world.getBlock(x, y - 1, z) != blockplant) {
				return false;
			}
			meta_lo = BlockDoublePlant.func_149890_d(world.getBlockMetadata(x, y - 1, z));
		} else {
			meta_lo = BlockDoublePlant.func_149890_d(meta);
		}

		IIcon icon = blockplant.func_149888_a(is_upper, meta_lo);
		rb.drawCrossedSquares(icon, xd, yd, zd, 1.0F);

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
		return BlockRenderIDs.DOUBLE_PLANT.id;
	}
}

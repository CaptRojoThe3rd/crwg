package com.captrojo.crwg.render.block;

import com.captrojo.crwg.block.BlockDoublePlantCRWG;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderBlockDoublePlant implements ISimpleBlockRenderingHandler
{
	private void render(IIcon icon, int brightness, int color, double xd, double yd, double zd, RenderBlocks rb)
	{
		Tessellator ts = Tessellator.instance;
		ts.setBrightness(brightness);

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
		rb.drawCrossedSquares(icon, xd, yd, zd, 1.0F);
	}
	
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks rb)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int model_id, RenderBlocks rb)
	{
		BlockDoublePlantCRWG blockplant = (BlockDoublePlantCRWG) block;

		int brightness = blockplant.getMixedBrightnessForBlock(world, x, y, z);

		long rng = (long) (x * 3129871) ^ (long) z * 116129781L;
		rng = rng * rng * 42317861L + rng * 11L;

		double xd = (double) x;
		double yd = (double) y;
		double zd = (double) z;
		xd += ((double) ((float) (rng >> 16 & 15L) / 15.0F) - 0.5D) * 0.3D;
		zd += ((double) ((float) (rng >> 24 & 15L) / 15.0F) - 0.5D) * 0.3D;
		
		IIcon icon = blockplant.getIcon(world, x, y, z, 0, false);
		this.render(icon, brightness, block.colorMultiplier(world, x, y, z), xd, yd, zd, rb);
		if (blockplant.non_colored_layer) {
			icon = blockplant.getIcon(world, x, y, z, 0, true);
			this.render(icon, brightness, 0xffffff, xd, yd, zd, rb);
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
		return BlockRenderIDs.DOUBLE_PLANT.id;
	}
}

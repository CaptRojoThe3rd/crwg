package com.captrojo.crwg.render.tileentity;

import com.captrojo.crwg.tileentity.TESignCRWG;

import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ResourceLocation;

public class RenderTESignCRWG extends TileEntitySignRenderer
{
	@Override
	protected void bindTexture(ResourceLocation resource)
	{
	}

	@Override
	public void renderTileEntityAt(TileEntitySign sign, double x, double y, double z, float fticks)
	{
		if (sign instanceof TESignCRWG sign_crwg) {
			super.bindTexture(sign_crwg.sign.resource);
		} else {
			super.bindTexture(TileEntitySignRenderer.field_147513_b);
		}
		super.renderTileEntityAt(sign, x, y, z, fticks);
	}
}

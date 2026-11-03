package com.captrojo.crwg.mixins.early.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.captrojo.crwg.block.BlockGrassCRWG;
import com.captrojo.crwg.block.ModBlocks;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;

@Mixin(RenderBlocks.class)
public abstract class RenderBlocksMixin
{
	@Shadow public float colorRedTopLeft;
	@Shadow public float colorRedBottomLeft;
	@Shadow public float colorRedBottomRight;
	@Shadow public float colorRedTopRight;
	@Shadow public float colorGreenTopLeft;
	@Shadow public float colorGreenBottomLeft;
	@Shadow public float colorGreenBottomRight;
	@Shadow public float colorGreenTopRight;
	@Shadow public float colorBlueTopLeft;
	@Shadow public float colorBlueBottomLeft;
	@Shadow public float colorBlueBottomRight;
	@Shadow public float colorBlueTopRight;

	@Shadow
	public abstract void renderFaceZNeg(Block block, double x, double y, double z, IIcon icon);

	@Shadow
	public abstract void renderFaceZPos(Block block, double x, double y, double z, IIcon icon);

	@Shadow
	public abstract void renderFaceXNeg(Block block, double x, double y, double z, IIcon icon);

	@Shadow
	public abstract void renderFaceXPos(Block block, double x, double y, double z, IIcon icon);

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusion",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceZNeg(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_zneg_rsbwao(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceZNeg(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusion",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceZPos(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_zpos_rsbwao(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceZPos(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusion",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceXNeg(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_xneg_rsbwao(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceXNeg(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusion",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceXPos(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_xpos_rsbwao(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceXPos(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusionPartial",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceZNeg(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_zneg_rsbwaop(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceZNeg(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusionPartial",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceZPos(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_zpos_rsbwaop(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceZPos(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusionPartial",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceXNeg(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_xneg_rsbwaop(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceXNeg(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusionPartial",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/RenderBlocks;renderFaceXPos(Lnet/minecraft/block/Block;DDDLnet/minecraft/util/IIcon;)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void crwg$grassBlockHack_xpos_rsbwaop(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir, 
		@Local(name = "iicon") IIcon iicon
	)
	{
		if (!RenderBlocks.fancyGrass) {
			return;
		}
		if (!(block instanceof BlockGrassCRWG grass)) {
			return;
		}

		this.colorRedTopLeft *= r;
                this.colorRedBottomLeft *= r;
                this.colorRedBottomRight *= r;
                this.colorRedTopRight *= r;
                this.colorGreenTopLeft *= g;
                this.colorGreenBottomLeft *= g;
                this.colorGreenBottomRight *= g;
                this.colorGreenTopRight *= g;
                this.colorBlueTopLeft *= b;
                this.colorBlueBottomLeft *= b;
                this.colorBlueBottomRight *= b;
                this.colorBlueTopRight *= b;

                this.renderFaceXPos(block, x, y, z, grass.getSideOverlayIcon());
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusion",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/Tessellator;setBrightness(I)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void grassBlockCheck_rsbwao(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir,
		@Local(name = "flag1") LocalBooleanRef flag1
	)
	{
		if (ModBlocks.isModGrassBlock(block)) {
			flag1.set(false);
		}
	}

	@Inject(
		method = "renderStandardBlockWithAmbientOcclusionPartial",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/Tessellator;setBrightness(I)V",
			shift = At.Shift.AFTER,
			ordinal = 0
		)
	)
	private void grassBlockCheck_rsbwaop(
		Block block,
		int x,
		int y,
		int z,
		float r,
		float g,
		float b,
		CallbackInfoReturnable<Boolean> cir,
		@Local(name = "flag1") LocalBooleanRef flag1
	)
	{
		if (ModBlocks.isModGrassBlock(block)) {
			flag1.set(false);
		}
	}

	@Definition(id = "grass", field = "Lnet/minecraft/init/Blocks;grass:Lnet/minecraft/block/BlockGrass;")
	@Expression(value = "? != grass")
	@ModifyExpressionValue(
		method = "renderStandardBlockWithColorMultiplier",
		at = @At("MIXINEXTRAS:EXPRESSION")
	)
	private boolean grassBlockCheck_rsbwcm(boolean original, @Local(argsOnly = true) Block block)
	{
		return original && !ModBlocks.isModGrassBlock(block);
	}

	@Definition(id = "grass", field = "Lnet/minecraft/init/Blocks;grass:Lnet/minecraft/block/BlockGrass;")
	@Expression(value = "? == grass")
	@ModifyExpressionValue(
		method = "renderBlockAsItem",
		at = @At("MIXINEXTRAS:EXPRESSION")
	)
	private boolean grassBlockCheck_rbai(boolean original, @Local(argsOnly = true) Block block)
	{
		return original || ModBlocks.isModGrassBlock(block);
	}
}

package com.captrojo.crwg.mixins.early.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.captrojo.crwg.block.ModBlocks;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;

@Mixin(BlockDeadBush.class)
public class BlockDeadBushMixin
{
	@Definition(id = "dirt", field = "Lnet/minecraft/init/Blocks;dirt:Lnet/minecraft/block/Block;")
	@Expression(value = "? == dirt")
	@ModifyExpressionValue(
		method = "canPlaceBlockOn",
		at = @At("MIXINEXTRAS:EXPRESSION")
	)
	private boolean dirtBlockCheck(boolean original)
	{
		return original || ModBlocks.isModDirtBlock((Block) (Object) this);
	}
}

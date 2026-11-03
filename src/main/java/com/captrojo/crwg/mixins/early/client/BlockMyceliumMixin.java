package com.captrojo.crwg.mixins.early.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.captrojo.crwg.main.CRWG;

import net.minecraft.block.BlockMycelium;
import net.minecraft.client.renderer.texture.IIconRegister;

@Mixin(BlockMycelium.class)
public abstract class BlockMyceliumMixin
{
	@Inject(
		method = "registerBlockIcons(Lnet/minecraft/client/renderer/texture/IIconRegister;)V",
		at = @At(value = "RETURN"),
		require = 1
	)
	private void changeSnowySideIcon(IIconRegister reg, CallbackInfo ci)
	{
		BlockMycelium b = (BlockMycelium) (Object) this;
		b.field_150199_b = reg.registerIcon(CRWG.ident("soil/normal_mycelium_side_snowed"));
	}
}

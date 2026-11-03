package com.captrojo.crwg.mixins.early.common.joke;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.gen.structure.MapGenVillage;

@Mixin(MapGenVillage.class)
public abstract class MapGenVillageMixin
{
	@Inject(method = "<init>(Ljava/util/Map;)V", at = @At(value = "RETURN"), require = 1)
	private void reduceDistances(CallbackInfo ci)
	{
		MapGenVillage v = (MapGenVillage) (Object) this;
		v.field_82665_g = 2;
		v.field_82666_h = 1;
	}
}

package com.captrojo.crwg.mixins;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class LateMixins implements ILateMixinLoader
{
	@Override
	public String getMixinConfig()
	{
		return "mixins.crwg.late.json";
	}

	@Nonnull
	@Override
	public List<String> getMixins(Set<String> loaded_mods)
	{
		List<String> mixins = new ArrayList<>();
		return mixins;
	}
}

package com.captrojo.crwg.mixins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class EarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader
{
	@Override
	public String[] getASMTransformerClass()
	{
		return null;
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}

	@Override
	public String getMixinConfig()
	{
		return "mixins.crwg.early.json";
	}

	@Nonnull
	@Override
	public List<String> getMixins(Set<String> loadedCoreMods)
	{
		List<String> mixins = new ArrayList<>();
		return mixins;
	}
}

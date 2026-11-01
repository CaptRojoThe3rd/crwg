package com.captrojo.crwg.mixins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.MixinEnvironment;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class EarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader
{
	public static final MixinEnvironment.Side SIDE = MixinEnvironment.getCurrentEnvironment().getSide();
	
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

	@Override
	public @Nonnull List<String> getMixins(Set<String> loaded_core_mods)
	{
		List<String> mixins = new ArrayList<>();
		
		boolean angelica = false;
		for (String mod : loaded_core_mods) {
			if (mod.contains("com.gtnewhorizons.angelica")) {
				angelica = true;
				break;
			}
		}
		
		mixins.add("common.BlockDeadBushMixin");
		
		mixins.add("common.ChunkMixin");
		mixins.add("common.WorldMixin");
		
		if (SIDE == MixinEnvironment.Side.CLIENT && !angelica) {
			mixins.add("client.RenderBlocksMixin");
		}
		
		return mixins;
	}
}

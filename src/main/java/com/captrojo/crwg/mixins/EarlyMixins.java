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
public class EarlyMixins extends ConfigurableMixins implements IFMLLoadingPlugin, IEarlyMixinLoader
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
		this.loadConfig("early_mixins");
		List<String> mixins = new ArrayList<>();

		boolean angelica = false;
		for (String mod : loaded_core_mods) {
			if (mod.contains("com.gtnewhorizons.angelica")) {
				angelica = true;
				break;
			}
		}

		this.addMixin(mixins, "common.BlockDeadBushMixin");

		this.addMixin(mixins, "common.ChunkMixin");
		this.addMixin(mixins, "common.WorldMixin");

		this.addMixin(mixins, "common.joke.MapGenVillageMixin");

		if (SIDE == MixinEnvironment.Side.CLIENT) {
			this.addMixin(mixins, "client.BlockMyceliumMixin");

			if (!angelica) {
				this.addMixin(mixins, "client.RenderBlocksMixin");
			}
		}

		this.saveConfig();

		return mixins;
	}
}

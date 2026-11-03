package com.captrojo.crwg.mixins;

import java.io.File;
import java.util.List;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;

public abstract class ConfigurableMixins
{
	Configuration config;
	
	void loadConfig(String name)
	{
		File config_dir = new File(Launch.minecraftHome, "config" + File.separator + "crwg");
		File config_file = new File(config_dir, name + ".cfg");
		this.config = new Configuration(config_file);
	}
	
	void saveConfig()
	{
		this.config.save();
	}
	
	void addMixin(List<String> mixins, String name)
	{
		String category = "";
		String setting_name = "";
		String[] split = name.split("\\.");
		for (int i = 0; i < split.length; i++) {
			if (i == (split.length - 1)) {
				setting_name = split[i];
			} else {
				if (category.length() != 0) {
					category += ".";
				}
				category += split[i];
			}
		}
		if (!this.config.getBoolean(setting_name, category, true, name)) {
			System.out.println("CRWG mixin " + name + " is disabled");
			return;
		}
		mixins.add(name);
	}
}

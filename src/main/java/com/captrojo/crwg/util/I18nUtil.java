package com.captrojo.crwg.util;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;

public abstract class I18nUtil
{
	@SideOnly(Side.CLIENT)
	public static void addItemDesc(ItemStack stack, List<String> list)
	{
		String key = stack.getUnlocalizedName() + ".desc";
		String val = I18nUtil.get(key);
		
		if (key.equals(val)) {
			return;
		}
		
		String[] lines = val.split("\\n");
		for (String str : lines) {
			list.add(str);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static String get(String key)
	{
		return I18n.format(key);
	}
	
	@SideOnly(Side.CLIENT)
	public static String getf(String key, Object...data)
	{
		return I18n.format(key, data);
	}
	
	public static ChatComponentTranslation chat(String key)
	{
		return new ChatComponentTranslation(key);
	}
	
	public static ChatComponentTranslation chatf(String key, Object...data)
	{
		return new ChatComponentTranslation(key, data);
	}
}

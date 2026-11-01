package com.captrojo.crwg.main;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Some helpful GameRegistry functions
 */
public class GameRegistry2
{
	/**
	 * Register a block with a specified item, rather than letting Forge create the item
	 * itself. The item should be an instance of ItemBlock, or Forge will screw up the ID.
	 */
	public static Block registerBlock(Block block, Item item, String name)
	{
		try {
			assert block != null : "registerBlock: block cannot be null";
			GameData.getMain().registerBlock(block, name);
			if (item != null) {
				GameData.getMain().registerItem(item, name);
			}
			return block;
		} catch (Exception e) {
			FMLLog.log(Level.ERROR, e, "Caught an exception during block registration");
			throw new LoaderException(e);
		}
	}
}

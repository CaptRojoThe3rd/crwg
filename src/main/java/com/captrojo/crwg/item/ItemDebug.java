package com.captrojo.crwg.item;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.world.gen.feature.tree.WorldGenTallTree;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

/**
 * Use this for whatever you are currently testing, it isn't meant to do any specific thing
 */
public class ItemDebug extends Item
{
	public ItemDebug()
	{
		this.setUnlocalizedName("debug_item");
		this.setTextureName(CRWG.ident("debug_item"));
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hx, float hy, float hz)
	{
		if (world.isRemote) {
			return true;
		}

		(new WorldGenTallTree(true)).generate(world, world.rand, x + Facing.offsetsXForSide[side], y + Facing.offsetsYForSide[side], z + Facing.offsetsZForSide[side]);
		return true;
	}
}

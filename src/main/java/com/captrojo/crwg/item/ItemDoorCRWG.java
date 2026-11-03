package com.captrojo.crwg.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDoorCRWG extends ItemBlockCRWG
{
	public ItemDoorCRWG(Block block)
	{
		super(block);
		this.setCreativeTab(null);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hx, float hy, float hz)
	{
		if (side != 1) {
			return false;
		}

		y++;
		Block block = this.field_150939_a;

		if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			if (!block.canPlaceBlockAt(world, x, y, z)) {
				return false;
			}
			int meta = MathHelper.floor_double((double) ((player.rotationYaw + 180.0f) * 4.0f / 360.0f) - 0.5) & 3;
			ItemDoor.placeDoorBlock(world, x, y, z, meta, block);
			stack.stackSize--;
			return true;
		}

		return false;
	}
}

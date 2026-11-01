package com.captrojo.crwg.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSignCRWG extends ItemBlockCRWG
{
	public final Block standing_sign, wall_sign;

	public ItemSignCRWG(String name, String texture_name, Block standing_sign, Block wall_sign)
	{
		super(standing_sign);

		this.standing_sign = standing_sign;
		this.wall_sign = wall_sign;

		this.setUnlocalizedName(name);
		this.setTextureName(texture_name);
		this.setCreativeTab(null);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "tile." + this.unlocalizedName;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "tile." + this.unlocalizedName;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hx, float hy, float hz)
	{
		if (world.isRemote) {
			return true;
		}
		if (side == 0) {
			return false;
		}
		if (!world.getBlock(x, y, z).getMaterial().isSolid()) {
			return false;
		}

		switch (side) {
		case 1:
			y++;
			break;
		case 2:
			z--;
			break;
		case 3:
			z++;
			break;
		case 4:
			x--;
			break;
		case 5:
			x++;
			break;
		}

		if (!player.canPlayerEdit(x, y, z, side, stack)) {
			return false;
		}
		if (!Blocks.standing_sign.canPlaceBlockAt(world, x, y, z)) {
			return false;
		}

		if (side == 1) {
			int meta = MathHelper.floor_double((double) ((player.rotationYaw + 180.0f) * 16.0f / 360.0f) + 0.5) & 0xf;
			world.setBlock(x, y, z, this.standing_sign, meta, 3);
		} else {
			world.setBlock(x, y, z, this.wall_sign, side, 3);
		}

		stack.stackSize--;
		TileEntitySign tileentitysign = (TileEntitySign) world.getTileEntity(x, y, z);

		if (tileentitysign != null) {
			player.func_146100_a(tileentitysign);
		}

		return true;
	}
}

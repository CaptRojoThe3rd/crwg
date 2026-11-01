package com.captrojo.crwg.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class Item2x2FloorPlant extends ItemBlock
{
	final boolean is_surface_water_plant;

	public Item2x2FloorPlant(Block block, boolean is_surface_water_plant)
	{
		super(block);
		this.is_surface_water_plant = is_surface_water_plant;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		return this.field_150939_a.getRenderColor(stack.getItemDamage());
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hx, float hy, float hz)
	{
		if (side != 1) {
			return false;
		}

		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if (block != this.field_150939_a) {
			if (!block.canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable) this.field_150939_a)) {
				return false;
			}
			y++;

			block = world.getBlock(x, y, z);
			if (
				block != this.field_150939_a &&
				!block.isReplaceable(world, x, y, z) &&
				!block.isAir(world, x, y, z)
			) {
				return false;
			}

			if (!player.canPlayerEdit(x, y, z, side, stack)) {
				return false;
			}

			if (world.isRemote) {
				return true;
			}

			if (!world.setBlock(x, y, z, this.field_150939_a, 0, 4)) {
				return true;
			}
		} else {
			if ((meta & 0xf) == 0xf) {
				return false;
			}
			if (world.isRemote) {
				return true;
			}
		}
		
		int old_meta = meta;
		if (hz < 0.5F) {
			if (hx < 0.5F) {
				meta |= 0b0001;
			} else {
				meta |= 0b0010;
			}
		} else {
			if (hx < 0.5F) {
				meta |= 0b0100;
			} else {
				meta |= 0b1000;
			}
		}
		if (old_meta == meta) {
			return true;
		}

		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
		world.playSoundEffect(
			(double) x + 0.5,
			(double) y + 0.5,
			(double) z + 0.5,
			this.field_150939_a.stepSound.func_150496_b(),
			(this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
			this.field_150939_a.stepSound.getPitch() * 0.8F
		);

		if (!player.capabilities.isCreativeMode) {
			stack.stackSize--;
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!this.is_surface_water_plant) {
			return super.onItemRightClick(stack, world, player);
		}
		
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);

		if (mop == null) {
			return stack;
		}

		if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;

			if (!world.canMineBlock(player, x, y, z)) {
				return stack;
			}
			if (!player.canPlayerEdit(x, y, z, mop.sideHit, stack)) {
				return stack;
			}
			if (world.getBlock(x, y, z).getMaterial() != Material.water) {
				return stack;
			}
			if (world.getBlockMetadata(x, y, z) != 0) {
				return stack;
			}
			if (!world.isAirBlock(x, y + 1, z)) {
				return stack;
			}
			
			float hx = (float) (mop.hitVec.xCoord - (double) x);
			float hy = (float) (mop.hitVec.yCoord - (double) y);
			float hz = (float) (mop.hitVec.zCoord - (double) z);
			
			if (world.isRemote) {
				player.swingItem();
			}

			this.onItemUse(stack, player, world, x, y, z, 1, hx, hy, hz);
		}

		return stack;
	}
}

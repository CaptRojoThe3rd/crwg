package com.captrojo.crwg.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFarmlandCRWG extends BlockFarmland
{
	private final Block dirt;

	public BlockFarmlandCRWG(String name, Block dirt)
	{
		this.setBlockName(name);
		this.setHarvestLevel("shovel", 0);
		this.dirt = dirt;
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		switch (plantable.getPlantType(world, x, y, z)) {
		case Crop:
		case Plains:
			return true;
		default:
			return super.canSustainPlant(world, x, y, z, direction, plantable);
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!this.func_149821_m(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z)) {
			int meta = world.getBlockMetadata(x, y, z);

			if (meta > 0) {
				world.setBlockMetadataWithNotify(x, y, z, meta - 1, 2);
			} else if (!this.func_149822_e(world, x, y, z)) {
				world.setBlock(x, y, z, this.dirt);
			}
		} else {
			world.setBlockMetadataWithNotify(x, y, z, 7, 2);
		}
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fall_distance)
	{
		if (world.isRemote) {
			return;
		}
		if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
			return;
		}
		if (world.rand.nextFloat() >= (fall_distance - 0.5f)) {
			return;
		}
		world.setBlock(x, y, z, this.dirt);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
	{
		super.onNeighborBlockChange(world, x, y, z, neighbor);
		Material material = world.getBlock(x, y + 1, z).getMaterial();

		if (material.isSolid()) {
			world.setBlock(x, y, z, this.dirt);
		}
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return this.dirt.getItemDropped(0, rand, fortune);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 1) {
			return (meta > 0) ? this.field_149824_a : this.field_149823_b;
		}
		return this.dirt.getIcon(side, meta);
	}
}

package com.captrojo.crwg.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSlabCRWG extends BlockSlab
{
	public static boolean isSingleSlab(IBlockAccess world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if (!(block instanceof BlockSlab blockslab)) {
			return false;
		}
		if (blockslab.field_150004_a) {
			return false;
		}
		return true;
	}

	private BlockCRWG parent_block = null;
	private BlockSlabCRWG single_slab = null;

	public int flammability = 0;
	public int fire_spread_speed = 0;

	public BlockSlabCRWG(String name, Material material)
	{
		super(false, material);
		this.setBlockName(name);
	}

	public BlockSlabCRWG(String name, BlockCRWG parent_block)
	{
		super(false, parent_block.getMaterial());
		this.setBlockName(name);

		this.parent_block = parent_block;

		this.setHarvestLevel(parent_block.getHarvestTool(0), parent_block.getHarvestLevel(0));

		this.flammability = parent_block.flammability;
		this.fire_spread_speed = parent_block.fire_spread_speed;
		this.blockHardness = parent_block.blockHardness;
		this.blockResistance = parent_block.blockResistance;
		this.stepSound = parent_block.stepSound;
	}

	public BlockSlabCRWG(String name, BlockSlabCRWG single_slab)
	{
		super(true, single_slab.getMaterial());
		this.setBlockName(name);

		this.single_slab = single_slab;

		this.setHarvestLevel(single_slab.getHarvestTool(0), single_slab.getHarvestLevel(0));

		this.flammability = single_slab.flammability;
		this.fire_spread_speed = single_slab.fire_spread_speed;
		this.blockHardness = single_slab.blockHardness;
		this.blockResistance = single_slab.blockResistance;
		this.stepSound = single_slab.stepSound;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		if (this.single_slab == null && this.parent_block == null) {
			super.registerBlockIcons(reg);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (this.single_slab != null) {
			return this.single_slab.getIcon(side, meta);
		}
		if (this.parent_block != null) {
			return this.parent_block.getIcon(side, meta);
		}
		return super.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		if (this.single_slab != null) {
			return;
		}
		super.getSubBlocks(item, tab, list);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		if (this.single_slab != null) {
			return Item.getItemFromBlock(this.single_slab);
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		if (this.field_150004_a) {
			return super.shouldSideBeRendered(world, x, y, z, side);
		}

		if (side != 1 && side != 0 && !super.shouldSideBeRendered(world, x, y, z, side)) {
			return false;
		}

		int xo = x + Facing.offsetsXForSide[Facing.oppositeSide[side]];
		int yo = y + Facing.offsetsYForSide[Facing.oppositeSide[side]];
		int zo = z + Facing.offsetsZForSide[Facing.oppositeSide[side]];

		int meta = world.getBlockMetadata(xo, yo, zo);

		boolean upper = (meta & 0x8) != 0;
		if (upper) {
			if (side == 0) {
				return true;
			}
			if (side == 1 && super.shouldSideBeRendered(world, x, y, z, side)) {
				return true;
			}
			return !BlockSlabCRWG.isSingleSlab(world, x, y, z) || (world.getBlockMetadata(x, y, z) & 0x8) == 0;
		}

		if (side == 1) {
			return true;
		}
		if (side == 0 && super.shouldSideBeRendered(world, x, y, z, side)) {
			return true;
		}
		return !BlockSlabCRWG.isSingleSlab(world, x, y, z) || (world.getBlockMetadata(x, y, z) & 0x8) != 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if ((meta & 0x4) != 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else if ((meta & 0x8) != 0) {
			this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		if (this.single_slab != null) {
			return Item.getItemFromBlock(this.single_slab);
		}
		return Item.getItemFromBlock(this);
	}

	@Override
	public String func_150002_b(int meta)
	{
		if (this.single_slab != null) {
			return this.single_slab.getUnlocalizedName();
		}
		return this.getUnlocalizedName();
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return this.flammability;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return this.fire_spread_speed;
	}
}

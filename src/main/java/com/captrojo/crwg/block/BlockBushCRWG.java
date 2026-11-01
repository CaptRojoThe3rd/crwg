package com.captrojo.crwg.block;

import java.util.ArrayList;
import java.util.Random;

import com.captrojo.crwg.block.data.Colorizer;
import com.captrojo.crwg.util.ItemUtil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBushCRWG extends BlockBush implements IShearable
{
	public final EnumPlantType type;
	public final int variants;

	boolean shears_required = false;
	Colorizer colorizer;

	public BlockBushCRWG(String name, int variants)
	{
		this(name, Material.plants, EnumPlantType.Plains, 1);
	}

	public BlockBushCRWG(String name, Material material, EnumPlantType type, int variants)
	{
		super(material);
		this.type = type;
		this.variants = variants;

		this.setBlockName(name);
		this.setCreativeTab(null);
		this.setStepSound(Block.soundTypeGrass);

		this.colorizer = Colorizer.NONE;
		this.setSize(0.4F, 0.6F);
	}

	public BlockBushCRWG setSize(float w, float h)
	{
		w /= 2.0F;
		this.setBlockBounds(0.5F - w, 0.0F, 0.5F - w, 0.5F + w, h, 0.5F + w);
		return this;
	}

	public BlockBushCRWG setShearsRequired()
	{
		this.shears_required = true;
		return this;
	}

	public BlockBushCRWG setColorizer(Colorizer colorizer)
	{
		this.colorizer = colorizer;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		return this.colorizer.getRenderColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		return this.colorizer.colorMultipler(world, x, y, z);
	}

	@Override
	public Item getItemDropped(int fortune, Random rand, int meta)
	{
		return this.shears_required ? null : super.getItemDropped(meta, rand, fortune);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return 60;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return this.type;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return this.shears_required;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack stack, IBlockAccess world, int x, int y, int z, int fortune)
	{
		return ItemUtil.createListWithStack(new ItemStack(this));
	}
}

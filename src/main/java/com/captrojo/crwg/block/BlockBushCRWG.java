package com.captrojo.crwg.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.captrojo.crwg.block.data.Colorizer;
import com.captrojo.crwg.util.ItemUtil;
import com.captrojo.crwg.util.RefStrs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBushCRWG extends BlockBush implements IShearable
{
	public final EnumPlantType type;

	public int variants = 1;
	public boolean render_only_variants = false;

	boolean shears_required = false;
	Colorizer colorizer = Colorizer.NONE;

	@SideOnly(Side.CLIENT)
	public IIcon[] icons;

	public BlockBushCRWG(String name, int variants)
	{
		this(name, Material.plants, EnumPlantType.Plains);
	}

	public BlockBushCRWG(String name, Material material, EnumPlantType type)
	{
		super(material);
		this.type = type;

		this.setBlockName(name);
		this.setCreativeTab(null);
		this.setStepSound(Block.soundTypeGrass);

		this.setSize(0.4F, 0.6F);
	}

	public BlockBushCRWG setVariantCount(int variants, boolean render_only_variants)
	{
		this.variants = variants;
		this.render_only_variants = render_only_variants;
		return this;
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
	public void registerBlockIcons(IIconRegister reg)
	{
		this.icons = new IIcon[this.variants];
		if (this.variants <= 1) {
			this.icons[0] = reg.registerIcon(this.textureName);
		} else {
			for (int i = 0; i < this.variants; i++) {
				this.icons[i] = reg.registerIcon(this.textureName + "_" + RefStrs.LETTERS[i]);
			}
		}
		this.blockIcon = this.icons[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.icons[meta % this.icons.length];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if (!this.render_only_variants) {
			return super.getIcon(world, x, y, z, side);
		}
		int rng = (x * 197521) ^ (z * 112663) ^ (y * 408173);
		return this.getIcon(side, rng % this.variants);
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
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		final int v = this.render_only_variants ? 1 : this.variants;
		for (int i = 0; i < v; i++) {
			list.add(new ItemStack(item, 1, i));
		}
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

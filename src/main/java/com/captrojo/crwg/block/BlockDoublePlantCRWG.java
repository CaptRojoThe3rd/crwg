package com.captrojo.crwg.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.captrojo.crwg.block.data.Colorizer;
import com.captrojo.crwg.render.block.BlockRenderIDs;
import com.captrojo.crwg.util.ItemUtil;
import com.captrojo.crwg.util.RefStrs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDoublePlantCRWG extends BlockDoublePlant
{
	final EnumPlantType type;
	final boolean is_grass;

	public int variants = 1;
	public boolean render_only_variants = false;
	boolean shears_required = false;

	Colorizer colorizer = Colorizer.NONE;
	public boolean non_colored_layer = false;

	@SideOnly(Side.CLIENT) IIcon[] bottom_icons_layer_2;
	@SideOnly(Side.CLIENT) IIcon[] top_icons_layer_2;

	String item_texture_name = null;

	public BlockDoublePlantCRWG(String name)
	{
		this(name, EnumPlantType.Plains, false);
	}

	public BlockDoublePlantCRWG(String name, EnumPlantType type, boolean is_grass)
	{
		this.type = type;
		this.is_grass = is_grass;

		this.setBlockName(name);
		this.setCreativeTab(null);
		this.setStepSound(Block.soundTypeGrass);
	}

	public BlockDoublePlantCRWG setVariantCount(int variants, boolean render_only_variants)
	{
		this.variants = variants;
		this.render_only_variants = render_only_variants;
		return this;
	}

	public BlockDoublePlantCRWG setShearsRequired()
	{
		this.shears_required = true;
		return this;
	}

	public BlockDoublePlantCRWG setColorizer(Colorizer colorizer, boolean non_colored_layer)
	{
		this.colorizer = colorizer;
		this.non_colored_layer = non_colored_layer;
		return this;
	}

	public BlockDoublePlantCRWG setItemTextureName(String item_texture_name)
	{
		this.item_texture_name = item_texture_name;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta, boolean second_pass)
	{
		boolean upper = BlockDoublePlant.func_149887_c(meta);
		meta = (meta & 0x7) % this.variants;
		if (second_pass) {
			return upper ? this.top_icons_layer_2[meta] : this.bottom_icons_layer_2[meta];
		}
		return upper ? this.doublePlantTopIcons[meta] : this.doublePlantBottomIcons[meta];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side, boolean second_pass)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (this.render_only_variants) {
			meta &= ~0x7;
			int rng = (x * 997783) ^ (y * 317089) ^ (z * 217081);
			meta |= (rng % this.variants);
		}
		return this.getIcon(side, meta, second_pass);
	}

	@SideOnly(Side.CLIENT)
	private void registerTopBottomIcons(int idx, IIconRegister reg, String append)
	{
		if (this.non_colored_layer) {
			this.doublePlantBottomIcons[idx] = reg.registerIcon(this.textureName + "_bottom_1" + append);
			this.bottom_icons_layer_2[idx] = reg.registerIcon(this.textureName + "_bottom_2" + append);
			this.doublePlantTopIcons[idx] = reg.registerIcon(this.textureName + "_top_1" + append);
			this.top_icons_layer_2[idx] = reg.registerIcon(this.textureName + "_top_2" + append);
		} else {
			this.doublePlantBottomIcons[idx] = reg.registerIcon(this.textureName + "_bottom" + append);
			this.doublePlantTopIcons[idx] = reg.registerIcon(this.textureName + "_top" + append);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return this.item_texture_name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.doublePlantBottomIcons = new IIcon[this.variants];
		this.doublePlantTopIcons = new IIcon[this.variants];
		if (this.non_colored_layer) {
			this.bottom_icons_layer_2 = new IIcon[this.variants];
			this.top_icons_layer_2 = new IIcon[this.variants];
		}

		if (this.variants == 1) {
			this.registerTopBottomIcons(0, reg, "");
		} else {
			for (int i = 0; i < this.variants; i++) {
				this.registerTopBottomIcons(i, reg, "_" + RefStrs.LETTERS[i]);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.getIcon(side, meta, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		return this.getIcon(world, x, y, z, side, false);
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
	public int getRenderType()
	{
		return BlockRenderIDs.DOUBLE_PLANT.id;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return this.shears_required ? null : Item.getItemFromBlock(this);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		if (func_149887_c(meta)) {
			if (world.getBlock(x, y - 1, z) == this) {
				if (!player.capabilities.isCreativeMode) {
					int bottom_meta = world.getBlockMetadata(x, y - 1, z);

					if (!this.is_grass) {
						world.func_147480_a(x, y - 1, z, true);
					} else {
						if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
							/* dropTwoShortGrass */
							/* TODO: replace this */
							this.func_149886_b(world, x, y, z, bottom_meta, player);
						}

						world.setBlockToAir(x, y - 1, z);
					}
				} else {
					world.setBlockToAir(x, y - 1, z);
				}
			}
		} else if (player.capabilities.isCreativeMode && world.getBlock(x, y + 1, z) == this) {
			world.setBlock(x, y + 1, z, Blocks.air, 0, 2);
		}

		super.onBlockHarvested(world, x, y, z, meta, player);
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

package com.captrojo.crwg.block;

import java.util.ArrayList;
import java.util.Random;

import com.captrojo.crwg.render.block.BlockRenderIDs;
import com.captrojo.crwg.util.ItemUtil;
import com.captrojo.crwg.util.RefStrs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDoublePlantCRWG extends BlockDoublePlant
{
	public final int variants;
	public final boolean is_grass;

	public BlockDoublePlantCRWG(String name, int variants, boolean is_grass)
	{
		this.variants = variants;
		this.is_grass = is_grass;

		this.setBlockName(name);
		this.setStepSound(Block.soundTypeGrass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.doublePlantBottomIcons = new IIcon[this.variants];
		this.doublePlantTopIcons = new IIcon[this.variants];

		if (this.variants == 1) {
			this.doublePlantBottomIcons[0] = reg.registerIcon(this.textureName + "_bottom");
			this.doublePlantTopIcons[0] = reg.registerIcon(this.textureName + "_top");
		} else {
			for (int i = 0; i < this.variants; i++) {
				this.doublePlantBottomIcons[i] = reg.registerIcon(this.textureName + "_bottom_" + RefStrs.LETTERS[i]);
				this.doublePlantTopIcons[i] = reg.registerIcon(this.textureName + "_top_" + RefStrs.LETTERS[i]);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return func_149887_c(meta) ? this.doublePlantBottomIcons[0] : this.doublePlantBottomIcons[meta & 0x7];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		if (this.is_grass) {
			return world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z);
		}
		return 0xffffff;
	}

	@Override
	public int getRenderType()
	{
		return BlockRenderIDs.DOUBLE_PLANT.id;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this);
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
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return this.is_grass;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack stack, IBlockAccess world, int x, int y, int z, int fortune)
	{
		return ItemUtil.createListWithStack(new ItemStack(this));
	}
}

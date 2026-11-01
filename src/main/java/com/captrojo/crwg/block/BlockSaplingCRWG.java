package com.captrojo.crwg.block;

import java.util.List;
import java.util.Random;

import com.captrojo.crwg.util.WorldFunction;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSaplingCRWG extends BlockSapling
{
	public static enum Arrangement
	{
		SINGLE(0, 0),
		TWOBYTWO(-1, -1);

		/** offsets for finding saplings */
		final int xo, zo;
		/** size of area of saplings */
		final int xs, zs;

		Arrangement(int xo, int zo)
		{
			this.xo = xo;
			this.zo = zo;
			this.xs = 1 - xo;
			this.zs = 1 - zo;
		}

		boolean check(World world, int x, int y, int z, BlockSaplingCRWG sapling)
		{
			for (int x1 = 0; x1 < this.xs; x1++) {
				for (int z1 = 0; z1 < this.zs; z1++) {
					if (!sapling.func_149880_a(world, x + x1, y, z1 + z, 0)) {
						return false;
					}
				}
			}
			return true;
		}

		void clear(World world, int x, int y, int z)
		{
			for (int x1 = 0; x1 < this.xs; x1++) {
				for (int z1 = 0; z1 < this.zs; z1++) {
					world.setBlock(x + x1, y, z + z1, Blocks.air, 0, 4);
				}
			}
		}

		void unclear(World world, int x, int y, int z, BlockSaplingCRWG sapling)
		{
			for (int x1 = 0; x1 < this.xs; x1++) {
				for (int z1 = 0; z1 < this.zs; z1++) {
					world.setBlock(x + x1, y, z + z1, sapling, 8, 4);
				}
			}
		}
	}

	final WorldFunction<WorldGenerator> func_gen;
	final Arrangement arrangement;

	public BlockSaplingCRWG(String name, WorldFunction<WorldGenerator> func_gen, Arrangement arrangement)
	{
		this.setBlockName(name);
		this.setCreativeTab(null);
		this.func_gen = func_gen;
		this.arrangement = arrangement;
	}

	/**
	 * Tree growth function
	 */
	@Override
	public void func_149878_d(World world, int x, int y, int z, Random rand)
	{
		if (!TerrainGen.saplingGrowTree(world, rand, x, y, z)) {
			return;
		}

		/* Check saplings and get offset to top-left sapling (if applicable) for generation */
		/* func_149880_a checks if the correct sapling is present */
		int x_offs = 0, z_offs = 0;
		if (this.arrangement == Arrangement.SINGLE) {
			if (!this.func_149880_a(world, x, y, z, 0)) {
				return;
			}
		} else {
			boolean saplings_correct = false;
		checkloop:
			for (x_offs = 0; x_offs >= this.arrangement.xo; x_offs--) {
				for (z_offs = 0; z_offs >= this.arrangement.zo; z_offs--) {
					if (this.arrangement.check(world, x + x_offs, y, z + z_offs, this)) {
						saplings_correct = true;
						break checkloop;
					}
				}
			}
			if (!saplings_correct) {
				return;
			}
		}

		x += x_offs;
		z += z_offs;

		WorldGenerator gen = this.func_gen.get(world, rand, x, y, z);
		if (gen == null) {
			return;
		}

		this.arrangement.clear(world, x, y, z);
		if (!gen.generate(world, rand, x, y, z)) {
			this.arrangement.unclear(world, x, y, z, this);
		}
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block ground)
	{
		if (this == WoodBlocks.PALM.sapling && ground == Blocks.sand) {
			return true;
		}
		return super.canPlaceBlockOn(ground);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		list.add(new ItemStack(item));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = reg.registerIcon(this.textureName);
	}
}

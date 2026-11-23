package com.captrojo.crwg.block;

import java.util.List;
import java.util.Random;

import com.captrojo.crwg.block.data.SaplingArrangement;
import com.captrojo.crwg.util.func.WorldFunctionA;
import com.captrojo.crwg.world.gen.feature.Feature;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

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
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSaplingCRWG extends BlockSapling
{
	final WorldFunctionA<Feature> func_gen;
	SaplingArrangement arrangement;

	public BlockSaplingCRWG(String name, WorldFunctionA<Feature> func_gen)
	{
		this.setBlockName(name);
		this.setCreativeTab(null);
		this.setStepSound(Block.soundTypeGrass);

		this.func_gen = func_gen;
		this.arrangement = SaplingArrangement.SINGLE;
	}

	public BlockSaplingCRWG setSaplingArrangement(SaplingArrangement arrangement)
	{
		this.arrangement = arrangement;
		return this;
	}

	@Override
	public BlockSaplingCRWG setBlockTextureName(String texture_name)
	{
		super.setBlockTextureName(texture_name);
		return this;
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
		if (this.arrangement == SaplingArrangement.SINGLE) {
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

		Feature gen = this.func_gen.get(world, rand, x, y, z);
		if (gen == null) {
			return;
		}

		this.arrangement.clear(world, x, y, z);
		if (!gen.generate(world, rand, new BlockPos(x, y, z))) {
			this.arrangement.unclear(world, x, y, z, this);
		}
	}

	@Override
	protected boolean canPlaceBlockOn(Block ground)
	{
		if (this == ModBlocks.sapling_palm && ground == Blocks.sand) {
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

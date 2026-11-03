package com.captrojo.crwg.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMyceliumCRWG extends BlockMycelium
{
	private final Block dirt_block;

	public BlockMyceliumCRWG(String name, Block dirt_block)
	{
		this.dirt_block = dirt_block;

		this.setBlockName(name);
		this.setCreativeTab(null);
		this.setHarvestLevel("shovel", 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 1) {
			return this.field_150200_a;
		}
		if (side == 0) {
			return this.dirt_block.getBlockTextureFromSide(0);
		}
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if (side == 1) {
			return this.field_150200_a;
		}
		if (side == 0) {
			return this.dirt_block.getBlockTextureFromSide(side);
		}
		Material material = world.getBlock(x, y + 1, z).getMaterial();
		if (material == Material.snow || material == Material.craftedSnow) {
			return this.field_150199_b;
		}
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = reg.registerIcon(this.getTextureName() + "_side");
		this.field_150200_a = reg.registerIcon(this.getTextureName() + "_top");
		this.field_150199_b = reg.registerIcon(this.getTextureName() + "_side_snowed");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.isRemote) {
			return;
		}
		if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2) {
			world.setBlock(x, y, z, this.dirt_block);
		} else if (world.getBlockLightValue(x, y + 1, z) >= 9) {
			for (int l = 0; l < 4; l++) {
				int x1 = x + rand.nextInt(3) - 1;
				int y1 = y + rand.nextInt(5) - 3;
				int z1 = z + rand.nextInt(3) - 1;

				if (
					world.getBlock(x1, y1, z1) == this.dirt_block &&
					world.getBlockMetadata(x1, y1, z1) == 0 &&
					world.getBlockLightValue(x1, y1 + 1, z1) >= 4 &&
					world.getBlockLightOpacity(x1, y1 + 1, z1) <= 2
				) {
					world.setBlock(x1, y1, z1, this);
				}
			}
		}
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return this.dirt_block.getItemDropped(0, rand, fortune);
	}
}

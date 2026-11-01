package com.captrojo.crwg.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGrassCRWG extends BlockGrass
{
	private final Block dirt_block;

	public BlockGrassCRWG(String name, Block dirt_block)
	{
		this.setBlockName(name);
		this.setCreativeTab(null);
		this.setHarvestLevel("shovel", 0);

		this.dirt_block = dirt_block;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getSideOverlayIcon()
	{
		return this.field_149994_N;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if (side == 1) {
			return this.field_149991_b;
		}
		if (side == 0) {
			return this.dirt_block.getBlockTextureFromSide(side);
		}
		Material mat = world.getBlock(x, y + 1, z).getMaterial();
		return mat != Material.snow && mat != Material.craftedSnow ? this.blockIcon : this.field_149993_M;
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this.dirt_block);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		switch (plantable.getPlantType(world, x, y, z)) {
		case Plains:
			return true;
		case Beach:
			boolean hasWater = (
				world.getBlock(x - 1, y, z).getMaterial() == Material.water ||
				world.getBlock(x + 1, y, z).getMaterial() == Material.water ||
				world.getBlock(x, y, z - 1).getMaterial() == Material.water ||
				world.getBlock(x, y, z + 1).getMaterial() == Material.water
			);
			return hasWater;
		default:
			return super.canSustainPlant(world, x, y, z, direction, plantable);
		}
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
					world.getBlockLightValue(x1, y1 + 1, z1) >= 4 &&
					world.getBlockLightOpacity(x1, y1 + 1, z1) <= 2
				) {
					world.setBlock(x1, y1, z1, this);
				}
			}
		}
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z)
	{
		int l = 0;

		while (l < 128) {
			int x1 = x;
			int y1 = y + 1;
			int z1 = z;
			int l1 = 0;

			while (true) {
				if (l1 < l / 16) {
					x1 += rand.nextInt(3) - 1;
					y1 += (rand.nextInt(3) - 1) * rand.nextInt(3) / 2;
					z1 += rand.nextInt(3) - 1;

					if (world.getBlock(x1, y1 - 1, z1) == this && !world.getBlock(x1, y1, z1).isNormalCube()) {
						l1++;
						continue;
					}
				} else if (world.getBlock(x1, y1, z1).getMaterial() == Material.air) {
					if (rand.nextInt(8) != 0) {
						if (Blocks.tallgrass.canBlockStay(world, x1, y1, z1)) {
							world.setBlock(x1, y1, z1, Blocks.tallgrass, 1, 3);
						}
					} else {
						world.getBiomeGenForCoords(x1, z1).plantFlower(world, rand, x1, y1, z1);
					}
				}

				l++;
				break;
			}
		}
	}
}

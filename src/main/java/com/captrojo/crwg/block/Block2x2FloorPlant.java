package com.captrojo.crwg.block;

import java.util.ArrayList;
import java.util.Random;

import com.captrojo.crwg.render.block.BlockRenderIDs;
import com.captrojo.crwg.util.RefStrs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class Block2x2FloorPlant extends BlockBushCRWG
{
	private static final Random RAND = new Random();
	
	public Block2x2FloorPlant(String name)
	{
		this(name, Material.plants, EnumPlantType.Plains);
	}

	public Block2x2FloorPlant(String name, Material material, EnumPlantType type)
	{
		super(name, material, type);
		this.setSize(1.0F, 0.03125F);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromRNG(int corner, int rng)
	{
		int v = (rng >>> 8) % this.variants;
		int c = (rng >> (corner * 2)) & 3;
		return this.icons[(v << 2) | c];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.icons = new IIcon[4];
		if (this.variants == 1) {
			for (int i = 0; i < 4; i++) {
				this.icons[i] = reg.registerIcon(this.textureName + "_" + RefStrs.LETTERS[i]);
			}
		} else {
			for (int v = 0; v < this.variants; v++) {
				for (int i = 0; i < 4; i++) {
					this.icons[i] = reg.registerIcon(this.textureName + "_" + RefStrs.LETTERS[i + v * 4]);
				}
			}
		}
		this.blockIcon = this.icons[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return this.textureName;
	}

	@Override
	public int getRenderType()
	{
		return BlockRenderIDs.FLOOR_PLANT_2X2.id;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random rand)
	{
		int q = 0;
		for (int m = meta; m != 0; m >>>= 1) {
			if ((m & 1) != 0) {
				q++;
			}
		}
		return q;
	}

	@Override
	protected boolean canPlaceBlockOn(Block ground)
	{
		return ground == this;
	}
	
	@Override
	public ArrayList<ItemStack> onSheared(ItemStack stack, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		for (int i = 0; i < this.quantityDropped(fortune, world.getBlockMetadata(x, y, z), RAND); i++) {
			ret.add(new ItemStack(this));
		}
		return ret;
	}
}

package com.captrojo.crwg.block;

import java.util.Random;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.tileentity.TESignCRWG;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSign;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BlockSignCRWG extends BlockSign
{
	public static enum Sign
	{
		BALD_CYPRESS("bald_cypress_sign"),
		CEDAR("cedar_sign"),
		PALM("palm_sign"),
		PINE("pine_sign");

		public final ResourceLocation resource;

		Sign(String texture_name)
		{
			this.resource = CRWG.resource("textures/tile_entities/" + texture_name + ".png");
		}
	}

	public final Sign sign;
	private String item_texture_name;

	public Block standing_sign;

	private BlockSignCRWG(String name, Sign sign, boolean standing)
	{
		super(TESignCRWG.class, true);
		this.sign = sign;
		this.setBlockName(name);
	}

	public BlockSignCRWG(String name, Sign sign, String item_name)
	{
		this(name, sign, true);
		this.item_texture_name = CRWG.ident("wood/" + item_name);
	}

	public BlockSignCRWG(String name, Sign sign, Block standing_sign)
	{
		this(name, sign, false);
		this.standing_sign = standing_sign;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TESignCRWG(this.sign);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		if (this.standing_sign == null) {
			return Item.getItemFromBlock(this);
		}
		return Item.getItemFromBlock(this.standing_sign);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, int x, int y, int z)
	{
		if (this.standing_sign == null) {
			return Item.getItemFromBlock(this);
		}
		return Item.getItemFromBlock(this.standing_sign);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return this.item_texture_name;
	}
}

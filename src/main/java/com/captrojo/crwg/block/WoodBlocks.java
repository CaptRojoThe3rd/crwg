package com.captrojo.crwg.block;

import java.util.List;

import com.captrojo.crwg.item.ItemDoorCRWG;
import com.captrojo.crwg.item.ItemLogCRWG;
import com.captrojo.crwg.item.ItemSignCRWG;
import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.main.GameRegistry2;
import com.captrojo.crwg.tileentity.TESignCRWG;
import com.captrojo.crwg.tileentity.TESignCRWG.Sign;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public enum WoodBlocks
{
	BALD_CYPRESS("bald_cypress", Sign.BALD_CYPRESS),
	CEDAR("cedar", Sign.CEDAR),
	CHROMATICUS_RED("red_chromaticus", Sign.CHROMATICUS_RED),
	CHROMATICUS_GREEN("green_chromaticus", Sign.CHROMATICUS_GREEN),
	CHROMATICUS_BLUE("blue_chromaticus", Sign.CHROMATICUS_BLUE),
	CHROMATICUS_VIOLET("violet_chromaticus", Sign.CHROMATICUS_VIOLET),
	ENCHANTED_ASH("enchanted_ash", Sign.ENCHANTED_ASH),
	PALM("palm", Sign.PALM),
	PINE("pine", Sign.PINE),
	TAMARACK("tamarack", Sign.TAMARACK),
	VIOLET("violet", Sign.VIOLET),
	WILLOW("willow", Sign.WILLOW);

	public final String name;

	public BlockLogCRWG log;
	public BlockCRWG planks;
	public BlockSlabCRWG slab;
	public BlockSlabCRWG double_slab;
	public BlockStairsCRWG stairs;
	public BlockFenceCRWG fence;
	public BlockFenceGateCRWG fence_gate;
	public BlockButtonCRWG button;
	public BlockPressurePlateCRWG pressure_plate;
	public BlockDoorCRWG door;
	public BlockTrapDoorCRWG trapdoor;
	public BlockSignCRWG sign_standing; /* this one has the item mapped to it */
	public BlockSignCRWG sign_wall;

	WoodBlocks(
		String name,
		TESignCRWG.Sign sign
	)
	{
		this.name = name;

		this.log = (BlockLogCRWG) (new BlockLogCRWG(name + "_log"))
			.setHarvestTool("axe", 0)
			.setFlammable(5, 5)
			.setHardness(2.0F)
			.setBlockTextureName(CRWG.ident("wood/" + name + "_log"))
			.setStepSound(Block.soundTypeWood);
		this.planks = (BlockCRWG) (new BlockCRWG(name + "_planks", Material.wood))
			.setFlammable(5, 20)
			.setHarvestTool("axe", 0)
			.setHardness(2.0F)
			.setResistance(5.0F)
			.setBlockTextureName(CRWG.ident("wood/" + name + "_planks"))
			.setStepSound(Block.soundTypeWood);
		this.slab = new BlockSlabCRWG(name + "_slab", this.planks);
		this.double_slab = new BlockSlabCRWG(name + "_double_slab", (BlockSlabCRWG) this.slab);
		this.stairs = new BlockStairsCRWG(name + "_stairs", this.planks, 0);
		this.fence = new BlockFenceCRWG(name + "_fence", this.planks);
		this.fence.setHarvestLevel("axe", 0);
		this.fence_gate = new BlockFenceGateCRWG(name + "_fence_gate", this.planks);
		this.fence_gate.setHarvestLevel("axe", 0);
		this.button = new BlockButtonCRWG(name + "_button", this.planks, true);
		this.button.setHarvestLevel("axe", 0);
		this.pressure_plate = new BlockPressurePlateCRWG(name + "_pressure_plate", this.planks, Sensitivity.everything);
		this.pressure_plate.setHarvestLevel("axe", 0);
		this.door = (BlockDoorCRWG) (new BlockDoorCRWG(name + "_door", Material.wood))
			.setHardness(2.0F)
			.setResistance(5.0F)
			.setBlockTextureName(CRWG.ident("wood/" + name + "_door"))
			.setStepSound(Block.soundTypeWood);
		this.door.setHarvestLevel("axe", 0);
		this.trapdoor = (BlockTrapDoorCRWG) (new BlockTrapDoorCRWG(name + "_trapdoor", Material.wood))
			.setHardness(2.0F)
			.setResistance(5.0F)
			.setBlockTextureName(CRWG.ident("wood/" + name + "_trapdoor"))
			.setStepSound(Block.soundTypeWood);
		this.trapdoor.setHarvestLevel("axe", 0);
		this.sign_standing = (BlockSignCRWG) (new BlockSignCRWG(name + "_standing_sign", sign, name + "_sign"))
			.setHardness(2.0F)
			.setResistance(5.0F)
			.setStepSound(Block.soundTypeWood);
		this.sign_standing.setHarvestLevel("axe", 0);
		this.sign_wall = (BlockSignCRWG) (new BlockSignCRWG(name + "_wall_sign", sign, this.sign_standing))
			.setHardness(2.0F)
			.setResistance(5.0F)
			.setStepSound(Block.soundTypeWood);
		this.sign_wall.setHarvestLevel("axe", 0);
	}

	void registerBlocks()
	{
		GameRegistry.registerBlock(this.log, ItemLogCRWG.class, this.log.getUnlocalizedName());
		GameRegistry.registerBlock(this.planks, this.planks.getUnlocalizedName());
		GameRegistry2.registerBlock(this.slab, new ItemSlab(this.slab, this.slab, this.double_slab, false), this.slab.getUnlocalizedName());
		GameRegistry2.registerBlock(this.double_slab, new ItemSlab(this.double_slab, this.slab, this.double_slab, true), this.double_slab.getUnlocalizedName());
		ModBlocks.registerBlock(this.stairs);
		ModBlocks.registerBlock(this.fence);
		ModBlocks.registerBlock(this.fence_gate);
		ModBlocks.registerBlock(this.button);
		ModBlocks.registerBlock(this.pressure_plate);
		GameRegistry.registerBlock(this.door, ItemDoorCRWG.class, this.door.getUnlocalizedName());
		ModBlocks.registerBlock(this.trapdoor);
		Item item = new ItemSignCRWG(this.name + "_sign", "wood/" + this.name + "_sign", this.sign_standing, this.sign_wall);
		GameRegistry2.registerBlock(this.sign_standing, item, this.sign_standing.getUnlocalizedName());
		GameRegistry2.registerBlock(this.sign_wall, null, this.sign_wall.getUnlocalizedName());
	}

	public void addWoodBlocksToList(List<ItemStack> list)
	{
		list.add(new ItemStack(this.log, 1, 0x0));
		list.add(new ItemStack(this.log, 1, 0x1));
		list.add(new ItemStack(this.log, 1, 0xc));
		list.add(new ItemStack(this.log, 1, 0xd));
		list.add(new ItemStack(this.planks));
		list.add(new ItemStack(this.slab));
		list.add(new ItemStack(this.stairs));
		list.add(new ItemStack(this.fence));
		list.add(new ItemStack(this.fence_gate));
		list.add(new ItemStack(this.button));
		list.add(new ItemStack(this.pressure_plate));
		list.add(new ItemStack(this.door));
		list.add(new ItemStack(this.trapdoor));
		list.add(new ItemStack(this.sign_standing));
	}
}

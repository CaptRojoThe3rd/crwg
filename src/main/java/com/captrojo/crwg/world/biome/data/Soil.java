package com.captrojo.crwg.world.biome.data;

import javax.annotation.Nullable;

import com.captrojo.crwg.block.ModBlocks;
import com.captrojo.crwg.util.BlockState;

import net.minecraft.block.Block;

public enum Soil
{
	NORMAL("Normal", BlockState.DIRT, BlockState.COARSE_DIRT, BlockState.GRASS, BlockState.PODZOL, BlockState.FARMLAND),
	SILTY("Silty", BlockState.SILTY_DIRT, BlockState.COARSE_SILTY_DIRT, BlockState.SILTY_GRASS, BlockState.SILTY_PODZOL, BlockState.SILTY_FARMLAND),
	LOAMY("Loamy", BlockState.LOAMY_DIRT, BlockState.COARSE_LOAMY_DIRT, BlockState.LOAMY_GRASS, BlockState.LOAMY_PODZOL, BlockState.LOAMY_FARMLAND),
	CLAY("Clay", BlockState.CLAY_DIRT, BlockState.COARSE_CLAY_DIRT, BlockState.CLAY_GRASS, BlockState.CLAY_PODZOL, BlockState.CLAY_FARMLAND);

	/**
	 * [s_temp][s_rain][species]
	 */
	public static final Soil[][][] Soil_Map = {
		/* frozen */ {
			/* dry */ {NORMAL, NORMAL, NORMAL, NORMAL},
			/* moderate */ {NORMAL, NORMAL, NORMAL, CLAY},
			/* wet */ {NORMAL, NORMAL, CLAY, CLAY}
		},
		/* cold */ {
			/* dry */ {NORMAL, NORMAL, NORMAL, CLAY},
			/* moderate */ {NORMAL, NORMAL, NORMAL, CLAY},
			/* wet */ {NORMAL, NORMAL, CLAY, CLAY}
		},
		/* temperate */ {
			/* dry */ {NORMAL, NORMAL, NORMAL, CLAY},
			/* moderate */ {NORMAL, NORMAL, NORMAL, CLAY},
			/* wet */ {LOAMY, NORMAL, NORMAL, CLAY}
		},
		/* warm */ {
			/* dry */ {NORMAL, NORMAL, CLAY, SILTY},
			/* moderate */ {LOAMY, NORMAL, CLAY, SILTY},
			/* wet */ {LOAMY, LOAMY, NORMAL, NORMAL}
		},
		/* hot */ {
			/* dry */ {SILTY, SILTY, SILTY, SILTY},
			/* moderate */ {NORMAL, SILTY, SILTY, CLAY},
			/* wet */ {LOAMY, LOAMY, NORMAL, CLAY}
		}
	};

	public static BlockState getPodzolFor(Block block)
	{
		if (block == ModBlocks.silty_dirt || block == ModBlocks.coarse_silty_dirt || block == ModBlocks.silty_grass || block == ModBlocks.silty_farmland) {
			return BlockState.SILTY_PODZOL;
		}
		if (block == ModBlocks.loamy_dirt || block == ModBlocks.coarse_loamy_dirt || block == ModBlocks.loamy_grass || block == ModBlocks.loamy_farmland) {
			return BlockState.LOAMY_PODZOL;
		}
		if (block == ModBlocks.clay_dirt || block == ModBlocks.coarse_clay_dirt || block == ModBlocks.clay_grass || block == ModBlocks.clay_farmland) {
			return BlockState.CLAY_PODZOL;
		}
		return BlockState.PODZOL;
	}

	public final String name;
	
	public final BlockState dirt;
	public final BlockState coarse_dirt;
	public final BlockState grass;
	public final BlockState podzol;
	public final BlockState farmland;

	private Soil(String name, BlockState dirt, BlockState coarse_dirt, BlockState grass, BlockState podzol, BlockState farmland)
	{
		this.name = name;
		
		this.dirt = dirt;
		this.coarse_dirt = coarse_dirt;
		this.grass = grass;
		this.podzol = podzol;
		this.farmland = farmland;
	}
	
	public @Nullable BlockState get(@Nullable SoilIdx idx)
	{
		if (idx == null) {
			return null;
		}
		switch (idx) {
		case DIRT:
			return this.dirt;
		case COARSE_DIRT:
			return this.coarse_dirt;
		case GRASS:
			return this.grass;
		case PODZOL:
			return this.podzol;
		case FARMLAND:
			return this.farmland;
		default:
			return null;
		}
	}
}

package com.captrojo.crwg.mixins.early.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.captrojo.crwg.mixins.interfaces.ChunkCRWG;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;
import com.captrojo.crwg.world.chunk.ChnkConst;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

@Mixin(Chunk.class)
public abstract class ChunkMixin implements ChunkCRWG
{
	/*                 bit
	 * 3      2 2      1 1
	 * 1      4 3      6 5      8 7      0
	 * -------- pppppppp SSSSBBBB BBLLLLLL
	 *          |||||||| |||||||| ||++++++-- Land type
	 *          |||||||| ||||++++-++-------- Biome type
	 *          |||||||| ++++--------------- Soil type
	 *          ++++++++-------------------- Subspecies
	 */

	@Unique int[] crwg$surf_data_map_a;
	
	@Unique int[] crwg$gnd_height_map;

	@Override
	@Unique
	public int[] crwg$getSurfDataMapA()
	{
		return this.crwg$surf_data_map_a;
	}

	@Override
	@Unique
	public void crwg$setSurfDataMapA(int[] map)
	{
		this.crwg$surf_data_map_a = map;
	}

	@Override
	@Unique
	public LandType crwg$getLandType(int x, int z)
	{
		int v = this.crwg$surf_data_map_a[x | (z << 4)];
		v &= ChnkConst.MASK_LANDTYPE;
		LandType[] enum_vals = LandType.values();
		if (v >= enum_vals.length) {
			v = 0;
		}
		return enum_vals[v];
	}

	@Override
	@Unique
	public void crwg$setLandType(int x, int z, LandType land_type)
	{
		int idx = x | (z << 4);
		int v = this.crwg$surf_data_map_a[idx];
		v &= ~ChnkConst.MASK_LANDTYPE;
		v |= land_type.ordinal();
		this.crwg$surf_data_map_a[idx] = v;
	}

	@Override
	@Unique
	public BiomeTypeCRWG crwg$getBiomeType(int x, int z)
	{
		int v = this.crwg$surf_data_map_a[x | (z << 4)];
		v = (v & ChnkConst.MASK_BIOMETYPE) >>> ChnkConst.SHIFT_BIOMETYPE;
		BiomeTypeCRWG[] enum_vals = BiomeTypeCRWG.values();
		if (v >= enum_vals.length) {
			v = 0;
		}
		return enum_vals[v];
	}

	@Override
	@Unique
	public void crwg$setBiomeType(int x, int z, BiomeTypeCRWG biome_type)
	{
		int idx = x | (z << 4);
		int v = this.crwg$surf_data_map_a[idx];
		v &= ~ChnkConst.MASK_BIOMETYPE;
		v |= (biome_type.ordinal() << ChnkConst.SHIFT_BIOMETYPE);
		this.crwg$surf_data_map_a[idx] = v;
	}

	@Override
	@Unique
	public Soil crwg$getSoilType(int x, int z)
	{
		int v = this.crwg$surf_data_map_a[x | (z << 4)];
		v = (v & ChnkConst.MASK_SOILTYPE) >>> ChnkConst.SHIFT_SOILTYPE;
		Soil[] enum_vals = Soil.values();
		if (v >= enum_vals.length) {
			v = 0;
		}
		return enum_vals[v];
	}

	@Override
	@Unique
	public void crwg$setSoilType(int x, int z, Soil soil_type)
	{
		int idx = x | (z << 4);
		int v = this.crwg$surf_data_map_a[idx];
		v &= ~ChnkConst.MASK_SOILTYPE;
		v |= (soil_type.ordinal() << ChnkConst.SHIFT_SOILTYPE);
		this.crwg$surf_data_map_a[idx] = v;
	}
	
	@Override
	@Unique
	public int crwg$getSubspeciesVal(int x, int z)
	{
		int v = this.crwg$surf_data_map_a[x | (z << 4)];
		v = (v & ChnkConst.MASK_SPECIES) >>> ChnkConst.SHIFT_SUBSPECIES;
		return v;
	}
	
	@Override
	@Unique
	public void crwg$setSubspeciesVal(int x, int z, int subspecies)
	{
		int idx = x | (z << 4);
		int v = this.crwg$surf_data_map_a[idx];
		v &= ~ChnkConst.MASK_SPECIES;
		v |= (subspecies << ChnkConst.SHIFT_SUBSPECIES);
		this.crwg$surf_data_map_a[idx] = v;
	}
	
	@Override
	@Unique
	public int[] crwg$getGndHeightMap()
	{
		return this.crwg$gnd_height_map;
	}
	
	@Override
	@Unique
	public void crwg$setGndHeightMap(int[] gnd_height_map)
	{
		this.crwg$gnd_height_map = gnd_height_map;
	}
	
	@Override
	@Unique
	public int crwg$getGndHeight(int x, int z)
	{
		return this.crwg$gnd_height_map[x | (z << 4)];
	}
	
	@Override
	@Unique
	public void crwg$setGndHeight(int x, int z, int height)
	{
		this.crwg$gnd_height_map[x | (z << 4)] = height;
	}
	
	@Inject(method = "<init>(Lnet/minecraft/world/World;II)V", at = @At(value = "RETURN"), require = 1)
	private void init(World world, int chunk_x, int chunk_z, CallbackInfo ci)
	{
		this.crwg$surf_data_map_a = new int[256];
		this.crwg$gnd_height_map = new int[256];
	}
}

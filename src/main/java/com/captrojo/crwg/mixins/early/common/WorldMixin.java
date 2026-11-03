package com.captrojo.crwg.mixins.early.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.mixins.interfaces.ChunkCRWG;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.biome.data.BiomeTypeCRWG;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.data.Soil;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

@Mixin(World.class)
public abstract class WorldMixin implements WorldCRWG
{
	@Unique
	private ReportedException crwg$crash(Throwable throwable, String msg, int x, int y, int z, ChunkCRWG chunk)
	{
		CrashReport report = CrashReport.makeCrashReport(throwable, msg);
		CrashReportCategory cat = report.makeCategory("Requested block coordinates");
		cat.addCrashSection("Found chunk", Boolean.valueOf(chunk == null));
		cat.addCrashSection("Location", CrashReportCategory.getLocationInfo(x, y, z));
		return new ReportedException(report);
	}

	@Override
	@Unique
	public ChunkCRWG crwg$getChunk(int chunk_x, int chunk_z)
	{
		return (ChunkCRWG) ((World) (Object) this).getChunkFromChunkCoords(chunk_x, chunk_z);
	}

	@Override
	@Unique
	public ChunkCRWG crwg$getChunkFromBlockCoords(int x, int z)
	{
		return (ChunkCRWG) ((World) (Object) this).getChunkFromBlockCoords(x, z);
	}

	@Override
	@Unique
	public LandType crwg$getLandType(int x, int z)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return LandType.NULL;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			return chunk.crwg$getLandType(x & 0xf, z & 0xf);
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception getting land type in world", x, 0, z, chunk);
		}
	}

	@Override
	@Unique
	public boolean crwg$setLandType(int x, int z, LandType land_type)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return false;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			chunk.crwg$setLandType(x & 0xf, z & 0xf, land_type);
			return true;
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception setting land type in world", x, 0, z, chunk);
		}
	}

	@Override
	@Unique
	public BiomeTypeCRWG crwg$getBiomeType(int x, int z)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return null;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			return chunk.crwg$getBiomeType(x & 0xf, z & 0xf);
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception getting biome type in world", x, 0, z, chunk);
		}
	}

	@Override
	@Unique
	public boolean crwg$setBiomeType(int x, int z, BiomeTypeCRWG biome_type)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return false;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			chunk.crwg$setBiomeType(x & 0xf, z & 0xf, biome_type);
			return true;
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception setting biome type in world", x, 0, z, chunk);
		}
	}

	@Override
	@Unique
	public Soil crwg$getSoilType(int x, int z)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return Soil.NORMAL;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			return chunk.crwg$getSoilType(x & 0xf, z & 0xf);
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception getting soil type in world", x, 0, z, chunk);
		}
	}

	@Override
	@Unique
	public boolean crwg$setSoilType(int x, int z, Soil soil_type)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return false;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			chunk.crwg$setSoilType(x & 0xf, z & 0xf, soil_type);
			return true;
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception setting soil type in world", x, 0, z, chunk);
		}
	}
	
	@Override
	@Unique
	public int crwg$getSubspeciesVal(int x, int z)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return 0;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			return chunk.crwg$getSubspeciesVal(x & 0xf, z & 0xf);
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception getting subspecies value in world", x, 0, z, chunk);
		}
	}
	
	@Override
	@Unique
	public boolean crwg$setSubspeciesVal(int x, int z, int species)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return false;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			chunk.crwg$setSubspeciesVal(x & 0xf, z & 0xf, species);
			return true;
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception setting subspecies value in world", x, 0, z, chunk);
		}
	}

	@Override
	@Unique
	public int crwg$getGndHeight(int x, int z)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return 0;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			return chunk.crwg$getGndHeight(x & 0xf, z & 0xf);
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception getting ground height in world", x, 0, z, chunk);
		}
	}

	@Override
	@Unique
	public boolean crwg$setGndHeight(int x, int z, int height)
	{
		if (!CRWG.funcs.isWithinWorld(x, z)) {
			return false;
		}
		ChunkCRWG chunk = null;
		try {
			chunk = this.crwg$getChunkFromBlockCoords(x, z);
			chunk.crwg$setGndHeight(x & 0xf, z & 0xf, height);
			return true;
		} catch (Throwable t) {
			throw this.crwg$crash(t, "Exception setting ground height in world", x, height, z, chunk);
		}
	}
}

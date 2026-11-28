package com.captrojo.crwg.world.biome.decoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.captrojo.crwg.config.GeneralConfig;
import com.captrojo.crwg.main.CRWG;
import com.captrojo.crwg.mixins.interfaces.WorldCRWG;
import com.captrojo.crwg.world.biome.BiomeCRWGBase;
import com.captrojo.crwg.world.biome.data.LandType;
import com.captrojo.crwg.world.biome.decoration.decors.Decoration;
import com.captrojo.crwg.world.biome.decoration.util.DecoCoords;
import com.captrojo.crwg.world.biome.decoration.util.LandTypeDecoMode;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

public class Decorator
{
	private boolean _decorating = false;

	private final BiomeCRWGBase biome;

	private LandTypeDecoMode lt_mode;
	private DecoratorOptions[] deco_optns;
	private List<Decoration>[] decorations;

	public Decorator(final BiomeCRWGBase biome, final long seed)
	{
		this.biome = biome;
		this.setLandTypeDecoMode(LandTypeDecoMode.SINGLE_LAND_TYPE);
	}

	public void setLandTypeDecoMode(final LandTypeDecoMode mode)
	{
		this.lt_mode = mode;
		this.deco_optns = new DecoratorOptions[this.lt_mode.arr_size];
	}

	public void setOptions(final DecoratorOptions optns, final LandType lt)
	{
		this.deco_optns[this.lt_mode.lt_to_idx[lt.ordinal()]] = optns;
	}

	public void setAllOptions(final DecoratorOptions optns)
	{
		Arrays.fill(this.deco_optns, optns);
	}

	public DecoratorOptions getOptions(final LandType lt)
	{
		if (lt == null) {
			return this.deco_optns[0];
		}
		return this.deco_optns[this.lt_mode.lt_to_idx[lt.ordinal()]];
	}

	@SuppressWarnings("unchecked")
	public void compileDecorations()
	{
		this.decorations = (List<Decoration>[]) new ArrayList[this.lt_mode.arr_size];
		for (int i = 0; i < this.lt_mode.arr_size; i++) {
			this.decorations[i] = this.deco_optns[i].compileDecorations(this.biome);
		}
		this.deco_optns = null;
	}

	public void decorate(final World world, final Random rand, final int x0, final int z0)
	{
		if (this._decorating) {
			CRWG.log.error("Already decorating! Chunk: {}, {} (Pos: {}, {})", x0 >> 4, z0 >> 4, x0, z0);
			Thread.dumpStack();
			if (!GeneralConfig.yeet_decorator_crash) {
				throw this.crash(x0, z0, new RuntimeException("Already decorating!!"));
			}
			return;
		}
		this._decorating = true;
		this.genDecorations(world, rand, x0, z0);
		this._decorating = false;
	}

	protected void genDecorations(final World world, final Random rand, final int x0, final int z0)
	{
		final Random event_rand = new Random(rand.nextLong());
		final DecoCoords dpos = new DecoCoords(rand, x0, z0);

		LandType lt = ((WorldCRWG) world).crwg$getLandType(dpos.get_x15(), dpos.get_z15());
		int idx = this.lt_mode.lt_to_idx[lt.ordinal()];

		this.decorations[idx].forEach((final Decoration deco) -> {
			if (deco.preDecoEvent(world, event_rand, dpos)) {
				deco.decorate(world, rand, dpos);
			}
		});
	}

	private ReportedException crash(final int x0, final int z0, final Throwable t)
	{
		CrashReport crash = CrashReport.makeCrashReport(t, t.getMessage());
		CrashReportCategory cat = crash.makeCategory("DecorationChunk");
		cat.addCrashSection("x0", Integer.valueOf(x0));
		cat.addCrashSection("z0", Integer.valueOf(z0));
		cat.addCrashSection("biome", this.biome.biomeName);
		cat.addCrashSection("biome id", Integer.valueOf(this.biome.biomeID));
		cat.addCrashSection("biome id (hex)", Integer.toString(this.biome.biomeID, 16));
		return new ReportedException(crash);
	}
}

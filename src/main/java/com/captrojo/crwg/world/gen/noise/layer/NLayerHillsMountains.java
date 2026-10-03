package com.captrojo.crwg.world.gen.noise.layer;

import com.captrojo.crwg.main.CRWG;

public class NLayerHillsMountains extends NLayer
{
	final NLayer parent;
	final NLayerSaved continent;
	final NLayer selector;
	final NLayer hill_noise;
	final NLayer mountain_noise;
	
	public NLayerHillsMountains(NLayer parent, NLayerSaved continent, NLayer selector, NLayer hill_noise, NLayer mountain_noise)
	{
		this.parent = parent;
		this.continent = continent;
		this.selector = selector;
		this.hill_noise = hill_noise;
		this.mountain_noise = mountain_noise;
	}
	
	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array_p = this.parent.generate(ax, ay, aw, ah, asize);
		double[] array_c = this.continent.saved;
		double[] array_s = this.selector.generate(ax, ay, aw, ah, asize);
		double[] array_h = this.hill_noise.generate(ax, ay, aw, ah, asize);
		double[] array_m = this.mountain_noise.generate(ax, ay, aw, ah, asize);
		
		for (int i = 0; i < asize; i++) {
			double c = array_c[i];
			if (c < 0.0) {
				continue;
			}
			
			double s = array_s[i];
			s *= Math.min(5.0 * c, 1.0);
			
			array_s[i] = s;
			
			double hill_sel;
			if (s < 0.5) {
				hill_sel = Math.min(8.0 * (s - 0.3), 1.0);
			} else {
				hill_sel = Math.min(-8.0 * (s - 0.7), 1.0);
			}
			hill_sel = Math.max(hill_sel, 0.0);
			
			double mountain_sel = Math.max(Math.min(5.0 * (s - 0.6), 1.0), 0.0);
			
			double h = array_h[i];
			double m = array_m[i];
			
			h = h * 0.4;
			m = m * 1.35 + 0.9;
			
			double combine = Math.max(Math.min(7.0 * (s - 0.575), 1.0), 0.0);
			m = (m * combine) + (h * (1.0 - combine));
			
			h *= hill_sel;
			m *= mountain_sel;
			
			array_p[i] += h;
			array_p[i] += m;
		}
		
		return array_p;
	}
}

package com.captrojo.crwg.world.gen.longlayer;

public class GenLayerLongSmooth extends GenLayerLong
{
	public GenLayerLongSmooth(long base_seed, GenLayerLong parent)
	{
		super(base_seed);
		this.parent = parent;
	}

	@Override
	public long[] getLongs(int ax, int ay, int aw, int ah)
	{
		int p_ax = ax - 1;
		int p_ay = ay - 1;
		int p_aw = aw + 2;
		int p_ah = ah + 2;
		long[] p_arr = this.parent.getLongs(p_ax, p_ay, p_aw, p_ah);
		long[] n_arr = LongCache.getLongCache(aw * ah);

		for (int y = 0; y < ah; y++) {
			for (int x = 0; x < aw; x++) {
				long px0y1 = p_arr[x + 0 + (y + 1) * p_aw];
				long px2y1 = p_arr[x + 2 + (y + 1) * p_aw];
				long px1y0 = p_arr[x + 1 + (y + 0) * p_aw];
				long px1y2 = p_arr[x + 1 + (y + 2) * p_aw];
				long px1y1 = p_arr[x + 1 + (y + 1) * p_aw];

				if (px0y1 == px2y1 && px1y0 == px1y2) {
					this.initChunkSeed((long) (x + ax), (long) (y + ay));

					if (this.nextInt(2) == 0) {
						px1y1 = px0y1;
					} else {
						px1y1 = px1y0;
					}
				} else {
					if (px0y1 == px2y1) {
						px1y1 = px0y1;
					}

					if (px1y0 == px1y2) {
						px1y1 = px1y0;
					}
				}

				n_arr[x + y * aw] = px1y1;
			}
		}

		return n_arr;
	}
}

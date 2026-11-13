package com.captrojo.crwg.world.gen.longlayer;

public class GenLayerLongZoom extends GenLayerLong
{
	public GenLayerLongZoom(long base_seed, GenLayerLong parent)
	{
		super(base_seed);
		this.parent = parent;
	}

	@Override
	public long[] getLongs(int ax, int ay, int aw, int ah)
	{
		int p_ax = ax >> 1;
		int p_ay = ay >> 1;
		int p_aw = (aw >> 1) + 2;
		int p_ah = (ah >> 1) + 2;
		long[] p_arr = this.parent.getLongs(p_ax, p_ay, p_aw, p_ah);
		int i_aw = p_aw - 1 << 1;
		int i_ah = p_ah - 1 << 1;
		long[] i_arr = LongCache.getLongCache(i_aw * i_ah);
		int l2;

		for (int p_y = 0; p_y < p_ah - 1; ++p_y) {
			l2 = (p_y << 1) * i_aw;
			int p_x = 0;
			long j3 = p_arr[p_x + 0 + (p_y + 0) * p_aw];

			for (long k3 = p_arr[p_x + 0 + (p_y + 1) * p_aw]; p_x < p_aw - 1; ++p_x) {
				this.initChunkSeed((long) (p_x + p_ax << 1), (long) (p_y + p_ay << 1));
				long l3 = p_arr[p_x + 1 + (p_y + 0) * p_aw];
				long i4 = p_arr[p_x + 1 + (p_y + 1) * p_aw];
				i_arr[l2] = j3;
				i_arr[l2++ + i_aw] = this.selectRandom(new long[] {j3, k3});
				i_arr[l2] = this.selectRandom(new long[] {j3, l3});
				i_arr[l2++ + i_aw] = this.selectModeOrRandom(j3, l3, k3, i4);
				j3 = l3;
				k3 = i4;
			}
		}

		long[] n_arr = LongCache.getLongCache(aw * ah);

		for (l2 = 0; l2 < ah; ++l2) {
			System.arraycopy(i_arr, (l2 + (ay & 1)) * i_aw + (ax & 1), n_arr, l2 * aw, aw);
		}

		return n_arr;
	}
}

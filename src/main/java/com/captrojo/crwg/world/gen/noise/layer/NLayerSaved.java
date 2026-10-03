package com.captrojo.crwg.world.gen.noise.layer;

/**
 * Either saves a reference to the generated array, or saves a duplicate of the generated array
 * at this point.
 */
public class NLayerSaved extends NLayer
{
	final NLayer parent;
	final boolean duplicate;

	public double[] saved;

	public NLayerSaved(NLayer parent, boolean duplicate)
	{
		this.parent = parent;
		this.duplicate = duplicate;
	}

	@Override
	public double[] generate(int ax, int ay, int aw, int ah, int asize)
	{
		double[] array = this.parent.generate(ax, ay, aw, ah, asize);

		if (this.duplicate) {
			if (this.saved == null || this.saved.length < asize) {
				this.saved = new double[asize];
			}
			System.arraycopy(array, 0, this.saved, 0, asize);
		} else {
			this.saved = array;
		}

		return array;
	}
}

package com.captrojo.crwg.world.biome.info;

import java.util.ArrayList;

import com.captrojo.crwg.util.I18nUtil;

public class BiomeInfo
{
	private static final ArrayList<BiomeInfo> INFO_LIST = new ArrayList<>();

	/* BASIC BIOMES */

	public static final BiomeInfo ocean = (new BiomeInfo(0x000, "ocean", 0x000070));
	public static final BiomeInfo ocean_lukewarm = (new BiomeInfo(0x001, "lukewarm_ocean", 0x102070));
	public static final BiomeInfo ocean_warm = (new BiomeInfo(0x002, "warm_ocean", 0x204070));
	public static final BiomeInfo ocean_cold = (new BiomeInfo(0x003, "cold_ocean", 0x808060));
	public static final BiomeInfo ocean_frozen = (new BiomeInfo(0x004, "frozen_ocean", 0x181850));

	public static final BiomeInfo beach_frozen = (new BiomeInfo(0x005, "frozen_beach", 0xa2a284));
	public static final BiomeInfo beach_gravel = (new BiomeInfo(0x006, "gravel_beach", 0xa3a392));
	public static final BiomeInfo beach = (new BiomeInfo(0x007, "beach", 0xc6c66f));
	public static final BiomeInfo beach_tropical = (new BiomeInfo(0x008, "tropical_beach", 0xe9f46b));

	public static final BiomeInfo river = (new BiomeInfo(0x009, "river", 0x0000ff));
	public static final BiomeInfo river_frozen = (new BiomeInfo(0x00a, "frozen_river", 0x5050ff));
	public static final BiomeInfo river_swampy = (new BiomeInfo(0x00b, "swampy_river", 0x0040ff));

	public static final BiomeInfo lake = (new BiomeInfo(0x00c, "lake", 0x2020f0));
	public static final BiomeInfo lake_frozen = (new BiomeInfo(0x00d, "frozen_lake", 0x4040f0));

	public static final BiomeInfo oasis = (new BiomeInfo(0x00e, "oasis", 0xc9f918));

	public static final BiomeInfo dark_swamp = (new BiomeInfo(0x00f, "dark_swamp", 0x07f9b2));
	/* reserved: mangrove swamp (010) */
	public static final BiomeInfo cypress_swamp = (new BiomeInfo(0x011, "cypress_swamp", 0x07f9b2));

	public static final BiomeInfo marsh_green = (new BiomeInfo(0x012, "marsh_g", 0x7b8440));
	public static final BiomeInfo marsh_yellow = (new BiomeInfo(0x013, "marsh_y", 0x628440));

	public static final BiomeInfo bog_green = (new BiomeInfo(0x014, "bog_g", 0xc4d14f));
	public static final BiomeInfo bog_yellow = (new BiomeInfo(0x015, "bog_y", 0x93852a));
	/* reserved: peat bog (016) */

	public static final BiomeInfo fen_green = (new BiomeInfo(0x017, "fen_g", 0x88b759));
	public static final BiomeInfo fen_yellow = (new BiomeInfo(0x018, "fen_y", 0xabb759));

	public static final BiomeInfo grassland = (new BiomeInfo(0x019, "grassland", 0x80ff50));
	public static final BiomeInfo sunflower_plains = (new BiomeInfo(0x01a, "sunflower_plains", 0x9aff68));
	public static final BiomeInfo dry_grassland = (new BiomeInfo(0x01b, "dry_grassland", 0xbde547));
	public static final BiomeInfo cold_grassland = (new BiomeInfo(0x01c, "cold_grassland", 0x5be572));

	public static final BiomeInfo sparse_temperate_mountains = (new BiomeInfo(0x01d, "sparse_temperate_mountains", 0x607060));
	public static final BiomeInfo sparse_plateau = (new BiomeInfo(0x01e, "sparse_plateau", 0x607060));

	public static final BiomeInfo oak_chaparral = (new BiomeInfo(0x01f, "oak_chaparral", 0x056621));
	public static final BiomeInfo oak_forest = (new BiomeInfo(0x020, "oak_forest", 0x056621));
	public static final BiomeInfo flower_chaparral = (new BiomeInfo(0x021, "flower_chaparral", 0x056621));
	public static final BiomeInfo flower_forest = (new BiomeInfo(0x022, "flower_forest", 0x056621));

	public static final BiomeInfo dark_oak_forest = (new BiomeInfo(0x023, "dark_oak_forest", 0x40511a));

	public static final BiomeInfo birch_chaparral = (new BiomeInfo(0x024, "birch_chaparral", 0x307444));
	public static final BiomeInfo birch_forest = (new BiomeInfo(0x025, "birch_forest", 0x307444));
	public static final BiomeInfo old_growth_birch_forest = (new BiomeInfo(0x026, "old_growth_birch_forest", 0x307444));

	/* reserved: maple (027, 028, 029) */
	/* reserved: yellow autumn maple (030, 031, 032) */
	/* reserved: red autumn maple (033, 034, 035) */

	public static final BiomeInfo stony_peaks = (new BiomeInfo(0x036, "stony_peaks", 0x606060));
	public static final BiomeInfo mountains = (new BiomeInfo(0x037, "mountains", 0x808080));
	public static final BiomeInfo frozen_mountains = (new BiomeInfo(0x038, "frozen_mountains", 0xa0a0c0));

	public static final BiomeInfo gravel_plateau = (new BiomeInfo(0x039, "gravel_plateau", 0xa0a0a0));
	public static final BiomeInfo snowy_plateau = (new BiomeInfo(0x03a, "snowy_plateau", 0xdfffff));

	public static final BiomeInfo wasteland = (new BiomeInfo(0x03b, "wasteland", 0x404040));
	public static final BiomeInfo frozen_wasteland = (new BiomeInfo(0x03c, "frozen_wasteland", 0xd7efef));
	public static final BiomeInfo tundra = (new BiomeInfo(0x03d, "tundra", 0xc0f0f0));
	public static final BiomeInfo ice_spikes = (new BiomeInfo(0x03e, "ice_spike_tundra", 0xb0e0f0));

	public static final BiomeInfo spruce_forest = (new BiomeInfo(0x03f, "spruce_forest", 0x0b6659));
	public static final BiomeInfo old_growth_spruce_forest = (new BiomeInfo(0x040, "old_growth_spruce_forest", 0x596651));
	public static final BiomeInfo snowy_spruce_forest = (new BiomeInfo(0x041, "snowy_spruce_forest", 0x0b6659));
	public static final BiomeInfo snowy_old_growth_spruce_forest = (new BiomeInfo(0x042, "snowy_old_growth_spruce_forest", 0x596651));

	public static final BiomeInfo pine_forest = (new BiomeInfo(0x043, "pine_forest", 0x0b663e));
	public static final BiomeInfo old_growth_pine_forest = (new BiomeInfo(0x044, "old_growth_pine_forest", 0x5e6650));
	public static final BiomeInfo snowy_pine_forest = (new BiomeInfo(0x045, "snowy_pine_forest", 0x0b663e));
	public static final BiomeInfo snowy_old_growth_pine_forest = (new BiomeInfo(0x046, "snowy_old_growth_pine_forest", 0x5e6650));

	public static final BiomeInfo cedar_forest = (new BiomeInfo(0x047, "cedar_forest", 0x226035));
	public static final BiomeInfo old_growth_cedar_forest = (new BiomeInfo(0x048, "old_growth_cedar_forest", 0x52664f));
	public static final BiomeInfo snowy_cedar_forest = (new BiomeInfo(0x049, "snowy_cedar_forest", 0x226035));
	public static final BiomeInfo snowy_old_growth_cedar_forest = (new BiomeInfo(0x04a, "snowy_old_growth_cedar_forest", 0x52664f));

	/* reserved: redwood (04b, 04c, 04d, 04e) */
	/* reserved: fir (04f, 050, 051, 052) */

	public static final BiomeInfo desert = (new BiomeInfo(0x053, "desert", 0xfa9418));
	public static final BiomeInfo desert_mountains = (new BiomeInfo(0x054, "desert_mountains", 0xffd458));
	public static final BiomeInfo desert_plateau = (new BiomeInfo(0x055, "desert_plateau", 0xffc448));

	public static final BiomeInfo jungle = (new BiomeInfo(0x056, "jungle", 0x537b09));
	public static final BiomeInfo jungle_plateau = (new BiomeInfo(0x057, "jungle_plateau", 0x93bb49));

	public static final BiomeInfo savanna = (new BiomeInfo(0x058, "savanna", 0xbdb25f));
	public static final BiomeInfo savanna_plateau = (new BiomeInfo(0x059, "savanna_plateau", 0xa79d64));

	/* MUSHROOM BIOMES */

	public static final BiomeInfo mushroom_shore = (new BiomeInfo(0x100, "mushroom_beach", 0xa000ff));
	public static final BiomeInfo mushroom_mountains = (new BiomeInfo(0x101, "mushroom_mountains", 0xe020e0));
	public static final BiomeInfo mushroom_plateau = (new BiomeInfo(0x102, "mushroom_plateau", 0xe010e0));

	public static final BiomeInfo mushroom_desert = (new BiomeInfo(0x103, "mushroom_wasteland", 0xff00c0));
	public static final BiomeInfo mushroom_field = (new BiomeInfo(0x104, "mushroom_field", 0xff00ff));
	public static final BiomeInfo mushroom_forest = (new BiomeInfo(0x105, "mushroom_forest", 0xd000d0));
	public static final BiomeInfo mushroom_tundra = (new BiomeInfo(0x106, "mushroom_tundra", 0xff70ff));

	/* MAGICAL BIOMES */

	public static final BiomeInfo vibrant_forest = (new BiomeInfo(0x200, "vibrant_forest", 0x30c965));
	public static final BiomeInfo enchanted_ash_forest = (new BiomeInfo(0x201, "enchanted_ash_forest", 0x80fcdd));

	public static final BiomeInfo rainbow_forest = (new BiomeInfo(0x202, "rainbow_forest", 0x6de88f));
	public static final BiomeInfo rainbow_jungle = (new BiomeInfo(0x203, "rainbow_jungle", 0x6de8b4));

	public static final BiomeInfo violet_forest = (new BiomeInfo(0x204, "violet_forest", 0x3c2a89));
	public static final BiomeInfo old_growth_violet_forest = (new BiomeInfo(0x205, "old_growth_violet_forest", 0x3e3663));
	public static final BiomeInfo snowy_violet_forest = (new BiomeInfo(0x206, "snowy_violet_forest", 0x604faf));
	public static final BiomeInfo snowy_old_growth_violet_forest = (new BiomeInfo(0x207, "snowy_old_growth_violet_forest", 0x69628c));

	public static ArrayList<BiomeInfo> getInfoList()
	{
		return INFO_LIST;
	}

	public int id;
	public final String unlocalized_name;
	public final int color;

	private BiomeInfo(int default_id, String unlocalized_name, int color)
	{
		BiomeInfo.INFO_LIST.add(this);

		this.id = default_id;
		this.unlocalized_name = unlocalized_name;
		this.color = color;
	}

	public String getLocalizedName()
	{
		return I18nUtil.get("biome." + this.unlocalized_name + ".name");
	}
}

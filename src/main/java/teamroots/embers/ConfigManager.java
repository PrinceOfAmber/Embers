package teamroots.embers;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

	public static Configuration config;

	//ORES
	public static int copperVeinSize, copperMinY, copperMaxY, copperVeinsPerChunk,
					leadVeinSize, leadMinY, leadMaxY, leadVeinsPerChunk,
					silverVeinSize, silverMinY, silverMaxY, silverVeinsPerChunk,
					quartzVeinSize, quartzMinY, quartzMaxY, quartzVeinsPerChunk,
					ancientGolemSpawnWeight;
	public static List<Integer> orespawnBlacklist = new ArrayList<Integer>();
	
	//STRUCTURES
	public static int smallRuinChance;
	
	//COMPAT
	public static boolean enableNickel, enableTin, enableAluminum, enableBronze, enableElectrum;
	public static int nickelVeinSize, nickelMinY, nickelMaxY, nickelVeinsPerChunk,
					tinVeinSize, tinMinY, tinMaxY, tinVeinsPerChunk,
					aluminumVeinSize, aluminumMinY, aluminumMaxY, aluminumVeinsPerChunk;

	public static void init(File configFile)
	{
		if(config == null)
		{
			config = new Configuration(configFile);
			load();
		}
	}
	
	public static void load()
	{
		config.addCustomCategoryComment("ores", "Settings related to ore generation.");
		
		String[] strings = config.getStringList("oreBlacklist", "ores", new String[]{"-1","1"}, "A list of all dimension IDs in which Embers orespawn is prohibited. Embers ores will spawn in any dimension not on this list, but only in vanilla stone.");
		for (String s : strings){
			orespawnBlacklist.add(Integer.valueOf(s));
		}
		
		copperVeinSize = config.getInt("copperVeinSize", "ores", 12, 0, 255, "Maximum size of a copper ore vein (in blocks)");
		copperMinY = config.getInt("copperMinY", "ores", 0, 0, 255, "Minimum height over which copper ore will spawn.");
		copperMaxY = config.getInt("copperMaxY", "ores", 64, 0, 255, "Maximum height under which copper ore will spawn.");
		copperVeinsPerChunk = config.getInt("copperVeinsPerChunk", "ores", 6, 0, 255, "Number of attempts to spawn copper ore the world generator will make for each chunk.");
		
		leadVeinSize = config.getInt("leadVeinSize", "ores", 8, 0, 255, "Maximum size of a lead ore vein (in blocks)");
		leadMinY = config.getInt("leadMinY", "ores", 0, 0, 255, "Minimum height over which lead ore will spawn.");
		leadMaxY = config.getInt("leadMaxY", "ores", 28, 0, 255, "Maximum height under which lead ore will spawn.");
		leadVeinsPerChunk = config.getInt("leadVeinsPerChunk", "ores", 4, 0, 255, "Number of attempts to spawn lead ore the world generator will make for each chunk.");
		
		silverVeinSize = config.getInt("silverVeinSize", "ores", 6, 0, 255, "Maximum size of a silver ore vein (in blocks)");
		silverMinY = config.getInt("silverMinY", "ores", 0, 0, 255, "Minimum height over which silver ore will spawn.");
		silverMaxY = config.getInt("silverMaxY", "ores", 28, 0, 255, "Maximum height under which silver ore will spawn.");
		silverVeinsPerChunk = config.getInt("silverVeinsPerChunk", "ores", 4, 0, 255, "Number of attempts to spawn silver ore the world generator will make for each chunk.");
		
		quartzVeinSize = config.getInt("quartzVeinSize", "ores", 8, 0, 255, "Maximum size of a quartz ore vein (in blocks)");
		quartzMinY = config.getInt("quartzMinY", "ores", 0, 0, 255, "Minimum height over which quartz ore will spawn.");
		quartzMaxY = config.getInt("quartzMaxY", "ores", 18, 0, 255, "Maximum height under which quartz ore will spawn.");
		quartzVeinsPerChunk = config.getInt("quartzVeinsPerChunk", "ores", 4, 0, 255, "Number of attempts to spawn quartz ore the world generator will make for each chunk.");
		
		config.addCustomCategoryComment("mobs", "Settings related to ore generation.");
		
		ancientGolemSpawnWeight = config.getInt("ancientGolemSpawnWeight", "mobs", 25, 0, 32767, "Spawning weight of the Ancient Golem mob. Higher values make golems spawn more frequently.");
		
		config.addCustomCategoryComment("structures", "Settings related to structure generation.");
		
		smallRuinChance = config.getInt("smallRuinChance", "structures", 5, 0, 32767, "Spawning frequency of the small ruin structure. A value of 0 will prevent spawning altogether.");

		config.addCustomCategoryComment("compat", "Settings related to compatibility with other mods.");
		
		enableNickel = config.getBoolean("enableNickel", "compat", true, "If true, Embers will register items, blocks, and recipes providing support for other mods' nickel.");
		enableTin = config.getBoolean("enableTin", "compat", true, "If true, Embers will register items, blocks, and recipes providing support for other mods' tin.");
		enableAluminum = config.getBoolean("enableAluminum", "compat", true, "If true, Embers will register items, blocks, and recipes providing support for other mods' aluminum.");
		enableBronze = config.getBoolean("enableBronze", "compat", true, "If true, Embers will register items, blocks, and recipes providing support for other mods' bronze.");
		enableElectrum = config.getBoolean("enableElectrum", "compat", true, "If true, Embers will register items, blocks, and recipes providing support for other mods' electrum.");
		
		aluminumVeinSize = config.getInt("aluminumVeinSize", "compat", 6, 0, 255, "Maximum size of a aluminum ore vein (in blocks)");
		aluminumMinY = config.getInt("aluminumMinY", "compat", 0, 0, 255, "Minimum height over which aluminum ore will spawn.");
		aluminumMaxY = config.getInt("aluminumMaxY", "compat", 58, 0, 255, "Maximum height under which aluminum ore will spawn.");
		aluminumVeinsPerChunk = config.getInt("aluminumVeinsPerChunk", "compat", 4, 0, 255, "Number of attempts to spawn aluminum ore the world generator will make for each chunk.");
		
		nickelVeinSize = config.getInt("nickelVeinSize", "compat", 6, 0, 255, "Maximum size of a nickel ore vein (in blocks)");
		nickelMinY = config.getInt("nickelMinY", "compat", 0, 0, 255, "Minimum height over which nickel ore will spawn.");
		nickelMaxY = config.getInt("nickelMaxY", "compat", 24, 0, 255, "Maximum height under which nickel ore will spawn.");
		nickelVeinsPerChunk = config.getInt("nickelVeinsPerChunk", "compat", 4, 0, 255, "Number of attempts to spawn nickel ore the world generator will make for each chunk.");
		
		tinVeinSize = config.getInt("tinVeinSize", "compat", 6, 0, 255, "Maximum size of a tin ore vein (in blocks)");
		tinMinY = config.getInt("tinMinY", "compat", 0, 0, 255, "Minimum height over which tin ore will spawn.");
		tinMaxY = config.getInt("tinMaxY", "compat", 48, 0, 255, "Maximum height under which tin ore will spawn.");
		tinVeinsPerChunk = config.getInt("tinVeinsPerChunk", "compat", 6, 0, 255, "Number of attempts to spawn tin ore the world generator will make for each chunk.");
		
		if (config.hasChanged())
		{
			config.save();
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equalsIgnoreCase(Embers.MODID))
		{
			load();
		}
	}
}

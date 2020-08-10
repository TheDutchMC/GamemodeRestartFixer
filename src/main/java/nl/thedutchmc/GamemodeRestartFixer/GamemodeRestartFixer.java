package nl.thedutchmc.GamemodeRestartFixer;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

//NMS, changes every version.
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;

public class GamemodeRestartFixer extends JavaPlugin {
	
	public static boolean wasHardcore = true;
	
	public static GamemodeRestartFixer INSTANCE;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		ConfigurationHandler configHandler = new ConfigurationHandler();
		configHandler.loadConfig();
		
		//Set the default gamemode
		Bukkit.getServer().setDefaultGameMode(GameMode.valueOf(ConfigurationHandler.gamemode));
		
		//Check if hardcore and set accordingly
		if(Bukkit.isHardcore()) wasHardcore = true;
		
		//set hardcore to the value it should be
		ServerPropertiesFileHandler propHandler = new ServerPropertiesFileHandler();
		propHandler.patch();
				
		//EventListeners
		Bukkit.getPluginManager().registerEvents(new PlayerRespawnEventListener(), this);
		
		//We use NMS to disable hardcore.
		for(World w : Bukkit.getServer().getWorlds()) {
			((CraftWorld) w).getHandle().worldDataServer.b.hardcore = false;
		}		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void logInfo(String log) {
		Bukkit.getLogger().info("[" + GamemodeRestartFixer.INSTANCE.getDescription().getName() + "] " + log);	
	}
	
	public static void logWarn(String log) {
		Bukkit.getLogger().warning("[" + GamemodeRestartFixer.INSTANCE.getDescription().getName() + "] " + log);	
	}
}

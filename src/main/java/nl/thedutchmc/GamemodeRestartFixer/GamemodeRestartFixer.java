package nl.thedutchmc.GamemodeRestartFixer;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class GamemodeRestartFixer extends JavaPlugin {
	
	public static boolean wasHardcore = true;
	
	public static GamemodeRestartFixer INSTANCE;
	
	public static final String NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	
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
		
		SetHardcoreForWorld shfw = null;
		
		switch(NMS_VERSION) {
		case "v1.16_R1": shfw = new SetHardcoreForWorld_1_16_R1(); break;
		case "v1.16_R2": shfw = new SetHardcoreForWorld_1_16_R2(); break;
		default:
			logWarn("This version of Minecraft is not supported! You are using " + NMS_VERSION);
			return;
		}
		
		for(World w : Bukkit.getServer().getWorlds()) {
			shfw.setHardcore(w, ConfigurationHandler.hardcore);
		}		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void logInfo(String log) {
		INSTANCE.getLogger().info(log);	
	}
	
	public static void logWarn(String log) {
		INSTANCE.getLogger().warning(log);	
	}
}

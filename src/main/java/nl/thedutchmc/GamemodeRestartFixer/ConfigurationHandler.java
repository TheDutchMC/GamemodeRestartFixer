package nl.thedutchmc.GamemodeRestartFixer;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHandler {

	public static String gamemode;
	public static boolean hardcore;
	
	private File file;
	private FileConfiguration config;
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void loadConfig() {
		file = new File(GamemodeRestartFixer.INSTANCE.getDataFolder(), "config.yml");
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			GamemodeRestartFixer.INSTANCE.saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		
		try {
			config.load(file);
			readConfig();
		} catch (InvalidConfigurationException e) {
			GamemodeRestartFixer.logWarn("Invalid config.yml!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readConfig() {
		gamemode = this.getConfig().getString("forcedGamemode").toUpperCase();
		hardcore = Boolean.valueOf(this.getConfig().getString("hardcore"));
	}
}

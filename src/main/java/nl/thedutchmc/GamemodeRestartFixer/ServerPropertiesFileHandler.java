package nl.thedutchmc.GamemodeRestartFixer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerPropertiesFileHandler {

	public void patch() {
		
		File propsFile = new File(GamemodeRestartFixer.INSTANCE.getDataFolder().getParentFile().getParentFile(), "server.properties");
		
		System.out.println(propsFile.getAbsolutePath());
		
		Properties props = new Properties();
		
		if(!propsFile.exists()) return;
		
		try {
			props.load(new FileInputStream(propsFile));
			
			boolean hardcore = Boolean.valueOf(props.getProperty("hardcore"));
			
			if(hardcore != ConfigurationHandler.hardcore) {
				props.setProperty("hardcore", String.valueOf(ConfigurationHandler.hardcore).toLowerCase());
			}
			
			props.store(new FileOutputStream(propsFile), "Modified hardcore");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
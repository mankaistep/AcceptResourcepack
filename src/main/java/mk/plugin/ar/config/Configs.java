package mk.plugin.ar.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Configs {
	
	public static String RESOURCEPACK;
	public static boolean FORCE;
	public static boolean REQUEST_ONLOGIN;
	 
	public static void reload(FileConfiguration config) {
		RESOURCEPACK = config.getString("resourcepack");
		FORCE = config.getBoolean("force");
		REQUEST_ONLOGIN = config.getBoolean("request-onlogin");
	}
	
}

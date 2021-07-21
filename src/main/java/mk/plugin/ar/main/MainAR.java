package mk.plugin.ar.main;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import mk.plugin.ar.config.Configs;
import mk.plugin.ar.listener.ARListener;
import mk.plugin.ar.yamlfile.YamlFile;

public class MainAR extends JavaPlugin {
	
	@EventHandler
	public void onEnable() {
		this.reload();
		this.registerListeners();
		this.registerCommands();
	}
	
	public void reload() {
		this.saveDefaultConfig();
		YamlFile.reloadAll(this);
		Configs.reload(YamlFile.CONFIG.get());
	}
	
	public void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new ARListener(), this);
	}
	
	public void registerCommands() {
		this.getCommand("goitainguyen").setExecutor(new ARCommand());
	}
}

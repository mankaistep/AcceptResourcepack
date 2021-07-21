package mk.plugin.ar.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mk.plugin.ar.config.Configs;

public class ARCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.setResourcePack(Configs.RESOURCEPACK);
		}
		
		return false;
	}

}

package mk.plugin.ar.listener;

import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import mk.plugin.ar.config.Configs;
import mk.plugin.ar.main.MainAR;

import java.util.Set;

public class ARListener implements Listener {

	private final Set<String> bypassed;

	public ARListener() {
		this.bypassed = Sets.newHashSet();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (!Configs.REQUEST_ONLOGIN) return;
		if (bypassed.contains(player.getName())) return;
		
		Bukkit.getScheduler().runTaskLater(MainAR.getPlugin(MainAR.class), () -> {
			if (player.hasMetadata("skybattle-quit")) return;
			player.sendTitle("§2§l§oGÓI TÀI NGUYÊN", "", 0, 100, 0);
			player.sendMessage("");
			player.sendMessage("§7-----------------------------------------------");
			player.sendMessage("§aGói tài nguyên đem lại trải nghiệm tốt nhất");
			player.sendMessage("§6Không cần cài gói tài nguyên nếu máy bạn yếu!");
			player.sendMessage("§7-----------------------------------------------");
			player.sendMessage("");
			Bukkit.getScheduler().runTaskLater(MainAR.getPlugin(MainAR.class), () -> {
				player.setResourcePack(Configs.RESOURCEPACK);
			}, 40);
		}, 5);
		

	}
	
	@EventHandler
	public void onRS(PlayerResourcePackStatusEvent e) {
		Player player = e.getPlayer();
		if (e.getStatus() == Status.ACCEPTED) {
			player.sendMessage("§aĐã chấp nhận, đang cài đặt...");
		}
		else if (e.getStatus() == Status.DECLINED) {
			if (Configs.FORCE) {
				Bukkit.getScheduler().runTaskLater(MainAR.getPlugin(MainAR.class), () -> {
					player.kickPlayer("§cBạn phải chấp nhận cài đặt resourcepack. Hướng dẫn §ahttps://samb440.gitlab.io/resourcepack.html");
				}, 10);
			}
			else {
				player.sendMessage("§6Bạn đã không chấp nhận cài resourcepack. Hãy cài để có trải nghiệm game tốt hơn!");
			}
		}
		else if (e.getStatus() == Status.SUCCESSFULLY_LOADED) {
			player.sendMessage("§aCài đặt thành công! Chúc bạn chơi game vui vẻ");
		}
		else if (e.getStatus() == Status.FAILED_DOWNLOAD) {
			player.sendMessage("§cCài đặt resourcepack thất bại");
		}
	}
	
}	

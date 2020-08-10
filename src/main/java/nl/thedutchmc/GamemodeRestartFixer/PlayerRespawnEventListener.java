package nl.thedutchmc.GamemodeRestartFixer;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawnEventListener implements Listener {

	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		if(!GamemodeRestartFixer.wasHardcore) return;
		
		new BukkitRunnable() {
			@Override
			public void run() {
				event.getPlayer().setGameMode(GameMode.valueOf(ConfigurationHandler.gamemode));
			}
		}.runTaskLater(GamemodeRestartFixer.INSTANCE, 1);
	}
}

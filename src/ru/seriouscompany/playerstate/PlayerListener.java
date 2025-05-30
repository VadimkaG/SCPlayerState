package ru.seriouscompany.playerstate;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
	protected PlayerState plugin;
	
	public PlayerListener(PlayerState plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerJoin(PlayerJoinEvent e) {
		plugin.initPlayer(e.getPlayer());
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		PlayerStateMetadata.fromPlayer(e.getPlayer()).save();
	}
}

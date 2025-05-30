package ru.seriouscompany.playerstate;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PlayerListener implements Listener {
	protected Plugin plugin;
	
	public PlayerListener(Plugin plugin) {
		this.plugin = plugin;
	}
	/**
	 * Инициализировать игрока
	 * @param player - Игрок
	 * @param plugin - Плагин для мета данных
	 * @return
	 */
	protected static PlayerStateMetadata initPlayer(Player player, Plugin plugin) {
		if (player.hasMetadata(PlayerStateMetadata.METADATA_NAME))
			return PlayerStateMetadata.fromPlayer(player);
		
		final String id = player.getUniqueId().toString();
		final String worldName = plugin.getServer().getWorlds().getFirst().getName();
		PlayerStateMetadata meta = new PlayerStateMetadata(
				plugin,
				new File(worldName+File.separator+"playerStates"+File.separator+id)
			);
		player.setMetadata(
			PlayerStateMetadata.METADATA_NAME,
			meta
		);
		return meta;
	}
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerJoin(PlayerJoinEvent e) {
		initPlayer(e.getPlayer(),plugin);
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		PlayerStateMetadata.fromPlayer(e.getPlayer()).save();
	}
}

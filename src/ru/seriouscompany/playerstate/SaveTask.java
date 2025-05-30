package ru.seriouscompany.playerstate;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveTask extends BukkitRunnable {

	protected Plugin plugin;
	
	public SaveTask(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
		for (final Player player : players) {
			plugin.getServer().getScheduler().runTaskAsynchronously(plugin, ()->{
				PlayerStateMetadata.fromPlayer(player).save();
			});
		}
	}

}

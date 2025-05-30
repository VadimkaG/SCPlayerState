package ru.seriouscompany.playerstate;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerState extends JavaPlugin {
	protected SaveTask timeSaver;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		if (timeSaver == null) {
			timeSaver = new SaveTask(this);
			timeSaver.runTaskTimerAsynchronously(this, 36000, getConfig().getInt("save_delay",36000));	
		}
	}
	@Override
	public void onDisable() {
		timeSaver.cancel();
		timeSaver = null;
	}
	/**
	 * Инициализировать игрока
	 * Это может потребоваться, если инициализация игрока происходит позже, чем требуется плагину
	 * @param player - Игрок
	 */
	public static PlayerStateMetadata initPlayer(Player player) {
		return PlayerListener.initPlayer(player,Bukkit.getPluginManager().getPlugin("PlayerState"));
	}
}

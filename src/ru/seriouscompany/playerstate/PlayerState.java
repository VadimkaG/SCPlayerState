package ru.seriouscompany.playerstate;

import java.io.File;

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
	 * Загрузить игрока по его UUID
	 * @param uuid
	 * @return
	 */
	public PlayerStateMetadata loadFromUUID(String uuid) {
		return new PlayerStateMetadata(
				this,
				new File(
						getServer().getWorlds().getFirst().getName()+
						File.separator+
						"playerStates"+
						File.separator+
						uuid
					)
			);
	}
	/**
	 * Инициализировать игрока
	 * Это может потребоваться, если инициализация игрока происходит позже, чем требуется плагину
	 * @param player - Игрок
	 */
	public PlayerStateMetadata initPlayer(Player player) {
		if (player.hasMetadata(PlayerStateMetadata.METADATA_NAME))
			return PlayerStateMetadata.fromPlayer(player);
		
		PlayerStateMetadata meta = loadFromUUID(player.getUniqueId().toString());
		player.setMetadata(
			PlayerStateMetadata.METADATA_NAME,
			meta
		);
		return meta;
	}
}

package ru.seriouscompany.playerstate;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class PlayerStateMetadata implements MetadataValue {
	
	public static final String METADATA_NAME = "PlayerStateConfig";
	
	protected Plugin plugin;
	protected File fileConfig;
	protected YamlConfiguration config;
	
	public PlayerStateMetadata(Plugin plugin,File fileConfig) {
		this.plugin = plugin;
		this.fileConfig = fileConfig;
		config = YamlConfiguration.loadConfiguration(fileConfig);
	}
	/**
	 * Получить конфигурацию игрока
	 * @param player
	 * @return
	 */
	public static PlayerStateMetadata fromPlayer(Player player) {
		List<MetadataValue> meta = player.getMetadata(METADATA_NAME);
		for (MetadataValue value : meta) {
			if (value instanceof PlayerStateMetadata)
				return (PlayerStateMetadata)value;
		}
		return null;
	}
	/**
	 * Получить конфигурацию
	 * @return
	 */
	public YamlConfiguration config() {
		return config;
	}
	/**
	 * Сохранить конфигурацию
	 */
	public void save() {
		try {
			config.save(fileConfig);
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING,"Не удалось сохранить конфигурацию игрока",e);
		}
	}
	@Override
	public boolean asBoolean() {return false;}
	@Override
	public byte asByte() {return 0;}
	@Override
	public double asDouble() {return 0;}
	@Override
	public float asFloat() {return 0;}
	@Override
	public int asInt() {return 0;}
	@Override
	public long asLong() {return 0;}
	@Override
	public short asShort() {return 0;}
	@Override
	public String asString() {return null;}
	@Override
	public Plugin getOwningPlugin() {return plugin;}
	@Override
	public void invalidate() {}
	@Override
	public Object value() {return config;}

}

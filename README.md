#How to use PlayerState API plugin
Get "PlayerStateConfig" metadata from Player and use `(YamlConfiguration)metadataValue.value()`. 
Or use `PlayerStateMetadata.fromPlayer(player).config()` method to get yaml config

PlayerStateMetadata automaticaly save config to file

You can use `save_delay` plugin config to set save delay
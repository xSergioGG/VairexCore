package me.zsergio.vairexcore.commands.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.zsergio.vairexcore.Main;

public class commandsMechanics implements Listener {
	
	private Main plugin = Main.getInstance();
	private FileConfiguration config = plugin.getConfig();
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		
		if(config.getStringList("blocked-commands").contains(event.getMessage())) {
			for(String str : config.getStringList("blocked-commands.whitelist")) {
				if(!(player.getWorld().getName() == str)) {
					player.sendMessage(config.getString("blocked-commands.msg").replace("&", "§"));
				}
			}
		}
	}

}

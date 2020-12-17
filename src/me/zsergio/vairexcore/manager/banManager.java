package me.zsergio.vairexcore.manager;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;

public class banManager {
	
	private Main plugin = Main.getInstance();
	
	private ArrayList<String> banlist = new ArrayList<>();
	
	public void reload() {
		for(String str : plugin.getConfig().getStringList("banned.list")) {
			banlist.add(str);
		}
	}
	
	public String getReason(OfflinePlayer player) {
		String name = player.getName();
		
		return plugin.getConfig().getString("banned."+name+".reason");
	}
	
	public ArrayList<String> getBanlist() {
		return banlist;
	}
	
	public void addBan(String tag, String reason) {
		banlist.add(tag);
		plugin.getConfig().set("banned." + tag + ".reason", reason);
		plugin.getConfig().set("banned.list", banlist);
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	
	public void addTempBan(String tag, String reason) {
		
		plugin.getConfig().set("banned." + tag + ".reason", reason);
		plugin.getConfig().set("banned.list", banlist);
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	
	public void removeBan(String name) {
		
		banlist.remove(name);
		plugin.getConfig().set("banned."+name, null);
		plugin.getConfig().set("banned.list", banlist);
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	
	public boolean ifBanner(Player player) {
		if(banlist.contains(player.getName())) {
			return true;
		} else {
			return false;
		}
	}

}

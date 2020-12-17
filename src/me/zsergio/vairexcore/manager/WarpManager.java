package me.zsergio.vairexcore.manager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.zsergio.vairexcore.Main;

public class WarpManager {
	
	private Main plugin = Main.getPlugin(Main.class);
	private FileConfiguration config = plugin.getConfig();
	
	private HashMap<String, Location> warplist = new HashMap<>();
	
	public Location getWarp(String str) {
		Location loc = new Location(Bukkit.getWorld(config.getString("warps."+str+".world")), 
				config.getInt("warps."+str+".x"), config.getInt("warps."+str+".y"), config.getInt("warps."+str+".z"));
		return loc;
		
	}
	
	public void addWarp(String name, Location loc) {
		
		if(!warplist.containsKey(name)) {
			warplist.put(name, loc);
			config.set("warps."+name+".world", loc.getWorld().getName());
			config.set("warps."+name+".x", loc.getX());
			config.set("warps."+name+".y", loc.getY());
			config.set("warps."+name+".z", loc.getZ());
			config.set("warps."+name+".pitch", loc.getPitch());
			config.set("warps."+name+".yaw", loc.getYaw());
			plugin.saveConfig();
		}
	}
	
	public HashMap<String, Location> getWarplist() {
		return warplist;
	}
	
	public void removeWarp(String name) {
		
		if(warplist.containsKey(name)) {
			config.set("warps."+name, null);
			plugin.saveConfig();
		}
	}
}

package me.zsergio.vairexcore.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;

public class vanishManager {
	
	private Main plugin = Main.getInstance();
	
	private ArrayList<String> svanished = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	public void start() {
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
			
				
			}
		}, 20L, 20L);
	}
	
	public void addStaff(Player player) {
		String name = player.getName();
		svanished.add(name);
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(!all.hasPermission("*"))
				all.hidePlayer(player);
			if(!all.hasPermission("vairex.vanish")) {
				all.hidePlayer(player);
			} else if(all.hasPermission("*") || all.hasPermission("vairex.vanish")) {
				all.showPlayer(player);
			}
		}

		player.sendMessage("§7Vanish: §aActivado");	
	}
	
	public void removeStaff(Player player) {
		String name = player.getName();
		svanished.remove(name);
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.showPlayer(player);
		}
		player.sendMessage("§7Vanish: §cDesactivado");
	}
	
	public ArrayList<String> getSvanished() {
		return svanished;
	}
	
}

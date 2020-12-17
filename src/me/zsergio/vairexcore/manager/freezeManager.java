package me.zsergio.vairexcore.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;

public class freezeManager {
	
	private Main plugin = Main.getInstance();
	
	private ArrayList<String> playerlist = new ArrayList<>();
	
	int task = 0;
	
	@SuppressWarnings("deprecation")
	public void addPlayer(Player player) {
		playerlist.add(player.getName());
		
		task++;
		task = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(playerlist.contains(player.getName())) {
					player.sendMessage("§6§lEstas en SS no te desconectes!");
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 100);
				} else {
					Bukkit.getScheduler().cancelTask(task);
				}
			}
		}, 0L, 20L);
	}
	
	public void removePlayer(Player player) {
		playerlist.remove(player.getName());
		
		player.sendMessage("§7Fuiste descongelado.");
	}
	
	public ArrayList<String> getPlayerlist() {
		return playerlist;
	}

}

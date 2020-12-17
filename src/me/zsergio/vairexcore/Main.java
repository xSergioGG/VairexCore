package me.zsergio.vairexcore;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.zsergio.vairexcore.commands.delWarpCMD;
import me.zsergio.vairexcore.commands.freezeCMD;
import me.zsergio.vairexcore.commands.gamemodeCMD;
import me.zsergio.vairexcore.commands.kickCMD;
import me.zsergio.vairexcore.commands.setWarpCMD;
import me.zsergio.vairexcore.commands.staffCMD;
import me.zsergio.vairexcore.commands.tphereCMD;
import me.zsergio.vairexcore.commands.vanishCMD;
import me.zsergio.vairexcore.commands.warpCMD;
import me.zsergio.vairexcore.commands.listeners.JoinListener;
import me.zsergio.vairexcore.commands.listeners.anticleanMechanics;
import me.zsergio.vairexcore.commands.listeners.commandsMechanics;
import me.zsergio.vairexcore.commands.listeners.vanishMechanics;
import me.zsergio.vairexcore.manager.WarpManager;
import me.zsergio.vairexcore.manager.banManager;
import me.zsergio.vairexcore.manager.freezeManager;
import me.zsergio.vairexcore.manager.staffManager;
import me.zsergio.vairexcore.manager.vanishManager;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	private WarpManager warpManager;
	private banManager banManager;
	private vanishManager vanishManager;
	private staffManager staffManager;
	private freezeManager freezeManager;
	
	@Override
	public void onEnable() {
		plugin = this;
		warpManager = new WarpManager();
		banManager = new banManager();
		vanishManager = new vanishManager();
		staffManager = new staffManager();
		freezeManager = new freezeManager();
		
		regConfig();
		
		banManager.reload();
		
		getCommand("warp").setExecutor(new warpCMD());
		getCommand("setwarp").setExecutor(new setWarpCMD());
		getCommand("delwarp").setExecutor(new delWarpCMD());
		getCommand("gamemode").setExecutor(new gamemodeCMD());
		getCommand("gm").setExecutor(new gamemodeCMD());
		getCommand("tphere").setExecutor(new tphereCMD());
		getCommand("kick").setExecutor(new kickCMD());
		getCommand("vanish").setExecutor(new vanishCMD());
		getCommand("staff").setExecutor(new staffCMD());
		getCommand("freeze").setExecutor(new freezeCMD());
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new vanishMechanics(), this);
		pm.registerEvents(new commandsMechanics(), this);
		pm.registerEvents(new anticleanMechanics(), this);
	}
	
	public freezeManager getFreezeManager() {
		return freezeManager;
	}
	
	public staffManager getStaffManager() {
		return staffManager;
	}
	
	public vanishManager getVanishManager() {
		return vanishManager;
	}
	
	public banManager getBanManager() {
		return banManager;
	}
	
	public static Main getInstance() {
		return plugin;
	}
	
	public WarpManager getWarpManager() {
		return warpManager;
	}
	
	private void regConfig() {
		File config = new File(this.getDataFolder(), "config.yml");
		if(!config.exists()) {
			this.getConfig().options().copyDefaults(true);
		}
		
		saveConfig();
	}
	
	@Override
	public void onDisable() {
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(staffManager.getStafflist().contains(all.getName())) {
				staffManager.toggleOff(all);
				
			}
		}
		
	}

}

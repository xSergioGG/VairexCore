package me.zsergio.vairexcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.WarpManager;

public class warpCMD implements CommandExecutor {

	private Main plugin = Main.getInstance();
	private WarpManager warpManager = plugin.getWarpManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.warp")) {
			Player player = (Player) sender;
			if(args[0].equalsIgnoreCase("list")) {
				player.sendMessage("§7Lista de Warps:");
				for(String str : warpManager.getWarplist().keySet()) {
					player.sendMessage("§7-§a "+str);
				}
			}
			
			if(args.length == 1) {
				try {
					player.teleport(warpManager.getWarp(args[0]));
					player.sendMessage("§7Teletransportando..");
				} catch(NullPointerException e) {
					player.sendMessage("§cEse Warp no existe :vv");
				}

			}
		}
		return true;
	}

}

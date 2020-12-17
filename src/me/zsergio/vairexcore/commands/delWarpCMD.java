package me.zsergio.vairexcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.WarpManager;

public class delWarpCMD implements CommandExecutor {

	private Main plugin = Main.getInstance();
	private WarpManager warpManager = plugin.getWarpManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.delwarp")) {
			Player player = (Player) sender;
			if(args.length == 1) {
				warpManager.removeWarp(args[0]);
				player.sendMessage("§7Has eliminado el Warp correctamente.");
			}
		}
		return true;
	}

}

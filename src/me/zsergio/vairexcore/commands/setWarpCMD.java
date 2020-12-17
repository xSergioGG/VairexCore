package me.zsergio.vairexcore.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.WarpManager;

public class setWarpCMD implements CommandExecutor {

	private Main plugin = Main.getInstance();
	private WarpManager warpManager = plugin.getWarpManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.setspawn")) {
			Player player = (Player) sender;
			if(args.length == 1) {
				Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), 
						player.getLocation().getZ());
				warpManager.addWarp(args[0], loc);
				player.sendMessage("§7Warp establecido correctamente.");
			} else {
				player.sendMessage("§cUso correcto: /setwarp <nombre>");
			}
		}
		return true;
	}

}

package me.zsergio.vairexcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tphereCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.tphere")) {
			if(args.length == 1) {
				Player player = (Player) sender;
				Player tag = Bukkit.getPlayer(args[0]);
				tag.teleport(player.getLocation());
				player.sendMessage("§7Has teletransportado a §e"+tag.getName()+"§7 hacia ti.");
			}
		}
		return true;
	}

}

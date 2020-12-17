package me.zsergio.vairexcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.staffManager;

public class staffCMD implements CommandExecutor {

	private Main plugin = Main.getInstance();
	private staffManager staffManager = plugin.getStaffManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.staff")) {
			Player player = (Player) sender;
			if(!staffManager.getStafflist().contains(player.getName())) {
				staffManager.toggleOn(player);
			} else {
				staffManager.toggleOff(player);
			}
		}
		return true;
	}

}

package me.zsergio.vairexcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.vanishManager;

public class vanishCMD implements CommandExecutor {

	private Main plugin = Main.getInstance();
	private vanishManager vanishManager = plugin.getVanishManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.vanish")) {
			Player player = (Player) sender;
				//VANISH OF STAFF
				if(!vanishManager.getSvanished().contains(player.getName())) {
					vanishManager.addStaff(player);
					player.sendMessage("§7Vanish: §aActivado");
				} else {
					vanishManager.removeStaff(player);
					player.sendMessage("§7Vanish: §cDesctivado");
				}
			
		}
		
		return true;
	}

}

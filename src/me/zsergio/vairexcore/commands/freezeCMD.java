package me.zsergio.vairexcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.freezeManager;

public class freezeCMD implements CommandExecutor {
	
	private Main plugin = Main.getInstance();
	private freezeManager freezeManager = plugin.getFreezeManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("staff.freeze")) {
			if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(!freezeManager.getPlayerlist().contains(target.getName())) {
					freezeManager.addPlayer(target);
					sender.sendMessage("§7Acabas de Congelar a §e"+target.getName());
				} else {
					freezeManager.removePlayer(target);
					sender.sendMessage("§7Acabas de Descongelar a §e"+target.getName());
				}
			} else {
				sender.hasPermission("§cUso correcto: /freeze <jugador>");
			}
		}
		return false;
	}

}

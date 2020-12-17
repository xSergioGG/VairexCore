package me.zsergio.vairexcore.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gamemodeCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.gamemode")) {
			Player player = (Player) sender;
			if(args[0].equalsIgnoreCase("0")) {
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("§7Tu modo de Juego ahora es §eSupervivencia§7.");
				return true;
			} if(args[0].equalsIgnoreCase("1")) {
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage("§7Tu modo de Juego ahora es §eCreativo§7.");
				return true;
			} if(args[0].equalsIgnoreCase("2")) {
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage("§7Tu modo de Juego ahora es §eAventura§7.");
				return true;
			} if(args[0].equalsIgnoreCase("3")) {
				player.setGameMode(GameMode.SPECTATOR);
				player.sendMessage("§7Tu modo de Juego ahora es §eEspectador§7.");
				return true;
			}
		}
		return true;
	}
	
	

}

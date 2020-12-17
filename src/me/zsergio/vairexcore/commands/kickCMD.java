package me.zsergio.vairexcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kickCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.kick")) {
			if(args.length >= 2) {
				Player tag = Bukkit.getPlayer(args[0]);
				String text = "";
				for(int x = 1; x < args.length; x++) {
					text += args[x]+ " ";
				}
				tag.kickPlayer("§6Has sido expulsado del Servidor por: "
						+ "§a\n"
						+ "§c\n§c"+text
						+ "§a\n"
						+"\n§7Para más Información entra a nuestro Discord:"
						+"\n§ehttps://discord.gg/6t7Cvzn");
				sender.sendMessage("§7El Jugador §e"+tag.getName()+"§7 ha sido expulsado correctamente.");
						
			} else {
				sender.sendMessage("§cUso correcto: /kick <jugador> <motivo>");
			}
		}
		return true;
	}

}

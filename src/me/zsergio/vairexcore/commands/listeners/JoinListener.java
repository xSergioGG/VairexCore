package me.zsergio.vairexcore.commands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.banManager;
import me.zsergio.vairexcore.manager.staffManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;

public class JoinListener implements Listener {
	
	private Main plugin = Main.getInstance();
	private FileConfiguration config = plugin.getConfig();
	private banManager banManager = plugin.getBanManager();
	private staffManager staffManager = plugin.getStaffManager();
	
	private LuckPerms api = LuckPermsProvider.get();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin , new Runnable() {
			
			@Override
			public void run() {
				if(config.getBoolean("custommessages.join.toggled") == true) {
					if(!staffManager.getStafflist().contains(player.getName())) {
						if(config.getStringList("custommessages.join.worlds").contains(event.getPlayer().getWorld().getName())) {
							if(player.hasPermission("vairex.joinmsg")) {
									for(Player all : Bukkit.getOnlinePlayers()) {
										if(all.getWorld() == player.getWorld()) {
											
											//User user = api.getUserManager().getUser(player.getUniqueId());
											//ContextManager cm = api.getContextManager();
											//QueryOptions queryOptions = cm.getQueryOptions(user).orElse(cm.getStaticQueryOptions());
											
											//all.sendMessage(config.getString("custommessages.join.msg").replace("%player%",
													//metaData.getSuffix() +" "+ player.getName())
													//.replace("&", "§"));
										}
									}
							}
						} else {
							event.setJoinMessage("");
						}
					}
				}
				
			}
		}, 5L);
		
	}
	
	@EventHandler
	public void onJoinBanned(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if(banManager.ifBanner(player)) {
			player.kickPlayer("§6Has sido baneado del Servidor por: "
					+ "§a\n"
					+ "§c\n§c"+banManager.getReason(player)
					+ "§a\n"
					+"\n§7Para más Información entra a nuestro Discord:"
					+"\n§ehttps://discord.gg/6t7Cvzn");
		}
		event.setJoinMessage(null);
		
	}
		
	
	
	@EventHandler
	public void onJoinOfWorld(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(player.hasPermission("vairex.joinmsg")) {
					if(config.getBoolean("custommessages.join.toggled") == true) {
						if(!staffManager.getStafflist().contains(player.getName())) {
							if(config.getStringList("custommessages.join.worlds").contains(event.getPlayer().getWorld().getName())) {
										for(Player all : Bukkit.getOnlinePlayers()) {
											if(all.getWorld() == player.getWorld()) {
												User user = api.getUserManager().getUser(player.getUniqueId());
												ContextManager cm = api.getContextManager();
												QueryOptions queryOptions = cm.getQueryOptions(user).orElse(cm.getStaticQueryOptions());
												CachedMetaData metaData = user.getCachedData().getMetaData(queryOptions);
												
												all.sendMessage(config.getString("custommessages.join.msg").replace("%player%",
														metaData.getSuffix() +" §a"+ player.getName())
														.replace("&", "§"));
											}
										}
								}
							}
					}
				}
				
			}
		}, 5L);
		
		
	}

}

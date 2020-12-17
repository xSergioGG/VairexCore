package me.zsergio.vairexcore.commands.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.zsergio.vairexcore.Main;
import me.zsergio.vairexcore.manager.freezeManager;
import me.zsergio.vairexcore.manager.staffManager;
import me.zsergio.vairexcore.manager.vanishManager;

public class vanishMechanics implements Listener {
	
	private Main plugin = Main.getInstance();
	private vanishManager vanishManager = plugin.getVanishManager();
	private staffManager staffManager = plugin.getStaffManager();
	private freezeManager freezeManager = plugin.getFreezeManager();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if(vanishManager.getSvanished().contains(player.getName())) {
			event.setJoinMessage("");
		}
		
		if(vanishManager.getSvanished().contains(player.getName())) {
			staffManager.getInv(player);
		}
		
		if(vanishManager.getSvanished().contains(player.getName())) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!all.hasPermission("*") || !all.hasPermission("vairex.vanish")) {
					all.hidePlayer(player);
				} else if(all.hasPermission("*") || all.hasPermission("vairex.vanish")){
					all.showPlayer(player);
				}
			}
		}
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(vanishManager.getSvanished().contains(all.getName())) {
						if(!player.hasPermission("*") || !player.hasPermission("vairex.vanish")) {
							player.hidePlayer(all);
						} else if(player.hasPermission("*") || player.hasPermission("vairex.vanish")) {
							player.showPlayer(all);
						}
					}
				}
				
			}
		}, 22);
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(staffManager.getStafflist().contains(player.getName())) {
			staffManager.restoreInv(player);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		
		if(event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			
				if(staffManager.getStafflist().contains(player.getName())) {
					event.setCancelled(true);
				}
				
		}
	}
	
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		if(vanishManager.getSvanished().contains(player.getName())) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!all.hasPermission("*") || !all.hasPermission("vairex.vanish")) {
					all.hidePlayer(player);
				} else if(all.hasPermission("*") || all.hasPermission("vairex.vanish")){
					all.showPlayer(player);
				}
			}
		}
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(vanishManager.getSvanished().contains(all.getName())) {
						if(!player.hasPermission("*") || !player.hasPermission("vairex.vanish")) {
							player.hidePlayer(all);
						} else if(player.hasPermission("*") || player.hasPermission("vairex.vanish")) {
							player.showPlayer(all);
						}
					}
				}
				
			}
		}, 22);
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		if(vanishManager.getSvanished().contains(player.getName())) {
			player.setGameMode(GameMode.SPECTATOR);
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				
				@Override
				public void run() {
					Player player = event.getPlayer();
					vanishManager.addStaff(player);
					player.setGameMode(GameMode.SURVIVAL);
					
				}
			}, 22L);
			
		}
		
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(vanishManager.getSvanished().contains(all.getName())) {
						if(!player.hasPermission("*") || !player.hasPermission("vairex.vanish")) {
							player.hidePlayer(all);
						} else if(player.hasPermission("*") || player.hasPermission("vairex.vanish")) {
							player.showPlayer(all);
						}
					}
				}
				
			}
		}, 22);
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(freezeManager.getPlayerlist().contains(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		
		if(staffManager.getStafflist().contains(player.getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void clickInv(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if(staffManager.getStafflist().contains(player.getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(vanishManager.getSvanished().contains(player.getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if(vanishManager.getSvanished().contains(player.getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(staffManager.getStafflist().contains(player.getName())) {
				if(player.getInventory().getItemInHand().getType() == Material.INK_SACK) {
					player.performCommand("vanish");
					staffManager.getInv(player);
				} if(player.getInventory().getItemInHand().getType() == Material.BLAZE_ROD) {
					List<String> players = new ArrayList<>();
					
					for(Player all : Bukkit.getOnlinePlayers()) {
						if(!all.hasPermission("*")) {
							players.add(all.getPlayer().getName());
						}
					}
					Random random = new Random();
					String starget = players.get(random.nextInt(players.size()));
					Player target = Bukkit.getPlayer(starget);
					
					player.teleport(target);
					player.sendMessage("§7Te has teletransportado hacia §e"+target.getName());
				}
			}
		}
	}
	
	@EventHandler
	public void onClickPlayer(PlayerInteractEntityEvent event) {
		Player player = (Player) event.getPlayer();
		Player target = (Player) event.getRightClicked();
		
		if(player.getInventory().getItemInHand().getType() == Material.PACKED_ICE) {
			if(staffManager.getStafflist().contains(player.getName())) {
				player.performCommand("freeze "+target.getName());
			}
		}
	}

}

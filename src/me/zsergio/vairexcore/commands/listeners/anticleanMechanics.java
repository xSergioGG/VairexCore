package me.zsergio.vairexcore.commands.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class anticleanMechanics implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onKill(EntityDamageByEntityEvent event) {
		Player killer = (Player) event.getDamager();
		Player killed = (Player) event.getEntity();
		killer.sendMessage("UEEJS");
		if(event.getEntityType() == EntityType.PLAYER) {
			if(killed.isDead() || killed.getHealth() <= 0) {
				killer.setHealth(24);
			}
		}
	}

}

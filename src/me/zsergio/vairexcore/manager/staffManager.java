package me.zsergio.vairexcore.manager;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.zsergio.vairexcore.Main;

public class staffManager {
	
	private Main plugin = Main.getInstance();
	private vanishManager vanishManager = plugin.getVanishManager();
	private ArrayList<String> stafflist = new ArrayList<>();
	private HashMap<String, ItemStack[]> invs = new HashMap<>();
	
	public void getInv(Player player) {
		Inventory inv = player.getInventory();
		player.getInventory().clear();
		if(!vanishManager.getSvanished().contains(player.getName())) {
			
			ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short) 1);
			ItemMeta vanishmeta = vanish.getItemMeta();
			vanishmeta.setDisplayName("§7Vanish §cDesactivado");
			vanish.setItemMeta(vanishmeta);
			inv.setItem(0, vanish);
		} else {
			
			ItemStack vanish = new ItemStack(Material.INK_SACK, 1, (short) 10);
			ItemMeta vanishmeta = vanish.getItemMeta();
			vanishmeta.setDisplayName("§7Vanish §aActivado");
			vanish.setItemMeta(vanishmeta);
			inv.setItem(0, vanish);
		}
		
		ItemStack randomtp = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta randomtpmeta = randomtp.getItemMeta();
		randomtpmeta.setDisplayName("§7Random TP");
		randomtp.setItemMeta(randomtpmeta);
		inv.setItem(4, randomtp);
		
		ItemStack freeze = new ItemStack(Material.PACKED_ICE, 1);
		ItemMeta freezemeta = freeze.getItemMeta();
		freezemeta.setDisplayName("§bCongelar Usuario");
		freeze.setItemMeta(freezemeta);
		inv.setItem(8, freeze);
		
		player.updateInventory();
	}
	
	public void restoreInv(Player player) {
		ItemStack[] items = invs.get(player.getName());
		player.getInventory().clear();
		player.getInventory().setContents(items);
		player.updateInventory();
	}
	
	public void toggleOn(Player player) {
		if(player.hasPermission("*")) {
			vanishManager.addStaff(player);
		} else {
			vanishManager.addStaff(player);
		}
		stafflist.add(player.getName());
		invs.put(player.getName(), player.getInventory().getContents());
		getInv(player);
		player.setAllowFlight(true);
		player.sendMessage("§eModo Staff:§a Habilitado");
	}
	
	public void toggleOff(Player player) {
		vanishManager.removeStaff(player);
		stafflist.remove(player.getName());
		restoreInv(player);
		player.setAllowFlight(false);
		player.sendMessage("§eModo Staff:§c Deshabilitado");
	}
	
	
	
	public ArrayList<String> getStafflist() {
		return stafflist;
	}
	
}

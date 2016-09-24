package com.github.syuchan1005.teleportdisplay;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by syuchan on 2016/09/24.
 */
@EasyRegister.AddListener
public class TDEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory clickedInventory = event.getClickedInventory();
		if (clickedInventory == null || !(clickedInventory.getHolder() instanceof TDHolder)) return;
		event.setCancelled(true);
		ItemStack currentItem = event.getCurrentItem();
		if (currentItem != null) {
			Location location = toLocation(currentItem.getItemMeta().getLore());
			event.getWhoClicked().teleport(location);
		}
	}

	public static Location toLocation(List<String> lore) {
		World world = Bukkit.getWorld(lore.get(0).substring(7));
		double x = Double.parseDouble(lore.get(1).substring(3));
		double y = Double.parseDouble(lore.get(2).substring(3));
		double z = Double.parseDouble(lore.get(3).substring(3));
		float pitch = Float.parseFloat(lore.get(4).substring(7));
		float yaw = Float.parseFloat(lore.get(5).substring(5));
		return new Location(world, x, y, z, pitch, yaw);
	}
}

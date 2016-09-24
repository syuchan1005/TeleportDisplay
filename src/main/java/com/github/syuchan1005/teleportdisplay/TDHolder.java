package com.github.syuchan1005.teleportdisplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syuchan on 2016/09/24.
 */
public class TDHolder implements InventoryHolder {
	private Plugin plugin;
	private static Inventory inventory;

	public TDHolder(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public Inventory getInventory() {
		if (inventory == null) {
			List<?> itemList = plugin.getConfig().getList("Items");
			inventory = Bukkit.createInventory(null, (itemList.size() / 9 + 1) * 9, "Teleport");
			for (Object items : itemList) {
				Map<String, Object> items1 = (HashMap) items;
				ItemStack item = new ItemStack(Material.valueOf(items1.get("Material").toString()));
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setDisplayName(items1.get("Name").toString());
				itemMeta.setLore(Arrays.asList(
						"World: " + items1.get("World"),
						"X: " + items1.get("X"),
						"Y: " + items1.get("Y"),
						"Z: " + items1.get("Z"),
						"Pitch: " + items1.get("Pitch"),
						"Yaw: " + items1.get("Yaw")));
				item.setItemMeta(itemMeta);
				inventory.addItem(item);
			}
		}
		return inventory;
	}
}

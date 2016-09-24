package com.github.syuchan1005.teleportdisplay;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class TeleportDisplay extends JavaPlugin {
	private static EasyRegister easyRegister;
	private static TeleportDisplay instance;

	@Override
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();
		try {
			easyRegister = new EasyRegister(this);
			easyRegister.registerCommand("td", "TestCommand", "/<command>", "", "You dont have Permission");
		} catch (ReflectiveOperationException | IOException e) {
			e.printStackTrace();
		}
	}

	@EasyRegister.AddCommand(Command = "td")
	public boolean onCommand(CommandSender sender, Command command, String[] args) {
		InventoryHolder holder = new TDHolder(instance);
		if (sender instanceof Player) ((Player) sender).openInventory(holder.getInventory());
		return true;
	}
}

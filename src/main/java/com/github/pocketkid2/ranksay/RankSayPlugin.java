package com.github.pocketkid2.ranksay;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.permission.Permission;

public class RankSayPlugin extends JavaPlugin {

	private static Permission perms = null;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		getCommand("say").setExecutor(new RankSayCommand(this));
		setupPermissions();
	}

	@Override
	public void onDisable() {

	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

	public static Permission getPerms() {
		return perms;
	}
}

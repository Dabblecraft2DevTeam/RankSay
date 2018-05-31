package com.github.pocketkid2.ranksay;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class RankSayPlugin extends JavaPlugin {

	private static Permission perms = null;

	private static Chat chat = null;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		getCommand("say").setExecutor(new RankSayCommand(this));
		setupPermissions();
		setupChat();
	}

	@Override
	public void onDisable() {

	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null;
	}

	public static Permission getPerms() {
		return perms;
	}

	public static Chat getChat() {
		return chat;
	}
}

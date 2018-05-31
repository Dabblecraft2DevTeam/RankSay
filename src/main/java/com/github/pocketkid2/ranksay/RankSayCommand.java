package com.github.pocketkid2.ranksay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankSayCommand implements CommandExecutor {

	private String format;

	public RankSayCommand(RankSayPlugin rankSayPlugin) {
		format = rankSayPlugin.getConfig().getString("format");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return true;
		}

		Player player = (Player) sender;

		if (args.length < 1) {
			player.sendMessage(ChatColor.RED + "You must type a message!");
			return false;
		}

		String message = String.join(" ", args);

		format = format.replace("%RANK%", RankSayPlugin.getPerms().getPrimaryGroup(player));
		format = format.replace("%PLAYER_PREFIX%", RankSayPlugin.getChat().getPlayerPrefix(player));
		format = format.replace("%DISPLAY_NAME%", player.getDisplayName());
		format = format.replace("%PLAYER_NAME%", player.getName());
		format = format.replace("%MESSAGE%", message);

		String broadcast = ChatColor.translateAlternateColorCodes('&', format);

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(broadcast);
		}

		return true;
	}

}

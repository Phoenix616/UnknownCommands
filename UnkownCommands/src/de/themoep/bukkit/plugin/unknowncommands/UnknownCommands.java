package de.themoep.bukkit.plugin.unknowncommands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UnknownCommands extends JavaPlugin implements CommandExecutor, Listener {
	String unknownCommandMsg;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		reloadConfig();
		// Get servertype to set appropriate errormessage (Only Spigot supported yet beside "normal" CraftBukkit)
		String serverversion = Bukkit.getVersion().toLowerCase();
		unknownCommandMsg = (!this.getConfig().getString("errormessage").equals("[]") ? this.getConfig().getString("errormessage") : (serverversion.contains("spigot") ? org.spigotmc.SpigotConfig.unknownCommandMessage : "Unknown command. Type \"/help\" for help."));
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
		
		// Hide commands
		if (command.getName().equalsIgnoreCase("hidecommand") && sender.hasPermission("unknowncommands.hide")) {
			if (args.length != 1) return false;
			// Remove the first / to save only the command name if the user inputed "/commmandname" and not only "comandname":
			String rmCmd = args[0].toLowerCase().replaceFirst("/", "");
			// Check of the whitelist even contains the command we want to remove:
			if (this.getConfig().getList("whitelist").contains(rmCmd)) {	
				List<String> whitelist = this.getConfig().getStringList("whitelist");
				whitelist.remove(rmCmd);
				this.getConfig().set("whitelist", whitelist);
				saveConfig();
				sender.sendMessage(ChatColor.AQUA + "Removed /" + rmCmd + " from the whitelist.");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Can not hide /" + rmCmd + ". It is currently not on the whitelist!");
			return true;
		} else if (command.getName().equalsIgnoreCase("unhidecommand") && sender.hasPermission("unknowncommands.unhide")) {
			if (args.length != 1) return false;if (args.length != 1) return false;
			// Remove the first / to save only the command name if the user inputed "/commmandname" and not only "comandname":
			String addCmd = args[0].toLowerCase().replaceFirst("/", "");
			// Check of the whitelist already contains the command we want to add:
			if (!this.getConfig().getList("whitelist").contains(addCmd)) {	
				List<String> whitelist = this.getConfig().getStringList("whitelist");
				whitelist.add(addCmd);
				this.getConfig().set("whitelist", whitelist);
				saveConfig();
				sender.sendMessage(ChatColor.AQUA + "Added /" + addCmd + " to the whitelist.");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "You do not need to unhide /" + addCmd + ". It is already on the whitelist!");
			return true;
		}
		return false;
	}
	
	@EventHandler
	public void cmdPreprocess(PlayerCommandPreprocessEvent event) {
		// Split the string so that we only get the first part aka the command:
		String command =  event.getMessage().toLowerCase().substring(1).split("\\s")[0];
		Player player = event.getPlayer();
		
		if(!this.getConfig().getList("whitelist").contains(command) && !player.hasPermission("unknowncommands.bypass") /*&& !player.hasPermission("unknowncommands.bypass." + command)*/) { // TODO: Figure out if you can check for permissions which are not registered in the plugin.yml!
			event.setCancelled(true);
			// Send Unknown Command Message
			player.sendMessage(unknownCommandMsg);
			// Send log message:
			getServer().getLogger().info(player.getName() + " issued server command: /" + command);
		}
	}
}

package de.themoep.bukkit.plugin.unknowncommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UnknownCommands extends JavaPlugin implements CommandExecutor, Listener {
	String unknownCommandMsg;

	@EventHandler
	public void cmdPreprocess(PlayerCommandPreprocessEvent event) {
		String command =  event.getMessage().toLowerCase().substring(1).split("\\s")[0];
		this.getLogger().info(command);
		Player player = event.getPlayer();
		
		if(!this.getConfig().getList("whitelist").contains(command) && !player.hasPermission("unknowncommands.bypass") && !player.hasPermission("unknowncommands.bypass." + command)) {
			event.setCancelled(true);
			player.sendMessage(unknownCommandMsg);
		}
	}
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		reloadConfig();
		// Get servertype to set appropriate errormessage (Only Spigot supported yet beside "normal" CraftBukkit)
		String serverversion = Bukkit.getVersion().toLowerCase();
		unknownCommandMsg = (!this.getConfig().getString("errormessage").equals("[]") ? this.getConfig().getString("errormessage") : (serverversion.contains("spigot") ? org.spigotmc.SpigotConfig.unknownCommandMessage : "Unknown command. Type \"/help\" for help."));
		getServer().getPluginManager().registerEvents(this, this);
	}
	
}

package de.lttodra.ttt.commands;

import de.lttodra.ttt.main.Main;
import de.lttodra.ttt.util.ConfigLocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    private Main plugin;

    public SetupCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("TTT.setup")) {
                if (args.length == 0) {
                    player.sendMessage(Main.PREFIX + "§cPlease use §6/setup <LOBBY>§c!");
                } else {
                    if (args[0].equalsIgnoreCase("lobby")) {
                        if (args.length == 1) {
                            new ConfigLocationUtil(plugin, player.getLocation(), "Lobby").saveLocation();
                            player.sendMessage(Main.PREFIX + "§a The Lobby was set.");
                        } else {
                            player.sendMessage(Main.PREFIX + "§cPlease use §6/setup <LOBBY>§c!");
                        }
                    }
                }
            } else {
                player.sendMessage(Main.NO_PERMISSION);
            }
        }
        return false;
    }
}

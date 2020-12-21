package de.lttodra.ttt.commands;

import de.lttodra.ttt.gamestates.LobbyState;
import de.lttodra.ttt.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private static final int START_SECONDS = 5;

    private Main plugin;

    public StartCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("ttt.start")) {
                if (args.length == 0) {
                    if (plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState) {
                        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
                        if (lobbyState.getCountdown().isRunning() && (lobbyState.getCountdown().getSeconds() > START_SECONDS)) {
                            lobbyState.getCountdown().setSeconds(START_SECONDS);
                            player.sendMessage(Main.PREFIX + "§aThe game start has been boosted!");
                        } else {
                            player.sendMessage(Main.PREFIX + "§cThe game already starts!");
                        }
                    } else {
                        player.sendMessage(Main.PREFIX + "§cThe game already starts!");
                    }
                } else {
                    player.sendMessage(Main.PREFIX + "§cPlease use §6/start§c!");
                }
            } else {
                player.sendMessage(Main.NO_PERMISSION);
            }
        }
        return false;
    }
}

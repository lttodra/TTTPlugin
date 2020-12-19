package de.lttodra.ttt.listeners;

import de.lttodra.ttt.gamestates.LobbyState;
import de.lttodra.ttt.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private Main plugin;

    public PlayerConnectionListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        if (!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) {
            return;
        }
        Player player = event.getPlayer();
        plugin.getPlayers().add(player);
        event.setJoinMessage(Main.PREFIX + "§a" + player.getDisplayName() + "§7has joined the game. [" +
                plugin.getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");

        if (plugin.getPlayers().size() >= LobbyState.MIN_PLAYERS) {
            //TODO Start Game
        }
    }

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        if (!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) {
            return;
        }
        Player player = event.getPlayer();
        plugin.getPlayers().remove(player);
        event.setQuitMessage(Main.PREFIX + "§c" + player.getDisplayName() + "§7has left the game. [" +
                plugin.getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");

    }
}

package de.lttodra.ttt.listeners;

import de.lttodra.ttt.countdowns.LobbyCountdown;
import de.lttodra.ttt.gamestates.LobbyState;
import de.lttodra.ttt.main.Main;
import de.lttodra.ttt.util.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLobbyConnectionListener implements Listener {

    private Main plugin;

    public PlayerLobbyConnectionListener(Main plugin) {
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

        ConfigLocationUtil locationUtil = new ConfigLocationUtil(plugin, "Lobby");
        if (locationUtil.loadLocation() != null) {
            player.teleport(locationUtil.loadLocation());
        } else {
            Bukkit.getConsoleSender().sendMessage("§cThe lobby Location wasn't set yet!");
        }

        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if (plugin.getPlayers().size() >= LobbyState.MIN_PLAYERS) {
            if (!countdown.isRunning()) {
                countdown.stopIdle();
                countdown.start();
            }
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

        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if (plugin.getPlayers().size() < LobbyState.MIN_PLAYERS) {
            if (countdown.isRunning()) {
                countdown.stop();
                countdown.startIdle();
            }
        }

    }
}

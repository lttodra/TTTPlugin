package de.lttodra.ttt.main;

import de.lttodra.ttt.commands.SetupCommand;
import de.lttodra.ttt.commands.StartCommand;
import de.lttodra.ttt.gamestates.GameState;
import de.lttodra.ttt.gamestates.GameStateManager;
import de.lttodra.ttt.listeners.PlayerLobbyConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    public static final String PREFIX = "§7[§cTTT§7] §r",
            NO_PERMISSION = PREFIX + "§cYou have no Permission!";

    private GameStateManager gameStateManager;
    private ArrayList<Player> players;

    @Override
    public void onEnable() {
        gameStateManager = new GameStateManager(this);
        players = new ArrayList<>();

        gameStateManager.setGameState(GameState.LOBBY_STATE);

        init(Bukkit.getPluginManager());
        System.out.println("[TTT] The Plugin was started!");
    }

    private void init(PluginManager pluginManager) {
        getCommand("setup").setExecutor(new SetupCommand(this));
        getCommand("start").setExecutor(new StartCommand(this));

        pluginManager.registerEvents(new PlayerLobbyConnectionListener(this), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[TTT] The Plugin was closed!");
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}

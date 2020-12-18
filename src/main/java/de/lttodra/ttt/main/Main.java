package de.lttodra.ttt.main;

import de.lttodra.ttt.gamestates.GameState;
import de.lttodra.ttt.gamestates.GameStateManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private GameStateManager gameStateManager;

    @Override
    public void onEnable() {
        gameStateManager = new GameStateManager(this);

        gameStateManager.setGameState(GameState.LOBBY_STATE);

        System.out.println("[TTT] The Plugin was started!");
    }

    @Override
    public void onDisable() {
        System.out.println("[TTT] The Plugin was closed!");
    }
}

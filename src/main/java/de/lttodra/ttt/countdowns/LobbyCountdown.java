package de.lttodra.ttt.countdowns;

import de.lttodra.ttt.gamestates.GameState;
import de.lttodra.ttt.gamestates.GameStateManager;
import de.lttodra.ttt.gamestates.LobbyState;
import de.lttodra.ttt.main.Main;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

    private static final int COUNTDOWN_TIME = 20,
            IDLE_TIME = 15;

    private GameStateManager gameStateManager;

    private int seconds;
    private int idleID;
    private boolean isRunning;
    private boolean isIdling;

    public LobbyCountdown(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {

            @Override
            public void run() {
                switch (seconds) {
                    case 20:
                    case 10:
                    case 5:
                    case 3:
                    case 2:
                        Bukkit.broadcastMessage(Main.PREFIX + "§7The Game starts in §6" + seconds + " seconds§7.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Main.PREFIX + "§7The Game starts in §a one second§7.");
                        break;
                    case 0:
                        gameStateManager.setGameState(GameState.INGAME_STATE);
                        break;

                    default:
                        break;
                }
                seconds--;
            }
        }, 0, 20);
    }

    @Override
    public void stop() {
        if (isRunning) {
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = COUNTDOWN_TIME;
        }
    }

    public void startIdle() {
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {

            @Override
            public void run() {
                Bukkit.broadcastMessage(Main.PREFIX + "§7For the Game start are missing §6" +
                        (LobbyState.MIN_PLAYERS - gameStateManager.getPlugin().getPlayers().size()) +
                        " Players §6.");
            }
        }, 0, 20 * IDLE_TIME);
    }

    public void stopIdle() {
        if (isIdling) {
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}

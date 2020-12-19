package de.lttodra.ttt.gamestates;

import de.lttodra.ttt.countdowns.LobbyCountdown;

public class LobbyState extends GameState {

    public static final int MIN_PLAYERS = 2,
            MAX_PLAYERS = 9;

    private LobbyCountdown countdown;

    public LobbyState(GameStateManager gameStateManager) {
        countdown = new LobbyCountdown(gameStateManager);
    }

    @Override
    public void start() {
        countdown.startIdle();
    }

    @Override
    public void stop() {

    }

    public LobbyCountdown getCountdown() {
        return countdown;
    }
}

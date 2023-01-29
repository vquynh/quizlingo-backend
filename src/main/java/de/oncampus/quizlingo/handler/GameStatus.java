package de.oncampus.quizlingo.handler;

import java.util.List;

public class GameStatus {
    public GameStatus() {
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public boolean isCanStart() {
        return canStart;
    }

    public void setCanStart(boolean canStart) {
        this.canStart = canStart;
    }

    private long gameId;
    private List<String> players;
    private boolean canStart = false;
    private boolean canEnd = false;

    public boolean isCanEnd() {
        return canEnd;
    }

    public void setCanEnd(boolean canEnd) {
        this.canEnd = canEnd;
    }
}

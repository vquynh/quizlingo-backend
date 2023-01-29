package de.oncampus.quizlingo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameDTO {
    private long id;
    private List<String> players = new ArrayList<>();
    private List<String> sessions = new ArrayList<>();
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTimestamp;

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public void addPlayer(String username) {
        this.players.add(username);
    }

    public void addSession(String sessionId) {
        List<String> newSessions = new ArrayList<>(this.sessions);
        newSessions.add(sessionId);
        this.sessions = newSessions;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public List<String> getPlayers() {
        return this.players;
    }
    public Date getStartTimestamp() {
        return this.startTimestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getSessions() {
        return sessions;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
    }
}

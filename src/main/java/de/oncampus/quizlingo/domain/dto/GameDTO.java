package de.oncampus.quizlingo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class GameDTO {

    List<String> players;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date startTimestamp;

    public List<String> getPlayers() {
        return this.players;
    }
    public Date getStartTimestamp() {
        return this.startTimestamp;
    }
}

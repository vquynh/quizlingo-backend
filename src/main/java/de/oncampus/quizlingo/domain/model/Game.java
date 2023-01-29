package de.oncampus.quizlingo.domain.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startedAt;

    private Date endedAt;

    @ElementCollection
    private List<String> sessions;

    public void setQuizUsers(List<String> quizUsers) {
        this.quizUsers = quizUsers;
    }

    @ElementCollection
    private List<String> quizUsers;

    private boolean isEnded;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public List<String> getQuizUsers() {
        return this.quizUsers;
    }

    public List<String> getSessions() {
        return this.sessions;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
    }
}

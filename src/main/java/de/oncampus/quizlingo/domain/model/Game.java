package de.oncampus.quizlingo.domain.model;

import de.oncampus.quizlingo.domain.model.user.Account;
import de.oncampus.quizlingo.domain.model.user.Player;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startedAt;

    private Date endedAt;

    @OneToMany
    private List<Round> rounds;

   @ManyToMany(mappedBy = "games")
    private Collection<Player> players;

   @OneToOne
   private Account wonBy;

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

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    public Account getWonBy() {
        return wonBy;
    }

    public void setWonBy(Account wonBy) {
        this.wonBy = wonBy;
    }
}

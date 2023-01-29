package de.oncampus.quizlingo.domain.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startedAt;

    private Date endedAt;

    @ManyToMany(mappedBy = "games")
    private Collection<QuizUser> quizUsers;

    @OneToOne
    private QuizUser wonBy;

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

    public Collection<QuizUser> getUsers() {
        return quizUsers;
    }

    public void setUsers(Collection<QuizUser> quizUsers) {
        this.quizUsers = quizUsers;
    }

    public QuizUser getWonBy() {
        return wonBy;
    }

    public void setWonBy(QuizUser wonBy) {
        this.wonBy = wonBy;
    }
}

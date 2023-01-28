package de.oncampus.quizlingo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.oncampus.quizlingo.domain.model.Game;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;
    @Column
    @JsonIgnore
    private String passwordHash;
    @Column
    private String name;

    @Column
    private String imageURL;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "users_games",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "game_id",
                    referencedColumnName = "id"
            )}
    )
    private Collection<Game> games;

    public Collection<Game> getGames() {
        return List.copyOf(this.games);
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

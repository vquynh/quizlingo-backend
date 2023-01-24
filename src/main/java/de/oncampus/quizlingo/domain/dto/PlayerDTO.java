package de.oncampus.quizlingo.domain.dto;

import com.github.javafaker.Faker;

import java.util.Date;

public class PlayerDTO {

    public PlayerDTO(String username) {
        this.username = username;
        this.profilePicUrl = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
        this.country = "Germany";
        this.level = 1;
        this.totalScore = 0;
        this.highScore = 0;
        this.averageScore = 0;
        this.totalGames = 0;
        this.winningPercentage = 0;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    private String username;
    private String profilePicUrl;
    private String country;
    private Integer level;

    private Integer totalScore;
    private float winningPercentage;
    private Integer totalGames;
    private Integer highScore;
    private Integer averageScore;
    private Date createdAt;
    private Date updatedAt;
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public float getWinningPercentage() {
        return winningPercentage;
    }

    public void setWinningPercentage(long winningPercentage) {
        this.winningPercentage = winningPercentage;
    }
}

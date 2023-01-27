package de.oncampus.quizlingo.controller;


public class UserInteraction {
    public UserInteraction(String user, int selectedAnswer, long questionId) {
        this.user = user;
        this.selectedAnswer = selectedAnswer;
        this.questionId = questionId;
    }

    String user;
    String sessionId;
    int selectedAnswer;
    long questionId;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}

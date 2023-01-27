package de.oncampus.quizlingo.controller;

public class AnswerResultMessage {
    public AnswerResultMessage(String user, String timestamp, long questionId) {
        this.user = user;
        this.timestamp = timestamp;
        this.questionId = questionId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    String user;
    String timestamp;
    long questionId;

    @Override
    public String toString() {
        return "AnswerResultMessage{" +
                "user='" + user + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}

package de.oncampus.quizlingo.controller;

public class AnswerResultMessage {
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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    String user;
    String timestamp;
    int selectedAnswer;
    long questionId;
    boolean isCorrect;

    @Override
    public String toString() {
        return "AnswerResultMessage{" +
                "user='" + user + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", selectedAnswer=" + selectedAnswer +
                ", questionId=" + questionId +
                ", isCorrect=" + isCorrect +
                '}';
    }
}

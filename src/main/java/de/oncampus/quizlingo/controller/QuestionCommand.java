package de.oncampus.quizlingo.controller;

import java.util.List;

public class QuestionCommand {


    private String taskText;

    public QuestionCommand(Long id, String questionText, String taskText, String topic, Integer level, List<String> terms, List<String> options, Integer correctOption) {
        this.id = id;
        this.taskText = taskText;
        this.questionText = questionText;
        this.level = level;
        this.topic = topic;
        this.terms = terms;
        this.options = options;
        this.correctOption = correctOption;
    }

    private Long id;
    private String questionText;
    private String topic;
    private List<String> terms;
    private List<String> options;
    private Integer correctOption;
    private Integer level;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Integer correctOption) {
        this.correctOption = correctOption;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
}

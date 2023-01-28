package de.oncampus.quizlingo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class QuestionDTO {

    @JsonIgnore
    private Integer correctOption;
    private String taskText;

    public QuestionDTO(Long id, String questionText, String taskText, String category, String type, List<String> terms, List<String> options, Integer correctOption) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.taskText = taskText;
        this.questionText = questionText;
        this.terms = terms;
        this.options = options;
        this.correctOption = correctOption;
    }
    private Long id;
    private String questionText;
    private String category;
    private String type;
    private List<String> terms;
    private List<String> options;
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public Integer getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Integer correctOption) {
        this.correctOption = correctOption;
    }
}

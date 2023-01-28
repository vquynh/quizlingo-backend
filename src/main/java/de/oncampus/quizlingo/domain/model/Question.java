package de.oncampus.quizlingo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Topic topic;

    private String questionText;
    private String taskText;
    private String type;

    @OneToMany
    private List<Term> terms;

    @ElementCollection
    List<String> options = new ArrayList<>();

    private int correctOption;

    private int level;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return List.copyOf(options);
    }

    public void setOptions(List<String> optionsText) {
        this.options = optionsText;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctAnswer) {
        this.correctOption = correctAnswer;
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
}

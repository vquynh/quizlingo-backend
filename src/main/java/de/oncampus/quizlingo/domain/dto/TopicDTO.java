package de.oncampus.quizlingo.domain.dto;

public class TopicDTO {
    public TopicDTO(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

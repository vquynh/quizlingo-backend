package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.QuestionCommand;
import de.oncampus.quizlingo.domain.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getAllQuestions();
    QuestionDTO getQuestion(long id);

    QuestionDTO addQuestion(QuestionCommand createQuestionCommand);

    List<QuestionDTO> getQuestionsByTopic(String topic);

    void deleteQuestion(Long id);

    QuestionDTO updateQuestion(Long id, QuestionCommand questionCommand);
}

package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.QuizAnswer;
import de.oncampus.quizlingo.handler.AnswerResult;

public interface QuizAnswerService {

    AnswerResult addQuizAnswer(QuizAnswer quizAnswer);
}

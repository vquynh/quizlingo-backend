package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.QuizAnswer;
import de.oncampus.quizlingo.handler.AnswerResult;
import de.oncampus.quizlingo.domain.model.Interaction;
import de.oncampus.quizlingo.repository.InteractionRepository;
import de.oncampus.quizlingo.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QuizAnswerServiceImpl implements QuizAnswerService {

    private final InteractionRepository interactionRepository;
    private final QuestionRepository questionRepository;

    public QuizAnswerServiceImpl(InteractionRepository interactionRepository, QuestionRepository questionRepository) {
        this.interactionRepository = interactionRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public AnswerResult addQuizAnswer(QuizAnswer quizAnswer) {
        Interaction interaction = new Interaction();
        interaction.setGameId(quizAnswer.getGameId());
        interaction.setQuestionId(quizAnswer.getQuestionId());
        interaction.setTime(new Date());
        interaction.setUsername(quizAnswer.getUsername());
        int selectedAnswer = quizAnswer.getSelectedAnswer();
        interaction.setSelectedAnswer(selectedAnswer);
        int correctAnswer = questionRepository.findQuestionById(quizAnswer.getQuestionId()).getCorrectOption();
        boolean isCorrect = correctAnswer == selectedAnswer;
        int newScore = isCorrect ? quizAnswer.getCurrentScore() + 1 : quizAnswer.getCurrentScore();
        interaction.setCorrect(isCorrect);
        interaction.setTotalScore(newScore);
        interactionRepository.save(interaction);
        return toInteractionDTO(interaction, correctAnswer);
    }

    private AnswerResult toInteractionDTO(Interaction interaction, int correctAnswer) {
        AnswerResult dto = new AnswerResult();
        dto.setGameId(interaction.getGameId());
        dto.setTimestamp(new SimpleDateFormat("HH:mm:ss.SSSZ").format(interaction.getTime()));
        dto.setUsername(interaction.getUsername());
        dto.setQuestionId(interaction.getQuestionId());
        dto.setCorrect(interaction.isCorrect());
        dto.setTotalScore(interaction.getTotalScore());
        dto.setSelectedAnswer(interaction.getSelectedAnswer());
        dto.setCorrectAnswer(correctAnswer);
        return dto;
    }
}

package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.InteractionCommand;
import de.oncampus.quizlingo.domain.dto.UserInteractionDTO;
import de.oncampus.quizlingo.domain.model.Interaction;
import de.oncampus.quizlingo.repository.InteractionRepository;
import de.oncampus.quizlingo.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final InteractionRepository interactionRepository;
    private final QuestionRepository questionRepository;

    public InteractionServiceImpl(InteractionRepository interactionRepository, QuestionRepository questionRepository) {
        this.interactionRepository = interactionRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public UserInteractionDTO addInteraction(InteractionCommand interactionCommand) {
        Interaction interaction = new Interaction();
        interaction.setGameId(interactionCommand.getGameId());
        interaction.setQuestionId(interactionCommand.getQuestionId());
        interaction.setTime(new Date());
        interaction.setUser(interactionCommand.getUsername());
        int selectedAnswer = interactionCommand.getSelectedAnswer();
        interaction.setSelectedAnswer(selectedAnswer);
        boolean isCorrect = questionRepository.findQuestionById(interactionCommand.getQuestionId()).getCorrectOption()
                == selectedAnswer;
        int newScore = isCorrect ? interactionCommand.getCurrentScore() + 1 : interactionCommand.getCurrentScore();
        interaction.setCorrect(isCorrect);
        interaction.setTotalScore(newScore);
        interactionRepository.save(interaction);
        return toInteractionDTO(interaction);
    }

    private UserInteractionDTO toInteractionDTO(Interaction interaction) {
        UserInteractionDTO dto = new UserInteractionDTO();
        dto.setGameId(interaction.getGameId());
        dto.setTimestamp(new SimpleDateFormat("HH:mm:ss.SSSZ").format(interaction.getTime()));
        dto.setUsername(interaction.getUser());
        dto.setQuestionId(interaction.getQuestionId());
        dto.setCorrect(interaction.isCorrect());
        dto.setTotalScore(interaction.getTotalScore());
        dto.setSelectedAnswer(interaction.getSelectedAnswer());
        return dto;
    }
}

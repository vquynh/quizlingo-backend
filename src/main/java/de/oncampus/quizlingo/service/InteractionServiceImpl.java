package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.InteractionCommand;
import de.oncampus.quizlingo.domain.dto.UserInteractionDTO;
import de.oncampus.quizlingo.domain.model.Interaction;
import de.oncampus.quizlingo.repository.InteractionRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final InteractionRepository interactionRepository;

    public InteractionServiceImpl(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    @Override
    public UserInteractionDTO addInteraction(InteractionCommand interactionCommand) {
        Interaction interaction = new Interaction();
        interaction.setGameId(interactionCommand.getGameId());
        interaction.setQuestionId(interactionCommand.getQuestionId());
        interaction.setTime(new Date());
        interaction.setUser(interactionCommand.getUsername());
        interaction.setSelectedAnswer(interactionCommand.getSelectedAnswer());
        interactionRepository.save(interaction);
        return toInteractionDTO(interaction);
    }

    private UserInteractionDTO toInteractionDTO(Interaction interaction) {
        UserInteractionDTO dto = new UserInteractionDTO();
        dto.setGameId(interaction.getGameId());
        dto.setTimestamp(new SimpleDateFormat("HH:mm:ss.SSSZ").format(interaction.getTime()));
        dto.setUsername(interaction.getUser());
        dto.setQuestionId(interaction.getQuestionId());
        return dto;
    }
}

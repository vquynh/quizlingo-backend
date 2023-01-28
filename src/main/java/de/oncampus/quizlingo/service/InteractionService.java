package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.InteractionCommand;
import de.oncampus.quizlingo.domain.dto.UserInteractionDTO;

public interface InteractionService {

    UserInteractionDTO addInteraction(InteractionCommand interactionCommand);
}

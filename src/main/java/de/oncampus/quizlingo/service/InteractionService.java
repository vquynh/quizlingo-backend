package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.controller.InteractionCommand;
import de.oncampus.quizlingo.domain.dto.AnswerResult;

public interface InteractionService {

    AnswerResult addInteraction(InteractionCommand interactionCommand);
}

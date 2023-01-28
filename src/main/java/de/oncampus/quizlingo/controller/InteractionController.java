package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.domain.dto.UserInteractionDTO;
import de.oncampus.quizlingo.service.InteractionService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @MessageMapping("/app/websocket")
    @SendTo("/topic/interactions")
    public UserInteractionDTO send(final InteractionCommand interactionCommand) {
        return interactionService.addInteraction(interactionCommand);
    }
}

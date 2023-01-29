package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.handler.AnswerResult;
import de.oncampus.quizlingo.service.QuizAnswerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class StompWebSocketController {

    private final QuizAnswerService quizAnswerService;

    public StompWebSocketController(QuizAnswerService quizAnswerService) {
        this.quizAnswerService = quizAnswerService;
    }

    @MessageMapping("/app/websocket")
    @SendTo("/topic/interactions")
    public AnswerResult send(final QuizAnswer quizAnswer) {
        return quizAnswerService.addQuizAnswer(quizAnswer);
    }
}

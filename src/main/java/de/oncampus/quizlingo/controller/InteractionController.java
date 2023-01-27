package de.oncampus.quizlingo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class InteractionController {

    /*


    @MessageMapping("/interaction")
    @SendTo("/topic/messages")
    public AnswerResultMessage send(final UserInteraction interaction) throws Exception {
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new AnswerResultMessage(interaction.user, time, interaction.selectedAnswer, interaction.getQuestionId(), true);
    }
     */

    @MessageMapping("/app/websocket")
    @SendTo("/topic/interactions")
    public AnswerResultMessage send(final UserInteraction interaction) throws Exception {
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        // note the selected answer

        //

        return new AnswerResultMessage(interaction.user, time, interaction.questionId);

        // wh
    }
}

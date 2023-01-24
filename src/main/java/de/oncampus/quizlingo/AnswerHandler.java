package de.oncampus.quizlingo;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import de.oncampus.quizlingo.controller.AnswerResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ThreadLocalRandom;


public class AnswerHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(AnswerHandler.class);
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private final Faker faker = new Faker();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Server connection opened");
        sessions.add(session);

        TextMessage message = new TextMessage("one-time message from server");
        logger.info("Server sends: {}", message);
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Server connection closed: {}", status);
        sessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        logger.info("Server received: {}", request);

        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        logger.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
    }

    @Scheduled(fixedRate = 10000)
    void sendPeriodicMessages() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                AnswerResultMessage message = new AnswerResultMessage();
                message.setUser(faker.name().username());
                message.setTimestamp(time);
                message.setSelectedAnswer(ThreadLocalRandom.current().nextInt(0,4));
                message.setQuestionId(ThreadLocalRandom.current().nextLong(0,14));
                message.setCorrect(ThreadLocalRandom.current().nextBoolean());
                logger.info("Sending message: " + message);
                String output = "";

                try {
                    // convert object to json string and return it
                    output = mapper.writeValueAsString(message);
                }
                catch (JsonGenerationException | JsonMappingException e) {
                    logger.error("Exception while creating json for AnswerResultMessage " + message.toString(), e);
                }

                session.sendMessage(new TextMessage(output));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Server transport error: {}", exception.getMessage());
    }

}

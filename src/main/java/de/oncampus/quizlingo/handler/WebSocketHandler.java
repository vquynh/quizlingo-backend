package de.oncampus.quizlingo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.oncampus.quizlingo.controller.InteractionCommand;
import de.oncampus.quizlingo.domain.dto.AnswerResult;
import de.oncampus.quizlingo.service.InteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final InteractionService interactionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Map<String, WebSocketSession> idToActiveSession = new HashMap<>();

    public WebSocketHandler(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketHandler.class);

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        LOG.error("error occured at sender " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        idToActiveSession.remove(session.getId());
        super.afterConnectionClosed(session, status);
        LOG.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        idToActiveSession.put(session.getId(), session);
        super.afterConnectionEstablished(session);
        LOG.info("Connected ... " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
        LOG.info("message received: " + jsonTextMessage.getPayload());
        AnswerResult dto = interactionService.addInteraction(
                objectMapper.readValue(jsonTextMessage.getPayload(),
                InteractionCommand.class));
        for (Map.Entry<String, WebSocketSession> otherSession : idToActiveSession.entrySet()) {
            otherSession.getValue().sendMessage(new TextMessage(objectMapper.writeValueAsString(dto)));
        }
    }
}
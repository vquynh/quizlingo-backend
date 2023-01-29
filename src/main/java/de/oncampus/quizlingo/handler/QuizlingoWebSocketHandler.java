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

/**
 * A handler for WebSocket messages from quizlingo clients.
 */
@Component
public class QuizlingoWebSocketHandler extends TextWebSocketHandler {
    private final InteractionService interactionService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, WebSocketSession> idToActiveSession = new HashMap<>();

    public QuizlingoWebSocketHandler(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    private static final Logger LOG = LoggerFactory.getLogger(QuizlingoWebSocketHandler.class);

    /**
     * Handles transport error that happen during a Websocket session
     *
     * @param session is the current WebSocketSession
     * @param throwable is the error
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        super.handleTransportError(session, throwable);
        LOG.error("error occurred at sender " + session, throwable);
    }

    /**
     * Adds the session to the map idToActiveSession of active sessions
     * after connection is established
     *
     * @param session is the current WebSocketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        idToActiveSession.put(session.getId(), session);
        super.afterConnectionEstablished(session);
        LOG.info("Connected ... " + session.getId());
    }

    /**
     * Removes the session from the map idToActiveSession of active sessions
     * after connection is closed
     *
     * @param session is the current WebSocketSession
     * @param status is the CloseStatus of the session
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        idToActiveSession.remove(session.getId());
        super.afterConnectionClosed(session, status);
        LOG.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    }

    /**
     * Handles the jsonTextMessage sent by the client
     *
     * @param session is the current WebSocketSession
     * @param jsonTextMessage is the TextMessage sent to the websocket endpoint
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage jsonTextMessage) throws Exception {
        LOG.info("message received: " + jsonTextMessage.getPayload());
        // Get the AnswerResult for the given user interaction
        AnswerResult answerResult = interactionService.addInteraction(
                objectMapper.readValue(jsonTextMessage.getPayload(),
                InteractionCommand.class));
        // Send the AnswerResult as text message to all active sessions
        // TODO: only send messages to the sessions of the same game
        // We can do this by storing the gameId in the idToActiveSession
        for (Map.Entry<String, WebSocketSession> otherSession : idToActiveSession.entrySet()) {
            otherSession.getValue().sendMessage(new TextMessage(objectMapper.writeValueAsString(answerResult)));
        }
    }
}
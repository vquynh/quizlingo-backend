package de.oncampus.quizlingo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.oncampus.quizlingo.domain.dto.GameDTO;
import de.oncampus.quizlingo.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

/**
 * A handler for WebSocket messages about joining and ending a game from clients.
 */
@Component
public class GameWebSocketHandler extends TextWebSocketHandler {
    private final GameService gameService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Map<Long, List<WebSocketSession>> gameIdToSessions = new HashMap<>();

    public GameWebSocketHandler(GameService gameService) {
        this.gameService = gameService;
    }

    private static final Logger LOG = LoggerFactory.getLogger(GameWebSocketHandler.class);

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
     * Logs when a websocket session is connected
     *
     * @param session is the current WebSocketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        LOG.info("Connected ... " + session.getId());
    }

    /**
     * Removes the session from the map sessionIdToGameId of active sessions
     * after connection is closed
     *
     * @param session is the current WebSocketSession
     * @param status is the CloseStatus of the session
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
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
        // Read the jsonTextMessage payload as JoinGameMessage
        GameInteractionMessage message = objectMapper.readValue(jsonTextMessage.getPayload(),
                GameInteractionMessage.class);

        // Handle message based on message type
        switch (message.getType()){
            case "join": joinGame(message, session); break;
            case "end":  endGame(message.getGameId()); break;
        }
    }

    private void endGame(long gameId) throws IOException {
        GameStatus gameStatus = new GameStatus();
        gameStatus.setGameId(gameId);
        gameStatus.setCanEnd(true);
        for (WebSocketSession activeSession : gameIdToSessions.get(gameId)
             ) {
            activeSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(gameStatus)));
        }
        this.gameIdToSessions.remove(gameId);
    }

    private void joinGame(GameInteractionMessage message, WebSocketSession session) throws IOException {
        Set<Long> activeGames = gameIdToSessions.keySet();
        long gameId;
        String username = message.getUsername();
        GameStatus gameStatus = new GameStatus();
        long nextAvailableGame = activeGames.stream().filter(id -> gameIdToSessions.get(id).size() == 1)
                .findFirst().orElse(-1L);
        if (nextAvailableGame == -1L) {
            // Add a new game
            GameDTO newGame = new GameDTO();
            newGame.setStartTimestamp(new Date());
            newGame.addPlayer(username);
            newGame.addSession(session.getId());
            GameDTO savedGame = gameService.saveGame(newGame);
            gameId = savedGame.getId();
            // Update the caches with the newly added game
            gameIdToSessions.put(gameId, Arrays.asList(session));
            // Create the GameStatus to send to client
            gameStatus.setGameId(gameId);
            gameStatus.setCanStart(false);
            gameStatus.setPlayers(savedGame.getPlayers());
        } else {
            // Update the caches with new session
            gameId = nextAvailableGame;
            List<WebSocketSession> sessions = new ArrayList<>(gameIdToSessions.get(nextAvailableGame));
            sessions.add(session);
            gameIdToSessions.put(nextAvailableGame, sessions);
            // Update the game with new player
            GameDTO gameDTO = gameService.getGameById(nextAvailableGame);
            gameDTO.addPlayer(message.getUsername());
            gameDTO.addSession(session.getId());
            gameService.saveGame(gameDTO);
            // Create the GameStatus to send to client
            gameStatus.setGameId(nextAvailableGame);
            gameStatus.setCanStart(true);
            gameStatus.setPlayers(gameDTO.getPlayers());
        }
        // Send the GameStatus as text message to all sessions of this game
        for (WebSocketSession webSocketSession : gameIdToSessions.get(gameId)) {
            webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(gameStatus)));
        }
    }
}
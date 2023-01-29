package de.oncampus.quizlingo.config;

import de.oncampus.quizlingo.handler.QuizlingoWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, WebSocketConfigurer {

    @Autowired
    protected QuizlingoWebSocketHandler quizlingoWebSocketHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("http://localhost:5001/", "http://localhost:3000/");
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("http://localhost:5001/", "http://localhost:3000/").withSockJS();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(quizlingoWebSocketHandler, "/websocket-native")
                .setAllowedOrigins("http://localhost:5001/", "http://localhost:3000/");
    }
}


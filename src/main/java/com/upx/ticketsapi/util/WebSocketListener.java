package com.upx.ticketsapi.util;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketListener {
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        var headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        if (headerAccessor.getDestination() == null || headerAccessor.getSessionAttributes() == null) {
            log.info("Destination or session attributes are null.");
            return;
        }

        var destination = headerAccessor.getDestination();
        if (destination == null) {
            log.info("Destination is null.");
            return;
        }

        var ticketId = destination.replace("/topic/chat/", "");
        var sessionAttributes = headerAccessor.getSessionAttributes();
        if (sessionAttributes != null) {
            var username = (String) sessionAttributes.get("username");
            if (username == null) {
                log.info("Username is null.");
                return;
            }
            log.info(String.format("User %s Disconnected from chat %s", username, ticketId));
        } 
    }
}

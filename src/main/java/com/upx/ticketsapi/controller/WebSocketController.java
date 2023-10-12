package com.upx.ticketsapi.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import com.upx.ticketsapi.model.Interaction;
import com.upx.ticketsapi.payload.InteractionDTO;
import com.upx.ticketsapi.service.InteractionService;

@Controller
public class WebSocketController {
    private final InteractionService interactionService;

    public WebSocketController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @MessageMapping("/chat/{ticketId}")
    @SendTo("/topic/chat/{ticketId}")
    public Interaction sendMessageToTicketChat(
            @DestinationVariable Integer ticketId,
            @Payload InteractionDTO interactionDTO) {
        return interactionService.create(interactionDTO);
    }

    public List<Interaction> addUser(
            @DestinationVariable Integer ticketId,
            @Payload InteractionDTO interactionDTO,
            SimpMessageHeaderAccessor headerAccessor) {

        if (headerAccessor == null || headerAccessor.getSessionAttributes() == null) {
            return Collections.emptyList();
        }

        var sessionAttributes = headerAccessor.getSessionAttributes();
        if (sessionAttributes == null) {
            return Collections.emptyList();
        }

        var userDTO = interactionDTO.getUser();
        if (userDTO != null) {
            String username = userDTO.getUserName();
            sessionAttributes.put("username", username);
        }
        return interactionService.getAllByTicketId(ticketId);
    }
}

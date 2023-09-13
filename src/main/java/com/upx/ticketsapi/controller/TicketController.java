package com.upx.ticketsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.payload.TicketDTO;
import com.upx.ticketsapi.service.TicketService;
import com.upx.ticketsapi.util.SuccessResponseUtil;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<Ticket>> createTicket(@RequestBody TicketDTO ticketDTO) {
        return SuccessResponseUtil.createdResponse(ticketService.save(ticketDTO));
    }
}

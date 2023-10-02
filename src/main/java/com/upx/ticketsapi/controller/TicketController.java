package com.upx.ticketsapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/requester/{userId}")
    public ResponseEntity<SuccessResponse<Page<Ticket>>> getUserTickets(
        @PathVariable Integer userId,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(defaultValue = "ticketId") String sortBy,
        @RequestParam(defaultValue = "desc") String direction) {
            return SuccessResponseUtil.okResponse(ticketService.getUserTickets(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)),userId));
        }
}

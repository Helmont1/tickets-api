package com.upx.ticketsapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.payload.RelatoryDTO;
import com.upx.ticketsapi.payload.RelatoryFilterDTO;
import com.upx.ticketsapi.payload.TicketDTO;
import com.upx.ticketsapi.service.TicketService;
import com.upx.ticketsapi.util.SuccessResponseUtil;

import jakarta.annotation.security.RolesAllowed;

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
        return SuccessResponseUtil.okResponse(ticketService.getUserTickets(
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)), userId));
    }

    @PutMapping("/update")
    @RolesAllowed("update-ticket")
    public ResponseEntity<SuccessResponse<Ticket>> updateTicket(
            @RequestBody TicketDTO ticketDTO) {
        return SuccessResponseUtil.okResponse(ticketService.update(ticketDTO));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<SuccessResponse<Ticket>> getTicketById(@PathVariable Integer ticketId) {
        return SuccessResponseUtil.okResponse(ticketService.getTicketById(ticketId));
    }

    @PostMapping("/analyst/{userId}")
    public ResponseEntity<SuccessResponse<Page<Ticket>>> getActiveTicketsByUser(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ticketId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestBody List<Integer> statusIds) {
        return SuccessResponseUtil.okResponse(ticketService.getActiveTicketsByUser(
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)), userId, statusIds));
    }

    @RolesAllowed("generate-relatory")
    @PostMapping("/relatory")
    public ResponseEntity<SuccessResponse<RelatoryDTO>> generateRelatory(
            @RequestBody RelatoryFilterDTO filter) {
        return SuccessResponseUtil.okResponse(ticketService.generateRelatory(filter));
    }
}

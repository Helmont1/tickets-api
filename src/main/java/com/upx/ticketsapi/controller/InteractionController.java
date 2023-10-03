package com.upx.ticketsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.Interaction;
import com.upx.ticketsapi.payload.InteractionDTO;
import com.upx.ticketsapi.service.InteractionService;
import com.upx.ticketsapi.util.SuccessResponseUtil;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<SuccessResponse<List<Interaction>>> getByTicket(@PathVariable Integer ticketId) {
        return SuccessResponseUtil.okResponse(interactionService.getAllByTicketId(ticketId));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<Interaction>> create( @RequestBody InteractionDTO interactionDTO) {
        return SuccessResponseUtil.okResponse(interactionService.create(interactionDTO));
    }

    @PutMapping("/{interactionId}")
    public ResponseEntity<SuccessResponse<Interaction>> update(@PathVariable Integer interactionId,
            @RequestBody InteractionDTO interactionDTO) {
        interactionDTO.setInteractionId(interactionId);
        return SuccessResponseUtil.okResponse(interactionService.update(interactionDTO));
    }
}

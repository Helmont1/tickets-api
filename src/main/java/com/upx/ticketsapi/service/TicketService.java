package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.payload.TicketDTO;
import com.upx.ticketsapi.repository.TicketRepository;

import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;


@Service
public class TicketService {
    private static final String TICKET_NOT_FOUND_MSG = "Ticket not found with id: ";

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TICKET_NOT_FOUND_MSG + id));
    }

    public Ticket save(TicketDTO ticketDTO) {
        return ticketRepository.save(fromDTO(ticketDTO, Ticket.class));
    }

    public Ticket update(TicketDTO ticketDTO) {
        var ticket = fromDTO(ticketDTO, Ticket.class);
        var ticketFromDb = getTicketById(ticketDTO.getTicketId());
        BeanUtils.copyProperties(ticket, ticketFromDb, "ticketId");
        return ticketRepository.save(ticketFromDb);
    }
}

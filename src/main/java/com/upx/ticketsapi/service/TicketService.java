package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.payload.TicketDTO;
import com.upx.ticketsapi.repository.TicketRepository;

import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

import java.util.List;


@Service
public class TicketService {
    private static final String TICKET_NOT_FOUND_MSG = "Ticket not found with id: ";

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final StatusChangeService statusChangeService;

    public TicketService(TicketRepository ticketRepository, UserService userService, StatusChangeService statusChangeService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.statusChangeService = statusChangeService;
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
        if (!ticketFromDb.getStatus().getStatusId().equals(ticket.getStatus().getStatusId())) {
            statusChangeService.create(ticketFromDb, ticket.getStatus());
        }
        BeanUtils.copyProperties(ticket, ticketFromDb, "ticketId");
        return ticketRepository.save(ticketFromDb);
    }

    public Page<Ticket> getUserTickets(PageRequest pageRequest, Integer userId) {
        userService.getById(userId);
        return ticketRepository.findByUser(pageRequest, userId);
    }

    public Page<Ticket> getActiveTicketsByUser(PageRequest pageRequest, Integer userId, List<Integer> statusIds) {
        return ticketRepository.findActiveTicketsByUser(pageRequest, userId, statusIds);
    }
}

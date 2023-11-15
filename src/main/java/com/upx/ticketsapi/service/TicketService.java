package com.upx.ticketsapi.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.exception.SqlException;
import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.payload.RelatoryDTO;
import com.upx.ticketsapi.payload.RelatoryFilterDTO;
import com.upx.ticketsapi.payload.TicketDTO;
import com.upx.ticketsapi.repository.TicketRepository;

import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class TicketService {
    private static final String TICKET_NOT_FOUND_MSG = "Ticket not found with id: ";

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final StatusChangeService statusChangeService;

    public TicketService(TicketRepository ticketRepository, UserService userService,
            StatusChangeService statusChangeService) {
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

    public RelatoryDTO generateRelatory(RelatoryFilterDTO filter) {
        return ticketRepository.findRelatoryByFilter(filter);
    }

    public InputStreamResource exportRelatory(RelatoryFilterDTO filter) {
        var relatory = generateRelatory(filter);
        var out = new ByteArrayOutputStream();
        try (var workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("Relatório");
            var header = sheet.createRow(0);
            header.createCell(0).setCellValue("Tickets não atribuídos");
            header.createCell(1).setCellValue("Tickets atribuídos");
            header.createCell(2).setCellValue("Tickets em espera");
            header.createCell(3).setCellValue("Total de tickets");
            var row = sheet.createRow(1);
            row.createCell(0).setCellValue(relatory.getNotAssignedTickets());
            row.createCell(1).setCellValue(relatory.getAssignedTickets());
            row.createCell(2).setCellValue(relatory.getOnHoldTickets());
            row.createCell(3).setCellValue(relatory.getTotalTickets());
            
            workbook.write(out);
            return new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));
        } catch (Exception e) {
            throw new SqlException("Error while exporting relatory");
        }
    }
}

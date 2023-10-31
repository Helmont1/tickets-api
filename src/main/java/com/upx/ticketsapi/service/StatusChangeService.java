package com.upx.ticketsapi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.upx.ticketsapi.model.Status;
import com.upx.ticketsapi.model.StatusChange;
import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.repository.StatusChangeRepository;

import lombok.NonNull;

@Service
public class StatusChangeService {
    private final StatusChangeRepository statusChangeRepository;

    public StatusChangeService(StatusChangeRepository statusChangeRepository) {
        this.statusChangeRepository = statusChangeRepository;
    }

    public StatusChange create(@NonNull Ticket ticket, @NonNull Status status) {
        var statusChange = new StatusChange();
        statusChange.setChangeDate(LocalDateTime.now());
        statusChange.setTicket(ticket);
        statusChange.setStatus(status);

        return statusChangeRepository.save(statusChange);
    }
}

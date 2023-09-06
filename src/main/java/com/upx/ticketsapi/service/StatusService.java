package com.upx.ticketsapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Status;
import com.upx.ticketsapi.repository.StatusRepository;

@Service
public class StatusService {
    private static final String STATUS_NOT_FOUND_MSG = "Status not found with id: ";
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getById(Integer statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new NotFoundException(STATUS_NOT_FOUND_MSG + statusId));
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    
}

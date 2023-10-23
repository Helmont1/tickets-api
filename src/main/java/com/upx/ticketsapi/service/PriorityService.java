package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Priority;
import com.upx.ticketsapi.payload.PriorityDTO;
import com.upx.ticketsapi.repository.PriorityRepository;

import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

import java.util.List;


@Service
public class PriorityService {
    private static final String DEPARTMENT_NOT_FOUND_MSG = "Department with id %s";
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public Priority getById(Integer priorityId) {
        return priorityRepository.findById(priorityId).orElseThrow(
                () -> new NotFoundException(String.format(DEPARTMENT_NOT_FOUND_MSG, priorityId)));
    }

    public Priority create(PriorityDTO priorityDTO) {
        return priorityRepository.save(fromDTO(priorityDTO, Priority.class));
    }

    public Priority update(PriorityDTO priorityDTO) {
        var priority = fromDTO(priorityDTO, Priority.class);
        var priorityFromDb = getById(priorityDTO.getPriorityId());
        BeanUtils.copyProperties(priority, priorityFromDb, "priorityId");
        return priorityRepository.save(priorityFromDb);
    }

    public List<Priority> getAll() {
        return priorityRepository.findAll();
    }
}

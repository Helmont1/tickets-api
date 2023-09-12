package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Interaction;
import com.upx.ticketsapi.payload.InteractionDTO;
import com.upx.ticketsapi.repository.InteractionRepository;
import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

import java.util.List;

@Service
public class InteractionService {
    private static final String DEPARTMENT_NOT_FOUND_MSG = "Department with id %s";
    private final InteractionRepository interactionRepository;

    public InteractionService(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    public Interaction getById(Integer interactionId) {
        return interactionRepository.findById(interactionId).orElseThrow(
                () -> new NotFoundException(String.format(DEPARTMENT_NOT_FOUND_MSG, interactionId)));
    }

    public Interaction create(InteractionDTO interactionDTO) {
        return interactionRepository.save(fromDTO(interactionDTO, Interaction.class));
    }

    public Interaction update(InteractionDTO interactionDTO) {
        var interaction = fromDTO(interactionDTO, Interaction.class);
        var interactionFromDb = getById(interactionDTO.getInteractionId());
        BeanUtils.copyProperties(interaction, interactionFromDb, "interactionId");
        return interactionRepository.save(interactionFromDb);
    }

    public List<Interaction> getAllByTicketId(Integer ticketId) {
        return interactionRepository.findAllByTicketId(ticketId);
    }
}

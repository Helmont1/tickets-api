package com.upx.ticketsapi.service;

import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Team;
import com.upx.ticketsapi.payload.TeamDTO;
import com.upx.ticketsapi.repository.TeamRepository;
import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private static final String TEAM_NOT_FOUND_MSG = "Team with id %s";

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getById(Integer teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundException(String.format(TEAM_NOT_FOUND_MSG, teamId)));
    }

    public Team createTeam(TeamDTO teamDTO) {
        return teamRepository.save(((Team) fromDTO(teamDTO, new Team())));

    }
}

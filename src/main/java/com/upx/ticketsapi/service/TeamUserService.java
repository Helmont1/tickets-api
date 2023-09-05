package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.TeamUser;
import com.upx.ticketsapi.payload.TeamUserDTO;
import com.upx.ticketsapi.repository.TeamUserRepository;

import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

@Service
public class TeamUserService {
    private static final String TEAM_USER_NOT_FOUND = "Team user not found with id: ";

    private final TeamUserRepository teamUserRepository;

    public TeamUserService(TeamUserRepository teamUserRepository) {
        this.teamUserRepository = teamUserRepository;
    }

    public TeamUser getById(Integer teamUserId) {
        return teamUserRepository.findById(teamUserId)
                .orElseThrow(() -> new NotFoundException(TEAM_USER_NOT_FOUND + teamUserId));
    }

    public TeamUser create(TeamUserDTO teamUserDTO) {
        return teamUserRepository.save(fromDTO(teamUserDTO, TeamUser.class));
    }

    public TeamUser update(TeamUserDTO teamUserDTO) {
        var teamUser = fromDTO(teamUserDTO, TeamUser.class);
        var teamUserFromDb = getById(teamUserDTO.getTeamUserId());
        BeanUtils.copyProperties(teamUser, teamUserFromDb, "teamUserId");
        return teamUserRepository.save(teamUserFromDb);
    }

    public TeamUser updateStatus(Integer teamUserId) {
        var teamUser = getById(teamUserId);
        teamUser.setActive(!teamUser.getActive());
        return teamUserRepository.save(teamUser);
    }
}

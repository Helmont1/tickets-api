package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.TeamDepartment;
import com.upx.ticketsapi.payload.TeamDepartmentDTO;
import com.upx.ticketsapi.repository.TeamDepartmentRepository;

@Service
public class TeamDepartmentService {
    private static final String TEAM_DEPARTMENT_NOT_FOUND_MSG = "TeamDepartment with id %s";
    private final TeamDepartmentRepository teamDepartmentRepository;

    public TeamDepartmentService(TeamDepartmentRepository teamDepartmentRepository) {
        this.teamDepartmentRepository = teamDepartmentRepository;
    }

    public TeamDepartment getById(Integer teamDepartmentId) {
        return teamDepartmentRepository.findById(teamDepartmentId)
                .orElseThrow(
                        () -> new NotFoundException(String.format(TEAM_DEPARTMENT_NOT_FOUND_MSG, teamDepartmentId)));
    }

    public TeamDepartment createTeamDepartment(TeamDepartmentDTO teamDepartmentDTO) {
        return teamDepartmentRepository.save(fromDTO(teamDepartmentDTO, TeamDepartment.class));

    }

    public TeamDepartment updateTeamDepartment(TeamDepartmentDTO teamDepartmentDTO) {
        var teamDepartment = fromDTO(teamDepartmentDTO, TeamDepartment.class);
        var teamDepartmentFromDb = getById(teamDepartmentDTO.getTeamDepartmentId());
        BeanUtils.copyProperties(teamDepartment, teamDepartmentFromDb, "teamDepartmentId");
        return teamDepartmentRepository.save(teamDepartmentFromDb);
    }

    public TeamDepartment updateStatus(Integer teamDepartmentId) {
        var teamDepartment = getById(teamDepartmentId);
        teamDepartment.setActive(!teamDepartment.getActive());
        return teamDepartmentRepository.save(teamDepartment);
    }

}

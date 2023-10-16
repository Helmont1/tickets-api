package com.upx.ticketsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.Team;
import com.upx.ticketsapi.payload.TeamDTO;
import com.upx.ticketsapi.service.TeamService;
import com.upx.ticketsapi.util.SuccessResponseUtil;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    @RolesAllowed("create_teams")
    public ResponseEntity<SuccessResponse<Team>> create(
            @RequestBody TeamDTO team) {
        return SuccessResponseUtil.createdResponse(teamService.createTeam(team));
    }
}

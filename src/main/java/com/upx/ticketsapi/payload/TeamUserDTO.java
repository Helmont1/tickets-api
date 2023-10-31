package com.upx.ticketsapi.payload;

import com.upx.ticketsapi.model.Team;
import com.upx.ticketsapi.model.User;

import lombok.Data;

@Data
public class TeamUserDTO {
    private Integer teamUserId;
    private Team team;
    private User user;
    private Boolean active;
    private Long avgTicketResolutionTime;
}

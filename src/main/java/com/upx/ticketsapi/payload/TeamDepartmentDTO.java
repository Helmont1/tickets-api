package com.upx.ticketsapi.payload;

import com.upx.ticketsapi.model.Department;
import com.upx.ticketsapi.model.Team;

import lombok.Data;

@Data
public class TeamDepartmentDTO {
    private Integer teamDepartmentId;
    private Team team;
    private Department department;
    private Boolean active;
}

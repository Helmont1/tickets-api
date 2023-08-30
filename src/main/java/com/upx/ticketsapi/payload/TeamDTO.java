package com.upx.ticketsapi.payload;

import com.upx.ticketsapi.model.User;

import lombok.Data;

@Data
public class TeamDTO {
    private Integer teamId;

    private User leader;

    private Boolean active;

    private String teamName;

}

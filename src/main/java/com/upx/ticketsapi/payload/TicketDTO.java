package com.upx.ticketsapi.payload;

import java.time.LocalDate;

import com.upx.ticketsapi.model.Department;
import com.upx.ticketsapi.model.Priority;
import com.upx.ticketsapi.model.Status;
import com.upx.ticketsapi.model.TeamUser;
import com.upx.ticketsapi.model.User;

import lombok.Data;

@Data
public class TicketDTO {
    private Integer ticketId;
    private String title;
    private User requester;
    private String content;
    private Priority priority;
    private Status status;
    private LocalDate openingDate;
    private LocalDate modificationDate;
    private Department department;
    private TeamUser teamUser;
}

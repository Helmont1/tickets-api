package com.upx.ticketsapi.payload;

import java.time.LocalDate;

import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.model.User;

import lombok.Data;

@Data
public class InteractionDTO {
    private Integer interactionId;
    private Ticket ticket;
    private User user;
    private Boolean intern;
    private LocalDate createdAt;
    private String content;
}

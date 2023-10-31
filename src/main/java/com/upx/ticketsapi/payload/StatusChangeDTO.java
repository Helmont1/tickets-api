package com.upx.ticketsapi.payload;

import java.time.LocalDateTime;

import com.upx.ticketsapi.model.Status;
import com.upx.ticketsapi.model.Ticket;

import lombok.Data;

@Data
public class StatusChangeDTO {
    private Integer statusChangeId;
    private Ticket ticket;
    private Status status;
    private LocalDateTime changeDate;
}

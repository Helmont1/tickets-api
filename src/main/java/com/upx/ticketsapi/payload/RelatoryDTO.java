package com.upx.ticketsapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelatoryDTO {
    //tickets nao atribuidos
    private Long notAssignedTickets;
    private Long assignedTickets;
    private Long onHoldTickets;
    private Long totalTickets;
}

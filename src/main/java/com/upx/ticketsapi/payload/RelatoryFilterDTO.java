package com.upx.ticketsapi.payload;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RelatoryFilterDTO {
    private Integer departmentId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

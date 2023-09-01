package com.upx.ticketsapi.payload;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private Boolean active;
}

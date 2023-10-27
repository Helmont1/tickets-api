package com.upx.ticketsapi.payload;

import com.upx.ticketsapi.model.Department;
import com.upx.ticketsapi.model.Priority;

import lombok.Data;



@Data
public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private Department department;
    private Priority priority;
    private Boolean active;
}

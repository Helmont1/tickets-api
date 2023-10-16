package com.upx.ticketsapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Department;
import com.upx.ticketsapi.repository.DepartmentRepository;

@Service
public class DepartmentService {
    private static final String DEPARTMENT_NOT_FOUND_MSG = "Department with id %s";
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getById(Integer departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(
                () -> new NotFoundException(String.format(DEPARTMENT_NOT_FOUND_MSG, departmentId)));
    }

    public Page<Department> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }
 
}

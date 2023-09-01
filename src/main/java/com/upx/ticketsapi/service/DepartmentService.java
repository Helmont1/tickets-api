package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.Department;
import com.upx.ticketsapi.payload.DepartmentDTO;
import com.upx.ticketsapi.repository.DepartmentRepository;

import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

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

    public Department createDepartment(DepartmentDTO departmentDTO) {
        return departmentRepository.save(fromDTO(departmentDTO, Department.class));
    }

    public Department updateDepartment(DepartmentDTO departmentDTO) {
        var department = fromDTO(departmentDTO, Department.class);
        var departmentFromDb = getById(departmentDTO.getDepartmentId());
        BeanUtils.copyProperties(department, departmentFromDb, "departmentId");
        return departmentRepository.save(departmentFromDb);
    }

    public Department updateStatus(Integer departmentId) {
        var department = getById(departmentId);
        department.setActive(!department.getActive());
        return departmentRepository.save(department);
    }

}

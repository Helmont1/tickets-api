package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
}

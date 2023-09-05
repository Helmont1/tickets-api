package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.TeamDepartment;

public interface TeamDepartmentRepository extends JpaRepository<TeamDepartment, Integer> {
    
}

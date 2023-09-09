package com.upx.ticketsapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upx.ticketsapi.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query(value = "SELECT d FROM Department d WHERE d.active = true")
    public Page<Department> findAll(Pageable pageable);
}

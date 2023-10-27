package com.upx.ticketsapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.upx.ticketsapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT c FROM Category c WHERE c.department.departmentId = :departmentId")
    Page<Category> findAllByDepartmentId(Pageable pageable, @Param("departmentId") Integer departmentId);

    @Query(value = "SELECT c FROM Category c WHERE c.department.departmentId = :departmentId AND c.active = true")
    List<Category> findAllActiveByDepartmentId(@Param("departmentId") Integer departmentId);
}

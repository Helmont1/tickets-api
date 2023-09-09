package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
    
}

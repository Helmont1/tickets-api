package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    
}

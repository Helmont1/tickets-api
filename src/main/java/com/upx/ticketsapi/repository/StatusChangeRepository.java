package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.StatusChange;

public interface StatusChangeRepository extends JpaRepository<StatusChange, Integer>{
    
}

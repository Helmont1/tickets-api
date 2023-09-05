package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.TeamUser;

public interface TeamUserRepository extends JpaRepository<TeamUser, Integer> {
    
}

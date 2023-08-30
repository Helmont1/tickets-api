package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{

}

package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}

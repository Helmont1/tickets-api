package com.upx.ticketsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upx.ticketsapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(value = "SELECT u FROM User u WHERE keycloakId = null")
    public List<User> findToQueue();
}

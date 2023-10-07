package com.upx.ticketsapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.upx.ticketsapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u WHERE keycloakId = null")
    public List<User> findToQueue();

    @Query(value = "SELECT u FROM User u WHERE email = :userEmail")
    public Optional<User> findByEmail(@Param("userEmail") String userEmail);

    @Query(value = "SELECT u FROM User u WHERE email in :emailList")
    public Page<User> findAllByEmail(@Param("emailList") List<String> userEmail, Pageable pageable);

}

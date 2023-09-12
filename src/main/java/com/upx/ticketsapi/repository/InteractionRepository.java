package com.upx.ticketsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.upx.ticketsapi.model.Interaction;


public interface InteractionRepository extends JpaRepository<Interaction, Integer> {

    @Query(value = "SELECT i FROM Interaction i WHERE i.ticket.ticketId = :ticketId")
    List<Interaction> findAllByTicketId(@Param("ticketId")Integer ticketId);
    
}

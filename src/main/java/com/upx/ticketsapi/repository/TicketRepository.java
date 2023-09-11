package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}

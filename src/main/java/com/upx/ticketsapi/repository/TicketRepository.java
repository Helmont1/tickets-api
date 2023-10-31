package com.upx.ticketsapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.upx.ticketsapi.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    @Query("SELECT t FROM Ticket t WHERE t.requester.userId = :userId")
    Page<Ticket> findByUser(PageRequest pageRequest, @Param("userId") Integer userId);

    @Query("SELECT t FROM Ticket t WHERE t.status.statusId IN :statusIds AND t.department.departmentId IN (SELECT td.department.departmentId FROM TeamDepartment td WHERE td.team.teamId IN (SELECT tu.team.teamId FROM TeamUser tu WHERE tu.user.userId = :userId))")
    Page<Ticket> findActiveTicketsByUser(PageRequest pageRequest, @Param("userId") Integer userId, @Param("statusIds") List<Integer> statusIds);
}

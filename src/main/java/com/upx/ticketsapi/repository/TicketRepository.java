package com.upx.ticketsapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.upx.ticketsapi.model.Ticket;
import com.upx.ticketsapi.payload.RelatoryDTO;
import com.upx.ticketsapi.payload.RelatoryFilterDTO;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    @Query("SELECT t FROM Ticket t WHERE t.requester.userId = :userId")
    Page<Ticket> findByUser(PageRequest pageRequest, @Param("userId") Integer userId);

    @Query("SELECT t FROM Ticket t WHERE t.status.statusId IN :statusIds AND t.department.departmentId IN (SELECT td.department.departmentId FROM TeamDepartment td WHERE td.team.teamId IN (SELECT tu.team.teamId FROM TeamUser tu WHERE tu.user.userId = :userId))")
    Page<Ticket> findActiveTicketsByUser(PageRequest pageRequest, @Param("userId") Integer userId, @Param("statusIds") List<Integer> statusIds);

    @Query("SELECT new com.upx.ticketsapi.payload.RelatoryDTO(COUNT(CASE WHEN t.status.statusId = 1 AND t.teamUser IS NULL THEN 1 END), COUNT(CASE WHEN t.teamUser IS NOT NULL AND t.status.statusId != 3 AND t.status.statusId != 1 THEN 1 END), COUNT(CASE WHEN t.status.statusId = 3 THEN 1 END), COUNT(t)) FROM Ticket t WHERE t.department.departmentId = :#{#filter.departmentId} AND t.openingDate BETWEEN :#{#filter.startDate} AND :#{#filter.endDate}")
    RelatoryDTO findRelatoryByFilter(RelatoryFilterDTO filter);
}

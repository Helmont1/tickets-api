package com.upx.ticketsapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status_change")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_change_id")
    private Integer statusChangeId;

    @JoinColumn(name = "ticket_id")
    @ManyToOne
    private Ticket ticket;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private Status status;

    @Column(name = "change_date")
    private LocalDateTime changeDate;
}

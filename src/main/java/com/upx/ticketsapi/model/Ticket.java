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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "requester_id")
    @ManyToOne
    private User requester;
    
    @Column(name = "content")
    private String content;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "priority_id")
    @ManyToOne
    private Priority priority;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private Status status;

    @Column(name = "opening_date")
    private LocalDateTime openingDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @JoinColumn(name = "department_id")
    @ManyToOne
    private Department department;

    @JoinColumn(name = "team_user_id")
    @ManyToOne
    private TeamUser teamUser;
}

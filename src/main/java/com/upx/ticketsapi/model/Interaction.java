package com.upx.ticketsapi.model;

import java.time.LocalDate;

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
@Table(name = "interaction")
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interaction_id")
    private Integer interactionId;

    @JoinColumn(name = "ticket_id")
    @ManyToOne
    private Ticket ticket;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "intern")
    private Boolean intern;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "content")
    private String content;
}

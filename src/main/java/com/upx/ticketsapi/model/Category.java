package com.upx.ticketsapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @NonNull
    private Integer categoryId;

    @Column(name = "category_name")
    @NonNull
    private String categoryName;

    @JoinColumn(name = "department_id")
    @NonNull
    @ManyToOne
    private Department department;

    @JoinColumn(name = "priority_id")
    @NonNull
    @ManyToOne
    private Priority priority;

    @Column(name = "active")
    @NonNull
    private Boolean active;
}

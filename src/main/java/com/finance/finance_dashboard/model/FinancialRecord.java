package com.finance.finance_dashboard.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Where(clause = "deleted=false")
@Table(name="financial_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Double amount;

@Enumerated(EnumType.STRING)
private RecordType type;

private String category;

private LocalDate date;

private String description;

@ManyToOne
@JoinColumn(name="created_by")
private User createdBy;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@CreationTimestamp
private LocalDateTime createdAt;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@UpdateTimestamp
private LocalDateTime updatedAt;

@Column(name = "is_deleted")
private boolean deleted = false;

}

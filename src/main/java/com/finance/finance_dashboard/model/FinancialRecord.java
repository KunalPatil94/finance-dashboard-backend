package com.finance.finance_dashboard.model;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
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

private String notes;

@ManyToOne
@JoinColumn(name="created_by")
private User createdBy;

private LocalDateTime createdAt = LocalDateTime.now();

}

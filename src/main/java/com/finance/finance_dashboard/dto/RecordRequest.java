package com.finance.finance_dashboard.dto;

import com.finance.finance_dashboard.model.RecordType;
import lombok.Data;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
@Data
public class RecordRequest {

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private RecordType type;

    @NotBlank
    private String category;

    private LocalDate date;

    private String notes;
}

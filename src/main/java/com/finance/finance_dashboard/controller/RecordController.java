package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.dto.RecordRequest;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.service.RecordService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // CREATE RECORD
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ANALYST')")
    public FinancialRecord createRecord(
            @Valid @RequestBody RecordRequest request,
            Authentication authentication) {

        String email = authentication.getName();
        return recordService.createRecord(request, email);
    }

    // GET ALL RECORDS WITH PAGINATION
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','ANALYST','VIEWER')")
    public Page<FinancialRecord> getRecords(
            @RequestParam int page,
            @RequestParam int size) {

        return recordService.getRecords(page, size);
    }

    // GET RECORDS BY TYPE
    @GetMapping("/type")
    @PreAuthorize("hasAnyAuthority('ADMIN','ANALYST','VIEWER')")
    public List<FinancialRecord> getByType(
            @RequestParam RecordType type) {

        return recordService.getRecordsByType(type);
    }

    // GET SINGLE RECORD
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','ANALYST','VIEWER')")
    public FinancialRecord getRecord(@PathVariable Long id) {
        return recordService.getRecord(id);
    }

    // DELETE RECORD (ADMIN ONLY)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
    }

    // FILTER RECORDS
    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('ADMIN','ANALYST','VIEWER')")
    public Page<FinancialRecord> filterRecords(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            Pageable pageable) {

        return recordService.filterRecords(type, category, startDate, endDate, pageable);
    }
}
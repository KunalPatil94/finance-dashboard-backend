package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.dto.RecordRequest;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.service.RecordService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public FinancialRecord createRecord(
            @Valid@RequestBody RecordRequest request,
            Authentication authentication){

        String email = authentication.getName();

        return recordService.createRecord(request,email);
    }

    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public Page<FinancialRecord> getRecords(
            @RequestParam int page,
            @RequestParam int size){

        return recordService.getRecords(page,size);
    }
    
    @GetMapping("/type")
    public List<FinancialRecord> getByType(
            @RequestParam RecordType type){

        return recordService.getRecordsByType(type);
    }

    
    @GetMapping("/{id}")
    public FinancialRecord getRecord(@PathVariable Long id) {
        return recordService.getRecord(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
    }
}

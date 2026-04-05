package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.service.DashboardService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    @GetMapping("/summary")
    public Map<String, Double> getSummary() {
        return dashboardService.getSummary();
    }
    
    @GetMapping("/category-summary")
    public Map<String, Double> categorySummary() {
        return dashboardService.categorySummary();
    }
    
    @GetMapping("/recent")
    public List<FinancialRecord> getRecentActivity() {
        return dashboardService.getRecentRecords();
    }
    
    @GetMapping("/monthly-trend")
    public List<Map<String, Object>> monthlyTrend() {
        return dashboardService.monthlyTrend();
    }
   
}
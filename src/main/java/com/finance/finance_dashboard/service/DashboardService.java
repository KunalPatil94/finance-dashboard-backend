package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.repo.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final FinancialRecordRepository recordRepository;

    public DashboardService(FinancialRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Map<String, Double> getSummary() {

        Double income = recordRepository.getTotalIncome();
        Double expense = recordRepository.getTotalExpense();

        Map<String, Double> result = new HashMap<>();

        result.put("totalIncome", income == null ? 0 : income);
        result.put("totalExpense", expense == null ? 0 : expense);
        result.put("netBalance", (income == null ? 0 : income) - (expense == null ? 0 : expense));

        return result;
    }
    
    public Map<String, Double> categorySummary() {

        List<Object[]> results = recordRepository.categoryTotals();

        Map<String, Double> summary = new HashMap<>();

        for (Object[] row : results) {
            summary.put((String) row[0], (Double) row[1]);
        }

        return summary;
    }
    public List<FinancialRecord> getRecentRecords() {
        return recordRepository.findTop5ByDeletedFalseOrderByCreatedAtDesc();
    }
    
    public List<Map<String, Object>> monthlyTrend() {

        List<Object[]> results = recordRepository.getMonthlySummary();

        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {

            Map<String, Object> map = new HashMap<>();

            map.put("month", row[0]);
            map.put("income", row[1]);
            map.put("expense", row[2]);

            response.add(map);
        }

        return response;
    }
}
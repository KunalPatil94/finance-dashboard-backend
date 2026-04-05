package com.finance.finance_dashboard.repo;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    // Dashboard totals
    @Query("SELECT COALESCE(SUM(f.amount),0) FROM FinancialRecord f WHERE f.type='INCOME' AND f.deleted=false")
    Double getTotalIncome();

    @Query("SELECT COALESCE(SUM(f.amount),0) FROM FinancialRecord f WHERE f.type='EXPENSE' AND f.deleted=false")
    Double getTotalExpense();

    // Filtering (soft delete aware)
    List<FinancialRecord> findByTypeAndDeletedFalse(RecordType type);

    List<FinancialRecord> findByCategoryAndDeletedFalse(String category);

    Page<FinancialRecord> findByDeletedFalse(Pageable pageable);

    Page<FinancialRecord> findByTypeAndDeletedFalse(RecordType type, Pageable pageable);

    Page<FinancialRecord> findByCategoryAndDeletedFalse(String category, Pageable pageable);

    Page<FinancialRecord> findByCreatedAtBetweenAndDeletedFalse(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable);

    Optional<FinancialRecord> findByIdAndDeletedFalse(Long id);

    List<FinancialRecord> findByDeletedFalse();

    // Category summary for dashboard
    @Query("""
           SELECT r.category, SUM(r.amount)
           FROM FinancialRecord r
           WHERE r.deleted=false
           GROUP BY r.category
           """)
    List<Object[]> categoryTotals();

    // Recent activity
    List<FinancialRecord> findTop5ByDeletedFalseOrderByCreatedAtDesc();

    // Monthly trend
    @Query("""
           SELECT FUNCTION('DATE_FORMAT', f.createdAt, '%Y-%m') as month,
                  SUM(CASE WHEN f.type='INCOME' THEN f.amount ELSE 0 END),
                  SUM(CASE WHEN f.type='EXPENSE' THEN f.amount ELSE 0 END)
           FROM FinancialRecord f
           WHERE f.deleted=false
           GROUP BY month
           ORDER BY month
           """)
    List<Object[]> getMonthlySummary();
}
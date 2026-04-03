package com.finance.finance_dashboard.repo;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord,Long>{

	@Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type='INCOME'")
	Double getTotalIncome();

	@Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type='EXPENSE'")
	Double getTotalExpense();
	
	List<FinancialRecord> findByType(RecordType type);

	List<FinancialRecord> findByCategory(String category);
}

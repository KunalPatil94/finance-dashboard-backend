package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.dto.RecordRequest;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.model.User;
import com.finance.finance_dashboard.repo.FinancialRecordRepository;
import com.finance.finance_dashboard.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class RecordService {

    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;

    public RecordService(FinancialRecordRepository recordRepository,
                         UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
    }
    
    public List<FinancialRecord> getRecordsByType(RecordType type){
        return recordRepository.findByTypeAndDeletedFalse(type);
    }

    public List<FinancialRecord> getRecordsByCategory(String category){
        return recordRepository.findByCategoryAndDeletedFalse(category);
    }

    public Page<FinancialRecord> getRecords(int page, int size){

        Pageable pageable = PageRequest.of(page,size);

        return recordRepository.findByDeletedFalse(pageable);
    }

    public Page<FinancialRecord> filterRecords(
            RecordType type,
            String category,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {

        if (type != null) {
            return recordRepository.findByTypeAndDeletedFalse(type, pageable);
        }

        if (category != null) {
            return recordRepository.findByCategoryAndDeletedFalse(category, pageable);
        }

        if (startDate != null && endDate != null) {
            return recordRepository.findByCreatedAtBetweenAndDeletedFalse(
                    startDate.atStartOfDay(),
                    endDate.atTime(23,59),
                    pageable);
        }

        return recordRepository.findByDeletedFalse(pageable);
    }

    public List<FinancialRecord> getAllRecords(){
        return recordRepository.findByDeletedFalse();
    }

    public FinancialRecord getRecord(Long id){
        return recordRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }
    
    public void deleteRecord(Long id){

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setDeleted(true);

        recordRepository.save(record);
    }
    
    public FinancialRecord createRecord(RecordRequest request, String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FinancialRecord record = FinancialRecord.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory())
                .date(request.getDate())
                .description(request.getDescription())
                .createdBy(user)
                .build();

        return recordRepository.save(record);
    }

}
package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.dto.RecordRequest;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.model.User;
import com.finance.finance_dashboard.repo.FinancialRecordRepository;
import com.finance.finance_dashboard.repo.UserRepository;
import org.springframework.stereotype.Service;

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
        return recordRepository.findByType(type);
    }

    public List<FinancialRecord> getRecordsByCategory(String category){
        return recordRepository.findByCategory(category);
    }
    
    public Page<FinancialRecord> getRecords(int page, int size){

        Pageable pageable = PageRequest.of(page,size);

        return recordRepository.findAll(pageable);
    }

    public FinancialRecord createRecord(RecordRequest request,String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FinancialRecord record = FinancialRecord.builder()
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory())
                .date(request.getDate())
                .notes(request.getNotes())
                .createdBy(user)
                .build();

        return recordRepository.save(record);
    }

    public List<FinancialRecord> getAllRecords(){
        return recordRepository.findAll();
    }

    public FinancialRecord getRecord(Long id){
        return recordRepository.findById(id).orElseThrow();
    }

    public void deleteRecord(Long id){
        recordRepository.deleteById(id);
    }

}
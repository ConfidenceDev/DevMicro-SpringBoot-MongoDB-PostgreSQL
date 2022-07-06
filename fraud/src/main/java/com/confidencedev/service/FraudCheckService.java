package com.confidencedev.service;

import com.confidencedev.dao.FraudRepo;
import com.confidencedev.model.Fraud;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudRepo fraudRepo) {

    public boolean isFraudulentCustomer(Integer customerId){
        Fraud history = Fraud.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();
        fraudRepo.save(history);
        return false;
    }
}

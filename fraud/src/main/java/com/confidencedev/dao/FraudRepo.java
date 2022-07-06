package com.confidencedev.dao;

import com.confidencedev.model.Fraud;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FraudRepo
        extends MongoRepository<Fraud, String> {
    Optional<Fraud> findDevBankByEmail(String email);
}

package com.confidencedev.service;

import com.confidencedev.model.CustomerRegistrationRequest;
import com.confidencedev.dao.CustomerRepository;
import com.confidencedev.model.FraudCheckResponse;
import com.confidencedev.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .fullName(request.fullName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
    }
}

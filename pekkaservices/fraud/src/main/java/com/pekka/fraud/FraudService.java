package com.pekka.fraud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudService {

    private final FraudRepository fraudRepository;

    @Autowired
    public FraudService(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
    }

    public boolean isFraudster(Integer customerId) {
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .checkedAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}

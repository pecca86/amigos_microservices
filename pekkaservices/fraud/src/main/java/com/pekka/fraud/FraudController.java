package com.pekka.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudService fraudService;

    @Autowired
    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Checking if customer with id {} is fraudulent...", customerId);
        boolean isFraud = fraudService.isFraudster(customerId);
        return new FraudCheckResponse(isFraud);
    }
}

package com.pekka.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign is a declarative web service client. It makes writing web service clients easier.
 * To use Feign create an interface and annotate it. It has pluggable annotation support including
 * Feign annotations and JAX-RS annotations. Feign also supports pluggable encoders and decoders.
 * Spring Cloud adds support for Spring MVC annotations and for using the same HttpMessageConverters used by default
 * in Spring Web.
 * Spring Cloud integrates Eureka, Spring Cloud CircuitBreaker, as well as Spring Cloud LoadBalancer to
 * provide a load-balanced http client when using Feign.
 *
 * All clients that want's to talk with the fraud service, just need to call this interface
 */

// This can also contain just "fraud"
@FeignClient("FRAUD")
public interface FraudClient {
    // Inside here we put the desired mappings

    // if no path specified inside FeignClient, put (path = "api/v1/fraud-check/{customerId}")
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);

}
